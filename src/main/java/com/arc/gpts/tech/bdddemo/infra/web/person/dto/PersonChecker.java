package com.arc.gpts.tech.bdddemo.infra.web.person.dto;

import ch.qos.logback.core.util.StringUtil;
import com.arc.gpts.tech.bdddemo.domain.model.Person;
import com.arc.gpts.tech.bdddemo.exceptions.BadRequestException;
import org.springframework.stereotype.Component;

@Component
public class PersonChecker {

    public void checkReference(String reference){
        if (StringUtil.isNullOrEmpty(reference)) {
            throw new BadRequestException("reference value is null", null);
        }
    }

    public void check(PersonDto personDto) {}
}
