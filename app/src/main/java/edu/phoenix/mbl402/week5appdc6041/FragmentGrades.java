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

import java.util.ArrayList;
import java.util.List;

public class FragmentGrades extends Fragment implements View.OnClickListener {

    private Button grAddButton;
    private View view;
    private RecyclerView.Adapter rvAdapter;

    public FragmentGrades() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.grades_fragment, null);
        DatabaseHelper mDB = new DatabaseHelper(container.getContext());

        List<SsgContainer> loadData = new ArrayList<>();

        loadData.add(0, mDB.getAllRecords(DatabaseHelper.STUDENT_TABLE_NAME));
        loadData.add(1, mDB.getAllRecords(DatabaseHelper.CLASSES_TABLE_NAME));
        loadData.add(2, mDB.getAllRecords(DatabaseHelper.GRADES_TABLE_NAME));

        rvAdapter = new GradeViewAdapter(container.getContext(), loadData);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.gr_fragment_rv);
        rv.setLayoutManager(new LinearLayoutManager(container.getContext()));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.addItemDecoration(new DividerItemDecoration(container.getContext(), LinearLayoutManager.VERTICAL));
        rv.setAdapter(rvAdapter);

        grAddButton = (Button) view.findViewById(R.id.gr_add);
        grAddButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent addGrade = new Intent(getContext(), AddGradeActivity.class);
        startActivity(addGrade);
    }
}