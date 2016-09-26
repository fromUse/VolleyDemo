package com.cqg.xposed.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cqg.bean.Contact;

import java.util.List;

import cqg.com.xposed.R;

/**
 * Created by chen on 2016/9/19.
 */
public class ListViewAdapter extends BaseAdapter {
    private List<Contact> mList;
    private Context mContext;

    public ListViewAdapter(List<Contact> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold hold = null;
        Contact contact = null;
        if (convertView == null) {

            hold = new ViewHold();

            convertView = View.inflate(mContext, R.layout.list_item, null);
            hold.name = (TextView) convertView.findViewById(R.id.tvName);
            hold.number = (TextView) convertView.findViewById(R.id.tvNumber);
            convertView.setTag(hold);
        }

        hold = (ViewHold) convertView.getTag();
        contact = mList.get(position);
        hold.name.setText(contact.getName());
        hold.number.setText(contact.getNumber());

        return convertView;
    }


    class ViewHold {
        public TextView name;
        public TextView number;
    }
}
