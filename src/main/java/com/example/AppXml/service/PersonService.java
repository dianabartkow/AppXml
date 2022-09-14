package com.example.AppXml.service;

import com.example.AppXml.repo.Person;

import java.util.List;

public interface PersonService {

    List<Person> getAll();
    Person getById(Integer id);
    Person create(Person person);
    void deleteById(Integer id);
}
