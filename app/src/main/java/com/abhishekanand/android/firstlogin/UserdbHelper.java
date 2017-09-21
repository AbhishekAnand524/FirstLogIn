package com.abhishekanand.android.firstlogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jerry on 7/14/2015.
 */

public class UserdbHelper extends SQLiteOpenHelper {

    String a="onelogin";
    String b="0000";
    private static final String DATABASE_NAME="USER_INFO.DB";
    private static final int DATABASE_VERSION=11;
    private static final String CREATE_QUERY=
            "CREATE TABLE "+ UserContract.NewUserInfo.TABLE_NAME+"("+ UserContract.NewUserInfo.USER_TITLE+" TEXT, "+
                    UserContract.NewUserInfo.USER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    UserContract.NewUserInfo.USER_CATEGORY+" INTEGER, "+
                    UserContract.NewUserInfo.USER_USERNAME+" TEXT, "+
                    UserContract.NewUserInfo.USER_COMPANY+" TEXT, "+
                    UserContract.NewUserInfo.USER_PASSWORD+" TEXT, "+
                    UserContract.NewUserInfo.USER_PHONE+" TEXT, "+
                    UserContract.NewUserInfo.USER_URL+" TEXT, "+
                    UserContract.NewUserInfo.USER_NOTE+" TEXT); ";
    public UserdbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);

    }


    public void addInformation(String title, String username, String company, String password, String phone,String url,String note, SQLiteDatabase db){
        ContentValues contentValues=new ContentValues();
        Categories_accounts a=new Categories_accounts();
        contentValues.put(UserContract.NewUserInfo.USER_TITLE, title);
        contentValues.put(UserContract.NewUserInfo.USER_CATEGORY, a.asa());
        contentValues.put(UserContract.NewUserInfo.USER_USERNAME, username);
        contentValues.put(UserContract.NewUserInfo.USER_COMPANY, company);
        contentValues.put(UserContract.NewUserInfo.USER_PASSWORD,password);
        contentValues.put(UserContract.NewUserInfo.USER_PHONE,phone);
        contentValues.put(UserContract.NewUserInfo.USER_URL, url);
        contentValues.put(UserContract.NewUserInfo.USER_NOTE,note);
        contentValues.putNull(UserContract.NewUserInfo.USER_ID);

        db.insert(UserContract.NewUserInfo.TABLE_NAME, null, contentValues);

    }

    public Cursor getInformation(SQLiteDatabase db){

        Cursor cursor;
        String[] projections={UserContract.NewUserInfo.USER_TITLE, UserContract.NewUserInfo.USER_ID,
                UserContract.NewUserInfo.USER_CATEGORY,
                UserContract.NewUserInfo.USER_USERNAME,
                UserContract.NewUserInfo.USER_COMPANY,
                UserContract.NewUserInfo.USER_PASSWORD,
                UserContract.NewUserInfo.USER_PHONE,
                UserContract.NewUserInfo.USER_URL,
                UserContract.NewUserInfo.USER_NOTE};
        cursor= db.query(UserContract.NewUserInfo.TABLE_NAME,projections,null,null,null,null,null);
        return cursor;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ UserContract.NewUserInfo.TABLE_NAME);
        onCreate(db);
    }
}
