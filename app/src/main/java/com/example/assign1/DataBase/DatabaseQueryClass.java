package com.example.assign1.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

import com.example.assign1.Model.Record;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseQueryClass {

    private Context context;

    public DatabaseQueryClass(Context context) {
        this.context = context;
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public long insertRecord(Record record) {

        long id = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(Config.COLUMN_ID, record.getId());
        contentValues.put(Config.COLUMN_WEIGHT, record.getWeight());
        contentValues.put(Config.COLUMN_AGE, record.getAge());
        contentValues.put(Config.COLUMN_DATE, record.getDate());
        contentValues.put(Config.COLUMN_FLAG, record.getFlag());

        try {
            id = sqLiteDatabase.insertOrThrow(Config.TABLE_RECORD, null, contentValues);
        } catch (SQLiteException e) {
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return id;
    }


    public List<Record> getAllStudent(String flag) {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            cursor = sqLiteDatabase.query(Config.TABLE_RECORD, null, null, null, null, null, null, null);


            String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s,%s FROM %s WHERE flag= %s ",
                    Config.COLUMN_ID_PRIMARY, Config.COLUMN_WEIGHT,
                    Config.COLUMN_AGE, Config.COLUMN_FLAG, Config.COLUMN_DATE,
                    Config.TABLE_RECORD, flag);
            cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);


            if (cursor != null)
                if (cursor.moveToFirst()) {
                    List<Record> studentList = new ArrayList<>();
                    do {
                        long id = cursor.getLong(cursor.getColumnIndex(Config.COLUMN_ID_PRIMARY));
                        String _id = cursor.getString(cursor.getColumnIndex(Config.COLUMN_ID));
                        String weight = cursor.getString(cursor.getColumnIndex(Config.COLUMN_WEIGHT));
                        String age = cursor.getString(cursor.getColumnIndex(Config.COLUMN_AGE));
                        String _flag = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FLAG));
                        String date = cursor.getString(cursor.getColumnIndex(Config.COLUMN_DATE));

                        studentList.add(new Record(id, _id, weight, age, _flag, date));
                    } while (cursor.moveToNext());

                    return studentList;
                }
        } catch (Exception e) {
            Logger.d("Exception: " + e.getMessage());
            //Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }

   /* public Student getStudentByRegNum(long registrationNum) {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        Student student = null;
        try {

            cursor = sqLiteDatabase.query(Config.TABLE_RECORD, null,
                    Config.COLUMN_AGE + " = ? ", new String[]{String.valueOf(registrationNum)},
                    null, null, null);

            *
             // If you want to execute raw query then uncomment below 2 lines. And comment out above sqLiteDatabase.query() method.
             String SELECT_QUERY = String.format("SELECT * FROM %s WHERE %s = %s", Config.TABLE_RECORD, Config.COLUMN_AGE, String.valueOf(registrationNum));
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);


            if (cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ID_PRIMARY));
                String name = cursor.getString(cursor.getColumnIndex(Config.COLUMN_WEIGHT));
                long registrationNumber = cursor.getLong(cursor.getColumnIndex(Config.COLUMN_AGE));
                String phone = cursor.getString(cursor.getColumnIndex(Config.COLUMN_DATE));
                String email = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FLAG));

                student = new Student(id, name, registrationNumber, phone, email);
            }
        } catch (Exception e) {
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return student;
    }

    public long updateStudentInfo(Student student) {

        long rowCount = 0;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_WEIGHT, student.getName());
        contentValues.put(Config.COLUMN_AGE, student.getRegistrationNumber());
        contentValues.put(Config.COLUMN_DATE, student.getPhoneNumber());
        contentValues.put(Config.COLUMN_FLAG, student.getEmail());

        try {
            rowCount = sqLiteDatabase.update(Config.TABLE_RECORD, contentValues,
                    Config.COLUMN_ID_PRIMARY + " = ? ",
                    new String[]{String.valueOf(student.getId())});
        } catch (SQLiteException e) {
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return rowCount;
    }

    public long deleteStudentByRegNum(long registrationNum) {
        long deletedRowCount = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            deletedRowCount = sqLiteDatabase.delete(Config.TABLE_RECORD,
                    Config.COLUMN_AGE + " = ? ",
                    new String[]{String.valueOf(registrationNum)});
        } catch (SQLiteException e) {
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return deletedRowCount;
    }

    public boolean deleteAllStudents() {
        boolean deleteStatus = false;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            //for "1" delete() method returns number of deleted rows
            //if you don't want row count just use delete(TABLE_NAME, null, null)
            sqLiteDatabase.delete(Config.TABLE_RECORD, null, null);

            long count = DatabaseUtils.queryNumEntries(sqLiteDatabase, Config.TABLE_RECORD);

            if (count == 0)
                deleteStatus = true;

        } catch (SQLiteException e) {
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return deleteStatus;
    }*/

}