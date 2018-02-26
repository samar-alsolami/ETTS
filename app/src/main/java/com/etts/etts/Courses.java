package com.etts.etts;

/**
 * Created by user on 13/02/18.
 */

public class Courses {
    private String course_id;
    private int level;
    private int section_num;

    public Courses(String course_id, int level, int section_num) {
        this.course_id = course_id;
        this.level = level;
        this.section_num = section_num;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSection_num() {
        return section_num;
    }

    public void setSection_num(int section_num) {
        this.section_num = section_num;
    }
}
