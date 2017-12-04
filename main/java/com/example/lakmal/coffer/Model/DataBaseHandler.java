package com.example.lakmal.coffer.Model;

//
//public class DataBaseHandler extends SQLiteOpenHelper {
//
//    private static final int DATABASE_VERSION = 1;
//
//    private static final String DATABASE_NAME = "coffer.db";
//
//    private final String TABLE_USER = "user";
//    private final String USER_ID = "user_id";
//    private final String USER_NAME = "user_name";
//
//
//    private final String TABLE_BANK = "bank";
//    private final String BANK_ID    = "bank_id";
//    private final String BANK_NAME  = "bank_name";
//    private final String LOGO_URL   = "logourl";
//    private final String WEBSITE    = "website";
//    private final String CONTACT    = "contact";
//
//    private final String BANK_FOREIGN = "f_key";
//
//    private final String TABLE_CARD = "card";
//    private final String CARD_ID = "card_id";
//    private final String CARD_NAME = "card_name";
//    private final String CARD_FOREIGN = "f_key";
//
//    private final String TABLE_INTERMEDIATE = "INTERMEDIATE";
//    private final String INTEM_USER_ID = "USER";
//    private final String INTEM_BANK_ID = "BANK";
//    private final String INTEM_CARD_ID = "CARD";
//
//    public DataBaseHandler(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//
//        //decalre strings to create tables
//        String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_USER + " ( " +
//                USER_ID + " varchar(255) NOT NULL , " +
//                USER_NAME + " varchar(255) NOT NULL , " +
//                "  PRIMARY KEY (" + USER_ID + ") )";
//
//        String CREATE_BANK_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_BANK + " ( " +
//                BANK_ID +   " varchar(255) NOT NULL , " +
//                BANK_NAME + " varchar(255) NOT NULL , " +
//                LOGO_URL +  " varchar(255) NOT NULL , " +
//                WEBSITE +   " varchar(255) NOT NULL , " +
//                CONTACT +   " varchar(255) NOT NULL , " +
//                "PRIMARY KEY (" + BANK_ID + ") );";
//
//        String CREATE_CARD_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CARD + " ( " +
//                CARD_ID + " varchar(255) NOT NULL, " +
//                CARD_NAME + " varchar(255) NOT NULL, " +
//                "  PRIMARY KEY(" + CARD_ID + "))";
//
////        String CREATE_INTERM_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_INTERMEDIATE + " ( " +
////                "FOREIGN KEY(" + INTEM_USER_ID + ")REFERENCES" + TABLE_USER + "(" + USER_ID + ")," +
////                "FOREIGN KEY(" + INTEM_BANK_ID + ")REFERENCES" + TABLE_BANK + "(" + BANK_ID + ")," +
////                "FOREIGN KEY(" + INTEM_CARD_ID + ")REFERENCES" + TABLE_CARD + "(" + CARD_ID + ") )";
//
//        //create tables
//        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
//        sqLiteDatabase.execSQL(CREATE_BANK_TABLE);
//        sqLiteDatabase.execSQL(CREATE_CARD_TABLE);
//      // sqLiteDatabase.execSQL(CREATE_INTERM_TABLE);
//    }
//
//    //this function is called iff there is a new database version from the server is received
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//
//        //drop tables if the tables exists
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BANK);
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CARD);
//      //  sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_INTERMEDIATE);
//
//        // Create tables again
//        onCreate(sqLiteDatabase);
//    }
//
//
//    //Called when the database needs to be downgraded. This is strictly similar to onUpgrade(SQLiteDatabase, int, int)
//    // method, but is called whenever current version is newer than requested one.
//    @Override
//    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
//        //drop tables if the tables exists
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BANK);
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CARD);
//       // sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_INTERMEDIATE);
//
//        // Create tables again
//        onCreate(sqLiteDatabase);
//    }
//
//    //reset db categories
//    public void restCategory() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BANK);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARD);
//      //  db.execSQL("DROP TABLE IF EXISTS " + TABLE_INTERMEDIATE);
//
//        // Create tables again
//        onCreate(db);
//    }
//
//    public long addUser(UserItem userItem) {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(USER_ID, userItem.getUserID());
//        values.put(USER_NAME, userItem.getUserName());
//        long inserted = db.insertWithOnConflict(TABLE_USER, null, values, SQLiteDatabase.CONFLICT_IGNORE);
//        db.close();
//        return inserted;
//    }
//
//    //must be called only in the Welcome class
//    public long addBank(BankItem bankItem) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        values.put(BANK_NAME, bankItem.getBankName());
//        values.put(WEBSITE, bankItem.getWebSite());
//        values.put(LOGO_URL, bankItem.getLogoURL());
//        values.put(BANK_ID, bankItem.getBankCode());
//        values.put(CONTACT, bankItem.getContact());
//
//        long inserted = db.insertWithOnConflict(TABLE_BANK, null, values, SQLiteDatabase.CONFLICT_IGNORE);
//        db.close();
//        return inserted;
//    }
//
//    //must be called only in the Welcome class
//    public long addCard(CardItem cardItem) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(CARD_ID, cardItem.getCardId());
//        values.put(CARD_NAME, cardItem.getCardName());
//        values.put(CARD_FOREIGN, cardItem.getCardId());
//        long inserted = db.insertWithOnConflict(TABLE_CARD, null, values, SQLiteDatabase.CONFLICT_IGNORE);
//        db.close();
//        return inserted;
//    }
//
//
//    /*
//        public long addIntermediate(UserModel categoryModel) {
//            SQLiteDatabase db = this.getWritableDatabase();
//            ContentValues values = new ContentValues();
//            values.put(USER_ID, categoryModel.getCatId());
//            values.put(USER_NAME, categoryModel.getCatName());
//            long inserted = db.insertWithOnConflict(TABLE_USER, null, values, SQLiteDatabase.CONFLICT_IGNORE);
//            db.close();
//            return inserted;
//        }
//
//
//        public void updateEmail(EmailContact emailContact) {
//            SQLiteDatabase db = this.getWritableDatabase();
//            ContentValues values = new ContentValues();
//            values.put(EMAIL_INVITED, 1);
//            db.update(TABLE_INVITE_EMAIL, values, EMAIL_ADDRESS + " = '" + emailContact.getEmailAdress() + "'", null);
//        }
//
//        public List<EmailContact> getAllEmails() {
//            List<EmailContact> emails = new ArrayList<>();
//            String selectQuery = "SELECT  * FROM " + TABLE_INVITE_EMAIL;
//            SQLiteDatabase db = this.getWritableDatabase();
//            Cursor cursor = db.rawQuery(selectQuery, null);
//            if (cursor.moveToFirst()) {
//                do {
//                    EmailContact emailContact = new EmailContact();
//                    emailContact.setEmailAdress(cursor.getString(cursor.getColumnIndex(EMAIL_ADDRESS)));
//                    emailContact.setImageUri(cursor.getString(cursor.getColumnIndex(EMAIL_IMAGEURI)));
//                    emailContact.setEmailType(cursor.getString(cursor.getColumnIndex(EMAIL_EMAILTYPE)));
//                    emailContact.setIsInvited(cursor.getInt(cursor.getColumnIndex(EMAIL_INVITED)));
//                    emails.add(emailContact);
//                } while (cursor.moveToNext());
//            }
//            return emails;
//        }
//    */
//    //return as useritem objects need to getuserid, username
//    public List<UserItem> getAllUsers() {
//        List<UserItem> maninCatList = new ArrayList<>();
//        String selectQuery = "SELECT  * FROM " + TABLE_USER;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//                UserItem USER = new UserItem();
//                USER.setUserID(cursor.getString(cursor.getColumnIndex(USER_ID)));
//                USER.setUserName(cursor.getString(cursor.getColumnIndex(USER_NAME)));
//                maninCatList.add(USER);
//            } while (cursor.moveToNext());
//        }
//        return maninCatList;
//    }
//
//    /*
//        public List<BANKegoryModel> getBANK(int USER) {
//            List<BANKegoryModel> BANKList = new ArrayList<BANKegoryModel>();
//            String selectQuery = "SELECT  * FROM " + TABLE_BANK + " WHERE " + BANK_USER + " = " + USER;
//
//            SQLiteDatabase db = this.getWritableDatabase();
//            Cursor cursor = db.rawQuery(selectQuery, null);
//            if (cursor.moveToFirst()) {
//                do {
//                    BANKegoryModel BANK = new BANKegoryModel();
//                    BANK.setCatId(cursor.getInt(cursor.getColumnIndex(BANK_ID)));
//                    BANK.setCatName(cursor.getString(cursor.getColumnIndex(BANK_NAMES)));
//                    BANK.setUSER(cursor.getInt(cursor.getColumnIndex(BANK_USER)));
//                    BANK.setCatSinhalaName(cursor.getString(cursor.getColumnIndex(BANK_SINHALANAME)));
//                    BANK.setCatTamilName(cursor.getString(cursor.getColumnIndex(BANK_TAMILNAME)));
//                    BANKList.add(BANK);
//                } while (cursor.moveToNext());
//            }
//            return BANKList;
//        }
//    */
//    public List<SelectedBanksAndCardsModel> getSelectedCards(List<String> bankIds) {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        //get the number of rows in the table TABLE_CARD
//        long cnt = DatabaseUtils.queryNumEntries(db, TABLE_CARD);
//        int numberOfTuples = (int) cnt;
//
//
//        List<SelectedBanksAndCardsModel> TotalData = new ArrayList<SelectedBanksAndCardsModel>();
//
//        for (int j = 0; j < bankIds.size(); j++) {
//            ArrayList<String> cardNames = new ArrayList<>();
//            String selectQuery = "SELECT" + CARD_ID + "," + CARD_NAME + "FROM " + TABLE_CARD + "WHERE" + CARD_FOREIGN + "=" + bankIds.get(j);
//            Cursor cursor = db.rawQuery(selectQuery, null);
//
//            SelectedBanksAndCardsModel oneData = new SelectedBanksAndCardsModel();
//            for (int i = 0; i < numberOfTuples; i++) {
//
//                if (cursor.moveToFirst()) {
//                    do {
//                        oneData.setBankName(cursor.getString(cursor.getColumnIndex(CARD_FOREIGN)));
//                        cardNames.add(cursor.getString(cursor.getColumnIndex(CARD_NAME)));
//
//                    } while (cursor.moveToNext());
//                }
//            }
//            oneData.setCardNames(cardNames);
//            TotalData.add(oneData);
//        }
//        return TotalData;
//    }
//
//
//    public List<String> getBankNames(List<String> bankIds) {
//
//        List<String> bankNames = new ArrayList<>();
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        for (int j = 0; j < bankIds.size(); j++) {
//            String selectQuery = "SELECT" + BANK_NAME + "FROM " + TABLE_BANK + " WHERE " + BANK_ID + " = " + bankIds.get(j);
//            Cursor cursor = db.rawQuery(selectQuery, null);
//
//            if (cursor.moveToFirst()) {
//                do {
//                    bankNames.add(cursor.getString(cursor.getColumnIndex(BANK_NAME)));
//                }
//                while (cursor.moveToNext());
//            }
//        }
//        return bankNames;
//    }
//
//}


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "coffer.db";

    private final String TABLE_USER = "user";
    private final String USER_ID = "user_id";
    private final String USER_NAME = "user_name";


    private final String TABLE_BANK = "bank";
    private final String BANK_ID    = "bank_id";
    private final String BANK_NAME  = "bank_name";
    private final String LOGO_URL   = "logo_url";
    private final String WEBSITE    = "website";
    private final String CONTACT    = "contact";

    private final String BANK_FOREIGN = "f_key";

    private final String TABLE_CARD = "card";
    private final String CARD_ID = "card_id";
    private final String CARD_NAME = "card_name";
    private final String CARD_FOREIGN = "f_key";

    private final String TABLE_INTERMEDIATE = "INTERMEDIATE";
    private final String INTEM_USER_ID = "USER";
    private final String INTEM_BANK_ID = "BANK";
    private final String INTEM_CARD_ID = "CARD";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //decalre strings to create tables
        String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_USER + " ( " +
                USER_ID + " varchar(255) NOT NULL , " +
                USER_NAME + " varchar(255) NOT NULL , " +
                "  PRIMARY KEY (" + USER_ID + ") )";

        String CREATE_BANK_TABLE = " CREATE TABLE IF NOT EXISTS " + TABLE_BANK + " ( " +
                BANK_ID +   " varchar(255) NOT NULL , " +
                BANK_NAME + " varchar(255) NOT NULL , " +
                LOGO_URL +  " varchar(255) NOT NULL , " +
                WEBSITE +   " varchar(255) NOT NULL , " +
                CONTACT +   " varchar(255) NOT NULL , " +
                "  PRIMARY KEY (" + BANK_ID + ") );";

        String CREATE_CARD_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CARD + " ( " +
                CARD_ID + " varchar(255) NOT NULL, " +
                CARD_NAME + " varchar(255) NOT NULL, " +
                "  PRIMARY KEY(" + CARD_ID + "))";

//        String CREATE_INTERM_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_INTERMEDIATE + " ( " +
//                "FOREIGN KEY(" + INTEM_USER_ID + ")REFERENCES" + TABLE_USER + "(" + USER_ID + ")," +
//                "FOREIGN KEY(" + INTEM_BANK_ID + ")REFERENCES" + TABLE_BANK + "(" + BANK_ID + ")," +
//                "FOREIGN KEY(" + INTEM_CARD_ID + ")REFERENCES" + TABLE_CARD + "(" + CARD_ID + ") )";

        //create tables
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(CREATE_BANK_TABLE);
        sqLiteDatabase.execSQL(CREATE_CARD_TABLE);
        // sqLiteDatabase.execSQL(CREATE_INTERM_TABLE);
    }

    //this function is called iff there is a new database version from the server is received

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        //drop tables if the tables exists
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BANK);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CARD);
        //  sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_INTERMEDIATE);

        // Create tables again
        onCreate(sqLiteDatabase);
    }


    //Called when the database needs to be downgraded. This is strictly similar to onUpgrade(SQLiteDatabase, int, int)
    // method, but is called whenever current version is newer than requested one.
    @Override
    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //drop tables if the tables exists
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BANK);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CARD);
        // sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_INTERMEDIATE);

        // Create tables again
        onCreate(sqLiteDatabase);
    }

    //reset db categories
    public void restCategory() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BANK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARD);
        //  db.execSQL("DROP TABLE IF EXISTS " + TABLE_INTERMEDIATE);

        // Create tables again
        onCreate(db);
    }

    public long addUser(UserItem userItem) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_ID, userItem.getUserID());
        values.put(USER_NAME, userItem.getUserName());
        long inserted = db.insertWithOnConflict(TABLE_USER, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        db.close();
        return inserted;
    }

    public List<UserItem> getAllUsers() {
        List<UserItem> maninCatList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                UserItem USER = new UserItem();
                USER.setUserID(cursor.getString(cursor.getColumnIndex(USER_ID)));
                USER.setUserName(cursor.getString(cursor.getColumnIndex(USER_NAME)));
                maninCatList.add(USER);
            } while (cursor.moveToNext());
        }
        return maninCatList;
    }

    //must be called only in the Welcome class
    public long addBank(BankItem bankItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(BANK_NAME, bankItem.getBankName());
        values.put(WEBSITE, bankItem.getWebSite());
        values.put(LOGO_URL, bankItem.getLogoURL());
        values.put(BANK_ID, bankItem.getBankCode());
        values.put(CONTACT, bankItem.getContact());

        long inserted = db.insertWithOnConflict(TABLE_BANK, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        db.close();
        return inserted;
    }


    /*
        public List<BANKegoryModel> getBANK(int USER) {
            List<BANKegoryModel> BANKList = new ArrayList<BANKegoryModel>();
            String selectQuery = "SELECT  * FROM " + TABLE_BANK + " WHERE " + BANK_USER + " = " + USER;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    BANKegoryModel BANK = new BANKegoryModel();
                    BANK.setCatId(cursor.getInt(cursor.getColumnIndex(BANK_ID)));
                    BANK.setCatName(cursor.getString(cursor.getColumnIndex(BANK_NAMES)));
                    BANK.setUSER(cursor.getInt(cursor.getColumnIndex(BANK_USER)));
                    BANK.setCatSinhalaName(cursor.getString(cursor.getColumnIndex(BANK_SINHALANAME)));
                    BANK.setCatTamilName(cursor.getString(cursor.getColumnIndex(BANK_TAMILNAME)));
                    BANKList.add(BANK);
                } while (cursor.moveToNext());
            }
            return BANKList;
        }
/
*/
    public List<String> getBankNames(List<String> bankIds) {

        List<String> bankNames = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        for (int j = 0; j < bankIds.size(); j++) {
            String selectQuery = " SELECT " + BANK_NAME + " FROM " + TABLE_BANK + " WHERE " + BANK_ID + " = " + bankIds.get(j);
            Cursor cursor = db.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                do {
                    bankNames.add(cursor.getString(cursor.getColumnIndex(BANK_NAME)));
                }
                while (cursor.moveToNext());
            }
        }
        return bankNames;
    }

}