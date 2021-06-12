package com.example.peopleapi.rest;

import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.peopleapi.model.Person;
import com.example.peopleapi.service.PersonService;

@RestController
@RequestMapping("/people")
public class PersonController {

    private final Logger log = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    public PersonService personService;

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable long id) {
        Person person = personService.getPerson(id);

        if (person != null) {
            return new ResponseEntity<>(person, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        log.info("All Persons ...");
        List<Person> persons = personService.getAll();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addPerson(@Valid @RequestBody Person person) throws Exception {
        log.info("New Person ..." + person);
        if (person.getId() != null) {
            throw new Exception("A new person cannot already have an ID, id exists");
        }
        personService.addPerson(person);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updatePerson(@Valid @RequestBody Person person) {
        log.info("Upadate Person ..." + person);
        personService.updatePerson(person);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> remove(@PathVariable long id) {
        log.info("Delete Person ..." + id);
        personService.remove(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/exception/{exception_id}")
    public void getSpecificException(@PathVariable("exception_id") String pException) {
        if ("not_found".equals(pException)) {
            throw new DuplicateItemException("Duplicate arguments");
        } else {
            throw new InternalException("internal error");
        }
    }

}
