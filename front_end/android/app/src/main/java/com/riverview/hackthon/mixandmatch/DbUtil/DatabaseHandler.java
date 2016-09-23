package com.riverview.hackthon.mixandmatch.DbUtil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.riverview.hackthon.mixandmatch.model.BeanCategory;
import com.riverview.hackthon.mixandmatch.model.BeanItem;
import com.riverview.hackthon.mixandmatch.model.BeanUserData;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Rumesha on 23/09/2016.
 */

public class DatabaseHandler extends SQLiteOpenHelper{


    private static String DATABASE_NAME = "mixnmatch.db";

   // public final static String DATABASE_PATH = "/assets/mixnmatch.db";

    private static String DB_PATH = "/data/data/com.riverview.hackthon.mixandmatch/databases/";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase dataBase;

    private final Context dbContext;

    // Contacts table name
    private static final String TABLE_CATEGORY = "category";
    // Contacts table name
    private static final String TABLE_ITEM = "item";
    // Contacts table name
    private static final String TABLE_STYLE = "style";

    private static final String TABLE_USER = "user";


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public DatabaseHandler(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
        this.dbContext = context;
        // checking database and open it if exists
        if (checkDataBase()) {
            openDataBase();
        } else {
            try {
                this.getReadableDatabase();
                copyDataBase();
                this.close();
                openDataBase();

            } catch (IOException e) {
                throw new Error("Error copying database");
            }
            Toast.makeText(context, "Initial database is created", Toast.LENGTH_LONG).show();
        }
    }

    private void copyDataBase() throws IOException{
        InputStream myInput = dbContext.getAssets().open(DATABASE_NAME);
        String outFileName = DB_PATH + DATABASE_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }



    public void openDataBase() throws SQLException {
        String dbPath = DB_PATH + DATABASE_NAME;
        dataBase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public long addClothItem(BeanItem beanItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("category_id", beanItem.getCategoryId());
        values.put("image", beanItem.getImage());
        values.put("color", beanItem.getColor());
        values.put("brand", beanItem.getBrand());

      /*  values.put("category_id", 1);
        values.put("image", "aa");
        values.put("color","aa");
        values.put("brand", "aa");*/

        // Inserting Row
        long value = db.insert(TABLE_ITEM, null, values);
        db.close(); // Closing database connection

        return value;
    }


    public long addUserData(BeanUserData beanUser) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", beanUser.getName() );
        values.put("email", beanUser.getEmail());
        values.put("contact_number", beanUser.getCantact_number());
        values.put("city", beanUser.getCity());
        values.put("country", beanUser.getCountry());

        // Inserting Row
        long value = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection

        return value;
    }


    private BeanCategory getCategory() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CATEGORY, new String[] { "id",
                        "name", "single_piece`","order" },null,
                null, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        BeanCategory beanCategory = new BeanCategory();
        beanCategory.setId(cursor.getInt(0));
        beanCategory.setName(cursor.getString(1));
        beanCategory.setSinglePiece(cursor.getInt(2));
        beanCategory.setOrder(cursor.getInt(3));
        // return contact
        return beanCategory;
    }

    public  HashMap<String,Integer> getAllCategory() {
        SQLiteDatabase db = null;
        HashMap<String,Integer> categoryList = null;
        try {

            categoryList = new HashMap<>();
            // List<BeanCategory> categoryList = new ArrayList<>();
            // Select All Query
            String selectQuery = "SELECT id,name FROM " + TABLE_CATEGORY;

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    categoryList.put(cursor.getString(1),cursor.getInt(0));
                } while (cursor.moveToNext());
            }
            db.close();

        }catch (Exception ex){
            db.close();
        }


        // return contact list
        return categoryList;
    }

    public  ArrayList<BeanItem> getAllClothItem() {
        SQLiteDatabase db = null;
        ArrayList clothItemList = null;
        try {

            clothItemList = new ArrayList();
            // Select All Query
            String selectQuery = "SELECT * FROM " + TABLE_ITEM;

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    BeanItem clothItem = new BeanItem();
                    clothItem.setId(cursor.getInt(0));
                    clothItem.setCategoryId(cursor.getInt(1));
                    clothItem.setImage(cursor.getString(2));
                    clothItem.setColor(cursor.getString(3));
                    clothItem.setBrand(cursor.getString(4));
                    clothItemList.add(clothItem);
                } while (cursor.moveToNext());
            }
            db.close();

        }catch (Exception ex){
            db.close();
        }


        // return contact list
        return clothItemList;
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        boolean exist = false;
        try {
            String dbPath = DB_PATH + DATABASE_NAME ;
            checkDB = SQLiteDatabase.openDatabase(dbPath, null,
                    SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            Log.v("db log", "database does't exist");
        }

        if (checkDB != null) {
            exist = true;
            checkDB.close();
        }
        return exist;
    }

    @Override
    public synchronized void close() {

        if(dataBase != null)
            dataBase.close();

        super.close();

    }
}
