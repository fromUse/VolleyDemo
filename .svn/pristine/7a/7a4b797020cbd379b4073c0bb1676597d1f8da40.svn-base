package com.cqg.xposed.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cqg.Factory.BatchFactory;
import com.cqg.base.IBatchable;
import com.cqg.base.action.ContactsBatch;
import com.cqg.bean.Contact;

import java.util.ArrayList;

import cqg.com.xposed.R;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textView);
    }

    public void click(View view) {
        Toast.makeText(MainActivity.this, getStr(), Toast.LENGTH_SHORT).show();
    }


    public String getStr() {
        return tv.getText().toString();
    }

    public void getIMEI(View view) {

        TelephonyManager manager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);

        Toast.makeText(MainActivity.this, manager.getDeviceId(), Toast.LENGTH_SHORT).show();
    }


    public void startContact(View view) {
        startActivity(new Intent(this, ContactActivity.class));
    }

    public void batchInsert(View view) {

        final IBatchable<Contact> contactsBatch = BatchFactory.newInstance(getApplicationContext(), BatchFactory.CONTACTS);
        final ArrayList<Contact> peoples = new ArrayList<>();
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.show();
        for (int i = 0; i < 200; i++) {
            Contact con = null;
            if (i > 9) {
                con = new Contact("a姓名a" + i, "138001380" + i);
            } else {
                con = new Contact("b姓名b" + i, "1380013800" + i);
            }
            peoples.add(con);
        }

        new Thread() {
            @Override
            public void run() {
                try {
                    contactsBatch.setInsertListener(new IBatchable.OnBatchInertListener() {
                        @Override
                        public void onInsertSuccess() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    dialog.dismiss();
                                    Toast.makeText(MainActivity.this, "批量插入联系人完成", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onInsertFail(Exception e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "批量插入联系失败!!!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    contactsBatch.batchInsert(peoples);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    public void clearContact(View view) {
        ContactsBatch contactsBatch = (ContactsBatch) BatchFactory.newInstance(getApplicationContext(), BatchFactory.CONTACTS);
        contactsBatch.setClearListener(new IBatchable.OnBatcClearListener() {
            @Override
            public void onClearSuccess() {
                Toast.makeText(MainActivity.this, "批量删除联系成功!!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClearFail(Exception e) {
                Toast.makeText(MainActivity.this, "批量删除联系失败!!!", Toast.LENGTH_SHORT).show();
            }
        });
        contactsBatch.clear();
    }
}
