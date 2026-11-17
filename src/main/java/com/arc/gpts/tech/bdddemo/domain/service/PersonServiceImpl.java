package com.arc.gpts.tech.bdddemo.domain.service;

import com.arc.gpts.tech.bdddemo.domain.model.Person;
import com.arc.gpts.tech.bdddemo.domain.repository.PersonRepository;
import com.arc.gpts.tech.bdddemo.exceptions.BusinessException;
import com.arc.gpts.tech.bdddemo.port.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    public Person create(Person person) {
        checkReferenceDoesntExist(person);
        return personRepository.save(person);
    }

    private void checkReferenceDoesntExist(Person person) {
        personRepository
                .findByReference(person.getReference())
                .ifPresent(existing -> {
                    throw new BusinessException("Person with reference " + existing.getReference() + " already exists", null);
                });
    }

    public Person findByReference(String reference) {
        return personRepository
                .findByReference(reference)
                .orElseThrow(()->new BusinessException("Person with reference " +reference + " doesn't exists", null));
    }
}
