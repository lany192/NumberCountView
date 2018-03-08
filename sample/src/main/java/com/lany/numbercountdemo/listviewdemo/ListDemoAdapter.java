package com.lany.numbercountdemo.listviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lany.numbercount.NumberCountView;
import com.lany.numbercountdemo.R;


public class ListDemoAdapter extends BaseAdapter {

    private Context context;

    public ListDemoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public Object getItem(int position) {
        return "";
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.adapter_listdemo, null);
            holder = new ViewHolder();
            holder.stepperCustom = (NumberCountView) convertView.findViewById(R.id.stepperCustom);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(position == 5){
            holder.stepperCustom.setMinValue(0);
            holder.stepperCustom.setValue(10);
        }
        return convertView;
    }

    private class ViewHolder {
        NumberCountView stepperCustom;
    }
}
