package com.example.shubhm.fileexplorer;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class shared extends AppCompatActivity {
    EditText e1,e2,e3;
    Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);

        e1=(EditText)findViewById(R.id.editText7);
        e2=(EditText)findViewById(R.id.editText8);
        e3=(EditText)findViewById(R.id.editText9);

        b1=(Button) findViewById(R.id.button8);
        b2=(Button) findViewById(R.id.button9);
        b3=(Button) findViewById(R.id.button10);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sf=shared.this.getSharedPreferences("myfile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sf.edit();
                editor.putString("uname",e1.getText().toString());
                editor.putString("roll",e2.getText().toString());
                editor.putString("sem",e3.getText().toString());
                editor.commit();
                Toast.makeText(getApplicationContext(),"Data Saved",Toast.LENGTH_SHORT).show();


            }





        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e1.setText("");
                e2.setText("");
                e3.setText("");
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            SharedPreferences userDetails = getSharedPreferences("myfile", Context.MODE_PRIVATE);
            String name = userDetails.getString("uname", "");
            String roll = userDetails.getString("roll", "");
            String sem = userDetails.getString("sem", "");
            Toast.makeText(getApplicationContext(),name+"\t"+roll+"\t"+sem,Toast.LENGTH_SHORT).show();

        }
    });

    }

}
