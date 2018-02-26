package com.etts.etts;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddSchedule extends AppCompatActivity {

    DB_Connection db=new DB_Connection(this);
    private ArrayList c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);



        TextView course=(TextView)findViewById(R.id.CourseName);

        //level spinner
        final Spinner levelSpinner=(Spinner)findViewById(R.id.LevelSpinner);

        final List<String> levelNameArray=new ArrayList<String>();
        levelNameArray.add("Level 3");
        levelNameArray.add("Level 4");
        levelNameArray.add("Level 5");
        levelNameArray.add("Level 6");
        levelNameArray.add("Level 7");
        levelNameArray.add("Level 8");
        levelNameArray.add("Level 9");
        levelNameArray.add("Level 10");

        ArrayAdapter<String> levelAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,levelNameArray);
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        levelSpinner.setAdapter(levelAdapter);
        levelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int level = Integer.parseInt(levelNameArray.get(i));
                 setC(db.getOtherCorses(level));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                levelSpinner.setSelection(0);
            }
        });
        ///courses spinner
        final Spinner CoursesSpinner=(Spinner)findViewById(R.id.LevelSpinner);

        final ArrayList<String> Courses_Array=new ArrayList<String>();
        ArrayList<String> Courses_Array2=getC();
        for(int j=0;j<Courses_Array2.size();j++){
            Courses_Array.add(Courses_Array2.get(j));
        }

        ArrayAdapter<String> CoursesAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Courses_Array);
        CoursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        CoursesSpinner.setAdapter(CoursesAdapter);
        CoursesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

              //  int level = Integer.parseInt(CoursesArray.get(i));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                CoursesSpinner.setSelection(0);

            }
        });
        //Section Spinner
        final Spinner SectionSpinner=(Spinner)findViewById(R.id.Section_spinner);
        final ArrayList<String> SectionsArray=new ArrayList<>();
        ArrayList <String> section=db.SectionSpinner(course.toString());
        for(int j=0;j<section.size();j++){
            SectionsArray.add(section.get(j));
        }
        ArrayAdapter<String> SectionAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,SectionsArray);

        SectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        SectionSpinner.setAdapter(SectionAdapter);
        SectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String Section_name=SectionsArray.get(i);
                //section_obj.setSection_time(Section_time);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                SectionSpinner.setSelection(0);
            }
        });

    }
    public ArrayList getC() {
        return c;
    }

    public void setC(ArrayList c) {
        this.c = c;
    }

    public void back_to_login(View v){
        ImageButton button2 =(ImageButton) v;
        startActivity(new Intent(AddSchedule.this, LoginActivity.class));
    }
}
