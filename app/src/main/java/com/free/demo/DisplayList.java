package com.free.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class DisplayList extends AppCompatActivity {
    private AlertDialog dialog;
    private AlertDialog dlg2;
    private ProgressDialog dlg;
    private final static int MSG_Finish = 0x0002;
    private final static int MSG_PROGRESS = 0x0001;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            //运行在UI线程中
            //设置进度
            switch (msg.what) {
                case MSG_PROGRESS:
                    int progress = msg.arg1;
                    dlg.setProgress(progress);
                    break;
                case MSG_Finish:
                    //关闭进度条对话框
                    dlg.dismiss();
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);
        createSingleDialog();
        createMuliDialog();
        createProgressDialog();
    }

    private void createProgressDialog() {
        dlg = new ProgressDialog(this);
        dlg.setIcon(R.drawable.ic_android_black_24dp);
        dlg.setMessage("这是一个进度条");
        dlg.setTitle("进度条");
        dlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL); //设置水平进度条
    }

    /**
     * 显示进度条对话框
     *
     * @param view
     */
    public void displayProgressDialog(View view) {
        dlg.show();
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    Message msg = handler.obtainMessage();
                    msg.what = MSG_PROGRESS;
                    msg.arg1 = i;
                    handler.sendMessage(msg);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.sendEmptyMessage(MSG_Finish);
            }

            ;
        }.start();
    }


    /**
     * 单选对话框
     */
    private void createSingleDialog() {
        final String[] items = {"AAAA", "BBB", "CCC", "DDD"};
        dialog = new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_android_black_24dp)
                .setTitle("列表对话框")
                .setSingleChoiceItems(items, 0, null)
                .setPositiveButton("确定", null)
                .create();
    }

    public void displayRadioButtonDialog(View view) {
        //显示单选对话框的按钮
        dialog.show();
    }

    /**
     * 多选对话框
     */
    private void createMuliDialog() {
        final String[] items = {"AAA", "BBB", "CCC", "DDD"};
        dlg2 = new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_android_black_24dp)
                .setTitle("多选对话框")
                .setMultiChoiceItems(items, new boolean[]{false, true, false, false}, null)
                .setPositiveButton("确定", null)
                .create();
    }

    public void displayMulitDialog(View view) {
        //显示多选
        dlg2.show();
    }

    public void display2(View view) {
        final String[] items = {"AAA", "BBB", "CCC", "DDD"};
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_android_black_24dp)
                .setTitle("列表对话框")
                .setSingleChoiceItems(items, 0, null)
                .show();
    }

    public void display(View view) {
        final String[] items = {"AAA", "BBB", "CCC", "DDD"};
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_android_black_24dp)
                .setTitle("列表对话框")
                .setItems(items, new DialogInterface.OnClickListener() {
                    /**
                     *dialog 发出事件的对话框
                     * which 表示列表项的索引
                     */
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Log.i("TEST", which + "");
                        Toast.makeText(DisplayList.this, items[which], Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

}