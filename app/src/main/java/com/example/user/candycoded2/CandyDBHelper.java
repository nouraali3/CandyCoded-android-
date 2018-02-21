package com.example.user.candycoded2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 10/02/2018.
 */


public class CandyDBHelper extends SQLiteOpenHelper
{
    public CandyDBHelper(Context context)
        {super(context, CandyContract.db_name,null, CandyContract.db_version);}

    @Override
    public void onCreate(SQLiteDatabase db)
        {db.execSQL(CandyContract.sql_greate_entries);}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL(CandyContract.sql_delete_entries);
            onCreate(db);
        }
}
