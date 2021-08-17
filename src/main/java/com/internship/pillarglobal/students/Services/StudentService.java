package com.internship.pillarglobal.students.Services;

import com.internship.pillarglobal.students.Models.Student;
import com.internship.pillarglobal.students.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }


    public boolean exist(String id) {
        return studentRepository.existsById(id);
    }

    public Student getStudent(String id) {
        return studentRepository.findById(id).get();
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public void updateStudent(Student student) {
        studentRepository.save(student);
    }

    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }
}
