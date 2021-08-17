package com.internship.pillarglobal.students.Repositories;

import com.internship.pillarglobal.students.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,String> {
}
