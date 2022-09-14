package com.example.AppXml.service;

import com.example.AppXml.repo.Person;
import com.example.AppXml.repo.PersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class XmlMapper implements MapperInterface {
    private final PersonRepository personRepository;
    private final ReadFile readFile;
    private final ObjectMapper xmlMapper;

    public void map(String body) throws JsonProcessingException {
        personRepository.save(xmlMapper.readValue(body, Person.class));
    }
}
