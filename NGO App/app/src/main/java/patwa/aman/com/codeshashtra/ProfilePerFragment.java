package patwa.aman.com.codeshashtra;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

public class ProfilePerFragment extends Fragment {
    TextView name,phone,description,email;
    Button desp,desp2;
    EditText adddes;
    String madddes;
    DatabaseReference mDatabaseReference;
    private String uid;
    String mname,mphone,memail,mdescription;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=LayoutInflater.from(getActivity()).inflate(R.layout.per_profile_fragment,container,false);

        name=(TextView) view.findViewById(R.id.user_txtname);
        email=(TextView) view.findViewById(R.id.user_txtemail);
        phone=(TextView) view.findViewById(R.id.user_txtphone);
        description=(TextView) view.findViewById(R.id.user_txtdescp);
        desp=(Button)view.findViewById(R.id.user_btn);
        desp2=(Button)view.findViewById(R.id.user_btndes);
        adddes=(EditText)view.findViewById(R.id.user_adddescrip);
        uid= FirebaseAuth.getInstance().getCurrentUser( ).getUid();

        mDatabaseReference= FirebaseDatabase.getInstance().getReference("users").child(uid);

        desp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adddes.setVisibility(View.VISIBLE);
                desp2.setVisibility(View.VISIBLE);
            }
        });


        desp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                madddes=adddes.getText().toString();
                description.setText(madddes);
                mDatabaseReference.child("description").setValue(madddes);


            }
        });


        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mname=dataSnapshot.child("username").getValue().toString();
                mphone=dataSnapshot.child("mobile").getValue().toString();
                memail=dataSnapshot.child("email").getValue().toString();
                mdescription=dataSnapshot.child("description").getValue().toString();

                name.setText(mname);
                phone.setText(mphone);
                email.setText(memail);
                description.setText(mdescription);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }




}

