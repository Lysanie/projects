package com.schoolcourses.app.service;

import com.schoolcourses.app.exception.TechnicalException;
import com.schoolcourses.app.model.Course;
import com.schoolcourses.app.model.Student;
import com.schoolcourses.app.repository.CourseRepository;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class CourseServiceImplTest {

    private CourseServiceImpl instance;
    private CourseRepository courseRepository;

    @BeforeEach
     void setUp() {
        courseRepository = mock(CourseRepository.class);
        instance = new CourseServiceImpl(courseRepository);
    }


     @TestFactory
     Collection<DynamicTest> findCourseById_shouldThrowException_whenInvalidArguments() {

        return Arrays.asList(
                DynamicTest.dynamicTest("Null Course Id",() ->
                        assertThatNullPointerException().isThrownBy(
                                () -> instance.findCourseById(null))),
                DynamicTest.dynamicTest("Blank Course ID", () ->
                        assertThatIllegalArgumentException().isThrownBy(
                                () -> instance.findCourseById(" ")))
                );
    }

     @Test
     @Tag("UnitTest")
     void findCourseById_shouldFinishWithSuccess(){
        Course course = new Course();
        course.setName("Test");

        when(courseRepository.findByName(anyString())).thenReturn(course);

        assertThat(instance.findCourseById(course.getName())).isSameAs(course);

        verify(courseRepository).findByName(eq(course.getName()));
    }

    @Test
    @Tag("UnitTest")
    void deleteById_shouldFinishWithSuccess(){
        String courseId= "000111";

        doNothing().when(courseRepository).deleteById(anyString());

        assertThatCode(() -> instance.deleteById(courseId)).doesNotThrowAnyException();
    }

    @Test
    @Tag("UnitTest")
    void update_shouldThrowTechnicalException_whenInvalidStudents(){
        Course course = new Course();
        course.setName("Test");
        course.setDate(new Date());
        course.setStudentList(Arrays.asList(new Student(), new Student()));

        instance.nMaxStudents = 1;

        assertThatExceptionOfType(TechnicalException.class)
            .isThrownBy(() -> instance.update(course));

        verify(courseRepository, times(0)).save(any());
    }

}
