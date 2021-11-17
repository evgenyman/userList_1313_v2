package com.example.userList_1313_v2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserBaseHalper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "userBase_1313.db";
    public UserBaseHalper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+UserDBSchema.UserTable.NAME+" (" +
                "_id integer primary key autoincrement, " +
                UserDBSchema.Cols.UUID+", " +
                UserDBSchema.Cols.USERNAME+", " +
                UserDBSchema.Cols.USERLASTNAME+", " +
                UserDBSchema.Cols.PHONE+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Этот метод вызывается тогла, когда мы меняем первоначальную структуру таблицы
        // (добавляем или удаляем столбцы). Но когда мы не хотим потерять ранее заполненное
        // содержимое.
    }
}
