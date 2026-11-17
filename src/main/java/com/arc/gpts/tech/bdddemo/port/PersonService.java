package com.arc.gpts.tech.bdddemo.port;

import com.arc.gpts.tech.bdddemo.domain.model.Person;
import org.springframework.stereotype.Service;

@Service
public interface PersonService {

    Person create(Person person);
    Person findByReference(String reference);
}
