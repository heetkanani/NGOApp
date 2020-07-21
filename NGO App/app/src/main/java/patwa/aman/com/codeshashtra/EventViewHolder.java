package patwa.aman.com.codeshashtra;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EventViewHolder extends RecyclerView.ViewHolder {
    TextView date,time,venue,name;
    Button join;

    public EventViewHolder(@NonNull View itemView) {
        super(itemView);

        date=(TextView)itemView.findViewById(R.id.evtdate);
        time=(TextView)itemView.findViewById(R.id.evttime);
        venue=(TextView)itemView.findViewById(R.id.evtvenue);
        name=(TextView)itemView.findViewById(R.id.evtname);
        join=(Button)itemView.findViewById(R.id.eventbtn);


    }

}
