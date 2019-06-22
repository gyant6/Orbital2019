package com.example.orbital2019;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.orbital2019.auth.LoginFragment;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
        //implements BottomNavigationView.OnNavigationItemSelectedListener {


    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displaySelectedScreen(R.id.nav_login);
        bottomNavigationView = findViewById(R.id.main_nav);
        bottomNavigationView.setVisibility(View.VISIBLE);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();

                displaySelectedScreen(id);

                return true;
            }
        });
    }

/*
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        displaySelectedScreen(id);

        //DrawerLayout drawer = findViewById(R.id.drawer_layout);

        //drawer.closeDrawer(GravityCompat.START);
        return true;
    }
*/
    //displaying the selected screen
    private void displaySelectedScreen(int id) {

        // creating the fragment
        Fragment fragment = null;


        if (id == R.id.nav_home) {
            fragment = new HomeFragment();

        }  else if (id == R.id.nav_facil) {
            fragment = new FacilitiesFragment();

        } else if (id == R.id.nav_events) {
            fragment = new EventsFragment();

        } else if (id == R.id.nav_about) {
            fragment = new AboutDetails();

        } else if (id == R.id.nav_login) {
            fragment = new LoginFragment();
        }


        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_frame, fragment);
            ft.commit();
        }
    }
}
