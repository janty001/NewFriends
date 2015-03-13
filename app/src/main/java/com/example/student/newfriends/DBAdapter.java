package com.example.student.newfriends;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by student on 2/24/15.
 */
public class DBAdapter {
    private static final String DATABASE_NAME = "myDatabase.db";
    private static final String DATABASE_TABLE = "friends";
    private static final String DATABASE_VIEW = "v_friends";
    private static final int DATABASE_VERSION = 1;

    public static final String FRIEND_NAME = "name";
    public static final String FRIEND_EMAIL = "email";
    public static final String FRIEND_PHONE = "phone";
    public static final String FRIEND_ID = "id" ;


    final Context context;

    MyDBHandler DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new MyDBHandler(context,null,null,1);
    }


    //---opens the database---
    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getReadableDatabase();//.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }

    //---retrieves all the contacts---
    public Cursor getAllContacts()
    {
        return db.query(DATABASE_TABLE, new String[] {FRIEND_ID, FRIEND_NAME,
                FRIEND_EMAIL,FRIEND_PHONE}, null, null, null, null, null);
    }

    //--retrieves a particular contact--

    public Cursor findMyFriends( String name ) throws SQLException {
        Cursor myCursor = db.query(true, DATABASE_TABLE, new String[]{ FRIEND_ID, FRIEND_NAME, FRIEND_EMAIL,FRIEND_PHONE }, FRIEND_NAME + " like%" + name + "% ", null, null,null,null,null );
        if(myCursor != null )
            myCursor.moveToFirst();
        return myCursor;
    }






}
