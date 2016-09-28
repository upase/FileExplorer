package com.example.shubhm.fileexplorer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button b1;
    EditText e1,e2;
    RadioButton r1,r2;
    public static int flag=0;
    public static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        if(flag==0)
            setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context=getApplicationContext();
        e1=(EditText) findViewById(R.id.editText11);
        e2=(EditText) findViewById(R.id.editText12);


        r1=(RadioButton) findViewById(R.id.radioButton) ;
        r2=(RadioButton) findViewById(R.id.radioButton2) ;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        b1=(Button) findViewById(R.id.button5);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String txtname=e1.getText().toString();
                String txtData=e2.getText().toString();
                    if(r1.isChecked())
                    {
                        if(canWriteOnExternalStorage()) {
                            try{writeToSDFile();}catch(Exception e){Toast.makeText(getApplicationContext(),"Error Writing File",Toast.LENGTH_SHORT).show();}
                        }
                    }
                    else if(r2.isChecked())
                    {
                        try {
                            FileOutputStream fOut = openFileOutput(txtname,MODE_WORLD_READABLE);
                            fOut.write(txtData.getBytes());
                            fOut.close();
                            Toast.makeText(getBaseContext(),"file saved",Toast.LENGTH_SHORT).show();
                        }

                        catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

            }
        });
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
           // Toast.makeText(getApplicationContext(),"hshs",Toast.LENGTH_SHORT).show();
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.shared) {
            Intent i=new Intent(getApplicationContext(),shared.class);
            startActivity(i);

        } else if (id == R.id.explorer) {
            if(flag==1){
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);}

        }else if (id == R.id.db) {
           Intent i=new Intent(getApplicationContext(),Database.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    private void writeToSDFile(){
        try {
            File sdcard = Environment.getExternalStorageDirectory();
// to this path add a new directory path
            File dir = new File(sdcard.getAbsolutePath());
// create this directory if not already created
            dir.mkdir();
// create the file in which we will write the contents
            File file = new File(dir,e1.getText().toString() );
            FileOutputStream os = new FileOutputStream(file);
            String data =e2.getText().toString();
            os.write(data.getBytes());
            os.close();
            Toast.makeText(this,"file saved",Toast.LENGTH_SHORT).show();

        }
        catch(Exception e){Toast.makeText(this,"Couldn't Create File",Toast.LENGTH_SHORT).show();}
    }




    public boolean canWriteOnExternalStorage() {
        // get the state of your external storage
        try {
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                // if storage is mounted return true
                return true;
            }
        }
        catch (Exception e)
        {Toast.makeText(this,"err",Toast.LENGTH_SHORT).show();}
        return false;
    }

    @Override
    protected void onStop() {
        flag=1;
        super.onStop();
    }

    @Override
    protected void onResume() {
        flag=0;
        {Toast.makeText(this,"Please Touch Top Left Corner For\n\t\t\t Navigation Drawer",Toast.LENGTH_LONG).show();}
        super.onResume();
    }
}
