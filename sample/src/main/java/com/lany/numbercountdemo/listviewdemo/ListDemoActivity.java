package com.lany.numbercountdemo.listviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lany.numbercountdemo.R;

public class ListDemoActivity extends Activity implements AdapterView.OnItemClickListener {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdemo);
        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(new ListDemoAdapter(this));
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println("点击了列表＝＝＝＝＝＝＝");
    }
}
