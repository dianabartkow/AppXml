package com.example.AppXml.dto;

import com.example.AppXml.repo.Adress;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Data
public class PersonDto {


    private   String name;
    private   String surename;

    private  List<Adress> listOfAdress;
}
