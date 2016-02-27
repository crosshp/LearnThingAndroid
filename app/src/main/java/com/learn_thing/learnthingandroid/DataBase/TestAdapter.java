package com.learn_thing.learnthingandroid.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.learn_thing.learnthingandroid.Entity.TestQuestion;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestAdapter {
    protected static final String TAG = "DataAdapter";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataHelper mDbHelper;

    public TestAdapter(Context context) {
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
        String TEST = "Test";
    }

    public interface TestColumns {
        String id = "_id";
        String question = "question";
        String answer1 = "answer_1";
        String answer2 = "answer_2";
        String answer3 = "answer_3";
        String answer4 = "answer_4";
        String isOpenQuestion = "is_open_question";
    }


    public TestAdapter createDatabase() throws SQLException {
        try {
            mDbHelper.createDataBase();
        } catch (IOException mIOException) {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public TestAdapter open() throws SQLException {
        mDbHelper.openDataBase();
        mDbHelper.close();
        mDb = mDbHelper.getReadableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public List<TestQuestion> getTestTable() {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(TABLES.TEST);
        Cursor cursor = qb.query(mDb, null, null, null, null, null, null);

        List<TestQuestion> list = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            TestQuestion testQuestion = new TestQuestion();
            testQuestion.setQuestion(cursor.getString(cursor.getColumnIndex(TestColumns.question)));
            testQuestion.setAnswer1(cursor.getString(cursor.getColumnIndex(TestColumns.answer1)));
            testQuestion.setAnswer2(cursor.getString(cursor.getColumnIndex(TestColumns.answer2)));
            testQuestion.setAnswer3(cursor.getString(cursor.getColumnIndex(TestColumns.answer3)));
            testQuestion.setAnswer4(cursor.getString(cursor.getColumnIndex(TestColumns.answer4)));
            Integer isOpenQuestion = cursor.getInt(cursor.getColumnIndex(TestColumns.isOpenQuestion));
            if (isOpenQuestion != 0) {
                testQuestion.setIsOpenQuestion(true);
            } else {
                testQuestion.setIsOpenQuestion(false);
            }
            list.add(testQuestion);
            cursor.moveToNext();
        }

        return list;
    }
}
