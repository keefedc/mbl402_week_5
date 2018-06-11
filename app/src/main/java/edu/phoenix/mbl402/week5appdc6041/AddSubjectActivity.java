package edu.phoenix.mbl402.week5appdc6041;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddSubjectActivity extends AppCompatActivity {

    EditText inputClassName, inputClassNumber;
    Button addClassButton;
    DatabaseHelper mDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_subject);
        mDB = new DatabaseHelper(this);

        inputClassName = (EditText) findViewById(R.id.sub_in_subName);
        inputClassNumber = (EditText) findViewById(R.id.sub_in_id);
        addClassButton = (Button) findViewById(R.id.add_sub_add);
        addClass();
    }

    private void addClass() {
        addClassButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(DatabaseHelper.CLASSES_COL_2, inputClassName.getText().toString());
                        contentValues.put(DatabaseHelper.CLASSES_COL_1, inputClassNumber.getText().toString());
                        mDB.insertData(contentValues, DatabaseHelper.CLASSES_TABLE_NAME);
                        finish();
                    }
                }
        );
    }

}
