package com.bottle.alive.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.bottle.alive.db.upgrade.Migration;
import com.bottle.alive.gen.DaoMaster;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.StandardDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据表升级，配合app/build.gradle 中的 greendao#schemaVersion使用，升级数据库中的数据表(如增加column等操作)
 * 建议：1.开始设计时要尽量考虑未来的需求，避免改动；
 *       2.如果要修改表，尽量新增column，而不要删除column，也不要修改column的类别
 *       3.没必要迁移整张表的数据，考虑使用SQL 新增column或者修改column
 *       4.版本名称从1开始，每次升级增加1，并且记录每个版本的变化
 */
public class MyOpenHelper extends DaoMaster.OpenHelper {

    private List<Migration> migrations;

    public MyOpenHelper(Context context, String name) {
        super(context, name);
    }

    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        if(migrations == null) {
            // 如果没有提供升级的对象，那么删除所有的表，然后再重新建表
            DaoMaster.dropAllTables( new StandardDatabase(db), true);
            onCreate(db);
        } else {
            for (Migration migration : migrations) {
                migration.migrate(db, oldVersion);
            }
        }
    }

    /**
     * 按照从低到高的版本顺序排列
     * @param migrations 每升级一个版本就新建一个Migration类
     */
    public void setMigrations(Migration... migrations) {
        if(migrations == null || migrations.length == 0) {
            return;
        }
        if(this.migrations == null) {
            this.migrations = new ArrayList<>();
        }
        this.migrations.clear();
        for(Migration migration : migrations) {
            this.migrations.add(migration);
        }
    }

}
