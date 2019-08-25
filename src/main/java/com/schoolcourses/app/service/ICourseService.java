package com.schoolcourses.app.service;

import com.schoolcourses.app.model.Course;

import java.util.List;

public interface ICourseService {

    List<Course> findAll();

    Course findCourseById(String courseName);

    Course create(Course course);

    void update(Course course);

    void deleteById(String courseId);
}
