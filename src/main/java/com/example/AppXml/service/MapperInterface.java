package com.example.AppXml.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface MapperInterface {
    void map(String body) throws JsonProcessingException;
}
