package com.example.imcapp.db;

import android.provider.BaseColumns;

public final class ImcContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private ImcContract() {}

    public static abstract class UserEntry implements BaseColumns {
        public final static String _ID = BaseColumns._ID;
        public final static String TABLE_NAME = "users";
        public final static String COLUMN_NIT = "nit";
        public final static String COLUMN_NAME = "name";
        public final static String COLUMN_LAST_NAME = "lastname";
        public final static String COLUMN_AGE = "age";
        public final static String COLUMN_PHONE= "phone";
        public final static String COLUMN_GENDER = "gender";
        /**
         * Possible values for the gender
         */
        public final static int GENDER_UNKNOWN = 0;
        public final static int GENDER_MALE = 1;
        public final static int GENDER_FEMALE = 2;
    }

    public static abstract class IMCEntry implements BaseColumns {
        public final static String TABLE_NAME = "imc_data";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_DATE = "date";
        public final static String COLUMN_WEIGHT = "weight";
        public final static String COLUMN_HEIGHT = "height";
        public final static  String COLUMN_RESULT = "result";
        public final static String COLUMN_USER_ID = "user_id";
    }
}
