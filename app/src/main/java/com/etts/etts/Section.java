package com.etts.etts;

/**
 * Created by user on 17/02/18.
 */

public class Section {
    private String Course_id;
    private String Section_name;
    private int level;
    private String Section_time;
    private String days;
    private int days_num;

    public Section() {
    }

    public Section(String course_id, String section_name, int level, String section_time, String days, int days_num) {
        Course_id = course_id;
        Section_name = section_name;
        this.level = level;
        Section_time = section_time;
        this.days = days;
        this.days_num = days_num;
    }



    public String getCourse_id() {
        return Course_id;
    }

    public void setCourse_id(String course_id) {
        Course_id = course_id;
    }

    public String getSection_name() {
        return Section_name;
    }

    public void setSection_name(String section_name) {
        Section_name = section_name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getSection_time() {
        return Section_time;
    }

    public void setSection_time(String section_time) {
        Section_time = section_time;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public int getDays_num() {
        return days_num;
    }

    public void setDays_num(int days_num) {
        this.days_num = days_num;
    }
}
