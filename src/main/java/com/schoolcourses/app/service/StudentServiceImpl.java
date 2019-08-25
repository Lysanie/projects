package com.schoolcourses.app.service;

import com.schoolcourses.app.model.Student;
import com.schoolcourses.app.repository.StudentRepository;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class StudentServiceImpl implements IStudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findStudentByStudentId(Long studentId) {
        return ofNullable(studentRepository.findByStudentCode(studentId))
                .orElseGet(Student::new);
    }

    @Override
    public Student create(Student student) {
        return insert(student);
    }


    private Student insert(Student student) {
        long code = studentRepository.count() + 1;
        student.setStudentCode(code);
        return studentRepository.insert(student);
    }

    @Override
    public void update(Student student) {
        Validate.notNull(student);
        long code = ofNullable(student.getStudentCode()).orElse(-1L);

        if ( code <= 0) insert(student);
        else studentRepository.save(student);
    }

    @Override
    public void deleteById(Long studentId) {
        ofNullable(studentRepository.findByStudentCode(studentId)).ifPresent(
                s -> studentRepository.delete(s)
        );

    }

}
