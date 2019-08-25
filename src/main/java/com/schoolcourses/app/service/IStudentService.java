package com.schoolcourses.app.service;

import com.schoolcourses.app.model.Student;

import java.util.List;

public interface IStudentService {

    List<Student> findAll();

    Student findStudentByStudentId(Long studentId);

    Student create(Student student);

    /**
     * Update student info (codeStudent/id not null)
     * or create a new one if passed student doesn't exists yet
     *
     * @param student not nullable
     */
    void update(Student student);

    void deleteById(Long studentId);
}
