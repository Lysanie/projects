package com.schoolcourses.app.repository;

import com.schoolcourses.app.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends MongoRepository<Course, String> {

    /**
     * Get course by name
     * @param name Course Name
     * @return found Course detail
     */
    Course findByName(String name);

    Optional<Course> findById(String Id);

}