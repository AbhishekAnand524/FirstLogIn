package com.abhishekanand.android.firstlogin;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class AccDetails extends ActionBarActivity {
    TextView title_detail,username_detail,company_detail,password_detail,phone_detail,url_detail,note_detail,
            title_display,username_display,company_display,password_display,phone_display,url_display,note_display;

    SQLiteDatabase sqLiteDatabase;
    UserdbHelper userdbHelper;
    Cursor cursor;
    static int p,check_edit=0;
    private Context cntx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc_details);
        this.cntx = getApplicationContext();
        //Add a 32 digit number
		String username2 = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        //Add a 16 digit Number
		String iv = "xxxxxxxxxxxxxxxx";
        KeyManager km = new KeyManager(getApplicationContext());
        km.setIv(iv.getBytes());
        km.setId(username2.getBytes());
        String Decrypted_Data = "data";

        title_detail=(TextView)findViewById(R.id.acc_detail_title);
        username_detail=(TextView)findViewById(R.id.acc_detail_username);
        company_detail=(TextView)findViewById(R.id.acc_detail_company);
        password_detail=(TextView)findViewById(R.id.acc_detail_password);
        phone_detail=(TextView)findViewById(R.id.acc_detail_phone);
        url_detail=(TextView)findViewById(R.id.acc_detail_url);
        note_detail=(TextView)findViewById(R.id.acc_detail_note);

        title_display=(TextView)findViewById(R.id.textView2);
        username_display=(TextView)findViewById(R.id.textView3);
        company_display=(TextView)findViewById(R.id.textView4);
        password_display=(TextView)findViewById(R.id.textView5);
        phone_display=(TextView)findViewById(R.id.textView6);
        url_display=(TextView)findViewById(R.id.textView7);
        note_display=(TextView)findViewById(R.id.textView8);
        check_edit=0;
        MainActivity m=new MainActivity();
        Categories_accounts a=new Categories_accounts();
        SearchResult s=new SearchResult();
        int n,change_hint=0;
        change_hint=a.asa();
        if(change_hint==4){
            company_display.setText("PC Details:");
            phone_display.setText("Note 1:");
            url_display.setText("Note 2:");
            note_display.setText("Note 3:");
        }
        userdbHelper = new UserdbHelper(getApplicationContext());
        sqLiteDatabase = userdbHelper.getReadableDatabase();
        cursor = userdbHelper.getInformation(sqLiteDatabase);
        int main_position = m.fsa();
        int j=1,check_search=s.check_search(),sp=s.search_position();
        if (cursor.moveToFirst()) {
            do {

                String title ,username, company ,password ,phone ,url ,note, id;
                n = cursor.getInt(2);
                if(check_search==0) {
                    if (n == a.asa()) {
                        if (j == main_position) {
                            id = String.valueOf(cursor.getInt(1));
                            p = Integer.parseInt(id);
                            title = cursor.getString(0);
                            username = cursor.getString(3);
                            company = cursor.getString(4);
                            password = cursor.getString(5);
                            phone = cursor.getString(6);
                            url = cursor.getString(7);
                            note = cursor.getString(8);

                            try {
                                Crypto crypto = new Crypto(cntx);
                                Decrypted_Data = crypto.armorDecrypt(password);
                            }   catch (InvalidKeyException e) {
                            } catch (NoSuchAlgorithmException e) {
                            } catch (NoSuchPaddingException e) {
                            } catch (IllegalBlockSizeException e) {
                            } catch (BadPaddingException e) {
                            } catch (InvalidAlgorithmParameterException e) {
                            }
                            title_detail.setText(title);
                            username_detail.setText(username);
                            company_detail.setText(company);
                            password_detail.setText(Decrypted_Data);
                            phone_detail.setText(phone);
                            url_detail.setText(url);
                            note_detail.setText(note);
                        }j++;
                    }
                }
                else
                {
                    if(sp==j){
                        id = String.valueOf(cursor.getInt(1));
                        p=Integer.parseInt(id);
                        title = cursor.getString(0);
                        username = cursor.getString(3);
                        company = cursor.getString(4);
                        password = cursor.getString(5);
                        phone = cursor.getString(6);
                        url = cursor.getString(7);
                        note = cursor.getString(8);
                        Log.e("check database", password);
                        try {
                            Crypto crypto = new Crypto(cntx);
                            Decrypted_Data = crypto.armorDecrypt(password);
                        }   catch (InvalidKeyException e) {
                        } catch (NoSuchAlgorithmException e) {
                        } catch (NoSuchPaddingException e) {
                        } catch (IllegalBlockSizeException e) {
                        } catch (BadPaddingException e) {
                        } catch (InvalidAlgorithmParameterException e) {
                        }
                        title_detail.setText(title);
                        username_detail.setText(username);
                        company_detail.setText(company);
                        password_detail.setText(Decrypted_Data);
                        phone_detail.setText(phone);
                        url_detail.setText(url);
                        note_detail.setText(note);
                    }j++;
                }

            }while (cursor.moveToNext());
        }
        cursor.moveToFirst();

    }

    public void edit_details(View view)
    {
        Intent intent=new Intent(AccDetails.this,AddContent.class);
        startActivity(intent);
        check_edit=1;
        finish();
    }

    public int getCheck_edit()
    {
        return check_edit;
    }

    public void delete_details(View view)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Delete?");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                sqLiteDatabase = userdbHelper.getWritableDatabase();
                sqLiteDatabase.execSQL("DELETE FROM " + UserContract.NewUserInfo.TABLE_NAME + " WHERE " + UserContract.NewUserInfo.USER_ID + " = " + p + ";");
                Intent intent = new Intent(AccDetails.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        builder.show();


    }

    @Override
    public void onBackPressed(){
        SearchResult s=new SearchResult();
        int check_search=s.check_search();
        if(check_search==0) {
            Intent intent = new Intent(AccDetails.this, MainActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(AccDetails.this, SearchResult.class);
            startActivity(intent);
        }
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_acc_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(AccDetails.this,MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}