package com.schoolcourses.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schoolcourses.app.model.Student;
import com.schoolcourses.app.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SchoolCoursesApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    IStudentService studentService;

    @MockBean
    CourseRepository courseRepository;

    @Test
    public void testCreateRetrieveWithMockMVC() throws Exception {
        Student st = new Student();
        st.setFirstName("First Name");
        st.setLastName("Last Name");
        st.setEmail("t@t.com");

        //studentRepository.insert(st);
        MvcResult mvcResult = mockMvc.perform(post("/student/new")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(st)))
                .andExpect(status().isOk())
                .andReturn();

        ArgumentCaptor<Student> stCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentService, times(1)).create(stCaptor.capture());
        assertThat(stCaptor.getValue().getFirstName()).isEqualTo("First Name");
        assertThat(stCaptor.getValue().getEmail()).isEqualTo("t@t.com");


    }


}
