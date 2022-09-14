package com.example.AppXml.service;

import com.example.AppXml.repo.Person;
import com.example.AppXml.repo.PersonRepository;

import java.util.Collections;
import java.util.List;

public class PersonServiceImpl implements PersonService {

    
    PersonRepository repository;

    @Override
    public List<Person> getAll() {
        return (List<Person>) repository.findAll();
    }

    @Override
    public Person getById(Integer id) {
        return (Person) repository.findAllById(Collections.singleton(id));
    }

    @Override
    public Person create(Person person) {
        return repository.save(person);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
