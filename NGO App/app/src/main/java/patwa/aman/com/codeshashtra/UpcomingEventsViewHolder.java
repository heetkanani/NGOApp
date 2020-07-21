package patwa.aman.com.codeshashtra;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UpcomingEventsViewHolder extends RecyclerView.ViewHolder {
    TextView date,time,venue,name;
    Button chat;
    public UpcomingEventsViewHolder(@NonNull View itemView) {
        super(itemView);

        date=(TextView)itemView.findViewById(R.id.up_evtdate);
        time=(TextView)itemView.findViewById(R.id.up_evttime);
        venue=(TextView)itemView.findViewById(R.id.up_evtvenue);
        name=(TextView)itemView.findViewById(R.id.up_evtname);
        chat=(Button)itemView.findViewById(R.id.up_evtchat);
    }
}
