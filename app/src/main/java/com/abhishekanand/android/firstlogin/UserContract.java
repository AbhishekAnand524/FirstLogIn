package com.abhishekanand.android.firstlogin;

/**
 * Created by Jerry on 9/20/2015.
 */
public class UserContract {

    public static abstract class NewUserInfo{

        public static final String USER_ID="_user_id";
        public static final String USER_CATEGORY="user_category";
        public static final String TABLE_NAME ="user_info5";


        public static final String USER_TITLE="_user_title";
        public static final String USER_USERNAME="user_username";
        public static final String USER_COMPANY="user_company";
        public static final String USER_PASSWORD="user_password";
        public static final String USER_PHONE="user_phone";
        public static final String USER_URL="user_url";
        public static final String USER_NOTE="user_note";

        public static final String TABLE2="login_password";
        public static final String COL1="data_pass";
        public static final String COL2="id";


        public static final String TABLE3="alt_login_password";
        public static final String COL11="data_pass_username";
        public static final String COL12="data_pass_password";
        public static final String COL13="id1";

    }
}

