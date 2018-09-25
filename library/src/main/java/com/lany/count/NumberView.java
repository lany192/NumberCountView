package com.lany.count;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 数量加减控件
 */
public class NumberView extends LinearLayout {
    private OnValueListener mListener;

    private int mValue = 0;
    private int mMinValue = 0;
    private int mMaxValue = Integer.MAX_VALUE;

    private TextView mValueText;
    private ActionView mLeftBtn;
    private ActionView mRightBtn;



    public NumberView(Context context) {
        super(context);
        init(null);
    }

    public NumberView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public NumberView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //-------------------------------------------
        Drawable background = new ColorDrawable(Color.LTGRAY);
        int valueTextColor = Color.BLACK;
        int valueBackgroundColor = Color.parseColor("#f2f2f2");
        float valueTextSize = 16;
        int height = dp2px(25);
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.NumberView);

            mMinValue = a.getInt(R.styleable.NumberView_nv_min, mMinValue);
            mMaxValue = a.getInt(R.styleable.NumberView_nv_max, mMaxValue);
            mValue = a.getInt(R.styleable.NumberView_nv_value, mValue);
            background = a.getDrawable(R.styleable.NumberView_nv_btn_background);
            valueBackgroundColor = a.getColor(R.styleable.NumberView_nv_value_background, valueBackgroundColor);
            valueTextColor = a.getColor(R.styleable.NumberView_nv_value_text_color, valueTextColor);
            valueTextSize = a.getFloat(R.styleable.NumberView_nv_value_text_size, valueTextSize);

            a.recycle();
        }
        //-------------------------------------------
        mValueText = new TextView(getContext());
        mValueText.setGravity(Gravity.CENTER);
        mValueText.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, height));
        mValueText.setPadding(dp2px(8), 0, dp2px(8), 0);
        mValueText.setText(String.valueOf(mValue));
        mValueText.setTextSize(valueTextSize);
        mValueText.setTextColor(valueTextColor);
        mValueText.setBackgroundColor(valueBackgroundColor);
        //-------------------------------------------
        int btnPadding = dp2px(7);
        mLeftBtn = new ActionView(getContext());
        mLeftBtn.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, height));
        mLeftBtn.setImageResource(R.drawable.number_minus);
        mLeftBtn.setPadding(btnPadding, btnPadding, btnPadding, btnPadding);
        if (background != null) {
            mLeftBtn.setBackgroundDrawable(background);
        }
        mLeftBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mValue--;
                changeValue();
            }
        });

        mRightBtn = new ActionView(getContext());
        mRightBtn.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, height));
        mRightBtn.setImageResource(R.drawable.number_plus);
        mRightBtn.setPadding(btnPadding, btnPadding, btnPadding, btnPadding);
        if (background != null) {
            mRightBtn.setBackgroundDrawable(background);
        }
        mRightBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mValue++;
                changeValue();
            }
        });
        //-------------------------------------------
        addView(mLeftBtn);
        addView(mValueText);
        addView(mRightBtn);
    }

    private int dp2px(float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, Resources.getSystem().getDisplayMetrics());
    }

    private void changeValue() {
        if (mValue > mMaxValue) {
            mValue = mMaxValue;
        }
        if (mValue < mMinValue) {
            mValue = mMinValue;
        }
        mValueText.setText(String.valueOf(mValue));
        if (mListener != null) {
            mListener.onValue(this, mValue);
        }
    }

    public void setOnValueListener(OnValueListener listener) {
        this.mListener = listener;
    }

    public void setMinValue(int minValue) {
        this.mMinValue = minValue;
    }

    public void setMaxValue(int maxValue) {
        this.mMaxValue = maxValue;
    }

    public void setValueBackgroundResource(@DrawableRes int resId) {
        mValueText.setBackgroundResource(resId);
    }

    public void setValueBackgroundDrawable(Drawable drawable) {
        mValueText.setBackgroundDrawable(drawable);
    }

    public void setValueTextColor(@ColorInt int color) {
        mValueText.setTextColor(color);
    }

    public void setValue(int value) {
        this.mValue = value;
        mValueText.setText(String.valueOf(mValue));
    }

    public void setValueTextSize(float size) {
        mValueText.setTextSize(size);
    }

    public void setButtonBackgroundResource(@DrawableRes int resId) {
        mLeftBtn.setBackgroundResource(resId);
        mRightBtn.setBackgroundResource(resId);
    }

    public void setButtonBackgroundColor(@ColorInt int color) {
        mLeftBtn.setBackgroundColor(color);
        mRightBtn.setBackgroundColor(color);
    }

    public interface OnValueListener {
        void onValue(View view, int value);
    }

    /**
     * 正方形ImageView
     */
    class ActionView extends ImageView {

        public ActionView(Context context) {
            super(context);
        }

        public ActionView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public ActionView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            setMeasuredDimension(getMeasuredHeight(), getMeasuredHeight());
        }
    }
}
