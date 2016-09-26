package com.cqg.xposed.activity;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cqg.xposed.adapter.ListViewAdapter;
import com.cqg.bean.Contact;
import com.cqg.xposed.commn.MimeType;

import java.util.ArrayList;
import java.util.List;

import cqg.com.xposed.R;

public class ContactActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "ContactActivity";
    private ListView mListView;
    private ListViewAdapter adapter;
    private List<Contact> mList;
    Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        init();
        initData();
    }

    private void initData() {
        Cursor cursor = null;
        int count = 1;
        try {
            // Log.i(TAG, "initData: " +uri.toString()) ;
            //cursor = getContentResolver().query(uri,new String[]{ContactsContract.CommonDataKinds.Phone._ID,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER},"_id = ?", new String[]{"44"}, null);
            cursor = getContentResolver().query(uri, null, null, null, null);
            if (mList == null) {
                mList = new ArrayList<>();
            } else {
                mList.clear();
            }
            //moveToNext查看是否还可以读数据
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                // String email = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DISPLAY_NAME));
                Log.i(TAG, "initData: id ：" + cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID)));
                Contact aFriend = new Contact(name + "(" + count++ + ")", number);
                mList.add(aFriend);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        if (mList != null) {
            adapter = new ListViewAdapter(mList, this);
            mListView.setAdapter(adapter);
        }
    }

    private void init() {
        mListView = (ListView) findViewById(R.id.listview);
        mListView.setOnItemClickListener(this);
    }


    public void addContact(View view) {

        View root = View.inflate(this, R.layout.dialog_layout, null);
        final ContentResolver resolver = getContentResolver();
        final Uri raw_uri = ContactsContract.RawContacts.CONTENT_URI;

        final EditText tvName = (EditText) root.findViewById(R.id.tvName);
        final EditText tvNumber = (EditText) root.findViewById(R.id.tvNumber);
        final EditText tvEmail = (EditText) root.findViewById(R.id.tvEmail);

        AlertDialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("添加联系人")
                .setView(root)
                .setPositiveButton("确定", new AlertDialog.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = tvName.getText().toString();
                        String number = tvNumber.getText().toString();
                        String email = tvEmail.getText().toString();

                        if (name.equals("") || number.equals("")) {
                            Toast.makeText(ContactActivity.this, "姓名和号码不能为空!!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        ContentValues values = new ContentValues();
                        //插入一个空数据
                        //获的raw_contactid
                        long raw_contactid = ContentUris.parseId(resolver.insert(raw_uri, values));
                        values.clear();
                        //插入联系人姓名
                        values.put(ContactsContract.Data.RAW_CONTACT_ID, raw_contactid);
                        values.put(MimeType.MIME_TYPE, MimeType.NAME_TYPE);
                        values.put(ContactsContract.Data.DATA1, name);
                        resolver.insert(ContactsContract.Data.CONTENT_URI, values);
                        values.clear();
                        //插入联系人电话号码
                        values.put(ContactsContract.Data.RAW_CONTACT_ID, raw_contactid);
                        values.put(MimeType.MIME_TYPE, MimeType.PHONE_TYPE);
                        values.put(ContactsContract.Data.DATA1, number);
                        resolver.insert(ContactsContract.Data.CONTENT_URI, values);
                        values.clear();
                        //当联系人Email有写才添加
                        if (email != null && !email.equals("")) {
                            values.put(ContactsContract.Data.RAW_CONTACT_ID, raw_contactid);
                            values.put(MimeType.MIME_TYPE, MimeType.EMAIL_TYPE);
                            values.put(ContactsContract.Data.DATA1, email);
                            resolver.insert(ContactsContract.Data.CONTENT_URI, values);
                        }
                        initData();
                        // dialog.dismiss();
                    }
                }).setNegativeButton("取消", new AlertDialog.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
        dialog = builder.create();
        if (dialog != null) {
            dialog.show();
        }

//        ContentValues values = new ContentValues();
//        //插入空数据
//        //获取raw_contactid
//        Uri id = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI,values);
//       long raw_contactid =  ContentUris.parseId(id);
//        //将数据插入到对应raw_contactid的行
//        values.put(ContactsContract.Data.RAW_CONTACT_ID,raw_contactid);
//        values.put(ContactsContract.Data.MIME_TYPE,"vnd.android.cursor.item/name");
//        values.put(ContactsContract.Data.DATA1,"lalala");
//        getContentResolver().insert(ContactsContract.Data.CONTENT_URI,values);
//
//        values.clear();
//        values.put(ContactsContract.Data.RAW_CONTACT_ID,raw_contactid);
//        values.put(ContactsContract.Data.MIME_TYPE,"vnd.android.cursor.item/phone_v2");
//        values.put(ContactsContract.Data.DATA1,"123456789");
//        getContentResolver().insert(ContactsContract.Data.CONTENT_URI,values);
//        values.clear();
//
//        values.put("raw_contact_id", raw_contactid);
//        values.put(ContactsContract.Data.MIME_TYPE,"vnd.android.cursor.item/email_v2");
//        values.put("data2", "2");   //单位
//        values.put("data1", "xzdong@xzdong.com");
//        getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
//        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String name = ((TextView) view.findViewById(R.id.tvName)).getText().toString();
        String number = ((TextView) view.findViewById(R.id.tvNumber)).getText().toString();
        Toast.makeText(ContactActivity.this, "name: " + name + "\nnumber: " + number, Toast.LENGTH_SHORT).show();

    }
}
