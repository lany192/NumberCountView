package com.lany.numbercountdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.lany.numbercount.NumberCountView;

public class MainActivity extends AppCompatActivity{
    private NumberCountView countView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countView = findViewById(R.id.number_count_view);
        countView.setOnValueChangeListener(new NumberCountView.OnValueChangeListener() {

            @Override
            public void onValueChange(View view, int value) {
                Toast.makeText(MainActivity.this, "" + value, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
