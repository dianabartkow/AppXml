package com.example.AppXml.repo;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.*;


@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
//adnotacja table??
//import org.hibernate.annotations.Table; czy import javax.persistence?
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    private  String country;
    private  String city;
    private  String postcode;
    private String HouseNumber;

    @ManyToOne(fetch = FetchType.LAZY)//który FetchType?
    private Person person;
}
