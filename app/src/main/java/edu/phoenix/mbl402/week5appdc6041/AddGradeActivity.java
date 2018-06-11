package edu.phoenix.mbl402.week5appdc6041;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;

public class AddGradeActivity extends AppCompatActivity {

    private DatabaseHelper mDB;
    private Button addGradeButton;
    private EditText inputScore;
    private Spinner studentSelect, classSelect;
    private List<String> nameList, subjectList;
    private List<Student> studentList;
    private List<Subjects> classList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_grade);
        mDB = new DatabaseHelper(this);

        studentList = mDB.getAllRecords(DatabaseHelper.STUDENT_TABLE_NAME).getSsgList();
        nameList = new ArrayList<>();
        classList = mDB.getAllRecords(DatabaseHelper.CLASSES_TABLE_NAME).getSsgList();
        subjectList = new ArrayList<>();

        int i = 0;
        do {
            nameList.add(i,studentList.get(i).getFullName());
            subjectList.add(i, classList.get(i).getClassName());
            i++;
        } while (i < studentList.size());

        inputScore = (EditText) findViewById(R.id.gr_in_score);
        studentSelect = (Spinner) findViewById(R.id.gr_name_spin);
        classSelect = (Spinner) findViewById(R.id.gr_sub_spin);

        addGradeButton = (Button) findViewById(R.id.gr_gr_add);

        addStudentSelect();
        addClassSelect();
        addListenerOnButton();
    }

    public void addStudentSelect(){
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, nameList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        studentSelect.setAdapter(dataAdapter);
        studentSelect.setOnItemSelectedListener(new SpinnerActivity());
        }

    public void addClassSelect(){
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, subjectList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSelect.setAdapter(dataAdapter);
        classSelect.setOnItemSelectedListener(new SpinnerActivity());
    }

    public void addListenerOnButton(){
        addGradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int b = 0;
                int i = 0;
                do {
                    if (studentList.get(i).getFullName().equals(String.valueOf(studentSelect.getSelectedItem()))) {
                        b = i;
                        break;
                    }

                    i++;
                } while (i < studentList.size());

                int c = 0;
                i = 0;
                do {
                    if (studentList.get(i).getFullName().equals(String.valueOf(classSelect.getSelectedItem()))) {
                        c = i;
                        break;
                    }

                    i++;
                } while (i < studentList.size());

                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseHelper.GRADES_COL_4, inputScore.getText().toString());
                contentValues.put(DatabaseHelper.GRADES_COL_2, String.valueOf(studentList.get(b).getStudentID()));
                contentValues.put(DatabaseHelper.GRADES_COL_3, String.valueOf(classList.get(c).getClassID()));
                mDB.insertData(contentValues, DatabaseHelper.GRADES_TABLE_NAME);
                finish();
            }
        });
    }

    class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener{

        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
        }

        public void onNothingSelected(AdapterView<?> parent) {
        }
    }
}
