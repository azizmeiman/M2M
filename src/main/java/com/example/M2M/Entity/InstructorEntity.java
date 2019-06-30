package com.example.M2M.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="instructor")
@Data
public class InstructorEntity {

    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="phoneNumber")
    private int phoneNumber;





    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private Set<StudentEntity> students;

}
