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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

public class SemesterInformation extends AppCompatActivity {
    DB_Connection db=new DB_Connection(this);
     Section section_obj=new Section();
    private DrawerLayout mdrawerlayout ;
    private ActionBarDrawerToggle mtoggle;

    /////
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mtoggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester_information);

        mdrawerlayout =(DrawerLayout)findViewById(R.id.drawerlayout);
        mtoggle=new ActionBarDrawerToggle(this,mdrawerlayout,R.string.open,R.string.close);
        mdrawerlayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Button addSectionButton= (Button) findViewById(R.id.AddSectionButton);
        final Button addCourseButton= (Button) findViewById(R.id.NextCourseButton);

        final TextView txtcourse=(TextView)findViewById(R.id.CourseName);
        final EditText Section_name=(EditText)findViewById(R.id.SectionName);

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

                section_obj.setLevel(level);
                ArrayList array_List= db.getcoursesbylevel(level);

                for(int j=0;j<array_List.size() && addCourseButton.callOnClick();j++){

                    section_obj.setCourse_id((String) array_List.get(j));

                 //one of them
                   // txtcourse.append((CharSequence) array_List.get(j));
                    txtcourse.setText(array_List.get(j).toString());

                    //String tx=array_List.get(j).toString();
                    section_obj.setSection_name(Section_name.getText().toString());

                  /*  if(addSectionButton.callOnClick()){
                        db.insertSection(section_obj);
                    }*/



                }



            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                levelSpinner.setSelection(0);
            }
        });


//time spinner
        final Spinner TimeSpinner=(Spinner)findViewById(R.id.TimeSpinner);

        final List<String> TimeArray=new ArrayList<String>();
        TimeArray.add("8:00 - 8:50");
        TimeArray.add("9:00 - 9:50");
        TimeArray.add("10:00 - 10:50");
        TimeArray.add("11:00 - 11:50");
        TimeArray.add("12:00 - 12:50");
        TimeArray.add("13:00 - 13:50");
        TimeArray.add("14:00 - 14:50");
        TimeArray.add("8:00 - 9:20");
        TimeArray.add("9:30 - 10:50");
        TimeArray.add("11:00 - 12:20");
        TimeArray.add("12:30 - 13:50");
        TimeArray.add("14:00 - 15:20");
        TimeArray.add("8:00 - 9:50");
        TimeArray.add("9:00 - 10:50");
        TimeArray.add("10:00 - 11:50");
        TimeArray.add("12:00 - 13:50");
        TimeArray.add("13:00 - 14:50");

        ArrayAdapter<String>TimeAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,TimeArray);

        TimeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        TimeSpinner.setAdapter(TimeAdapter);
        TimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String Section_time=TimeArray.get(i);
                section_obj.setSection_time(Section_time);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                TimeSpinner.setSelection(0);
            }
        });
//days Spinner

        final Spinner DaysSpinner=(Spinner)findViewById(R.id.DaysSpinner);

        final List<String> DaysArray=new ArrayList<String>();
        DaysArray.add("U, T, R");
        DaysArray.add("U, T");
        DaysArray.add("U, R");
        DaysArray.add("T, R");
        DaysArray.add("M, W");
        DaysArray.add("U");
        DaysArray.add("T");
        DaysArray.add("R");

        ArrayAdapter<String>DaysAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,DaysArray);


        DaysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        DaysSpinner.setAdapter(DaysAdapter);
        DaysSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String  Section_days=DaysArray.get(i);
                section_obj.setDays(Section_days);

                if(i==0){
                    section_obj.setDays_num(3);
                }else if(i==1 || i==2 || i==3 ||i==4){
                    section_obj.setDays_num(2);
                }else if(i==5 || i==6 || i==7){
                    section_obj.setDays_num(1);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                DaysSpinner.setSelection(0);
            }
        });


    }
    public void back_to_login(View v){
        ImageButton button2 =(ImageButton) v;
        startActivity(new Intent(SemesterInformation.this, LoginActivity.class));
    }

    public void add_section(View v){
        db.insertSection(section_obj);
    } public void nextCourse(View v){
        db.insertSection(section_obj);
    }
}
