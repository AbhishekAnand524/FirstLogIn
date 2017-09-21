package com.abhishekanand.android.firstlogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Jerry on 2/23/2016.
 */
public class UserdbHelper2 extends SQLiteOpenHelper {

    String a="onelogin";
    String b="0000";
    private static final String DATABASE_NAME="USER_INFO2.DB";
    private static final int DATABASE_VERSION=10;
      public UserdbHelper2(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("CREATE TABLE " + UserContract.NewUserInfo.TABLE2 + "(" + UserContract.NewUserInfo.COL1 + " TEXT, " +
                UserContract.NewUserInfo.COL2 + " INTEGER);");
        db.execSQL("CREATE TABLE " + UserContract.NewUserInfo.TABLE3 + "(" + UserContract.NewUserInfo.COL11 + " TEXT, " +
                UserContract.NewUserInfo.COL12 + " TEXT, " +
                UserContract.NewUserInfo.COL13 + " INTEGER);");
        ContentValues contentValues=new ContentValues();
        contentValues.put(UserContract.NewUserInfo.COL1, b);
        contentValues.put(UserContract.NewUserInfo.COL2, 1);
        db.insert(UserContract.NewUserInfo.TABLE2, null, contentValues);
        ContentValues contentValues1=new ContentValues();
        contentValues1.put(UserContract.NewUserInfo.COL11, a);
        contentValues1.put(UserContract.NewUserInfo.COL12, b);
        contentValues1.put(UserContract.NewUserInfo.COL13, 1);
        db.insert(UserContract.NewUserInfo.TABLE3, null, contentValues1);

    }




    public Cursor getpass(SQLiteDatabase db){
        Cursor cursor;
        String[] projections={UserContract.NewUserInfo.COL1,UserContract.NewUserInfo.COL2};
        cursor=db.query(UserContract.NewUserInfo.TABLE2,projections,null,null,null,null,null);
        return cursor;
    }

    public Cursor getpass2(SQLiteDatabase db){
        Cursor cursor;
        String[] projections={UserContract.NewUserInfo.COL11,UserContract.NewUserInfo.COL12,UserContract.NewUserInfo.COL13};
        cursor=db.query(UserContract.NewUserInfo.TABLE3,projections,null,null,null,null,null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ UserContract.NewUserInfo.TABLE2);
        db.execSQL("DROP TABLE IF EXISTS "+ UserContract.NewUserInfo.TABLE3);
        onCreate(db);
    }
}

