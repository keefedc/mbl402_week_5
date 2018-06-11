package edu.phoenix.mbl402.week5appdc6041;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "school.db";

    public static final String STUDENT_TABLE_NAME = "students";
    public static final String STUDENT_COL_1 = "id";
    public static final String STUDENT_COL_2 = "f_name";
    public static final String STUDENT_COL_3 = "l_name";

    public static final String CLASSES_TABLE_NAME = "classes";
    public static final String CLASSES_COL_1 = "id";
    public static final String CLASSES_COL_2 = "c_name";

    public static final String GRADES_TABLE_NAME = "grades";
    public static final String GRADES_COL_1 = "id";
    public static final String GRADES_COL_2 = "s_id";
    public static final String GRADES_COL_3 = "c_id";
    public static final String GRADES_COL_4 = "grade";
    public static final String GRADES_COL_5 = "letter_grade";

    //constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + STUDENT_TABLE_NAME + " (" +
                STUDENT_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                STUDENT_COL_2 + " TEXT, " +
                STUDENT_COL_3 + " TEXT)"
        );

        db.execSQL("CREATE TABLE " + CLASSES_TABLE_NAME + " (" +
                CLASSES_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CLASSES_COL_2 + " TEXT)"
        );

        db.execSQL("CREATE TABLE " + GRADES_TABLE_NAME + " (" +
                GRADES_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                GRADES_COL_2 + " INTEGER, " +
                GRADES_COL_3 + " INTEGER, " +
                GRADES_COL_4 + " NUMERIC, " +
                GRADES_COL_5 + " TEXT, " +
                "FOREIGN KEY(" + GRADES_COL_2 + ") REFERENCES " + STUDENT_TABLE_NAME + "(" + STUDENT_COL_1 + "), " +
                "FOREIGN KEY(" + GRADES_COL_3 + ") REFERENCES " + CLASSES_TABLE_NAME + "(" + CLASSES_COL_1 + ")" +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CLASSES_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + GRADES_TABLE_NAME);
        onCreate(db);
    }

    public boolean dataCheck(String serTable, String serColumn, int serID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor results = db.rawQuery("select * from " + serTable + " where " +
                        serColumn + " = ?", new String[]{String.valueOf(serID)});

        if(results.getCount() <= 0){
            results.close();
            db.close();
            return false;
        }
        results.close();
        db.close();
        return true;
    }

    public void insertData(ContentValues content, String inTable) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(inTable, null, content);

        db.close();
    }

    public void updateRecord(ContentValues content, String inTable, String inColumn) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(inTable, content, inColumn + " = ?", new String[]{content.get("id").toString()});
        db.close();
    }

    public SsgContainer getAllRecords(String inTable) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor results = db.rawQuery("select * from " + inTable, null);

        if (results.getCount() == 0) {
            //error message here
            return null;
        }

        switch (inTable) {
            case STUDENT_TABLE_NAME:
                SsgContainer<Student> studentList = new SsgContainer(Student.class);
                while (results.moveToNext()) {
                    Student student = new Student(results.getInt(0),
                            results.getString(1),
                            results.getString(2)
                    );
                    studentList.addObject(student);
                }
                results.close();
                db.close();
                return studentList;
            case CLASSES_TABLE_NAME:
                SsgContainer<Subjects> classList = new SsgContainer(Subjects.class);
                while (results.moveToNext()) {
                    Subjects subjects = new Subjects(results.getInt(0),
                            results.getString(1)
                    );
                    classList.addObject(subjects);
                }
                results.close();
                db.close();
                return classList;
            case GRADES_TABLE_NAME:
                SsgContainer<Grades> gradesList = new SsgContainer(Grades.class);
                while (results.moveToNext()) {
                    Grades grades = new Grades(results.getInt(0),
                            results.getInt(1),
                            results.getInt(2),
                            results.getDouble(3)
                    );
                    gradesList.addObject(grades);
                }
                results.close();
                db.close();
                return gradesList;
        }
        results.close();
        db.close();
        return null;
    }

    public SsgContainer fetch(int id, String inTable) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor results = db.rawQuery("select * from " + inTable + " where id = ?", new String[]{String.valueOf(id)});

        if (results.getCount() == 0) {
            //error message here
            return null;
        }

        switch (inTable) {
            case STUDENT_TABLE_NAME:
                SsgContainer<Student> studentList = new SsgContainer(Student.class);
                while (results.moveToNext()) {
                    Student student = new Student(results.getInt(0),
                            results.getString(1),
                            results.getString(2)
                    );
                    studentList.addObject(student);
                }
                results.close();
                db.close();
                return studentList;
            case CLASSES_TABLE_NAME:
                SsgContainer<Subjects> classList = new SsgContainer(Subjects.class);
                while (results.moveToNext()) {
                    Subjects subjects = new Subjects(results.getInt(0),
                            results.getString(1)
                    );
                    classList.addObject(subjects);
                }
                results.close();
                db.close();
                return classList;
            case GRADES_TABLE_NAME:
                SsgContainer<Grades> gradesList = new SsgContainer(Grades.class);
                while (results.moveToNext()) {
                    Grades grades = new Grades(results.getInt(0),
                            results.getInt(1),
                            results.getInt(2),
                            results.getDouble(3)
                    );
                    gradesList.addObject(grades);
                }
                results.close();
                db.close();
                return gradesList;
        }
        results.close();
        db.close();
        return null;
    }

    public void DeleteRecord(int id, String inTable) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(inTable, "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

}
