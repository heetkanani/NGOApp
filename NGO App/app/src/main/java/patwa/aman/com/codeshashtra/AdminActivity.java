package patwa.aman.com.codeshashtra;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AdminActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;
    String uid;
    RecyclerView adminRecyclerView;
    FirebaseRecyclerOptions<AdminModel> options;
    FirebaseRecyclerAdapter<AdminModel,AdminViewHolder> adapter;
    DatabaseReference getAdminData,verified,organization;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.nav_actionbar);
        setSupportActionBar(toolbar);

        adminRecyclerView=findViewById(R.id.admin_rv);
        getAdminData= FirebaseDatabase.getInstance().getReference("admin");
        verified=FirebaseDatabase.getInstance().getReference("verified");
        organization=FirebaseDatabase.getInstance().getReference("organization");

        adminRecyclerView.setHasFixedSize(true);
        adminRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        options=new FirebaseRecyclerOptions.Builder<AdminModel>()
                .setQuery(getAdminData,AdminModel.class)
                .build();

        adapter=new FirebaseRecyclerAdapter<AdminModel, AdminViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final AdminViewHolder holder, int position, @NonNull final AdminModel model) {
                model.setKey(options.getSnapshots().getSnapshot(position).getKey());
                holder.vhname.setText("Name :"+model.getOrgname());
                holder.vhemail.setText("Email :"+model.getEmail());
                holder.vhphone.setText("Phone :"+model.getMobile());
                holder.vhtrustno.setText("Trust No:"+model.getTrustno());
                holder.vhdesc.setText("Description :"+model.getDescription());
                holder.vhpassbook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent photoIntent=new Intent(AdminActivity.this,ViewDocsActivity.class);
                        photoIntent.putExtra("image",model.getPassbook());
                        startActivity(photoIntent);
                    }
                });
                holder.vhproof.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent photoIntent=new Intent(AdminActivity.this,ViewDocsActivity.class);
                        photoIntent.putExtra("image",model.getProof());
                        startActivity(photoIntent);
                    }
                });

                holder.vhverify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        HashMap<String,String> adminMap=new HashMap<>();
                        adminMap.put("description",model.getDescription());
                        adminMap.put("email",model.getEmail());
                        adminMap.put("mobile",model.getMobile());
                        adminMap.put("passbook",model.getPassbook());
                        adminMap.put("proof",model.getProof());
                        adminMap.put("trustno",model.getTrustno());
                        adminMap.put("orgname",model.getOrgname());

                        verified.child(model.getKey()).setValue(adminMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                organization.child(model.getKey()).child("status").setValue("Verified").addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        getAdminData.child(model.getKey()).removeValue();
                                        System.out.println("Successful");
                                    }
                                });
                            }
                        });

                    }
                });
                holder.vhdontverify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getAdminData.child(model.getKey()).removeValue();
                    }
                });

            }

            @NonNull
            @Override
            public AdminViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view= LayoutInflater.from(AdminActivity.this).inflate(R.layout.admin_viewholder,viewGroup,false);
                AdminViewHolder viewHolder=new AdminViewHolder(view);
                return viewHolder;
            }
        };

        adapter.startListening();
        adminRecyclerView.setAdapter(adapter);

    }
}
