package com.lany.numbercountdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lany.numbercount.NumberCountView;
import com.lany.numbercountdemo.listviewdemo.ListDemoActivity;

public class MainActivity extends AppCompatActivity{

    private TextView tvValue;
    private NumberCountView numberCountView;
    private TextView tvValueCustom;
    private NumberCountView stepperCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvValue =  findViewById(R.id.tvValue);
        numberCountView =  findViewById(R.id.number_count_view);
        tvValueCustom = findViewById(R.id.tvValueCustom);
        stepperCustom = findViewById(R.id.stepperCustom);

        tvValueCustom.setText(String.valueOf(stepperCustom.getValue()));
        numberCountView.setOnValueChangeListener(new NumberCountView.OnValueChangeListener() {
            @Override
            public void onValueChange(View view, int value) {
                tvValue.setText(String.valueOf(value));
            }
        });
        stepperCustom.setOnValueChangeListener(new NumberCountView.OnValueChangeListener() {
            @Override
            public void onValueChange(View view, int value) {
                tvValueCustom.setText(String.valueOf(value));
            }
        });

        stepperCustom.setBackgroundColor(getResources().getColor(R.color.colorStepperButtonNormal));
        stepperCustom.setButtonBackGround(R.drawable.sl_steppercustom_button_bg);
        stepperCustom.setContentBackground(R.color.colorStepperContentBg);
        stepperCustom.setContentTextColor(R.color.colorStepperText);
        stepperCustom.setContentTextSize(18);
        stepperCustom.setLeftButtonResources(R.drawable.ic_stepper_left);
        stepperCustom.setRightButtonResources(R.drawable.ic_stepper_right);
    }

    public void openListView(View v) {
        startActivity(new Intent(this, ListDemoActivity.class));
    }
}
