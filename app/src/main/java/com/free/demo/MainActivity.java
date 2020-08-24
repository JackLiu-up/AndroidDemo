package com.free.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn1, btn2, btn3, btn4;
    private TextView textView;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            //处理消息，运行在主线程中
            switch (msg.what) {
                case 0x0001:
                    int index = msg.arg1;
                    textView.setText(index+"");//允许，运行在
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

    public void test(View view) {
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
    }


}