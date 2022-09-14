package com.example.AppXml.controller;

import com.example.AppXml.dto.PersonDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

public interface PersonController <T,I> {

    public ResponseEntity<T> createPerson(@RequestParam String path) throws IOException;

    public ResponseEntity<List<PersonDto>> getAll();

    public ResponseEntity<T> getPersonById(@RequestParam I i);

}
