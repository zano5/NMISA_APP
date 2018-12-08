package com.example.zanoxolomlab.nmisa_app;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.TrafficStats;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.zanoxolomlab.nmisa_app.Adapter.AppListAdapter;
import com.example.zanoxolomlab.nmisa_app.classFile.AppListData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private FrameLayout frame_container;
    private AppListAdapter adapter;
    View view;
    RecyclerView recyclerView;
    int UID;
    double totalData;
    List<AppListData> dataList = new ArrayList<>();
    LayoutInflater inflater;
    private Context context = this;

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                  //  mTextMessage.setText(R.string.title_home);

                    getPakagesInfoMobile();
                    inflater = null;
                    view=null;
                    frame_container.removeAllViews();





                    inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.recycler_view,null);

                   //  view = LayoutInflater.from(MainActivity.this).inflate(R.layout.recycler_view,null);

                    recyclerView = view.findViewById(R.id.recycler);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setHasFixedSize(true);

                    adapter = new AppListAdapter(dataList,MainActivity.this);


                    recyclerView.setAdapter(adapter);

                    frame_container.addView(view);









                    return true;
                case R.id.navigation_dashboard:
                   // mTextMessage.setText(R.string.title_dashboard);

                    inflater = null;
                    view=null;
                    frame_container.removeAllViews();




                    getPakagesInfoWifi();


                    inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.recycler_view,null);

                    //  view = LayoutInflater.from(MainActivity.this).inflate(R.layout.recycler_view,null);

                    recyclerView = view.findViewById(R.id.recycler);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setHasFixedSize(true);

                    adapter = new AppListAdapter(dataList,MainActivity.this);


                    frame_container.addView(view);

                    return true;
                case R.id.navigation_notifications:
                   // mTextMessage.setText(R.string.title_notifications);
                    frame_container.removeAllViews();

                    getPakagesInfoUsingHashMap();


                    view = LayoutInflater.from(MainActivity.this).inflate(R.layout.recycler_view,null);

                    recyclerView = view.findViewById(R.id.recycler);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                    recyclerView.setHasFixedSize(true);
                    adapter = new AppListAdapter(dataList,MainActivity.this);

                    recyclerView.setAdapter(adapter);

                    frame_container.addView(view);







                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        frame_container  = (FrameLayout) findViewById(R.id.frame_container);

     //   mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        inflater = null;
        view=null;
        frame_container.removeAllViews();



        getPakagesInfoWifi();


        view = LayoutInflater.from(MainActivity.this).inflate(R.layout.recycler_view,null);

        view = LayoutInflater.from(MainActivity.this).inflate(R.layout.recycler_view,null);

        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerView.setHasFixedSize(true);
        adapter = new AppListAdapter(dataList,MainActivity.this);

        recyclerView.setAdapter(adapter);

        frame_container.addView(view);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public void getPakagesInfoUsingHashMap() {
        final PackageManager pm = getPackageManager();
        // get a list of installed apps.
        List<ApplicationInfo> packages = pm.getInstalledApplications(0);

        // loop through the list of installed packages and see if the selected
        // app is in the list
        for (ApplicationInfo packageInfo : packages) {
            // get the UID for the selected app
            UID = packageInfo.uid;
            String package_name = packageInfo.packageName;
            ApplicationInfo app = null;
            try {
                app = pm.getApplicationInfo(package_name, 0);
            } catch (PackageManager.NameNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String name = (String) pm.getApplicationLabel(app);
            Drawable icon = pm.getApplicationIcon(app);
            // internet usage for particular app(sent and received)
            double received = (double) TrafficStats.getUidRxBytes(UID)

                    / (1024 * 1024);
            double send = (double) TrafficStats.getUidTxBytes(UID)
                    / (1024 * 1024);
            double total = received + send;

            if(total>0)
            {
                AppListData pi =new AppListData();
                pi.name=name;
                pi.packageName=package_name;
                pi.icon=icon;
                pi.totalMB=String.format( "%.2f", total )+" MB";
                pi.individual_mb=String.format( "%.2f", total );
               // totalData += Double.parseDouble(String.format( "%.2f", total ));
               // dataHash.add(pi);

                this.dataList.add(pi);
                Log.e(name,String.format( "%.2f", total )+" MB");
            }


        }


        this.adapter = new AppListAdapter(this.dataList,MainActivity.this);
//        Editor edit=shared.edit();
//        edit.putString("Total",String.format( "%.2f", totalData));
//        edit.commit();
    }


    public void getPakagesInfoMobile() {
        final PackageManager pm = getPackageManager();
        // get a list of installed apps.
        List<ApplicationInfo> packages = pm.getInstalledApplications(0);

        // loop through the list of installed packages and see if the selected
        // app is in the list
        for (ApplicationInfo packageInfo : packages) {
            // get the UID for the selected app
            UID = packageInfo.uid;
            String package_name = packageInfo.packageName;
            ApplicationInfo app = null;
            try {
                app = pm.getApplicationInfo(package_name, 0);
            } catch (PackageManager.NameNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String name = (String) pm.getApplicationLabel(app);
            Drawable icon = pm.getApplicationIcon(app);
            // internet usage for particular app(sent and received)
            double received = (double) TrafficStats.getMobileTxBytes()

                    / (1024 * 1024);
            double send = (double) TrafficStats.getMobileRxBytes()
                    / (1024 * 1024);
            double total = received + send;

            if(total>0)
            {
                AppListData pi =new AppListData();
                pi.name=name;
                pi.packageName=package_name;
                pi.icon=icon;
                pi.totalMB=String.format( "%.2f", total )+" MB";
                pi.individual_mb=String.format( "%.2f", total );
                // totalData += Double.parseDouble(String.format( "%.2f", total ));
                // dataHash.add(pi);

                this.dataList.add(pi);
                Log.e(name,String.format( "%.2f", total )+" MB");
            }


        }


        this.adapter = new AppListAdapter(this.dataList, MainActivity.this);
//        Editor edit=shared.edit();
//        edit.putString("Total",String.format( "%.2f", totalData));
//        edit.commit();
    }



    public void getPakagesInfoWifi() {
        final PackageManager pm = getPackageManager();
        // get a list of installed apps.
        List<ApplicationInfo> packages = pm.getInstalledApplications(0);

        // loop through the list of installed packages and see if the selected
        // app is in the list
        for (ApplicationInfo packageInfo : packages) {
            // get the UID for the selected app
            UID = packageInfo.uid;
            String package_name = packageInfo.packageName;
            ApplicationInfo app = null;
            try {
                app = pm.getApplicationInfo(package_name, 0);
            } catch (PackageManager.NameNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String name = (String) pm.getApplicationLabel(app);
            Drawable icon = pm.getApplicationIcon(app);
            // internet usage for particular app(sent and received)
            double received = (double) TrafficStats.getMobileTxBytes()

                    / (1024 * 1024);
            double send = (double) TrafficStats.getMobileRxBytes()
                    / (1024 * 1024);
            double total = received + send;

            if(total>0)
            {
                AppListData pi =new AppListData();
                pi.name=name;
                pi.packageName=package_name;
                pi.icon=icon;
               // pi.totalMB=String.format( "%.2f", total )+" MB";
                pi.individual_mb=String.format( "%.2f", total );
                // totalData += Double.parseDouble(String.format( "%.2f", total ));
                // dataHash.add(pi);


                long rxBytes = TrafficStats.getTotalRxBytes()/1048576;
                long txBytes = TrafficStats.getTotalTxBytes()/1048576;

                long mobUpload = TrafficStats.getMobileTxBytes()/1048576;
                long mobDown = TrafficStats.getMobileRxBytes()/1048576;

                long wifiUpload = txBytes-(mobUpload);
                long wifiDown = rxBytes-(mobDown);

                totalData = wifiDown + wifiUpload;

                pi.totalMB=String.format( "%.2f", totalData )+" MB";

                this.dataList.add(pi);
                Log.e(name,String.format( "%.2f", total )+" MB");
            }


        }


        this.adapter = new AppListAdapter(this.dataList, MainActivity.this);
//        Editor edit=shared.edit();
//        edit.putString("Total",String.format( "%.2f", totalData));
//        edit.commit();
    }
}
