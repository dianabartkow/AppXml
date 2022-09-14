package com.example.AppXml.dto;

import com.example.AppXml.repo.Adress;
import com.example.AppXml.repo.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springfox.documentation.swagger2.mappers.ModelMapper;

@Service
@RequiredArgsConstructor
public class DtoMapper {

    private ModelMapper mapper;

    public PersonDto mapEntityPersonToDto(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setName(person.getName());
        personDto.setSurename(person.getSurename());
        personDto.setListOfAdress(person.getListOfAdress());

        return personDto;
    }

    public AdressDto mapEntityAdressToDto(Adress adress) {
        AdressDto adressDto = new AdressDto();
        adressDto.setCity(adress.getCity());
        adressDto.setCountry(adress.getCountry());
        adressDto.setHouseNumber(adress.getHouseNumber());

        return adressDto;
    }
}
