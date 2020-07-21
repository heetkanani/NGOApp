package patwa.aman.com.codeshashtra;


import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.HashMap;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrgVerificationFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;
    TextView chooseproof,choosepassbook;
    Button passbook1,proof1,verify;
    ImageView img_passbook,img_proof;
    private StorageTask muploadTask;
    private Uri mimageUri;
    private StorageReference mstorageReference;
    private DatabaseReference adminreference;
    private DatabaseReference mdatabaseReference;
    String uid;
    String b;
    String description,email,mobile,orgname,passbook,proof,trustno;

    public OrgVerificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_org_verification,container,false);
        // Inflate the layout for this fragment

        passbook1=(Button)view.findViewById(R.id.org_verify_bank);
        proof1=(Button)view.findViewById(R.id.org_verify_proof);
        verify=(Button)view.findViewById(R.id.org_verify_verification);
        img_passbook=(ImageView)view.findViewById(R.id.org_verify_img_bank);
        img_proof=(ImageView) view.findViewById(R.id.org_verify_img_proof);
        choosepassbook=(TextView)view.findViewById(R.id.org_verify_choose_bank);
        chooseproof=(TextView)view.findViewById(R.id.org_verify_choose_file);
        uid= FirebaseAuth.getInstance().getCurrentUser().getUid();

        mstorageReference= FirebaseStorage.getInstance().getReference("uploads").child(uid);
        mdatabaseReference= FirebaseDatabase.getInstance().getReference("organization").child(uid);
        adminreference=FirebaseDatabase.getInstance().getReference("admin").child(uid);

        passbook1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(muploadTask!=null && muploadTask.isInProgress())
                {
                    Toast.makeText(getActivity(),"Upload in progress",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    uploadFile("passbook");
                }
            }
        });

        proof1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(muploadTask!=null && muploadTask.isInProgress())
                {
                    Toast.makeText(getActivity(),"Upload in progress",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    uploadFile("proof");
                }

            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mdatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        orgname=dataSnapshot.child("orgname").getValue().toString();
                        description=dataSnapshot.child("description").getValue().toString();
                        email=dataSnapshot.child("email").getValue().toString();
                        mobile=dataSnapshot.child("mobile").getValue().toString();
                        passbook=dataSnapshot.child("passbook").getValue().toString();
                        proof=dataSnapshot.child("proof").getValue().toString();
                        trustno=dataSnapshot.child("trustno").getValue().toString();


                        if(passbook.equals("None") || proof.equals("None")){
                            Toast.makeText(getActivity(), "Please enter all the details", Toast.LENGTH_SHORT).show();
                        }
                        else{

                            HashMap<String,String> adminMap=new HashMap<>();
                            adminMap.put("description",description);
                            adminMap.put("email",email);
                            adminMap.put("mobile",mobile);
                            adminMap.put("passbook",passbook);
                            adminMap.put("proof",proof);
                            adminMap.put("trustno",trustno);
                            adminMap.put("orgname",orgname);

                            adminreference.setValue(adminMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                  Toast.makeText(getActivity(), "Data sent for verification", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        choosepassbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
                b="passbook";
            }
        });

        chooseproof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
                b="proof";
            }
        });

        return view;
    }

    private void openFileChooser()
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
            mimageUri= data.getData();
            if(b.equals("passbook")) {
                Picasso.with(getActivity()).load(mimageUri).into(img_passbook);
            }
            if(b.equals("proof")) {
                Picasso.with(getActivity()).load(mimageUri).into(img_proof);
            }
        }
    }

    private void uploadFile(final String data)
    {
        if(mimageUri!=null)
        {
            System.out.println("Data"+data);
            StorageReference reference=mstorageReference.child(uid+""+data+".jpg");
            muploadTask=reference.putFile(mimageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            mdatabaseReference.child(data).setValue(taskSnapshot.getStorage().toString());
                            Toast.makeText(getActivity(),"Upload Successfull",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });

        }
        else
        {
            Toast.makeText(getActivity(),"No File Selected",Toast.LENGTH_SHORT).show();
        }
    }
}
