package com.robpercival.demoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayMessage(View v){
        EditText input = (EditText) findViewById(R.id.nameInput);
        Toast.makeText(MainActivity.this, String.format("Hi there %s!", input.getText()), Toast.LENGTH_LONG).show();
    }
}
