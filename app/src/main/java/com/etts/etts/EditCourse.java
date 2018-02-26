package com.etts.etts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EditCourse extends AppCompatActivity {
    DB_Connection db=new DB_Connection(this);
    final Section section_obj=new Section();
    ArrayAdapter<String>adapter;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);

        TextView course_name=(TextView)findViewById(R.id.View_Course_name);
        section_obj.setCourse_id(course_name.toString());

        // search
        SearchView search=(SearchView)findViewById(R.id.Search_id);
        ListView listView=(ListView)findViewById(R.id.SeachListView);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,db.getAllCorses());
        listView.setAdapter(adapter);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(db.getAllCorses().contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(EditCourse.this, "No Match found", Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //    adapter.getFilter().filter(newText);
                return false;
            }
        });



        //Section Spinner
        final Spinner SectionSpinner=(Spinner)findViewById(R.id.Section_spinner);
        final ArrayList<String> SectionsArray=new ArrayList<>();
        ArrayList <String> section=db.SectionSpinner(course_name.toString());
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
                section_obj.setSection_name(Section_name);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                SectionSpinner.setSelection(0);
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

        ArrayAdapter<String> TimeAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,TimeArray);

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
    }public void clickDrop(View view){

        db.DropSection(section_obj);
    }public void clickEdit(View view){
        db.UpdateSecion(section_obj);
    }
}
