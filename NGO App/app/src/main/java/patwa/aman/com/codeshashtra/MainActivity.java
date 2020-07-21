package patwa.aman.com.codeshashtra;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private android.support.v7.widget.Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.nav_actionbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = (NavigationView) findViewById(R.id.main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout, new ProfilePerFragment()).commit();
            navigationView.setCheckedItem(R.id.main_nav_profile);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout, new ProfilePerFragment()).commit();
                break;
            case R.id.main_nav_organization:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout, new OrganizationPerFragment()).commit();
                break;
            case R.id.main_nav_gov_progs:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout, new GovernmentProgFragment()).commit();
                break;
            case R.id.main_nav_upcomming_events:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout, new UpcominEventsFragment()).commit();
                break;


        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if(toggle.onOptionsItemSelected(item))
            return true;
        return false;
    }
}
