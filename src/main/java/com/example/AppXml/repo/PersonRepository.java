package com.example.AppXml.repo;

import com.example.AppXml.repo.Person;
import org.springframework.data.repository.CrudRepository;

//TODO: optimize imports :D

public interface PersonRepository extends CrudRepository<Person,Integer> {
}
