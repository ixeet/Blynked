package com.example.admin.blynked;

/**
 * Created by Admin on 29-07-2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by PRIYANKA GUPTA on 3/21/2015.
 */

public class SqliteHelper1 extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "histr";
    private static final int DATABASE_VERSION = 1;
    public SqliteHelper1(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE histr " +
                "(id TEXT PRIMARY KEY,time1 LONG,flag INTEGER,d1 TEXT,rcp TEXT,desti TEXT,selmsg1 TEXT,seltime1 TEXT,crnt TEXT,fs INTEGER)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXISTS submit");
        //deleteAllData();
        onCreate(db);
    }

    public boolean saveUser (String id,long time1,int flag,String d1,String rcp,String desti,String selmsg1,String seltime1,String crnt,int fs)
    {
        Cursor cursor = getUser(id);

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("time1", time1);
        contentValues.put("flag", flag);
        contentValues.put("d1", d1);
        contentValues.put("rcp", rcp);
        contentValues.put("desti", desti);
        contentValues.put("selmsg1", selmsg1);
        contentValues.put("seltime1", seltime1);
        contentValues.put("crnt", crnt);
        contentValues.put("fs", fs);




        long result;
        if (cursor.getCount() == 0) { // Record does not exist
            contentValues.put("id", id);
            result = db.insert("histr", null, contentValues);
        } else { // Record exists
            result = db.update("histr", contentValues, "id=?", new String[] { id });
        }
        cursor.close();
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }
    public void update(String id, int flag ) {
        SQLiteDatabase db= this.getWritableDatabase();
        String strFilter = "id=" + id;
        ContentValues args = new ContentValues();
        args.put("flag", flag);
        db.update("histr", args, strFilter, null);
    }
    public void update1(String id, long time1 ) {
        SQLiteDatabase db= this.getWritableDatabase();
        String strFilter = "id=" + id;
        ContentValues args = new ContentValues();
        args.put("time1", time1);
        db.update("histr", args, strFilter, null);
    }
    public ArrayList<HashMap<String, Object>> getAllUsers() {
        ArrayList<HashMap<String, Object>> wordList;
        wordList = new ArrayList<HashMap<String, Object>>();
        String selectQuery = "SELECT  * FROM histr";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToNext()) {
            do {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("id", cursor.getString(0));
                //   map.put("button", cursor.getInt(1));
                map.put("time1",cursor.getLong(0));
                map.put("flag",cursor.getInt(0));
                map.put("d1",cursor.getString(1));
                map.put("rcp",cursor.getString(2));
                map.put("desti",cursor.getString(3));
                map.put("selmsg1",cursor.getString(4));
                map.put("seltime1",cursor.getString(5));
                map.put("crnt",cursor.getString(6));
                map.put("fs",cursor.getInt(1));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        return wordList;
    }
    public Cursor getUser(String id){

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM histr WHERE id=?";

        return db.rawQuery(sql, new String[] { id } );
    }
    void deleteAllData()
    {
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete("flg", null, null);

    }

    public void delete_byID(String id){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete("histr", id+"="+id, null);
       // Toast.makeText(this, "view clicked: ", Toast.LENGTH_SHORT).show();
    }

    /**
     * Compose JSON out of SQLite records
     * @return
     */
  /*  public String composeJSONfromSQLite(){
        ArrayList<HashMap<String, Object>> wordList;
        wordList = new ArrayList<HashMap<String, Object>>();
        String selectQuery = "SELECT  * FROM submit";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, Object> map = new HashMap<String, Object>();
                 map.put("quest_id", cursor.getString(0));
            //   map.put("button", cursor.getInt(1));
                 map.put("selection",cursor.getString(2));
                 map.put("ccorrect",cursor.getString(3));
                 map.put("exam_id",cursor.getString(4));
                map.put("user_id",cursor.getString(5));
                 wordList.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        Gson gson = new GsonBuilder().create();
        //Use GSON to serialize Array List to JSON
        return gson.toJson(wordList);
    }*/

    /**
     * Get Sync status of SQLite
     * @return
     */
    public String getSyncStatus(){
        String msg = null;
        if(this.dbSyncCount() == 0){
            msg = "SQLite and Remote MySQL DBs are in Sync!";
        }else{
            msg = "DB Sync needed\n";
        }
        return msg;
    }

    /**
     * Get SQLite records that are yet to be Synced
     * @return
     */
    public int dbSyncCount(){
        int count = 0;
        String selectQuery = "SELECT * FROM histr";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        count = cursor.getCount();
        database.close();
        return count;
    }



}

