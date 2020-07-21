package patwa.aman.com.codeshashtra;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrganizationPerFragment extends Fragment {

    RecyclerView recyclerView;

    FirebaseRecyclerOptions<VerifiedOrgModel> options;
    FirebaseRecyclerAdapter<VerifiedOrgModel,VerifiedOrgViewHolder> adapter;
    DatabaseReference verifiedorg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.per_organization_fragment,container,false);
        verifiedorg= FirebaseDatabase.getInstance().getReference("verified");
        recyclerView=view.findViewById(R.id.org_ver_rv);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        options=new FirebaseRecyclerOptions.Builder<VerifiedOrgModel>()
                .setQuery(verifiedorg,VerifiedOrgModel.class)
                .build();

       adapter=new FirebaseRecyclerAdapter<VerifiedOrgModel, VerifiedOrgViewHolder>(options) {
           @Override
           protected void onBindViewHolder(@NonNull VerifiedOrgViewHolder holder, int position, @NonNull final VerifiedOrgModel model) {
               System.out.println("Description:"+model.getDescription());
               model.setKey(options.getSnapshots().getSnapshot(position).getKey());
               holder.description.setText(model.getDescription());
               holder.email.setText("Email :"+model.getEmail());
               holder.name.setText("Name :"+model.getOrgname());
               String passbook=model.getPassbook();
               String proof=model.getProof();
               holder.phone.setText("Phone :"+model.getMobile());
               holder.trustno.setText("Trust No :"+model.getTrustno());
               holder.viewEvents.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       Intent intent=new Intent(getActivity(),EventsActivity.class);
                       intent.putExtra("uid",model.getKey());
                       startActivity(intent);
                   }
               });

           }

           @NonNull
           @Override
           public VerifiedOrgViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

               View view= LayoutInflater.from(getActivity()).inflate(R.layout.verified_org_view_holder,viewGroup,false);
               VerifiedOrgViewHolder viewHolder=new VerifiedOrgViewHolder(view);
               return viewHolder;
           }
       };

        adapter.startListening();
        recyclerView.setAdapter(adapter);

        return view;
    }
}
