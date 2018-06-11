package edu.phoenix.mbl402.week5appdc6041;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseHelper mDB = new DatabaseHelper(this);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbarid);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new FragmentStudent(), "Students");
        adapter.AddFragment(new FragmentClasses(), "Classes");
        adapter.AddFragment(new FragmentGrades(),"Grades");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        AutoPopulate autoPop = new AutoPopulate(mDB);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DatabaseHelper mDB = new DatabaseHelper(this);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbarid);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new FragmentStudent(), "Students");
        adapter.AddFragment(new FragmentClasses(), "Classes");
        adapter.AddFragment(new FragmentGrades(),"Grades");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
