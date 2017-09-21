package com.abhishekanand.android.firstlogin;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class AddContent extends ActionBarActivity {

    EditText contact_title,contact_username,contact_company,contact_password,contact_phone,contact_url,contact_note;
    Context context=this;
    UserdbHelper userdbHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    AccDetails accDetails=new AccDetails();
    static int p;
    private Context cntx;
    String Decrypted_Data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_content);
        this.cntx = getApplicationContext();
        contact_title=(EditText)findViewById(R.id.add_title);
        contact_username=(EditText)findViewById(R.id.add_username);
        contact_company=(EditText)findViewById(R.id.add_company);
        contact_password=(EditText)findViewById(R.id.add_password);
        contact_phone=(EditText)findViewById(R.id.add_phone);
        contact_url=(EditText)findViewById(R.id.add_url);
        contact_note=(EditText)findViewById(R.id.add_note);
        Categories_accounts a=new Categories_accounts();
        int check_edit=accDetails.getCheck_edit();
        int change_hint=0;
        change_hint=a.asa();
           if(change_hint==4){
            contact_company.setHint("PC Details");
            contact_url.setHint("Note 2");
            contact_phone.setHint("Note 1");
            contact_note.setHint("Note 3");
        }
        if(check_edit==1)
        {
            userdbHelper = new UserdbHelper(getApplicationContext());
            sqLiteDatabase = userdbHelper.getWritableDatabase();
            cursor = userdbHelper.getInformation(sqLiteDatabase);
            MainActivity m=new MainActivity();
            int n;
            int t = m.fsa();
            SearchResult s=new SearchResult();
            int j=1,check_search=s.check_search(),sp=s.search_position();
            if (cursor.moveToFirst()) {
                do {
                    String title ,username, company ,password ,phone ,url ,note, id;
                    n = cursor.getInt(2);
                    if(check_search==0) {
                        if (n == a.asa()) {
                            if (j == t) {
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
                                contact_title.setText(title);
                                contact_username.setText(username);
                                contact_company.setText(company);
                                contact_password.setText(Decrypted_Data);
                                contact_phone.setText(phone);
                                contact_url.setText(url);
                                contact_note.setText(note);
                            }
                            j++;
                        }
                    }
                    else{
                        if(j==sp){
                            id = String.valueOf(cursor.getInt(1));
                            p=Integer.parseInt(id);
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
                            contact_title.setText(title);
                            contact_username.setText(username);
                            contact_company.setText(company);
                            contact_password.setText(Decrypted_Data);
                            contact_phone.setText(phone);
                            contact_url.setText(url);
                            contact_note.setText(note);
                        }  j++;
                    }

                } while (cursor.moveToNext());
            }
            cursor.moveToFirst();

        }

    }

    public void addContact(View view) {
        int empty = 1;
        String username2 = "59485037487246908738507523067957";
        String iv = "7493659274759305";
        KeyManager km = new KeyManager(getApplicationContext());
        km.setIv(iv.getBytes());
        km.setId(username2.getBytes());
        String Encrypted_Data="data";
        String Data=contact_password.getText().toString();
        try {
            Crypto crypto = new Crypto(cntx);
            Encrypted_Data = crypto.armorEncrypt(Data.getBytes());
        }   catch (InvalidKeyException e) {
        } catch (NoSuchAlgorithmException e) {
        } catch (NoSuchPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (BadPaddingException e) {
        } catch (InvalidAlgorithmParameterException e) {
        }
        if (accDetails.getCheck_edit() == 1) {
            ContentValues contentValues = new ContentValues();
            if (contact_title.getText().toString().equals("") && contact_username.getText().toString().equals("")) {
                Toast.makeText(getBaseContext(), "Title or Username Required", Toast.LENGTH_LONG).show();
                empty = 0;
            } else {

                contentValues.put(UserContract.NewUserInfo.USER_TITLE, contact_title.getText().toString());
                contentValues.put(UserContract.NewUserInfo.USER_USERNAME, contact_username.getText().toString());
                contentValues.put(UserContract.NewUserInfo.USER_COMPANY, contact_company.getText().toString());
                contentValues.put(UserContract.NewUserInfo.USER_PASSWORD, Encrypted_Data);
                contentValues.put(UserContract.NewUserInfo.USER_PHONE, contact_phone.getText().toString());
                contentValues.put(UserContract.NewUserInfo.USER_URL, contact_url.getText().toString());
                contentValues.put(UserContract.NewUserInfo.USER_NOTE, contact_note.getText().toString());
                sqLiteDatabase.update(UserContract.NewUserInfo.TABLE_NAME, contentValues, UserContract.NewUserInfo.USER_ID + "=" + p, null);
                Toast.makeText(getBaseContext(), "Data Updated", Toast.LENGTH_LONG).show();
            }

        } else {
            String title = contact_title.getText().toString();
            String username = contact_username.getText().toString();
            String company = contact_company.getText().toString();
            String password = Encrypted_Data;
            String phone = contact_phone.getText().toString();
            String url = contact_url.getText().toString();
            String note = contact_note.getText().toString();
            userdbHelper = new UserdbHelper(context);
            if (title.equals("") && username.equals("")) {
                Toast.makeText(getBaseContext(), "Title or Username Required", Toast.LENGTH_LONG).show();
                empty = 0;
            } else {
                sqLiteDatabase = userdbHelper.getWritableDatabase();
                userdbHelper.addInformation(title, username, company, password, phone, url, note, sqLiteDatabase);
                Toast.makeText(getBaseContext(), "Data saved", Toast.LENGTH_LONG).show();
                userdbHelper.close();
            }
        }
        if (empty != 0) {
            if (accDetails.getCheck_edit() == 1) {
                Intent intent = new Intent(AddContent.this, AccDetails.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(AddContent.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }

    @Override
    public void onBackPressed(){
        if (accDetails.getCheck_edit()==1)
        {
            Intent intent=new Intent(AddContent.this,AccDetails.class);
            startActivity(intent);
            finish();
        }
        else {
            Intent intent = new Intent(AddContent.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }



    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(AddContent.this,MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
