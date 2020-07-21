package patwa.aman.com.codeshashtra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ViewDocsActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_docs);

        imageView=(ImageView)findViewById(R.id.imageview);
        String image=getIntent().getStringExtra("image");
        System.out.println(image);
        Picasso.with(this).load(image).fit().centerCrop().into(imageView);
    }
}
