package com.free.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int MSG_FINISH=0x0001;
    private Button btn1, btn2, btn3, btn4, btn5;
    private TextView textView, tvContent;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            //处理消息，运行在主线程中
            switch (msg.what) {
                case 0x0001:
                    int index = msg.arg1;
                    textView.setText(index + "");//允许，运行在
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initContral();


    }

    /*private List<String> getPullParserContent(Resources res, int id) throws IOException, XmlPullParserException {
        List<String> contents = null;
        String tagName;
        //XMLPullParser
        XmlPullParser parser = getResources().getXml(R.xml.words);
        //Pull解析本质是SAX解析

        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT://文档开始
                    Log.i("TEST", "STATE_DOCUMENT");
                    contents = new ArrayList<String>();
                    break;
                case XmlPullParser.END_DOCUMENT://文档结束
                    break;
                case XmlPullParser.START_TAG://标记（标签，元素，节点）
                    tagName = parser.getName();//得到标记的名字
                    if (tagName.equals("word")) {
                        String value = parser.getAttributeValue(0);
                        contents.add(value);
                        Log.i("TEST", "START_TAG" + tagName + " " + value);
                    }
                    break;
                case XmlPullParser.END_TAG://结束
                    tagName = parser.getName();
                    Log.i("TEST", "END_TAG" + tagName);
                    break;
            }
            eventType = parser.next();

        }
        return contents;
    }*/


    public void test(View view) throws JSONException {
        switch (view.getId()) {
            case R.id.button1:
                startActivity(new Intent(MainActivity.this, CountActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(MainActivity.this, FragmentActivity.class));
                break;
            case R.id.btn3:
                startActivity(new Intent(MainActivity.this, ScanActivity.class));
                break;
            case R.id.btnSynced:
                //使用线程完成
                //工作线程是一个匿名类
                new Thread() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 100; i++) {
                            //textView.setText(i+"")//不允许，子线程不可更新ui线程中控件得属性
                            Message msg = new Message();
                            msg.what = 0x0001;//消息的what是消息标识，必须有
                            msg.arg1 = i; //要传递的参数
                            handler.sendMessage(msg);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    ;
                }.start();
                break;
            case R.id.btn5:
                String data="{\"name\":\"Jason\",\"age\":\"24\"}";
                try {
                    JSONObject jsonObject = new JSONObject(data);
                   //通过Key 获取值
                    String name = jsonObject.getString("name");
                    tvContent.setText(name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.toast1:
                //使用静态方法makeText
                //context上下文，text消息内容，duration持续时间 值Toast.LENGTH_LONG,LENGTH_SHORT,固定值
                //.show()方法 显示
                Toast.makeText(this, "这是一个测试提示消息", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.toast2:
                //1.构造来创建Toast
                Toast toast = new Toast(this);
                //2.创建要显示的view
                ImageView imageView = new ImageView(this);
                imageView.setImageResource(R.drawable.ic_android_black_24dp);

                toast.setView(imageView);
                toast.setDuration(1000);
                toast.show();
                break;
        }
    }

    private void initContral() {
        btn1 = findViewById(R.id.button1);
       /* btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CountActivity.class));
            }
        });*/
        btn2 = findViewById(R.id.button2);
     /*   btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FragmentActivity.class));
            }
        });*/

        btn3 = findViewById(R.id.btn3);
       /* btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ScanActivity.class));
            }
        });*/

        btn4 = findViewById(R.id.btnSynced);
        textView = findViewById(R.id.textView);

        btn5 = findViewById(R.id.btn5);
        tvContent = findViewById(R.id.tvContent);
    }


}