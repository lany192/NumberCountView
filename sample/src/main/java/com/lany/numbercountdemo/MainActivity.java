package com.lany.numbercountdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.lany.count.NumberView;

public class MainActivity extends AppCompatActivity{
    private NumberView mNumberView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNumberView = findViewById(R.id.number_view);
        mNumberView.setOnValueListener(new NumberView.OnValueListener() {

            @Override
            public void onValue(View view, int value) {
                Toast.makeText(MainActivity.this, "" + value, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
