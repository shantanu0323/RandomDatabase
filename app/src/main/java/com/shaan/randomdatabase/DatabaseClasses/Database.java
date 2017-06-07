package com.shaan.randomdatabase.DatabaseClasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SHAAN on 04-02-17.
 */
public class Database {

    public static final String KEY_ROWID = "id";
    public static final String USERNAME = "name";
    public static final String PASSWORD = "reg_no";

    private static final String TABLE_NAME = "STUDENT";
    private static final String DATABASE_TABLE = "student";
    private static final int DATABASE_VERSION = 1;

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public Database(Context c) {
        this.ourContext = c;
    }

    public Database open()throws SQLException {
        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        ourHelper.close();
    }


    public void putInfo(String username, String password) {
        ContentValues cv = new ContentValues();
        cv.put(USERNAME,username);
        cv.put(PASSWORD,password);
        ourDatabase.insert(DATABASE_TABLE, null, cv);
    }

    public Cursor getInfo() {
        String[] columns = new String[]{ KEY_ROWID, USERNAME, PASSWORD};
        Cursor cursor = ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        cursor.moveToFirst();
        return cursor;
    }
    public String[] getData() {
        String[] columns = new String[]{ KEY_ROWID, USERNAME, PASSWORD};
        Cursor c = ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        if(c.getCount() == 0)
            return new String[]{"empty", "empty", "empty"};
        String usernames = "", passwords = "", rowIds = "";

        int iRowId = c.getColumnIndex(KEY_ROWID);
        int iUsername = c.getColumnIndex(USERNAME);
        int iPassword = c.getColumnIndex(PASSWORD);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            rowIds = rowIds + c.getString(iRowId) + "\n";
            usernames = usernames + c.getString(iUsername) + "\n";
            passwords = passwords + c.getString(iPassword) + "\n";
        }

        return new String[]{rowIds, usernames, passwords};
    }

    public void clearDatabase() {
        String AUTOINCREMENT_RESET = "DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + TABLE_NAME + "';";
        ourDatabase.delete(TABLE_NAME, null, null);
        ourDatabase.execSQL(AUTOINCREMENT_RESET);
    }

    public void updateInfo(String oldUser, String newUser, String oldPass, String newPass) {
        String UPDATE_USERNAME = "UPDATE " + TABLE_NAME +
                " SET " + USERNAME + " = '" + newUser + "'" +
                " WHERE " + USERNAME + " = '" + oldUser + "';";
        String UPDATE_PASSWORD = "UPDATE " + TABLE_NAME +
                " SET " + PASSWORD + " = '" + newPass + "'" +
                " WHERE " + PASSWORD + " = '" + oldPass + "';";
        ourDatabase.execSQL(UPDATE_USERNAME);
        ourDatabase.execSQL(UPDATE_PASSWORD);
    }

    public void deleteInfo(String username) {
        String DELETE = "DELETE FROM " + TABLE_NAME +
                        " WHERE " + USERNAME + " = " + "'" + username + "';";
        ourDatabase.execSQL(DELETE);
    }

    private static class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context) {
            super(context, TABLE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                    KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    USERNAME + " VARCHAR2(20) NOT NULL, " +
                    PASSWORD + " VARCHAR2(20) UNIQUE);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE + ";" );
            onCreate(db);
        }
    }

}




