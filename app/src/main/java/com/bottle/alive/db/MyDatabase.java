package com.bottle.alive.db;

import android.content.Context;
import android.util.Log;

import com.bottle.alive.db.upgrade.Migration2;
import com.bottle.alive.gen.DaoMaster;
import com.bottle.alive.gen.DaoSession;

import org.greenrobot.greendao.database.Database;

public class MyDatabase {

    private String databaseName;
    private MyOpenHelper helper;
    private Database database;
    private DaoSession daoSession;

    private static MyDatabase instance;

    public static MyDatabase getInstance() {
        if(instance == null) {
            Log.e("MyDatabase", "init first!");
        }
        return instance;
    }

    public static void init(Context context, String databaseName) {
        instance = new MyDatabase(context, databaseName);
    }

    private MyDatabase(Context context, String databaseName){
        this.databaseName = databaseName;
        this.helper = new MyOpenHelper(context, this.databaseName);
        setUpgradeVersions(this.helper);
        this.database = helper.getWritableDb();
        this.daoSession = new DaoMaster(helper.getWritableDb()).newSession();
    }

    public DaoSession getDaoSession(){
        return daoSession;
    }

    public Database getDatabase(){
        return this.database;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    private void setUpgradeVersions(MyOpenHelper helper) {
        helper.setMigrations(new Migration2());
    }
}
