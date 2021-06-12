package com.example.peopleapi.service;

import java.util.List;

import com.example.peopleapi.model.Person;
import com.example.peopleapi.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    private final Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

    private PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAll() {
        List<Person> persons = personRepository.findAll();
        log.info("Get Persons ..." + persons);
        return persons;
    }

    @Override
    public boolean addPerson(Person person) {
        
        log.info("New Person ..." + person);
        personRepository.save(person);
        return true;
    }

    @Override
    public boolean updatePerson(Person person) {
        log.info("Upadate Person ..." + person);
        personRepository.save(person);
        return true;
    }

    @Override
    public boolean remove(long id) {
        log.info("Delete Person ..." + id);
        personRepository.delete(id);
        return true;
    }

    @Override
    public Person getPerson(long id) {
		return personRepository.findOne(id);
	}

}
