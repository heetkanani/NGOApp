package patwa.aman.com.codeshashtra;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpcominEventsFragment extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference eventsData;
    FirebaseRecyclerOptions<UpComingEventsModel> options;
    FirebaseRecyclerAdapter<UpComingEventsModel,UpcomingEventsViewHolder> adapter;

    String uid;

    public UpcominEventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_upcomin_events,container,false);
        uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        // Inflate the layout for this fragment
        eventsData= FirebaseDatabase.getInstance().getReference("joined").child(uid);

        recyclerView=view.findViewById(R.id.upcoming_event_rv);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        options=new FirebaseRecyclerOptions.Builder<UpComingEventsModel>()
                .setQuery(eventsData,UpComingEventsModel.class)
                .build();

        adapter=new FirebaseRecyclerAdapter<UpComingEventsModel, UpcomingEventsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull UpcomingEventsViewHolder holder, int position, @NonNull final UpComingEventsModel model) {
                holder.date.setText("Date :"+model.getDate());
                holder.name.setText("Event Name :"+model.getEvent_name());
                holder.time.setText("Time :"+model.getTime());
                holder.venue.setText("Venue :"+model.getVenue());
                holder.chat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent chatIntent=new Intent(getActivity(),MainChatActivity.class);
                        chatIntent.putExtra("event",model.getEvent_name());
                        startActivity(chatIntent);
                    }
                });
            }

            @NonNull
            @Override
            public UpcomingEventsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view=LayoutInflater.from(getActivity()).inflate(R.layout.upcomming_events_viewholder,viewGroup,false);
                UpcomingEventsViewHolder viewHolder=new UpcomingEventsViewHolder(view);
                return viewHolder;
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);

        return view;
    }

}
