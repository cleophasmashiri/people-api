package com.example.peopleapi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.List;

import com.example.peopleapi.model.Person;
import com.example.peopleapi.repository.PersonRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PersonServiceTests {

    private static final String DEFAULT_ID_NUMBER = "7000535578646";
    private static final String DEFAULT_MOBILE_NUMBER = "+27590111533";
    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String DEFAULT_PHYSICAL_ADDRESS = "AAAAAAAAAA";
    
    @Mock
    public PersonRepository personRepository;

    private PersonService personService;
    private Person person;

    @Before
    public void setUp() {
        // With this call to initMocks we tell Mockito to process the annotations
        MockitoAnnotations.initMocks(this);
        personService = new PersonServiceImpl(personRepository);
        person = new Person();
        person.setFirstname(DEFAULT_FIRST_NAME);
        person.setLastname(DEFAULT_LAST_NAME);
        person.setIdNumber(DEFAULT_ID_NUMBER);
        person.setMobileNumber(DEFAULT_MOBILE_NUMBER);
        person.setPhysicalAddress(DEFAULT_PHYSICAL_ADDRESS);
    }

    @Test
    public void getAllTest() {
        List<Person> fakePersons = Arrays.asList(person);
        given(personRepository.findAll()).willReturn(Arrays.asList(person));
        List<Person> persons = personService.getAll();
        assertThat(persons).isEqualTo(fakePersons);
    }

    @Test
    public void getPersonTest() {
        Person fakePerson = person;
        long id = 1;
        given(personRepository.findOne(id)).willReturn(person);
        Person personFound = personService.getPerson(id);
        assertThat(personFound).isEqualTo(fakePerson);
    }

    @Test
    public void createPersonTest() {
        Person fakePerson = person;
        given(personRepository.save(person)).willReturn(fakePerson, fakePerson);
        boolean personAdded = personService.addPerson(person);
        assertThat(personAdded).isTrue();
    }

    @Test
    public void updatePersonTest() {
        Person fakePerson = person;
        given(personRepository.save(person)).willReturn(fakePerson, fakePerson);
        boolean personUpdated = personService.updatePerson(person);
        assertThat(personUpdated).isTrue();
    }

    @Test
    public void deletePersonTest() {
        long id = 1;
        boolean personDeleted = personService.remove(id);
        assertThat(personDeleted).isTrue();
    }

}
