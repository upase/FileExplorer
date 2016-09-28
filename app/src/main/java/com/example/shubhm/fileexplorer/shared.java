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
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);

        e1=(EditText)findViewById(R.id.editText7);
        e2=(EditText)findViewById(R.id.editText8);
        e3=(EditText)findViewById(R.id.editText9);

        b1=(Button) findViewById(R.id.button8);
        b2=(Button) findViewById(R.id.button9);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sf=shared.this.getSharedPreferences(getString(R.string.PREF_FILE), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sf.edit();
                editor.putString(getString(R.string.NAME),e1.getText().toString());
                editor.putString(getString(R.string.ROLL),e2.getText().toString());
                editor.putString(getString(R.string.SEM),e3.getText().toString());
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
    }
}
