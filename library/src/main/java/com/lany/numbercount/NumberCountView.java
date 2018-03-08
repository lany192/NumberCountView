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
    private OnValueChangeListener listener;
    private TextView mContentText;
    private SquaredImageView mMinusBtn, mPlusBtn;
    private int value = 0;
    private int minValue = 0;
    private int maxValue = 100;

    public NumberCountView(Context context) {
        this(context, null);
    }

    public NumberCountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(attrs);
    }

    private void initViews(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.view_number_count, this, true);
        mContentText = findViewById(R.id.tvStepperContent);
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
            minValue = a.getInt(R.styleable.NumberCountView_ncv_min, minValue);
            maxValue = a.getInt(R.styleable.NumberCountView_ncv_max, maxValue);
            value = a.getInt(R.styleable.NumberCountView_ncv_value, value);
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
        mContentText.setTextColor(contentTextColor);
        if (contentTextSize > 0)
            setContentTextSize(contentTextSize);

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
        mContentText.setText(String.valueOf(value));
        mMinusBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                value--;
                if (value < minValue) {
                    value = minValue;
                }
                mContentText.setText(String.valueOf(value));
                if (listener != null) {
                    listener.onValueChange(NumberCountView.this, value);
                }
            }
        });
        mPlusBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                value++;
                if (value > maxValue) {
                    value = maxValue;
                }
                mContentText.setText(String.valueOf(value));
                if (listener != null) {
                    listener.onValueChange(NumberCountView.this, value);
                }
            }
        });
    }

    public void setOnValueChangeListener(OnValueChangeListener listener) {
        this.listener = listener;
    }

    public void setMinMaxValue(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public void setContentBackground(int resId) {
        mContentText.setBackgroundResource(resId);
    }

    public void setContentBackground(Drawable drawable) {
        mContentText.setBackgroundDrawable(drawable);
    }

    public void setContentTextColor(int resId) {
        mContentText.setTextColor(getResources().getColor(resId));
    }

    public void setText(String text) {
        mContentText.setText(text);
    }

    public void setContentTextSize(float size) {
        mContentText.setTextSize(size);
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
