package com.db.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by newUser on 10/22/13.
 */
public class SqlDataHandler {
    public static final String KEY_ROWID = "ID";
    public static final String KEY_SSN = "SSN";
    public static final String KEY_NAME = "Name";
    public static final String KEY_ADDRESS = "Address";
    public static final String KEY_ITEM = "Item";
    public static final String KEY_COST = "Cost";

    public static final String DATABASE_NAME = "DATABASE_APP";
    public static final String DATABASE_TABLE = "CUSTOMER_TABLE";
    public static final int DATABASE_VERSION = 1;

    private DBHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public SqlDataHandler(Context c) {
        ourContext = c;
    }

    public void createEntry(String ssn, String name, String address, String item, String cost) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_SSN, ssn);
        cv.put(KEY_NAME, name);
        cv.put(KEY_ADDRESS, address);
        cv.put(KEY_ITEM, item);
        cv.put(KEY_COST, cost);
        ourDatabase.insert(DATABASE_TABLE, null, cv);
    }

    public String getData() {
        String[] columns = new String[]{KEY_ROWID, KEY_SSN, KEY_NAME, KEY_ADDRESS, KEY_ITEM, KEY_COST};
        Cursor c = ourDatabase.query(DATABASE_TABLE, null, null, null, null, null, null);
        String result = "";

        int iId = c.getColumnIndex(KEY_ROWID);
        int iSSN = c.getColumnIndex(KEY_SSN);
        int iName = c.getColumnIndex(KEY_NAME);
        int iAddress = c.getColumnIndex(KEY_ADDRESS);
        int iItem = c.getColumnIndex(KEY_ITEM);
        int iCost = c.getColumnIndex(KEY_COST);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            result = result + c.getString(iId) + " " + c.getString(iSSN) + " " + c.getString(iName) + " " + c.getString(iAddress) + " " + c.getString(iItem) + " " + c.getString(iCost) + "\n";
        }
        return result;
    }

    private static class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
                    KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_SSN + " INTEGER NOT NULL, " +
                    KEY_NAME + " TEXT NOT NULL, " +
                    KEY_ADDRESS + " TEXT NOT NULL, " +
                    KEY_ITEM + " TEXT NOT NULL, " +
                    KEY_COST + " TEXT NOT NULL);"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i2) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        }
    }

    public SqlDataHandler open() throws SQLException {
        ourHelper = new DBHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        ourHelper.close();
    }
}
