package patwa.aman.com.codeshashtra;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminViewHolder extends RecyclerView.ViewHolder {
    Button vhpassbook,vhproof,vhverify,vhdontverify;
    TextView vhname,vhphone,vhemail,vhtrustno,vhdesc;


    public AdminViewHolder(@NonNull View itemView) {
        super(itemView);

        vhpassbook=(Button)itemView.findViewById(R.id.btnpassbook);
        vhproof=(Button)itemView.findViewById(R.id.btnproof);
        vhname=(TextView)itemView.findViewById(R.id.vhname);
        vhemail=(TextView)itemView.findViewById(R.id.vhemail);
        vhphone=(TextView)itemView.findViewById(R.id.vhphone);
        vhverify=(Button)itemView.findViewById(R.id.vhverify);
        vhdontverify=(Button)itemView.findViewById(R.id.vhdverify);
        vhtrustno=(TextView)itemView.findViewById(R.id.vhtrustno);
        vhdesc=(TextView)itemView.findViewById(R.id.vhdesc);

    }
}
