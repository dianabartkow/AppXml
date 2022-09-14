package com.example.AppXml.dto;

import com.example.AppXml.repo.Person;
import lombok.Data;

@Data
public class AdressDto {


    private String country;
    private String city;
    private String postcode;
    private String HouseNumber;

    private Person person;
}
