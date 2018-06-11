package edu.phoenix.mbl402.week5appdc6041;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddStudentActivity extends AppCompatActivity {

    private EditText inputFirstName, inputLastName;
    private Button addStudentButton;
    private DatabaseHelper mDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);
        mDB = new DatabaseHelper(this);

        inputFirstName = (EditText) findViewById(R.id.stu_in_firstName);
        inputLastName = (EditText) findViewById(R.id.stu_in_lastName);
        addStudentButton = (Button) findViewById(R.id.add_stu_add);
        addStudent();
    }

    private void addStudent() {
        addStudentButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(DatabaseHelper.STUDENT_COL_2, inputFirstName.getText().toString());
                        contentValues.put(DatabaseHelper.STUDENT_COL_3, inputLastName.getText().toString());
                        mDB.insertData(contentValues, DatabaseHelper.STUDENT_TABLE_NAME);
                        finish();
                    }
                }
        );
    }

}
