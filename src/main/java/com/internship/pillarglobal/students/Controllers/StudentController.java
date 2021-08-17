package com.internship.pillarglobal.students.Controllers;

import com.internship.pillarglobal.students.Models.Student;
import com.internship.pillarglobal.students.Services.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/getStudents")
    public ResponseEntity<?> getStudents(){
        return new ResponseEntity<>(studentService.getStudents(), HttpStatus.OK);
    }
    @GetMapping("/getStudent/{id}")
    public ResponseEntity<?> getStudent(@PathVariable String id){
        if(studentService.exist(id)){
            return new ResponseEntity<>(studentService.getStudent(id), HttpStatus.OK);
        }
        else
          return new ResponseEntity<>("Student with id "+id+" does not exist", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addStudent")
    public ResponseEntity<?> addStudent(@RequestBody Student student){
        if(!studentService.exist(student.getId())){
            studentService.addStudent(student);
            return new ResponseEntity<>("Student "+student+" was successfully added", HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>("Student "+student+" exists, try update", HttpStatus.METHOD_NOT_ALLOWED);
    }


    @PutMapping("/updateStudent")
    public ResponseEntity<?> updateStudent(@RequestBody Student student){
        if(studentService.exist(student.getId())){
            studentService.updateStudent(student);
            return new ResponseEntity<>("Student "+student+" was successfully updated", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Student "+student+" does not exist, try addStudent", HttpStatus.METHOD_NOT_ALLOWED);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable String id){
        if(studentService.exist(id)){
            studentService.deleteStudent(id);
            return new ResponseEntity<>("Student with id "+id+" was successfully deleted", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Student with id "+id+" does not exist.", HttpStatus.METHOD_NOT_ALLOWED);
    }






}
