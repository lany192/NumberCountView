package com.lany.numbercountdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lany.numbercount.NumberCountView;
import com.lany.numbercountdemo.listviewdemo.ListDemoActivity;

public class MainActivity extends AppCompatActivity implements NumberCountView.OnValueChangeListener {

    private TextView tvValue;
    private NumberCountView stepper;
    private TextView tvValueCustom;
    private NumberCountView stepperCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvValue = (TextView) findViewById(R.id.tvValue);
        stepper = (NumberCountView) findViewById(R.id.stepper);
        tvValueCustom = (TextView) findViewById(R.id.tvValueCustom);
        stepperCustom = (NumberCountView) findViewById(R.id.stepperCustom);

        tvValue.setText(String.valueOf(stepper.getValue()));
        tvValueCustom.setText(String.valueOf(stepperCustom.getValue()));
        stepper.setOnValueChangeListener(this);
        stepperCustom.setOnValueChangeListener(this);

        stepperCustom.setBackgroundColor(getResources().getColor(R.color.colorStepperButtonNormal));
        stepperCustom.setButtonBackGround(R.drawable.sl_steppercustom_button_bg);
        stepperCustom.setContentBackground(R.color.colorStepperContentBg);
        stepperCustom.setContentTextColor(R.color.colorStepperText);
        stepperCustom.setContentTextSize(18);
        stepperCustom.setLeftButtonResources(R.drawable.ic_stepper_left);
        stepperCustom.setRightButtonResources(R.drawable.ic_stepper_right);
    }

    @Override
    public void onValueChange(View view, int value) {
        switch (view.getId()) {
            case R.id.stepper:
                tvValue.setText(String.valueOf(value));
                break;
            case R.id.stepperCustom:
                tvValueCustom.setText(String.valueOf(value));
                break;
        }
    }

    public void openListView(View v) {
        startActivity(new Intent(this, ListDemoActivity.class));
    }
}
