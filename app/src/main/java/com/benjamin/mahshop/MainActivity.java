package com.benjamin.mahshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Starts menu app
     * @param view
     */
    public void beginApp(View view) {
        Intent menuActivity = new Intent(this, MenuActivity.class);
        startActivity(menuActivity);
    }
}
