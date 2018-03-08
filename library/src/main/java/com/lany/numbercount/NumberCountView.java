package com.lany.numbercount;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NumberCountView extends RelativeLayout {
    private OnValueChangeListener mListener;
    private TextView mValueText;
    private SquaredImageView mMinusBtn, mPlusBtn;
    private int mValue = 0;
    private int mMinValue = 0;
    private int mMaxValue = 99;

    public NumberCountView(Context context) {
        this(context, null);
    }

    public NumberCountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(attrs);
    }

    private void initViews(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.view_number_count, this, true);
        mValueText = findViewById(R.id.tvStepperContent);
        mMinusBtn = findViewById(R.id.ivStepperMinus);
        mPlusBtn = findViewById(R.id.ivStepperPlus);

        Drawable background = null;
        Drawable contentBackground = null;
        Drawable leftButtonResources = null;
        Drawable rightButtonResources = null;
        Drawable leftButtonBackground = null;
        Drawable rightButtonBackground = null;
        int contentTextColor = getResources().getColor(R.color.ncv_text_color);
        float contentTextSize = 0;
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.NumberCountView);
            mMinValue = a.getInt(R.styleable.NumberCountView_ncv_min, mMinValue);
            mMaxValue = a.getInt(R.styleable.NumberCountView_ncv_max, mMaxValue);
            mValue = a.getInt(R.styleable.NumberCountView_ncv_value, mValue);
            background = a.getDrawable(R.styleable.NumberCountView_ncv_background);
            contentBackground = a.getDrawable(R.styleable.NumberCountView_ncv_contentBackground);
            leftButtonResources = a.getDrawable(R.styleable.NumberCountView_ncv_leftButtonResources);
            rightButtonResources = a.getDrawable(R.styleable.NumberCountView_ncv_rightButtonResources);
            leftButtonBackground = a.getDrawable(R.styleable.NumberCountView_ncv_leftBtnBg);
            rightButtonBackground = a.getDrawable(R.styleable.NumberCountView_ncv_leftBtnBg);
            contentTextColor = a.getColor(R.styleable.NumberCountView_ncv_contentTextColor, contentTextColor);
            contentTextSize = a.getFloat(R.styleable.NumberCountView_ncv_contentTextSize, 0);
            a.recycle();
        }

        if (background != null) {
            setBackgroundDrawable(background);
        } else {
            setBackgroundResource(R.color.ncv_button_pressed);
        }
        if (contentBackground != null) {
            setContentBackground(contentBackground);
        }
        mValueText.setTextColor(contentTextColor);
        if (contentTextSize > 0) {
            setValueTextSize(contentTextSize);
        }
        if (leftButtonBackground != null) {
            mMinusBtn.setBackgroundDrawable(leftButtonBackground);
        }
        if (rightButtonBackground != null) {
            mPlusBtn.setBackgroundDrawable(rightButtonBackground);
        }
        if (leftButtonResources != null) {
            setLeftButtonResources(leftButtonResources);
        }
        if (rightButtonResources != null) {
            setRightButtonResources(rightButtonResources);
        }
        mValueText.setText(String.valueOf(mValue));
        mMinusBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mValue--;
                showValue();
            }
        });
        mPlusBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mValue++;
                showValue();
            }
        });
    }

    private void showValue(){
        if (mValue > mMaxValue) {
            mValue = mMaxValue;
        }
        if (mValue < mMinValue) {
            mValue = mMinValue;
        }
        mValueText.setText(String.valueOf(mValue));
        if (mListener != null) {
            mListener.onValueChange(NumberCountView.this, mValue);
        }
    }

    public void setOnValueChangeListener(OnValueChangeListener listener) {
        this.mListener = listener;
    }

    public void setMinValue(int minValue) {
        this.mMinValue = minValue;
    }

    public void setMaxValue(int maxValue) {
        this.mMaxValue = maxValue;
    }

    public void setValueBackground(int resId) {
        mValueText.setBackgroundResource(resId);
    }

    public void setContentBackground(Drawable drawable) {
        mValueText.setBackgroundDrawable(drawable);
    }

    public void setValueTextColor(int resId) {
        mValueText.setTextColor(getResources().getColor(resId));
    }

    public void setValue(int value) {
        this.mValue = value;
        mValueText.setText(String.valueOf(mValue));
    }

    public void setValueTextSize(float size) {
        mValueText.setTextSize(size);
    }

    public void setButtonBackGround(int resId) {
        mMinusBtn.setBackgroundResource(resId);
        mPlusBtn.setBackgroundResource(resId);
    }

    public void setLeftButtonResources(int resId) {
        mMinusBtn.setImageResource(resId);
    }

    public void setLeftButtonResources(Drawable drawable) {
        mMinusBtn.setImageDrawable(drawable);
    }

    public void setRightButtonResources(int resId) {
        mPlusBtn.setImageResource(resId);
    }

    public void setRightButtonResources(Drawable drawable) {
        mPlusBtn.setImageDrawable(drawable);
    }

    public interface OnValueChangeListener {
        void onValueChange(View view, int value);
    }
}
