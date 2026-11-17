package com.arc.gpts.tech.bdddemo.infra.web.person.dto;

import com.arc.gpts.tech.bdddemo.domain.model.Person;
import com.arc.gpts.tech.bdddemo.exceptions.BadRequestException;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public PersonDto toDto(Person person) {
        if (person == null) {
            throw new BadRequestException("The Person DTO is null.", null);
        }
        PersonDto dto = new PersonDto();
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setReference(person.getReference());
        return dto;
    }

    public Person toEntity(PersonDto dto) {
        if (dto == null) {
            throw new BadRequestException("The Person object is null.", null);
        }
        Person person = new Person();
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setReference(dto.getReference());
        return person;
    }
}
