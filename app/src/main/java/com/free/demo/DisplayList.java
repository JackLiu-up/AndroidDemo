package com.free.demo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class DisplayList extends AppCompatActivity {
    private AlertDialog dialog;
    private AlertDialog dlg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);
        createSingleDialog();
        createMuliDialog();
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
                .setPositiveButton("确定",null)
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