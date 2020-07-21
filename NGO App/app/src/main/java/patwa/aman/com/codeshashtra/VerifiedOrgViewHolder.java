package patwa.aman.com.codeshashtra;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VerifiedOrgViewHolder extends RecyclerView.ViewHolder {

    TextView name, email, trustno, phone, description;
    Button viewEvents;
    public VerifiedOrgViewHolder(@NonNull View itemView) {
        super(itemView);

        name=(TextView)itemView.findViewById(R.id.ver_name);
        email=(TextView)itemView.findViewById(R.id.ver_email);
        trustno=(TextView)itemView.findViewById(R.id.ver_trustno);
        phone=(TextView)itemView.findViewById(R.id.ver_phone);
        description=(TextView)itemView.findViewById(R.id.ver_desc);
        viewEvents=(Button)itemView.findViewById(R.id.ver_view_events);
    }
}
