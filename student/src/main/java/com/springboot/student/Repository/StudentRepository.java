package com.springboot.student.Repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.springboot.student.Student;

public interface StudentRepository extends JpaRepository<Student,Integer>{

    void save(Optional<Student> student2);

  
    
}
