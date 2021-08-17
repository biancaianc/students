package com.internship.pillarglobal.students.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="students",schema = "students_schema")
@Data
public class Student {
    @Id
    private String id;
    private String name;
    private int age;
    private String mail;
}
