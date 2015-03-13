package com.example.student.newfriends;




import android.database.Cursor;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import android.widget.Toast;



public class MainActivity extends ActionBarActivity {
    EditText search;
    String searchString="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = (EditText) findViewById(R.id.txtSearch );

        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        final DBAdapter db = new DBAdapter(this);
        db.open();
        Cursor c = db.getAllContacts();
        final FriendListVIewAdapter clva = new FriendListVIewAdapter(MainActivity.this);
        if (c.moveToFirst()) {
            do {
                clva.setDataForListView(c);
            } while (c.moveToNext());
        }
        db.close();
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(clva);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                MyFriends ct = (MyFriends) arg0.getItemAtPosition(arg2);
                DisplayContact(ct);
            }
        });


        search.setOnKeyListener( new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( (event.getAction() == KeyEvent.ACTION_DOWN ) && (keyCode == KeyEvent.KEYCODE_ENTER )){
                    searchString = search.getText().toString();
                    return true;
                }
                return false;
            }
        });


        //---get all contacts---
        if(searchString == "") {

            db.open();
            Cursor c1 = db.getAllContacts();
            final FriendListVIewAdapter clva1 = new FriendListVIewAdapter(MainActivity.this);
            if (c.moveToFirst()) {
                do {
                    clva.setDataForListView(c);
                } while (c.moveToNext());
            }

            db.close();
            ListView lv1 = (ListView) findViewById(R.id.listView);
            lv.setAdapter(clva);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    MyFriends ct = (MyFriends) arg0.getItemAtPosition(arg2);
                    DisplayContact(ct);
                }
            });
        }else{
            //searchString = search.getText().toString();
            db.open();
            Cursor cResult = db.findMyFriends( searchString );
            final FriendListVIewAdapter cl = new FriendListVIewAdapter(MainActivity.this);
            if( cResult.moveToFirst() ){
                do{
                    cl.setDataForListView( cResult );
                }while(c.moveToFirst() );
            }
            db.close();
            ListView lvR = (ListView) findViewById(R.id.listView);
            lv.setAdapter(cl);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MyFriends ct = (MyFriends) parent.getItemAtPosition(position);
                    DisplayContact(ct);
                }
            });
        }



    }





//        String name = "Qin Chen";
//        String email = "qin@hotmail.com";
//        String phone = "444-885-86213";
//        MyFriends friend = new MyFriends(name, email, phone);
//        MyFriends friend = new MyFriends();
//        dbHandler.addFriend(friend);


//    public void addFriend( ) {
//        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
//
//        String name = "Qin Chen";
//        String email = "qin@hotmail.com";
//        String phone = "444-885-86213";
//        MyFriends friend = new MyFriends(name, email, phone);
//        dbHandler.addFriend(friend);
//
//    }


//        try {
//            String destPath = "/data/data/" + getPackageName() +
//                    "/databases";
//            File f = new File(destPath);
//            if (f.exists()) {
//                f.mkdirs();
//                f.createNewFile();
//
//
//                //---copy the db from the assets folder into
//                // the databases folder---
//                CopyDB(getBaseContext().getAssets().open("myFriends.db"),
//                        new FileOutputStream(destPath + "/myDatabase"));
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//


//    public void CopyDB(InputStream inputStream,
//                       OutputStream outputStream) throws IOException {
//        //---copy 1K bytes at a time---
//        byte[] buffer = new byte[1024];
//        int length;
//        while ((length = inputStream.read(buffer)) > 0) {
//            outputStream.write(buffer, 0, length);
//        }
//        inputStream.close();
//        outputStream.close();
//    }

    public void DisplayContact(MyFriends c) {
        Toast.makeText(this,
                "Name: " + c.getName() + "\n" +
                        "Email: " + c.getEmail() + "\n" +
                        "Phone:  " + c.getPhone() + "\n" ,
                Toast.LENGTH_LONG).show();
    }

































    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
