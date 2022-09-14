package com.example.AppXml.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor

public class ReadFile {

    public String read(String path) throws IOException {
       return Files.readString(Path.of(path));

    }
}
