package com.example.M2M.Controller;

import com.example.M2M.Entity.StudentEntity;
import com.example.M2M.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class StudentController {

    @Autowired
    public StudentRepository studentRepository;

    public List<StudentEntity> listAll(){
        return studentRepository.findAll();
    }

}
