package com.example.peopleapi.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.peopleapi.model.Person;
import com.example.peopleapi.repository.PersonRepository;
import com.example.peopleapi.rest.PersonController;

@Service
public class PersonServiceImpl implements PersonService {

    private final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> getAll() {
        List<Person> persons = personRepository.findAll();
        LOGGER.info("Get Persons ..." + persons);
        return persons;
    }

    @Override
    public boolean addPerson(Person person) {
        
        LOGGER.info("New Person ..." + person);
        throw new DataIntegrityViolationException("");
        // personRepository.save(person);
        // return true;
    }

    @Override
    public boolean updatePerson(Person person) {
        LOGGER.info("Upadate Person ..." + person);
        personRepository.save(person);
        return true;
    }

    @Override
    public boolean remove(long id) {
        LOGGER.info("Delete Person ..." + id);
        personRepository.delete(id);
        return true;
    }

    @Override
    public Person getPerson(long id) {
		return personRepository.findOne(id);
	}

}
