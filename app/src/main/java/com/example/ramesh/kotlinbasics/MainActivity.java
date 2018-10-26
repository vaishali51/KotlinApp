package com.example.ramesh.kotlinbasics;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setActionBarTitle("Kotlin-Android Tutorial");

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_NETWORK_STATE
                }, 100);
            }
            return;
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        KotlinBasics kotlinBasics = new KotlinBasics();
        transaction.replace(R.id.fragment_container, kotlinBasics).commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    int flag=0;
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(flag==0) {
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
            flag=1;
        } else{
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_kotlin) {
            // Handle the camera action
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            KotlinBasics kotlinBasics = new KotlinBasics();
            transaction.replace(R.id.fragment_container, kotlinBasics).commit();
        } else if (id == R.id.nav_android) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            AndroidPrograms kotlinAndroid = new AndroidPrograms();
            transaction.replace(R.id.fragment_container, kotlinAndroid).commit();
        } else if (id == R.id.nav_java) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            JavaToKotlin kotlinJava = new JavaToKotlin();
            transaction.replace(R.id.fragment_container, kotlinJava).commit();
        } else if (id == R.id.nav_share) {
            shareApp();
        } else if (id == R.id.nav_video) {
            if(isOnline()) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                VideoTutorials videoTutorials = new VideoTutorials();
                transaction.replace(R.id.fragment_container, videoTutorials).commit();
            }else{
                Toast.makeText(this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.nav_rate) {
            rateUs();
        } else if (id == R.id.nav_feedback){
            feedback();
        } else if (id == R.id.nav_about){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            About about = new About();
            transaction.replace(R.id.fragment_container, about).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void rateUs() {
        Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp");
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, " unable to find market app", Toast.LENGTH_LONG).show();
        }

    }

    private void feedback() {
        Intent i = new Intent(Intent.ACTION_SENDTO);
        String uriText = "mailto:" + Uri.encode("vaishalig714@gmail.com") +
                "?subject=" + Uri.encode("Feedback") +
                "&body=" + Uri.encode("");
        Uri uri = Uri.parse(uriText);
        i.setData(uri);
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (ActivityNotFoundException e){
            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }

        /*i.setData(Uri.parse("mailto:"));
        i.setType("message/rfc822");
        PackageManager pkgManager = getApplicationContext().getPackageManager();
        List<ResolveInfo> activities = pkgManager.queryIntentActivities(i, 0);
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"vaishalig714@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }*/
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


    private void shareApp() {
        try {
            Intent i = new Intent(android.content.Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "Kotlin Android");
            String sAux = "Kotlin Tutorial - Learn Kotlin from your phone through this app.\n";
            sAux = sAux + "https://play.google.com/store/apps/details?id=com.whatsapp";        //link of app
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            startActivityForResult(Intent.createChooser(i, "Share Via"), 1);
            getFragmentManager().popBackStack();
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void setActionBarTitle(String title) {
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#48d1cc")));
        bar.setTitle(title);
    }

}
