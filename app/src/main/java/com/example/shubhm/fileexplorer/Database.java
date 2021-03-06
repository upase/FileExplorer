package com.example.shubhm.fileexplorer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Database extends AppCompatActivity {
    private ListView obj;
    DBHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);


        mydb = new DBHelper(this);
        ArrayList array_list = mydb.getAllCotacts();
//this as an adapter
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);

        obj = (ListView)findViewById(R.id.listView1);
        obj.setAdapter(arrayAdapter);
        obj.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                int id_To_Search = arg2 + 1;

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);

                myintent(dataBundle);
            }
        });
    }

    private void myintent(Bundle dataBundle) {
        Intent intent = new Intent(getApplicationContext(),DisplayContact.class);

        intent.putExtras(dataBundle);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);

        switch(item.getItemId())
        {
            case R.id.item1:Bundle dataBundle = new Bundle();
               return optiona(dataBundle);
            default:
                return optionb(item);
        }
    }

    private boolean optionb(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private boolean optiona(Bundle dataBundle) {
        dataBundle.putInt("id", 0);

        Intent intent = new Intent(getApplicationContext(),DisplayContact.class);
        intent.putExtras(dataBundle);

        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
}
//done!!!
