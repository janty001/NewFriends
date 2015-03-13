package com.example.student.newfriends;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2/24/15.
 */
public class FriendListVIewAdapter extends BaseAdapter {

    List<MyFriends> friendList = new ArrayList<MyFriends>();
    Context context;
    LayoutInflater inflater;

    public FriendListVIewAdapter(Context context ){
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }
    public int getCount(){
        return friendList.size();
    }

    public Object getItem(int arg0 ){
        return friendList.get(arg0);
    }
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        if(arg1==null)
        {
            arg1 = inflater.inflate(R.layout.listviewitem, arg2,false);
        }

        TextView tvName = (TextView)arg1.findViewById(R.id.textView1);
        TextView tvEmail = (TextView)arg1.findViewById(R.id.textView2);
        TextView tvNumber = (TextView)arg1.findViewById(R.id.textView3);

        MyFriends contact = friendList.get(arg0);

        tvName.setText(contact.getName());
        tvEmail.setText(contact.getEmail());
        tvNumber.setText(contact.getPhone());

        return arg1;
    }
    public void setDataForListView(Cursor c){
        MyFriends contact = new MyFriends( c.getInt(0),c.getString(1),c.getString(2),c.getString(3));
        friendList.add(contact);
    }
}
