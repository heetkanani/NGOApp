package patwa.aman.com.codeshashtra;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SchemeDisplayActivity extends AppCompatActivity {

    TextView desc,income,applicable,nationality,status,documents;
    Button b;
    private android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme_display);
        Log.d("New Activity Started","vdh");
        Intent intent = getIntent();
        schemeData sd = (schemeData) intent.getSerializableExtra("OBJECT");

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.nav_actionbar);
        setSupportActionBar(toolbar);

        desc = findViewById(R.id.description);
        income = findViewById(R.id.income);
        applicable = findViewById(R.id.applicable);
        nationality = findViewById(R.id.nationality);

        status = findViewById(R.id.status);
        documents = findViewById(R.id.docs);
        b = findViewById(R.id.linkButton);

        desc.setText(sd.description);
        income.setText("INCOME : " + sd.incomelimit);
        applicable.setText("APPLICABLE FOR : " + sd.applicable);
        nationality.setText("NATIONALITY : " + sd.nationality);
        //age.setText("AGE : " + sd.agegrp);
        status.setText("STATUS : " + sd.status);
        documents.setText(sd.documents);
        final String urlthis = sd.url;

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(urlthis));
                startActivity(i);
            }
        });
    }
}

