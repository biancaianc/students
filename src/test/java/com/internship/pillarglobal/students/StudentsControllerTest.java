package com.internship.pillarglobal.students;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internship.pillarglobal.students.Controllers.StudentController;
import com.internship.pillarglobal.students.Models.Student;
import com.internship.pillarglobal.students.Repositories.StudentRepository;
import com.internship.pillarglobal.students.Services.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = StudentsApplication.class)
@AutoConfigureMockMvc
public class StudentsControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    StudentRepository studentRepository;
    @Autowired
    StudentService studentService;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getStudents() throws Exception {
        List<Student> providedList = Arrays.asList(new Student("1", "2", 3, "4"), new Student("5", "6", 3, "4"));
        when(studentRepository.findAll()).thenReturn(providedList);
        mockMvc.perform(MockMvcRequestBuilders.
                get("/getStudents"))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":\"1\",\"name\":\"2\",\"age\":3,\"mail\":\"4\"},{\"id\":\"5\",\"name\":\"6\",\"age\":3,\"mail\":\"4\"}]"));
        Mockito.verify(studentRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void getStudent() throws Exception {
        Student student = new Student("1", "2", 3, "4");
        when(studentRepository.findById(student.getId())).thenReturn(java.util.Optional.of(student));
        when(studentRepository.existsById(student.getId())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.
                get("/getStudent/{id}", student.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":\"1\",\"name\":\"2\",\"age\":3,\"mail\":\"4\"}"));
        Mockito.verify(studentRepository, Mockito.times(1)).findById(student.getId());
        Mockito.verify(studentRepository, Mockito.times(1)).existsById(student.getId());
    }

    @Test
    public void addStudent() throws Exception {
        Student student = new Student("1", "2", 3, "4");
        when(studentRepository.save(student)).thenReturn(student);
        when(studentRepository.existsById(student.getId())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.
                post("/addStudent")
                .content(asJsonString(student))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string("Student " + student + " was successfully added"));
        Mockito.verify(studentRepository, Mockito.times(1)).save(student);
        Mockito.verify(studentRepository, Mockito.times(1)).existsById(student.getId());
    }

    @Test
    public void updateStudent() throws Exception {
        Student student = new Student("1", "2", 3, "4");
        when(studentRepository.save(student)).thenReturn(student);
        when(studentRepository.existsById(student.getId())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.
                put("/updateStudent")
                .content(asJsonString(student))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Student " + student + " was successfully updated"));
        Mockito.verify(studentRepository, Mockito.times(1)).save(student);
        Mockito.verify(studentRepository, Mockito.times(1)).existsById(student.getId());
    }

    @Test
    public void deleteStudent() throws Exception {
        Student student = new Student("1", "2", 3, "4");
        when(studentRepository.existsById(student.getId())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.
                delete("/deleteStudent/{id}", student.getId())
        )
                .andExpect(status().isOk());
        Mockito.verify(studentRepository, Mockito.times(1)).existsById(student.getId());
        Mockito.verify(studentRepository, Mockito.times(1)).deleteById(student.getId());
    }


}
