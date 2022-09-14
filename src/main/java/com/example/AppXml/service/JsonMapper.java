package com.example.AppXml.service;

import com.example.AppXml.repo.Person;
import com.example.AppXml.repo.PersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class JsonMapper implements MapperInterface{
    private final PersonRepository personRepository;
    private final ReadFile readFile;
    private final ObjectMapper jsonMapper;

    @Override
    public void map(String body) throws JsonProcessingException {
        personRepository.save(jsonMapper.readValue(body, Person.class));
    }
}
