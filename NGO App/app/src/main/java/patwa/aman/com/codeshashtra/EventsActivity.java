package patwa.aman.com.codeshashtra;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class EventsActivity extends AppCompatActivity {

    FirebaseRecyclerOptions<EventModel> options;
    FirebaseRecyclerAdapter<EventModel,EventViewHolder> adapter;
    RecyclerView recyclerView;
    DatabaseReference events,joined,eventsThere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        String uid= getIntent().getStringExtra("uid");

        recyclerView=findViewById(R.id.event_rv);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        events= FirebaseDatabase.getInstance().getReference("events").child(uid);
        joined=FirebaseDatabase.getInstance().getReference("joined");
        eventsThere=FirebaseDatabase.getInstance().getReference("people_joined");

        options=new FirebaseRecyclerOptions.Builder<EventModel>()
                .setQuery(events,EventModel.class)
                .build();
        adapter=new FirebaseRecyclerAdapter<EventModel, EventViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull EventViewHolder holder, int position, @NonNull final EventModel model) {
                holder.date.setText("Date :"+model.getDate());
                holder.name.setText("Event Name :"+model.getEvent_name());
                holder.time.setText("Time :"+model.getTime());
                holder.venue.setText("Venue :"+model.getVenue());

                final HashMap<String,String> add=new HashMap<>();
                add.put("event_name",model.getEvent_name());
                add.put("date",model.getDate());
                add.put("time",model.getTime());
                add.put("venue",model.getVenue());
                holder.join.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(EventsActivity.this, "Clicked join", Toast.LENGTH_SHORT).show();
                        final String cuid= FirebaseAuth.getInstance().getCurrentUser().getUid();
                        joined.child(cuid).child(model.getEvent_name()).setValue(add).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                eventsThere.child(model.getEvent_name()).child(cuid).setValue(cuid).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        
                                    }
                                });

                            }
                        });
                    }
                });
            }

            @NonNull
            @Override
            public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view= LayoutInflater.from(EventsActivity.this).inflate(R.layout.event_viewholder,viewGroup,false);
                EventViewHolder viewHolder=new EventViewHolder(view);
                return viewHolder;
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }
}
