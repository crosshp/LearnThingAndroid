package com.learn_thing.learnthingandroid.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.learn_thing.learnthingandroid.Entity.Methodic;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 10.03.2016.
 */
public class MethodicDB {
    protected static final String TAG = "DataAdapter";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataHelper mDbHelper;

    public MethodicDB(Context context) {
        this.mContext = context;

        mDbHelper = new DataHelper(mContext);
        try {
            createDatabase();
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public interface TABLES {
        String Methodic = "Methodic";
    }

    public interface MethodicColumns {
        String id = "_id";
        String name = "name";
        String description = "description";
        String img = "img";
        String interactive = "interactive";
    }


    public MethodicDB createDatabase() throws SQLException {
        try {
            mDbHelper.createDataBase();
        } catch (IOException mIOException) {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public MethodicDB open() throws SQLException {
        mDbHelper.openDataBase();
        mDbHelper.close();
        mDb = mDbHelper.getReadableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public List<Methodic> getAllMethodics() {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(TABLES.Methodic);
        Cursor cursor = qb.query(mDb, null, null, null, null, null, null);
        List<Methodic> list = new ArrayList<>();
        cursor.moveToFirst();
        int i = 1;
        while (!cursor.isAfterLast()) {
            Methodic methodic = new Methodic();
            methodic.setId(cursor.getInt(cursor.getColumnIndex(MethodicColumns.id)));
            methodic.setName(cursor.getString(cursor.getColumnIndex(MethodicColumns.name)));
            methodic.setDescription(cursor.getString(cursor.getColumnIndex(MethodicColumns.description)));
            methodic.setImg(i % 5 + 1);
            i++;
            if (cursor.getInt(cursor.getColumnIndex(MethodicColumns.interactive)) != 0) {
                methodic.setIsInteractive(true);
            } else {
                methodic.setIsInteractive(false);
            }
            list.add(methodic);
            cursor.moveToNext();
        }
        return list;
    }

    public Methodic getMethodicById(int id) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(TABLES.Methodic);
        Cursor cursor = mDb.rawQuery("select * from " + TABLES.Methodic + " where " + MethodicColumns.id + "=" + id, null);
        cursor.moveToFirst();
        Methodic methodic = new Methodic();
        methodic.setId(cursor.getInt(cursor.getColumnIndex(MethodicColumns.id)));
        methodic.setName(cursor.getString(cursor.getColumnIndex(MethodicColumns.name)));
        methodic.setDescription(cursor.getString(cursor.getColumnIndex(MethodicColumns.description)));
        methodic.setImg((id + 1) % 5 + 1);
        if (cursor.getInt(cursor.getColumnIndex(MethodicColumns.interactive)) != 0) {
            methodic.setIsInteractive(true);
        } else {
            methodic.setIsInteractive(false);
        }
        return methodic;
    }
}
