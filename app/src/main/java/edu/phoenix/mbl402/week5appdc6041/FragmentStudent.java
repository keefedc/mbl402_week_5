package edu.phoenix.mbl402.week5appdc6041;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentStudent extends Fragment implements View.OnClickListener {

    private Button stuAddButton;
    private View view;
    private RecyclerView.Adapter rvAdapter;

    public FragmentStudent() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.student_fragment, null);
        DatabaseHelper mDB = new DatabaseHelper(container.getContext());

        rvAdapter = new StudentViewAdapter(container.getContext(),mDB.getAllRecords(DatabaseHelper.STUDENT_TABLE_NAME));
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.student_fragment_rv);
        rv.setLayoutManager(new LinearLayoutManager(container.getContext()));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.addItemDecoration(new DividerItemDecoration(container.getContext(), LinearLayoutManager.VERTICAL));
        rv.setAdapter(rvAdapter);

        stuAddButton = (Button) view.findViewById(R.id.stu_add);
        stuAddButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent addStudent = new Intent(getContext(), AddStudentActivity.class);
        startActivity(addStudent);
    }
}
