package com.example.M2M.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="student")
@Data
public class StudentEntity {


    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="GPA")
    private double GPA;

    @ManyToMany(mappedBy = "students")
    private Set<InstructorEntity> instructors;





}
