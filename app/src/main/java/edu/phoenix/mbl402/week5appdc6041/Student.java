package edu.phoenix.mbl402.week5appdc6041;

import android.content.ContentValues;

public class Student {

    private int studentID;
    private String firstName, lastName;
    private ContentValues contentValues;

    public Student(){

    }

    public Student(int studentID, String firstName, String lastName) {

        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;

        this.contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.STUDENT_COL_1, this.studentID);
        contentValues.put(DatabaseHelper.STUDENT_COL_2, this.firstName);
        contentValues.put(DatabaseHelper.STUDENT_COL_3, this.lastName);

    }

    public int getStudentID() {
        return studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() { return this.firstName + " " + this.lastName;}

    public ContentValues getContentValues(){
        return this.contentValues;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        this.contentValues.remove(DatabaseHelper.STUDENT_COL_2);
        this.contentValues.put(DatabaseHelper.STUDENT_COL_2, firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        this.contentValues.remove(DatabaseHelper.STUDENT_COL_3);
        this.contentValues.put(DatabaseHelper.STUDENT_COL_3, lastName);
    }
}
