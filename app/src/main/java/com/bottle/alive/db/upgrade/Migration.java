package com.bottle.alive.db.upgrade;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

/**
 * 通过比较versionCode与oldVersion，如果 oldVersion < versionCode，则需要升级。
 */
public abstract class Migration {

    /**
     * 这个版本号可以理解是将要升级的版本，如当前将要发布的版本是2，上一个版本是1，则versionCode=2;
     * 以此类推，versionCode=2,3...(假定versionCode每次升级都自增1)
     */
    protected int versionCode;

    public Migration() {
    }

    public void migrate(SQLiteDatabase db, int oldVersion) {
        if(oldVersion < versionCode) {
            upgrade(db);
        }
    }

    protected String addColumn(String tableName, String columnName, String type, boolean nullAble, String defaultVal) {
        StringBuilder addColumn = new StringBuilder();
        addColumn.append("ALTER TABLE ").append(tableName)
                .append(" ADD COLUMN ").append(columnName).append(" ").append(type);
        if(!nullAble) {
            addColumn.append(" NOT NULL DEFAULT(").append(defaultVal).append(");");
        } else {
            addColumn.append(";");
        }
        String sql = addColumn.toString();
        Log.d("MyOpenHelper", sql);
        return sql;
    }

    /**
     *
     * @param tableName
     * @param columnName
     * @param type
     * @param defaultVal 要不要考虑类型？（待验证），比如说数值类型直接赋值就可以了，而字符串类型则需要，分号：'default-value'
     */
    protected void alterColumnNullAble(String tableName, String columnName, String type, boolean nullAble, String defaultVal) {
        // ALTER TABLE 表 ALTER COLUMN [字段名] 字段类型 NOT NULL
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ALTER TABLE ").append(tableName)
                .append(" ALTER COLUMN ").append(columnName).append(" ").append(type);
        if(nullAble) {
            stringBuilder.append(" NULL;");
        } else {
            stringBuilder.append(" NOT NULL DEFAULT(").append(defaultVal).append(");");
        }

    }

    protected boolean hasColumn(SQLiteDatabase db, String tableName, String column) {
        if (TextUtils.isEmpty(tableName) || TextUtils.isEmpty(column)) {
            return false;
        }
        Cursor cursor = null;
        try {
            cursor = db.query(tableName, null, null,
                    null, null, null, null);
            if (null != cursor && cursor.getColumnIndex(column) != -1) {
                return true;
            }
        } finally {
            if (null != cursor) {
                cursor.close();
            }
        }
        return false;
    }

    protected abstract void upgrade(SQLiteDatabase db);
}
