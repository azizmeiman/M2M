package com.example.M2M.Controller;


import com.example.M2M.Entity.InstructorEntity;
import com.example.M2M.Entity.StudentEntity;
import com.example.M2M.Repository.InstructorRepository;
import com.example.M2M.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/inst")
public class InstructorController {


    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    StudentRepository studentRepository;


    @GetMapping()
    public Page<InstructorEntity> getAllInstructors(Pageable pageable) {
        return instructorRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Optional<InstructorEntity> getInstructor(@PathVariable int id){
        Optional<InstructorEntity> p = instructorRepository.findById(id);
        if(p.isPresent()){
            return p;

        }else throw new RuntimeException("instructor with id"+id+" not found");
    }

    @PostMapping
    public InstructorEntity createInstructor(InstructorEntity Instructor) {
        return instructorRepository.save(Instructor);
    }

    @PutMapping()
    public InstructorEntity updateInstructor(int instId, InstructorEntity instructorRequest) {

        return instructorRepository.findById(instId).map(instructor -> {

            instructor.setName(instructorRequest.getName());

            return instructorRepository.save(instructor);
        }).orElseThrow(() -> new ResourceNotFoundException("instructor Id " + instId + " not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInstructor(@PathVariable int instId) {
        return instructorRepository.findById(instId).map(instructor -> {
            instructorRepository.delete(instructor);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("instructor ID " + instId + " not found"));
    }



    //new

    @GetMapping("/{instuctorId}/students")
    public Set<StudentEntity> getStudents(@PathVariable int instuctorId){
        // Finds lecturer by id and returns it's recorded students, otherwise throws exception
        return this.instructorRepository.findById(instuctorId).map((instructor) -> {
            return instructor.getStudents();
        }).orElseThrow(() -> new ResourceNotFoundException("Instructor not found"));
    }


    @PostMapping("/{id}/students/{studentId}")
    public Set<StudentEntity> addStudent(@PathVariable int id, @PathVariable int studentId){
        // Finds a persisted student
        StudentEntity student = this.studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student not founded")
        );

        // Finds a lecturer and adds the given student to the lecturer's set.
        return this.instructorRepository.findById(id).map((instructor) -> {
            instructor.getStudents().add(student);
            return this.instructorRepository.save(instructor).getStudents();
        }).orElseThrow(() -> new ResourceNotFoundException("Instructor not found "));
    }
}
