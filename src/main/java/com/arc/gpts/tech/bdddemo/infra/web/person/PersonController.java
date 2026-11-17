package com.arc.gpts.tech.bdddemo.infra.web.person;

import ch.qos.logback.core.util.StringUtil;
import com.arc.gpts.tech.bdddemo.HelloController;
import com.arc.gpts.tech.bdddemo.domain.model.Person;
import com.arc.gpts.tech.bdddemo.infra.web.person.dto.PersonChecker;
import com.arc.gpts.tech.bdddemo.infra.web.person.dto.PersonDto;
import com.arc.gpts.tech.bdddemo.infra.web.person.dto.PersonMapper;
import com.arc.gpts.tech.bdddemo.port.PersonService;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    private PersonChecker personChecker;
    private PersonMapper personMapper;
    private PersonService personService;

    @GetMapping("/{reference}")
    public HelloController.Greeting findByReference(@PathVariable String reference) {
        personChecker.checkReference(reference);
        return new HelloController.Greeting("Bonjour " + reference);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDto create(@RequestBody PersonDto personDto) {
        personChecker.check(personDto);
        Person personRequest = personMapper.toEntity(personDto);
        Person personCreated = personService.create(personRequest);
        return personMapper.toDto(personCreated);
    }
}
