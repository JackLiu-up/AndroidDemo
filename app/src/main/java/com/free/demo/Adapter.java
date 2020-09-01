package com.free.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Adapter extends AppCompatActivity {

    //数据源
    private String[] data = {"上海", "北京", "广州", "深圳"};
    //控件
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);

        listView = (ListView) findViewById(R.id.listView1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long id) {
                Log.i("TEST",position+" ");
                Toast.makeText(Adapter.this,"你所选中的城市是"+"  "+ data[position],Toast.LENGTH_SHORT).show();
            }
        });

        //适配器
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                this,    //上下文
//                R.layout.list_item, //项布局
//                R.id.text1,         //数据要显示控件id
//                data                //数据源
//        );
        //使用系统定义的布局
        ArrayAdapter<String>adapter1 = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,  //系统中定义过得布局文件
                android.R.id.text1,                    //布局文件中得TextView id 为 text1
                data);
        //设置适配器
        listView.setAdapter(adapter1);
    }
}