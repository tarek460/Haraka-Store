package com.example.haraka;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;


import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class Home extends AppCompatActivity {

    Toolbar toolBar;
    DrawerLayout dLayout;
    ActionBarDrawerToggle toggle;
    CardView groceryCard, legumesCard, drinksCrd, candyCard;
    NavigationView naView;
    SessionManager sessionManager;


    FirebaseAuth fireAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        groceryCard = findViewById(R.id.grocery_card);
        legumesCard = findViewById(R.id.legumes_card);
        drinksCrd = findViewById(R.id.drinks_card);
        candyCard = findViewById(R.id.cand_card);
        naView = findViewById(R.id.nav_view);
        dLayout = findViewById(R.id.d_layout);
        setContentView(R.layout.activity_home);
        toolBar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sessionManager = new SessionManager(getApplicationContext());

        toggle =new ActionBarDrawerToggle(this, dLayout, toolBar, R.string.open_navigation_drawer,
                R.string.close_navigation_drawer);
        dLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        naView.bringToFront();




        View view = naView.inflateHeaderView(R.layout.menu_header);

        naView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                int id = item.getItemId();

                switch (id){

                    case R.id.my_profile:

                        startActivity(new Intent(Home.this, UserProfile.class));
                        break;

                    case R.id.log_out:

                        sessionManager.setLogin(false);
                        sessionManager.setUsername("");
                        startActivity(new Intent(Home.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

                }

                return false;
            }
        });



        groceryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, MainActivity.class));
                finish();
            }
        });





    }

    private void setSupportActionBar(Toolbar toolBar) {


    }

}