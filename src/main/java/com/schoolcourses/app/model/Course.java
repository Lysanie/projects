package com.schoolcourses.app.model;

import com.mongodb.lang.NonNull;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Document (collection = "course")
public class Course {

    @Id
    private String id;

    @NonNull
    @Indexed(unique = true)
    private String name;

    @NonNull
    private Date date;

    @DBRef(lazy = true)
    private List<Student> studentList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
