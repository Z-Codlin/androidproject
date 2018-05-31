package com.example.dbutil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqliteDBHelper extends SQLiteOpenHelper {

    private static final String TAG = "SqliteDBHelper";
    public static final int VERSION = 1;

    //必须要有构造函数
    public SqliteDBHelper(Context context, String name, CursorFactory factory,
                       int version) {
        super(context, name, factory, version);
    }

    // 当第一次创建数据库的时候，调用该方法
    public void onCreate(SQLiteDatabase db) {
        String playerScoreTable = "create table score_table(id int primary key autoincrement,name varchar(50),score int)";
//输出创建数据库的日志信息
        Log.i(TAG, "create Database------------->");
//execSQL函数用于执行SQL语句
        db.execSQL(playerScoreTable);
        String gameStepsTable = "create table steps_table(id int primary key autoincrement,name varchar(50),start_pos int, end_pos int, step_order int, finish_round int)";
//输出创建数据库的日志信息
        Log.i(TAG, "create Database------------->");
//execSQL函数用于执行SQL语句
        db.execSQL(gameStepsTable);
    }

    //当更新数据库的时候执行该方法
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//输出更新数据库的日志信息
        Log.i(TAG, "update Database------------->");
    }
}