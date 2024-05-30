package com.yc.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    ContactsDatabase contactsDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DatabaseHelper(MainActivity.this);
        //SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        contactsDatabase = new ContactsDatabase(MainActivity.this);
        for (int i=1;i<10;i++){
            ContactsInfo contactsInfo = new ContactsInfo();
            contactsInfo.setName("zjy2");
            contactsInfo.setPhone("15112345678");
            contactsInfo.setCid(i);
            contactsInfo.setChoosed(true);
            contactsInfo.setSendPhone("反反复复2");
            contactsDatabase.insert(contactsInfo);
        }
        ContactsInfo contactsInfo2 = new ContactsInfo();
        contactsInfo2.setCid(5);
        contactsInfo2.setName("zjy3");
        contactsInfo2.setPhone("15112345678");
        contactsInfo2.setChoosed(true);
        contactsInfo2.setSendPhone("反反复复6");
        contactsDatabase.update(contactsInfo2);
//        for(int i=2;i<15;i++){
//            contactsDatabase.delete( " Where cid = '"+i+"'");
//        }
//        contactsDatabase.delete(" Where phone = '15112345678'");
        //删除表后需要卸载重新生成安装APP不然会报错
        //contactsDatabase.deleteTable("contacts_table");
        contactsDatabase.queryContactsInfo(" Where phone = '15112345678'");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        contactsDatabase.destroy();
    }
}