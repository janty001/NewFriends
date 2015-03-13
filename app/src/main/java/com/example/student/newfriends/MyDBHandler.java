package com.example.student.newfriends;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by student on 2/24/15.
 */
public class MyDBHandler  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "myDatabase.db";
    private static final String DATABASE_TABLE = "friends";
    private static final int DATABASE_VERSION = 1;

    public static final String FRIEND_NAME = "name";
    public static final String FRIEND_EMAIL = "email";
    public static final String FRIEND_PHONE = "phone";
    public static final String FRIEND_ID = "id" ;

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version ){
        super(context,DATABASE_NAME, factory, DATABASE_VERSION );
      }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FRIENDS_TABLE = "create table " +
                DATABASE_TABLE + "( " +
                FRIEND_ID + " INTEGER PRIMARY KEY  , " +
                FRIEND_NAME + " TEXT, " +
                FRIEND_EMAIL + " TEXT, " +
                FRIEND_PHONE + " TEXT " + ") ;" ;
        db.execSQL(CREATE_FRIENDS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("TaskDBAdapter", "Upgrading from version " +
                oldVersion + " to " +
                newVersion + ", which will destroy all old data");
        db.execSQL("drop table if exists" + DATABASE_TABLE );
        onCreate(db);
    }

    public MyFriends findStudent(String friendName ){
        String query = "Select * From " + DATABASE_TABLE + " WHERE " +
                FRIEND_NAME + " = \"" + friendName + "\"" ;


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        MyFriends friend = new MyFriends();
        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            friend.setId(Integer.parseInt(cursor.getString(0)));
            friend.setName(cursor.getString(1));
            friend.setEmail(cursor.getString(2));
            friend.setPhone(cursor.getString(3));
            cursor.close();
        }else{
            friend = null;
        }
        db.close();
        return friend;
    }

    public void addFriend(MyFriends friend){

        ContentValues values = new ContentValues();
        values.put(FRIEND_NAME, friend.getName() );
        values.put(FRIEND_EMAIL, friend.getEmail() );
        values.put(FRIEND_PHONE,friend.getPhone() );
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DATABASE_TABLE, null, values);
        db.close();
    }
}
