package com.example.M2M.Controller;


import com.example.M2M.Entity.InstructorEntity;
import com.example.M2M.Repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InstructorController {

    @Autowired
    public InstructorRepository instructorRepository;

    public List<InstructorEntity> getAll(){

        return instructorRepository.findAll();
    }
}
