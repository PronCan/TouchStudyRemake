package org.iptime.proncan.touchstudyremake;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by 호창 on 2017-07-03.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "questions";
    public static final int DATABASE_VERISION = 1;

    static ArrayList<Question> questionList;
    SQLiteDatabase database;
    ContentValues values;
    Cursor cursor;

    static boolean checkUpdate = false;

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        checkUpdate = true;
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        checkUpdate = true;

        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "question TEXT, " +
                "answer1 TEXT, " +
                "answer2 TEXT, " +
                "answer3 TEXT, " +
                "answer4 TEXT, " +
                "answerNum INTEGER, " +
                "comment TEXT," +
                "checkAnswer INTEGER )");
        // 순서대로 : 문제번호, 문제, 1번답, 2번답, 3번답, 4번답, 답 번호, 해설, 틀림여부
    }

    public void addQuestion(Question q) {
        database = this.getWritableDatabase();
        values = new ContentValues();

        values.put("question", q.question);
        values.put("answer1", q.answer1);
        values.put("answer2", q.answer2);
        values.put("answer3", q.answer3);
        values.put("answer4", q.answer4);
        values.put("answerNum", q.answerNum);
        values.put("comment", q.comment);
        values.put("checkAnswer", q.checkAnswer);

        database.insert(TABLE_NAME, null, values);
        database.close();
    }

    public ArrayList<Question> getMistakeQuestions() {
        database = this.getReadableDatabase();
        cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME + " where checkAnswer = 1", null);
        questionList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                Question q = new Question(
                        cursor.getString(cursor.getColumnIndex("question")),
                        cursor.getString(cursor.getColumnIndex("answer1")),
                        cursor.getString(cursor.getColumnIndex("answer2")),
                        cursor.getString(cursor.getColumnIndex("answer3")),
                        cursor.getString(cursor.getColumnIndex("answer4")),
                        cursor.getInt(cursor.getColumnIndex("answerNum")),
                        cursor.getString(cursor.getColumnIndex("comment"))
                );

                questionList.add(q);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return questionList;
    }

    public ArrayList<Question> getAllQuestions() {
        database = this.getReadableDatabase();
        cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        questionList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                Question q = new Question(
                        cursor.getString(cursor.getColumnIndex("question")),
                        cursor.getString(cursor.getColumnIndex("answer1")),
                        cursor.getString(cursor.getColumnIndex("answer2")),
                        cursor.getString(cursor.getColumnIndex("answer3")),
                        cursor.getString(cursor.getColumnIndex("answer4")),
                        cursor.getInt(cursor.getColumnIndex("answerNum")),
                        cursor.getString(cursor.getColumnIndex("comment"))
                );

                questionList.add(q);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return questionList;
    }

    public void changeDb(int id) {
        database.execSQL("UPDATE " + TABLE_NAME + " SET checkAnswer=1 WHERE id=" + id + ";");
        //database.execSQL("UPDATE "+TABLE_NAME+" SET checkAnswer=1 WHERE id = 30;");
    }

    public DBHelper(Context context) {
        super(context, "MyDatabase.db", null, DATABASE_VERISION);
    }
}