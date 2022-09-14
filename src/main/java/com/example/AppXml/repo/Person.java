package com.example.AppXml.repo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
//TODO: change to domain object
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private  String name;
    private  String surename;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Adress> listOfAdress;
    //TODO: report and another dependencies @OneToOne, @ManyToOne, @OneToMany, @ManyToMany
}
