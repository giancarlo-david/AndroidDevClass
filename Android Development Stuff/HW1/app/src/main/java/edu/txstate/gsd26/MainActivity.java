package edu.txstate.gsd26;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    String x = "Name: Gian David"
            + "\nSchool: Texas State University"
            + "\nMajor: Computer Information Systems";
}