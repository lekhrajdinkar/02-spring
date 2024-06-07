package com.lekhraj.java.spring.SB_99_RESTful_API.entities;

import com.lekhraj.java.spring.SB_99_RESTful_API.model.GenderEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name="STUDENT")
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="student_name", length=50, nullable=false, unique=false)
    private String name;

    @Transient
    private Integer age;

    @Column(name="birth_date")
    private LocalDateTime birthDate;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
}
