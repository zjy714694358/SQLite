package com.yc.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 进行表的增删改查操作
 * @Date 2024/5/30 14:25
 * @Author ZJY
 * @email 714694358@qq.com
 */
public class ContactsDatabase {
    private final DatabaseHelper dbHelper;

    public ContactsDatabase(Context context) {
        super();
        dbHelper = new DatabaseHelper(context);
    }

    /**
     * 增
     *
     * @param data
     */
    public void insert(ContactsInfo data) {
        String sql = "insert into " + DatabaseHelper.CONTACTS_TABLE_NAME;

        sql += "(sendPhone, name, phone) values(?,?,?)";

        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        sqlite.execSQL(sql, new String[] {data.getSendPhone() + "",data.getName() + "", data.getPhone() + ""});
        sqlite.close();
    }

    /**
     * 删
     *
     * @param where
     */
    public void delete(String where) {
        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        String sql = "delete from " + DatabaseHelper.CONTACTS_TABLE_NAME + where;
        sqlite.execSQL(sql);
        sqlite.close();
    }
//String deleteTableQuery = "DROP TABLE IF EXISTS " + tableName;
    public void deleteTable(String name){
        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        String sql = "DROP TABLE IF EXISTS " + name;
        sqlite.execSQL(sql);
        sqlite.close();
    }
    /**
     * 改
     *
     * @param data
     */
    public void update(ContactsInfo data) {
        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        String sql = ("update " + DatabaseHelper.CONTACTS_TABLE_NAME +" set sendPhone=?,name=?,phone=? where cid=?");
        sqlite.execSQL(sql,
                new String[] { data.getSendPhone() + "", data.getName() + "", data.getPhone()+ "", data.getCid()+ ""});
        sqlite.close();
    }
    /**
     * 查一条数据
     *
     * @param where
     * @return
     */
    public ContactsInfo queryContactsInfo(String where) {
        ContactsInfo contactsInfo = null;
        SQLiteDatabase sqlite = dbHelper.getReadableDatabase();
        String sql= "select * from "
                + DatabaseHelper.CONTACTS_TABLE_NAME + where;
        Cursor cursor = sqlite.rawQuery(sql, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            contactsInfo = new ContactsInfo();
            //Log.e("========",cursor.toString()+"");
            Log.e("========",cursor.getInt(0)+"");
            Log.e("========",cursor.getString(1)+"");
            Log.e("========",cursor.getString(2)+"");
            Log.e("========",cursor.getString(3)+"");
            contactsInfo.setCid(cursor.getInt(0));
            contactsInfo.setSendPhone(cursor.getString(1));
            contactsInfo.setName(cursor.getString(2));
            contactsInfo.setPhone(cursor.getString(3));
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        sqlite.close();
        return contactsInfo;
    }

    /**
     * 查
     *
     * @param where
     * @return
     */
    public List<ContactsInfo> query(String where) {
        SQLiteDatabase sqlite = dbHelper.getReadableDatabase();
        ArrayList<ContactsInfo> data = null;
        data = new ArrayList<ContactsInfo>();
        String sql="select * from "
                + DatabaseHelper.CONTACTS_TABLE_NAME + where;
        Cursor cursor = sqlite.rawQuery(sql, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            ContactsInfo contactsInfo = new ContactsInfo();
            contactsInfo.setSendPhone(cursor.getString(1));
            contactsInfo.setName(cursor.getString(2));
            contactsInfo.setPhone(cursor.getString(3));
            data.add(contactsInfo);
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        sqlite.close();
        return data;
    }
    /**
     * 查
     *
     * @param where
     * @return
     */
    public int queryCount(String where) {
        SQLiteDatabase sqlite = dbHelper.getReadableDatabase();
        String sql="select count(*) from "
                + DatabaseHelper.CONTACTS_TABLE_NAME+where ;
        Cursor cursor = sqlite.rawQuery(sql, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        sqlite.close();
        return count;
    }

    /**
     * 重置
     *
     * @param datas
     */
    public void reset(List<ContactsInfo> datas) {
        if (datas != null) {
            SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
            // 删除全部
            sqlite.execSQL("delete from " + DatabaseHelper.CONTACTS_TABLE_NAME);
            // 重新添加
            for (ContactsInfo data : datas) {
                insert(data);
            }
            sqlite.close();
        }
    }

    public void destroy() {
        dbHelper.close();
    }
}