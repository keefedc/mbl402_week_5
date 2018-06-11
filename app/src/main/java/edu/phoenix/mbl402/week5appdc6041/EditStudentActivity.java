package edu.phoenix.mbl402.week5appdc6041;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditStudentActivity extends AppCompatActivity {

    Button updateButton, deleteButton;
    EditText inputFirstName, inputLastName;
    DatabaseHelper mDB;
    Context context;

    int studentID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_student);

        mDB = new DatabaseHelper(this);
        context = this;

        inputFirstName = (EditText)findViewById(R.id.stu_edit_firstName);
        inputLastName = (EditText)findViewById(R.id.stu_edit_lastName);

        updateButton = (Button)findViewById(R.id.stu_update);
        deleteButton = (Button)findViewById(R.id.stu_delete);

        getIncomingIntent();

        SsgContainer<Student> container = mDB.fetch(studentID, DatabaseHelper.STUDENT_TABLE_NAME);

        inputFirstName.setText(container.getObject(0).getFirstName());
        inputLastName.setText(container.getObject(0).getLastName());

        deleteStudent();
        updateCustomer();
    }

    private void getIncomingIntent(){
        this.studentID = getIntent().getIntExtra("id", 0);
    }


    private void deleteStudent(){
        deleteButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v){

                        if(mDB.dataCheck(DatabaseHelper.GRADES_TABLE_NAME,
                                DatabaseHelper.GRADES_COL_2,studentID)){
                            Toast.makeText(context, "Can not delete due to dependencies.", Toast.LENGTH_LONG).show();

                        } else {
                            mDB.DeleteRecord(studentID, DatabaseHelper.STUDENT_TABLE_NAME);
                            finish();
                        }
                    }
                }
        );
    }

    private void updateCustomer(){
        updateButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        ContentValues content = new ContentValues();
                        content.put(DatabaseHelper.CLASSES_COL_1, studentID);
                        content.put(DatabaseHelper.STUDENT_COL_2, inputFirstName.getText().toString());
                        content.put(DatabaseHelper.STUDENT_COL_3, inputLastName.getText().toString());

                        mDB.updateRecord(content, DatabaseHelper.STUDENT_TABLE_NAME, DatabaseHelper.STUDENT_COL_1);

                        finish();

                    }
                }
        );
    }

}