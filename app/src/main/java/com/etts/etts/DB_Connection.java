package com.etts.etts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

//import com.sun.org.apache.bcel.internal.generic.Select;

public class DB_Connection extends SQLiteOpenHelper{
    SQLiteDatabase db;
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "etts_db";
    final Section section_obj=new Section();


    public DB_Connection(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create user_account table
        String CREATE_usersAccount_TABLE = "CREATE TABLE IF NOT EXISTS UsersAccount ( Id INTEGER PRIMARY KEY, Email TEXT, Password TEXT)";
        //create courses table
        String CREATE_Courses_TABLE = "CREATE TABLE IF NOT EXISTS Courses ( Course_id TEXT PRIMARY KEY, Level INTEGER, Section_num INTEGER ) " ;
        inset_Courses_2();
        //create section table
        String CREATE_Section_TABLE = "CREATE TABLE IF NOT EXISTS Section ( Section_name TEXT ,Course_id TEXT, Days TEXT, number_of_days INTEGER , Times TEXT PRIMARY KEY(Section_name,Course_id) FOREIGN KEY (Course_id) REFERENCES Courses(Course_id)  ) " ;
        //create student_schedule
        String CREATE_Student_Schedule_TABLE="CREATE TABLE IF NOT EXISTS Student_Schedule ( Student_Id INTEGER PRIMARY KEY, Level INTEGER, Courses txt, Num_of_Courses INTEGER,FOREIGN KEY (Student_Id) REFERENCES UsersAccount(Id),FOREIGN KEY (Level) REFERENCES UsersAccount(Id),  ";


        //execute statments for tables....
        db.execSQL(CREATE_usersAccount_TABLE);
        db.execSQL(CREATE_Courses_TABLE);
        db.execSQL(CREATE_Section_TABLE);
        db.execSQL(CREATE_Student_Schedule_TABLE);
    }
    //method to insert account
    public void insertaccount(users_account u){
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Id",u.getId());
        values.put("Email",u.getEmail());
        values.put("Password",u.getPassword());
        db.insert("UsersAccount",null,values);
    }
    //method to search password
    public String searchpass(String Id){
        db=this.getWritableDatabase();
        String query="select Id , Password from UsersAccount";
        Cursor cursor=db.rawQuery(query,null);
        String a, b;
        b="not found";
        if(cursor.moveToFirst()){
            do {
                a=cursor.getString(0);
                if(a.equals(Id)){
                    b=cursor.getString(1);
                    break;
                }

            }while(cursor.moveToNext());

        }
        return b;
    }
    ///////////////////////////////
    // method for insert courses
    public void insertCourses(String name, int level){

        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Course_id",name);
        values.put("Level",level);
        db.insert("Courses",null,values);
    }
    /////////////////////////////
    //method for write all courses in course table by using insertCourses method
    public void inset_Courses_2(){
        insertCourses("CPIT-221",3);
        insertCourses("CPIT-201",3);

        insertCourses("CPIT-220",4);

        insertCourses("CPIT-210",5);

        insertCourses("CPIT-260",6);
        insertCourses("CPIT-240",6);
        insertCourses("CPIT-250",6);
        insertCourses("CPIT-285",6);

        insertCourses("CPIT-370",7);
        insertCourses("CPIT-280",7);
        insertCourses("CPIT-251",7);

        insertCourses("CPIT-425",8);
        insertCourses("CPIT-252",8);
        insertCourses("CPIT-305",8);
        insertCourses("CPIT-380",8);
        insertCourses("CPIT-330",8);

        insertCourses("CPIT-345",9);
        insertCourses("CPIT-405",9);

        insertCourses("CPIT-435",10);
        insertCourses("CPIT-470",10);

        insertCourses("CPIT-475",0);
        insertCourses("CPIT-455",0);
        insertCourses("CPIT-445",0);
        insertCourses("CPIT-436",0);
        insertCourses("CPIT-430",0);
        insertCourses("CPIT-375",0);
        insertCourses("CPIT-456",0);
        insertCourses("CPIT-440",0);
        insertCourses("CPIT-340",0);
        insertCourses("CPIT-490",0);
        insertCourses("CPIT-485",0);
        insertCourses("CPIT-480",0);

    }
    /////////////////////
    //method for adding number of sections for courses
    public void add_section_number(String course_id , int section_num){
        //insert into Courses (Section_num) values(section_num) where Course_id= course_id;
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Section_num",section_num);

        db.insert("Courses",course_id,values);
    }
    /////////////////////
    //insert section Information in Section table
    public void insertSection(Section section_obj){
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Course_id",section_obj.getCourse_id());
        values.put("Level",section_obj.getLevel());
        values.put("Section_name",section_obj.getSection_name());
        values.put("Days",section_obj.getDays());
        values.put("time",section_obj.getSection_time());
        values.put("number_of_days",section_obj.getDays_num());

        db.insert("Section",null,values);
    }
    /////////////////////
    //get courses names for each level
    public ArrayList getcoursesbylevel(int level){
        ArrayList array_List=new ArrayList();
        // int level_number = (Integer.parseInt(level));
        db=this.getReadableDatabase();
        Cursor res =db.rawQuery("select * from Courses where Level = "+level,null);
        res.moveToFirst();
        while(res.isAfterLast()==false){
            array_List.add(res.getString(res.getColumnIndex("Course_id"))) ;
            res.moveToNext();

        }
        return array_List;

    }
    //////////////////
    //get other courses that are not in student level
    public ArrayList getOtherCorses(int level){
        ArrayList array_List=new ArrayList();
        db=this.getReadableDatabase();
        Cursor res =db.rawQuery("select * from Courses where Level != "+level +"&& Section_num >=1",null);
        res.moveToFirst();
        while(res.isAfterLast()==false) {
            array_List.add(res.getString(res.getColumnIndex("Course_id")));
            res.moveToNext();

        }
        return array_List;
    }

    //method for section spinner on edit course page
    public ArrayList SectionSpinner(String course_id){
        db=this.getReadableDatabase();
        ArrayList section_Array=new ArrayList();

        Cursor res =db.rawQuery("select * from Section where Course_id = "+course_id ,null);
        res.moveToFirst();
        while(res.isAfterLast()==false) {
            section_Array.add(res.getString(res.getColumnIndex("Course_id")));
            res.moveToNext();
        }
        return section_Array;
    }
    //method for admin to update section information
    public void UpdateSecion(Section section_obj){
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Times",section_obj.getSection_time());
        values.put("Days",section_obj.getDays());
        values.put("number_of_days",section_obj.getSection_name());


        db.update("Section",values,"Section_name=?, Course_id=?",new String[]{section_obj.getSection_name(),section_obj.getCourse_id()});
    }
    //method for admin to drop section
    public void DropSection(Section section_obj){
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        db.delete("Section","Section_name=?,  Course_id=?",new String[]{section_obj.getSection_name(),section_obj.getCourse_id()});
    }
    //seach course for admin in edit course page
    public ArrayList<String> searchMethod( String course){

        //  db.execSQL("Select Course_id from Section where Course_id like '"+course+"%'");

        ArrayList<String> searchArray=new ArrayList();

        Cursor res =db.rawQuery("Select * from Section where Course_id like '"+course+"%'" ,null);
        res.moveToFirst();
        while(res.isAfterLast()==false) {
            searchArray.add(res.getString(res.getColumnIndex("Course_id")));
            res.moveToNext();
        }
        return searchArray;
    }
    public ArrayList getAllCorses(){
        ArrayList array_List=new ArrayList();
        db=this.getReadableDatabase();
        Cursor res =db.rawQuery("select * from Courses where Section_num >=1 ",null);
        res.moveToFirst();
        while(res.isAfterLast()==false) {
            array_List.add(res.getString(res.getColumnIndex("Course_id")));
            res.moveToNext();

        }
        return array_List;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS UsersAccount");
        db.execSQL("DROP TABLE IF EXISTS Courses");
        db.execSQL("DROP TABLE IF EXISTS Section");
        db.execSQL("DROP TABLE IF EXISTS Student_Schedule");

// Creating tables again
        onCreate(db);
    }

}
