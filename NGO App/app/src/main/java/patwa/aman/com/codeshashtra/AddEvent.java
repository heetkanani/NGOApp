package patwa.aman.com.codeshashtra;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddEvent extends Fragment {

    TextInputLayout eventname, venue, date, time;
    Button add;
    String meventname, mvenue, mdate, mtime,uid;
    DatabaseReference eventsData;



    public AddEvent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_event,container,false);

        eventname=(TextInputLayout) view.findViewById(R.id.event);
        venue=(TextInputLayout) view.findViewById(R.id.venue);
        date=(TextInputLayout) view.findViewById(R.id.date);
        add=(Button)view.findViewById(R.id.eventbtn);
        time=(TextInputLayout)view.findViewById(R.id.time);
        uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        eventsData= FirebaseDatabase.getInstance().getReference("events").child(uid);




        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                meventname=eventname.getEditText().getText().toString();
                mvenue=venue.getEditText().getText().toString();
                mdate=date.getEditText().getText().toString();
                mtime=time.getEditText().getText().toString();
                if(meventname.equals(null) || mdate.equals(null) || mtime.equals(null) || mvenue.equals(null))
                {

                    Toast.makeText(getActivity(),"Enter all the details",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    HashMap<String,String> events=new HashMap<>();
                    events.put("event_name",meventname);
                    events.put("venue",mvenue);
                    events.put("date",mdate);
                    events.put("time",mtime);

                    eventsData.child(meventname).setValue(events).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getActivity(), "Event Added", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });




        // Inflate the layout for this fragment
        return view;
    }

}
