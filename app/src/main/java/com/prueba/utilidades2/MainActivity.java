package com.prueba.utilidades2;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");
        cuenta();
        CargarAnuncio();
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void cuenta() {
        int hours = 1;
        int mins = 1;

        int millis = ((hours*60)+mins)*60000; // Need milliseconds to use Timer


        new Timer().schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                NotificationCompat.Builder mBuilder;
                NotificationManager mNotifyMgr =(NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
                int icono = R.mipmap.ic_launcher;
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0,intent, 0);
                mBuilder =new NotificationCompat.Builder(getApplicationContext())
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(icono)
                        .setContentTitle("¿Quieres ver cuanto has quemado?")
                        .setContentText("Haz Click Aqui")
                        .setVibrate(new long[] {100, 250, 100, 500})
                        .setAutoCancel(true);
                mNotifyMgr.notify(1, mBuilder.build());
                //code that runs when timer is done

            }
        }, millis);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Podometro) {
            MostrarAnuncio();
            Intent miIntent = new Intent(MainActivity.this,Main4Activity.class);
            miIntent.putExtra("layout", R.layout.activity_main4);
            startActivity(miIntent);
            return  true;
        } else if (id == R.id.Calculadora) {
            MostrarAnuncio();
            Intent miIntent = new Intent(MainActivity.this,Main6Activity.class);
            miIntent.putExtra("layout", R.layout.content_main6);
            startActivity(miIntent);
            return  true;

        } else if (id == R.id.Calorias) {
            MostrarAnuncio();
            Intent miIntent = new Intent(MainActivity.this,Main2Activity.class);
            miIntent.putExtra("layout", R.layout.activity_main2);
            startActivity(miIntent);
            return  true;
        } else if (id == R.id.IMC) {
            MostrarAnuncio();
            Intent miIntent = new Intent(MainActivity.this,Main5Activity.class);
            startActivity(miIntent);
            return  true;
        } else if (id == R.id.nav_share) {
            MostrarAnuncio();
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Texto para compartir en la app");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        } else if (id == R.id.nav_send) {
            MostrarAnuncio();
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.prueba.Felipe")));
        }else if (id == R.id.Register){
            MostrarAnuncio();
            Intent miIntent = new Intent(MainActivity.this,Main3Activity.class);
            miIntent.putExtra("layout", R.layout.activity_main3);
            startActivity(miIntent);
            return  true;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void MostrarAnuncio() {
        mInterstitialAd.show();
    }


    private void CargarAnuncio(){
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        CargarAnuncio();
    }
}
