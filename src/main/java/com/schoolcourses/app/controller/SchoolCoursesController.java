package com.schoolcourses.app.controller;

import com.schoolcourses.app.model.Course;
import com.schoolcourses.app.model.Student;
import com.schoolcourses.app.service.ICourseService;
import com.schoolcourses.app.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.*;

@RestController
@CrossOrigin
public class SchoolCoursesController {

    private ICourseService courseService;

    private IStudentService studentService;

    @Autowired
    public SchoolCoursesController(ICourseService courseService, IStudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    /********************************************************************
     *                                                                  *
     *                              Courses                             *
     *                                                                  *
     ********************************************************************/

    @GetMapping(value = "/courses", produces=APPLICATION_JSON_VALUE)
    public List<Course> getAllCourses(){
        return courseService.findAll();
    }

    @GetMapping(value = "/course", produces = APPLICATION_JSON_VALUE)
    public Course getCourse(@RequestParam(name = "name") String courseName){
        return courseService.findCourseById(courseName);
    }

    @PostMapping(value = "/course/new", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Course createCourse(@RequestBody Course course){
        return courseService.create(course);
    }

    @PutMapping(value = "/course", consumes = APPLICATION_JSON_VALUE)
    public void saveCourse(@RequestBody Course course){
        courseService.update(course);
    }

    @DeleteMapping(value = "/course/{id}")
    public void deleteCourse(@PathVariable(name = "id") String courseName){
        courseService.deleteById(courseName);
    }

    /********************************************************************
     *                                                                  *
     *                              Students                            *
     *                                                                  *
     ********************************************************************/

    @GetMapping(value = "/students", produces = APPLICATION_JSON_VALUE)
    public List<Student> getAllStudents(){
        return studentService.findAll();
    }

    @GetMapping(value = "/student/{id}", produces = APPLICATION_JSON_VALUE)
    public Student getStudent(@PathVariable(value = "id") Long studentId){
        return studentService.findStudentByStudentId(studentId);
    }

    @PostMapping(value = "/student", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Student createStudent(@RequestBody Student student){
        return studentService.create(student);
    }

    @PutMapping(value = "/student", consumes = APPLICATION_JSON_VALUE)
    public void saveStudent(@RequestBody Student student){
        studentService.update(student);
    }

    @DeleteMapping(value = "/student/{id}")
    public void deleteStudent(@PathVariable(name = "id") Long studentId){
        studentService.deleteById(studentId);
    }
}
