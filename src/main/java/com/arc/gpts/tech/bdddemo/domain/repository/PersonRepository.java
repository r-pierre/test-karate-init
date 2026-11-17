package com.arc.gpts.tech.bdddemo.domain.repository;

import com.arc.gpts.tech.bdddemo.domain.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

    Optional<Person> findByReference(String reference);
}
