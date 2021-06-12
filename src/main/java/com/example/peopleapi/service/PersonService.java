package com.example.peopleapi.service;

import java.util.List;

import com.example.peopleapi.model.Person;

public interface PersonService {

    List<Person> getAll();

    boolean addPerson(Person person);

    boolean updatePerson(Person person);
    
    boolean remove(long id);

    Person getPerson(long id);
}
