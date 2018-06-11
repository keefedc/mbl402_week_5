package edu.phoenix.mbl402.week5appdc6041;

import android.content.ContentValues;
import android.provider.ContactsContract;

public class Grades {

    private int gradeID, studentID, classID;
    private String grade;
    private double score;
    private ContentValues contentValues;


    public Grades(int inGID, int inSID, int inCID, double score){
        this.gradeID = inGID;
        this.studentID = inSID;
        this.classID = inCID;
        this.score = score;
        this.grade = scoreToGrade(score);

        this.contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.GRADES_COL_1, this.gradeID);
        contentValues.put(DatabaseHelper.GRADES_COL_2, this.studentID);
        contentValues.put(DatabaseHelper.GRADES_COL_3, this.classID);
        contentValues.put(DatabaseHelper.GRADES_COL_4, this.score);
        contentValues.put(DatabaseHelper.GRADES_COL_5, this.grade);

    }

    public int getGradeID() {
        return this.gradeID;
    }

    public int getStudentID() {
        return this.studentID;
    }

    public int getClassID() {
        return this.classID;
    }

    public double getScore() { return score; }

    public String getGrade() {
        return this.grade;
    }

    public ContentValues getContentValues() {
        return contentValues;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
        this.contentValues.remove(DatabaseHelper.GRADES_COL_2);
        this.contentValues.put(DatabaseHelper.GRADES_COL_2, studentID);
    }

    public void setClassID(int classID) {
        this.classID = classID;
        this.contentValues.remove(DatabaseHelper.GRADES_COL_3);
        this.contentValues.put(DatabaseHelper.GRADES_COL_3, classID);
    }

    public void setGrade(double score) {
        this.score = score;
        this.grade = scoreToGrade(score);
        this.contentValues.remove(DatabaseHelper.GRADES_COL_4);
        this.contentValues.put(DatabaseHelper.GRADES_COL_4, grade);
        this.contentValues.remove(DatabaseHelper.GRADES_COL_5);
        this.contentValues.put(DatabaseHelper.GRADES_COL_5, scoreToGrade(score));
    }

    public String scoreToGrade(double score) {
        if(score < 60){
            return "F";
        } else if (score < 70) {
            return "D";
        } else if (score < 80) {
            return "C";
        } else if (score < 90) {
            return "B";
        } else {
            return "A";
        }
    }
}
