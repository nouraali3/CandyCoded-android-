package com.example.user.candycoded2;

import android.provider.BaseColumns;

/**
 * Created by user on 10/02/2018.
 * it is created to hold string representation of : tables names, columns, creagtion, and deletion
 */

public class CandyContract
{
    public static final String db_name = "  candycoded.db";
    public static final int db_version = 1 ;

    public static final String sql_greate_entries=
            "CREATE TABLE "+CandyEntry.table_name+"("+
                    CandyEntry._ID+" INTEGER PRIMARY KEY, "+
                    CandyEntry.column_name_name+" TEXT, "+
                    CandyEntry.column_name_price+" TEXT, "+
                    CandyEntry.column_name_desc+" TEXT,"+
                    CandyEntry.column_name_image+" TEXT)";

    public static final String sql_delete_entries = "DROP TABLE IF EXISTS "+CandyEntry.table_name;


    public static class CandyEntry implements BaseColumns //implements BaseColumns so that our class
                                                          // inherit primary key field that will be used later
    {
        public static final String table_name = "candy";
        public static final String column_name_name = "name";
        public static final String column_name_price = "price";
        public static final String column_name_desc = "description";
        public static final String column_name_image = "image";

    }
}
