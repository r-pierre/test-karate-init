package com.arc.gpts.tech.bdddemo.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;

@Data
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String firstName;

    @Column
    String lastName;

    @Column()
    String reference;
}
