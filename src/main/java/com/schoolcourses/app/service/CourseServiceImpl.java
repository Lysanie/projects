package com.schoolcourses.app.service;

import com.schoolcourses.app.exception.TechnicalException;
import com.schoolcourses.app.model.Course;
import com.schoolcourses.app.repository.CourseRepository;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class CourseServiceImpl implements ICourseService {

    private CourseRepository courseRepository;

    /**
     * Max number of students per course
     */
    @Value("${app.courses.maxStudents}")
    Integer nMaxStudents;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findCourseById(String courseName) {
        Validate.notBlank(courseName);
        //I can't figure out how findBy* stopped working :(
        //So I had to use this way

        /*return courseRepository.findAll().stream()
                .filter(c -> c.getId().equals(courseId))
                .findFirst()
                .orElseGet(Course::new);*/

        //return courseRepository.findById(courseId).orElseGet(Course::new);

        return ofNullable(courseRepository.findByName(courseName))
                .orElseGet(Course::new);
    }

    @Override
    public Course create(Course course) {
        Validate.notNull(course);

        return courseRepository.insert(course);
    }

    @Override
    public void update(Course course) {
        Validate.notNull(course);

        validateStudents(course);

        courseRepository.save(course);
    }

    @Override
    public void deleteById(String courseId) {
        Validate.notBlank(courseId);

        //I can't figure out how find* and deleteById stopped working :(
        //courseRepository.deleteCourseById(courseId);

        ofNullable(courseRepository.findByName(courseId)).ifPresent(
                c -> courseRepository.delete(c)
        );
    }

    private void validateStudents(Course course) {
        if (course.getStudentList().size() > nMaxStudents){
            throw new TechnicalException("Exceeded maximum number of students in a course");
        }
    }

}
