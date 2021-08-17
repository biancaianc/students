package com.internship.pillarglobal.students;

import com.internship.pillarglobal.students.Models.Student;
import com.internship.pillarglobal.students.Repositories.StudentRepository;
import com.internship.pillarglobal.students.Services.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentServiceTest {

    private StudentRepository studentRepository= Mockito.mock(StudentRepository.class);
    private StudentService studentService=new StudentService(studentRepository);

    @Test
    public void getStudents(){
        List<Student> providedList=Arrays.asList(new Student("1","2",3,"4"),new Student("5","6",3,"4"));
        List<Student> expectedList=Arrays.asList(new Student("1","2",3,"4"),new Student("5","6",3,"4"));
        Mockito.when(studentRepository.findAll()).thenReturn(providedList);
        List<Student> result=studentService.getStudents();
        assertEquals(result,expectedList);
    }
    @Test
    public void getStudent(){
        Student providedStudent=new Student("1","2",3,"4");
        Student expectedStudent=new Student("1","2",3,"4");

        Mockito.when(studentRepository.findById(providedStudent.getId())).thenReturn(Optional.of(providedStudent));
        Student result=studentService.getStudent(providedStudent.getId());
        Mockito.verify(studentRepository,Mockito.times(1)).findById(providedStudent.getId());
        assertEquals(result,expectedStudent);

    }
    @Test
    public void addStudent(){
        Student providedStudent=new Student("1","2",3,"4");
        Student expectedStudent=new Student("1","2",3,"4");
        Mockito.when(studentRepository.save(providedStudent)).thenReturn(providedStudent);
        studentService.addStudent(providedStudent);
        Mockito.verify(studentRepository,Mockito.times(1)).save(providedStudent);


    }
    @Test
    public void updateStudent(){
        Student providedStudent=new Student("1","2",3,"4");
        Student expectedStudent=new Student("1","2",3,"4");

        studentService.addStudent(providedStudent);
        Mockito.verify(studentRepository,Mockito.times(1)).save(providedStudent);


    }
    @Test
    public void deleteStudent(){
        Student providedStudent=new Student("1","2",3,"4");

        studentService.deleteStudent(providedStudent.getId());
        Mockito.verify(studentRepository,Mockito.times(1)).deleteById(providedStudent.getId());


    }





}
