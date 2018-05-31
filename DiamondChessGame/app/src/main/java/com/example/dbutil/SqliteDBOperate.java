package com.example.dbutil;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dbutil.SqliteDBHelper;

public class SqliteDBOperate {
    private SqliteDBHelper mSqliteDBHelper;
    private SQLiteDatabase mWritableDB;
    private SQLiteDatabase mReadableDB;
    //ArrayList<ContentValues> mSharedElements = new ArrayList<ContentValues>();

    public SqliteDBOperate(Context context, String dbName) {
        mSqliteDBHelper = new SqliteDBHelper(context, dbName, null, 1);
        mWritableDB = mSqliteDBHelper.getWritableDatabase();//得到一个可写数据库
        mReadableDB = mSqliteDBHelper.getReadableDatabase();//得到一个可读数据库
    }

    public void insertScoreItem(String name, int score){
        //生成ContentValues对象 //key:列名，value:想插入的值
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("score", score);
        mWritableDB.insert("score_table", null, cv);
    }
    public void insertStepsItem(String name, int startpos, int endpos, int steporder, int finish){
        //生成ContentValues对象 //key:列名，value:想插入的值
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("start_pos", startpos);
        cv.put("end_pos", endpos);
        cv.put("step_order", steporder);
        cv.put("finish_round", finish);
        mWritableDB.insert("steps_table", null, cv);
    }
    public ArrayList<ContentValues> selectTopOrderScoreItem() {
        ArrayList<ContentValues> result = new ArrayList<ContentValues>();
        Cursor cursor = mReadableDB.query("score_table",
                new String[]{"name","score"},
                null, null, null, null, "score", null);
        while(cursor.moveToNext()){
            ContentValues cv = new ContentValues();
            cv.put("name", cursor.getString(cursor.getColumnIndex("name")));
            cv.put("score", cursor.getInt(cursor.getColumnIndex("score")));
            result.add(cv);
            if(result.size() >= 10){
                break;
            }
        }
        if(result.isEmpty()) {
            return null;
        }
        return result;
    }
    public ArrayList<ContentValues> selectRoundStepsItemByName(String name) {
        ArrayList<ContentValues> result = new ArrayList<ContentValues>();
        Cursor cursor = mReadableDB.query("steps_table",
                new String[]{"name","start_pos", "end_pos", "step_order"},
                "name=?", new String[]{name}, null, null, "step_order", null);
        while(cursor.moveToNext()){
            ContentValues cv = new ContentValues();
            cv.put("name", cursor.getString(cursor.getColumnIndex("name")));
            cv.put("start_pos", cursor.getInt(cursor.getColumnIndex("start_pos")));
            cv.put("end_pos", cursor.getInt(cursor.getColumnIndex("end_pos")));
            cv.put("step_order", cursor.getInt(cursor.getColumnIndex("step_order")));
            result.add(cv);
        }
        if(result.isEmpty()) {
            return null;
        }
        return result;
    }
    public ArrayList<ContentValues> findNotFinishRoundByFlag(int finish) {
        ArrayList<ContentValues> result = new ArrayList<ContentValues>();
        Cursor cursor = mReadableDB.query("steps_table",
                new String[]{"name","start_pos", "end_pos", "step_order"},
                "finish_round=?", new Integer[]{finish}, null, null, "step_order", null);
        while(cursor.moveToNext()){
            ContentValues cv = new ContentValues();
            cv.put("name", cursor.getString(cursor.getColumnIndex("name")));
            cv.put("start_pos", cursor.getInt(cursor.getColumnIndex("start_pos")));
            cv.put("end_pos", cursor.getInt(cursor.getColumnIndex("end_pos")));
            cv.put("step_order", cursor.getInt(cursor.getColumnIndex("step_order")));
            result.add(cv);
        }
        if(result.isEmpty()) {
            return null;
        }
        return result;
    }
}