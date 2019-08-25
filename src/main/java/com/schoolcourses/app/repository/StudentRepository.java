package com.schoolcourses.app.repository;

import com.schoolcourses.app.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface StudentRepository extends MongoRepository<Student, String> {

    /**
     * Get student by name
     * @param studentCode Course Name
     * @return found Course detail
     */
    Student findByStudentCode(Long studentCode);

}