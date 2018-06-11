package edu.phoenix.mbl402.week5appdc6041;

import android.content.ContentValues;

public class Subjects {

    private int classID;
    private String className;
    private ContentValues contentValues;

    public Subjects(int inClassID, String inClassName) {
        this.classID = inClassID;
        this.className = inClassName;

        this.contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.CLASSES_COL_1, this.classID);
        contentValues.put(DatabaseHelper.CLASSES_COL_2, this.className);
    }

    public int getClassID() {
        return classID;
    }

    public String getClassName() {
        return className;
    }

    public ContentValues getContentValues() {
        return contentValues;
    }

    public void setClassName(String className) {
        this.className = className;
        this.contentValues.remove(DatabaseHelper.CLASSES_COL_2);
        this.contentValues.put(DatabaseHelper.CLASSES_COL_2, className);
    }
}
