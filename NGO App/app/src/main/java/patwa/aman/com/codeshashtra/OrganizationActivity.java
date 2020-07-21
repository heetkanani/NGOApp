package patwa.aman.com.codeshashtra;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class OrganizationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private android.support.v7.widget.Toolbar toolbar;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization);

        navigationView = (NavigationView) findViewById(R.id.org_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.nav_actionbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.org_drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.org_framelayout, new OrgProfileFragment()).commit();
            navigationView.setCheckedItem(R.id.org_nav_profile);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.org_nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.org_framelayout, new OrgProfileFragment()).commit();
                break;
            case R.id.org_nav_organization:
                getSupportFragmentManager().beginTransaction().replace(R.id.org_framelayout, new OrganizationPerFragment()).commit();
                break;
            case R.id.org_nav_gov_progs:
                getSupportFragmentManager().beginTransaction().replace(R.id.org_framelayout, new GovernmentProgFragment()).commit();
                break;
            case R.id.org_nav_verification:
                getSupportFragmentManager().beginTransaction().replace(R.id.org_framelayout, new OrgVerificationFragment()).commit();
                break;
            case R.id.org_nav_add_events:
                getSupportFragmentManager().beginTransaction().replace(R.id.org_framelayout, new AddEvent()).commit();
                break;


        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if(toggle.onOptionsItemSelected(item))
            return true;
        return false;
    }
}

