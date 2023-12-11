package com.intreve.sqlite_2021_q8;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    Context context;

    private static String DATABASE_NAME="student.db";
    private static int DATABASE_VERSION=1;
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS student (roll_no INTEGER PRIMARY KEY, name VARCHAR, gender CHAR)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS student");
        onCreate(db);
    }

    // SELECT operation
    public Cursor getAllStudents() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM student" , null);
    }



    //Inserting student records
    public void insertRecords(){
        SQLiteDatabase db=this.getWritableDatabase();

        String toastText="Inserted entries:\n";

        int[] student_roll_no={1,2,3};
        String[] student_name={"Steve", "Jennie", "Mike"};
        char[] student_gender={'M','F','M'};

        for(int i=0; i<student_roll_no.length; i++){

            String studentName=student_name[i];
            int studentRollNo=student_roll_no[i];
            String studentGender=String.valueOf(student_gender[i]);

            String query="INSERT INTO student(roll_no, name, gender) VALUES "+"("+studentRollNo+","+studentName+","+studentGender+")";
            db.execSQL(query);
            toastText+="Roll no. "+ student_roll_no[i]+ ", Name: "+student_name[i]+ ", Gender: "+student_gender[i]+"\n";



        }

        Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();

    }


    //Record deletion
    public void deleteRecords(String nameToDelete) {
        SQLiteDatabase db = this.getWritableDatabase();
       String query="DELETE FROM students WHERE roll_no = 101";
       db.execSQL(query);
    }

}
