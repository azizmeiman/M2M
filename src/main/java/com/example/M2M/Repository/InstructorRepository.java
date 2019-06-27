package com.example.M2M.Repository;

import com.example.M2M.Entity.InstructorEntity;
import com.example.M2M.Entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<InstructorEntity,Integer> {
}
