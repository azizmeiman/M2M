package com.example.M2M.Controller;

import com.example.M2M.Entity.InstructorEntity;
import com.example.M2M.Entity.StudentEntity;
import com.example.M2M.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    public StudentRepository studentRepository;

    @PostMapping()
    public StudentEntity add(@Valid @RequestBody StudentEntity std){

        return studentRepository.save(std);
    }

    @GetMapping()
    public Page<StudentEntity> listAll(Pageable pageable){
        return studentRepository.findAll(pageable);
    }



    @GetMapping("/{id}") // Finds a student by id (the variable must be wrapped by "{}" and match the @PathVariable name
    public StudentEntity getStudent(@PathVariable int id){
        // If the record exists by id return it, otherwise throw an exception
        return this.studentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Student not found")
        );
    }


    @PutMapping() // Validates the request body as a Student type
    public StudentEntity updateStudent(@Valid @RequestBody StudentEntity student){
        // Finds student by id, maps it's content, updates new values and save. Throws an exception if not found.
        return this.studentRepository.findById(student.getId()).map((student1) -> {
            student1.setName(student.getName());
            student1.setGPA(student.getGPA());
            return this.studentRepository.save(student1);
        }).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    @DeleteMapping("/{id}") // Find student by id
    public ResponseEntity deleteStudent(@PathVariable int id){
        // If id exists, delete the record and return a response message, otherwise throws exception
        return this.studentRepository.findById(id).map((student2) -> {
            this.studentRepository.delete(student2);
            return ResponseEntity.ok("Student id " + id + " deleted");
        }).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    @GetMapping("/{id}/lecturers")
    public Set<InstructorEntity> getInstructors(@PathVariable int id){
        // Finds student by id and returns it's recorded lecturers, otherwise throws exception
        return this.studentRepository.findById(id).map((student) -> {
            return student.getInstructors();
        }).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

}
