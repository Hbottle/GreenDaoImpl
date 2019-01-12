package com.bottle.alive.db.upgrade;

import android.database.sqlite.SQLiteDatabase;

public class Migration2 extends Migration {

    public Migration2() {
        this.versionCode = 2;
    }

    @Override
    public void upgrade(SQLiteDatabase db) {
         // db.execSQL(addColumn(UserDao.TABLENAME, UserDao.Properties.CreateTime.columnName, "long", true, "0"));
    }
}

