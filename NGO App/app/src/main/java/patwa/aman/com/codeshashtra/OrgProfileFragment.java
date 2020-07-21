package patwa.aman.com.codeshashtra;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrgProfileFragment extends Fragment {

    TextView orgname,orgphone,orgdescription,orgemail,orgtrustno,orgpass;
    Button orgdesp,orgdesp2;
    EditText orgadddes;
    String morgadddes;
    DatabaseReference mDatabaseReference;
    private String uid;
    String morgname,morgphone,morgemail,morgdescription,morgpass,morgtrustno;


    public OrgProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_org_profile,container,false);

        orgname=(TextView) view.findViewById(R.id.orgname);
        orgemail=(TextView) view.findViewById(R.id.orgemail);
        orgphone=(TextView) view.findViewById(R.id.orgnumber);
        orgdescription=(TextView) view.findViewById(R.id.orgdes);
        orgdesp=(Button)view.findViewById(R.id.orgbtn);
        orgdesp2=(Button)view.findViewById(R.id.orgbtndes);
        orgadddes=(EditText)view.findViewById(R.id.orgadddescrip);
        orgtrustno=(TextView)view.findViewById(R.id.orgtrustno);
        uid= FirebaseAuth.getInstance().getCurrentUser( ).getUid();

        mDatabaseReference= FirebaseDatabase.getInstance().getReference("organization").child(uid);

        orgdesp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orgadddes.setVisibility(View.VISIBLE);
                orgdesp2.setVisibility(View.VISIBLE);
            }
        });


        orgdesp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                morgadddes=orgadddes.getText().toString();
                orgdescription.setText(morgadddes);
                mDatabaseReference.child("description").setValue(morgadddes);



            }
        });


        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                morgname=dataSnapshot.child("orgname").getValue().toString();
                morgphone=dataSnapshot.child("mobile").getValue().toString();
                morgemail=dataSnapshot.child("email").getValue().toString();
                morgtrustno=dataSnapshot.child("trustno").getValue().toString();
                morgdescription=dataSnapshot.child("description").getValue().toString();

                orgname.setText(morgname);
                orgphone.setText(morgphone);
                orgemail.setText(morgemail);
                orgtrustno.setText(morgtrustno);
                orgdescription.setText(morgdescription);

            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });


        return view;
    }

}
