package com.example.communatio;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);
        loadFragment(new Homefragment());
    }
    private boolean loadFragment(Fragment fragment){
        if(fragment!=null){
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_container,fragment).commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment=null;
        switch(menuItem.getItemId())
        {
            case R.id.navigation_home:
                fragment=new Homefragment();
                break;
            case R.id.navigation_blogs:
                fragment=new fragmentblogs();
                break;
            case R.id.navigation_notifications:
                fragment=new notifications();
                break;
            case R.id.navigation_docs:
                fragment= new fragmentnotes();
                break;
        }
        boolean b=loadFragment(fragment);
        return b;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.threedotmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.profile:
                Intent i=new Intent(this,Profile.class);
                startActivity(i);
                break;
            case R.id.applied:
                Intent i1=new Intent(this,ApplyEvents.class);
                startActivity(i1);
                break;
            case R.id.logout:
                break;
        }
        return true;
    }
}
