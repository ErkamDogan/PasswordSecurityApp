package com.example.secapp.Engine;

import android.provider.BaseColumns;

public class TablesInfo {

    public static final class PasswordTableEntry implements BaseColumns {
        public static final String TABLE_NAME = "passwords";

        public static final String Id = "id";
        public static final String Name = "app_name";
        public static final String Password = "password";
        public static final String CreateDate = "create_date";
    }

}
