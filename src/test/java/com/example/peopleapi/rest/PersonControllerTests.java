package com.example.peopleapi.rest;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.peopleapi.model.Person;
import com.example.peopleapi.service.PersonService;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTests {

    private static final String DEFAULT_ID_NUMBER = "7000535578646";
    private static final String DEFAULT_MOBILE_NUMBER = "+27590111533";
    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String DEFAULT_PHYSICAL_ADDRESS = "AAAAAAAAAA";

    @MockBean
    private PersonService personService;

    @Autowired
    private MockMvc restPersonMockMvc;

    private Person person;

    private String jsonString = "{\"id\":null,\"idNumber\":\"7000535578646\",\"firstname\":\"AAAAAAAAAA\",\"lastname\":\"AAAAAAAAAA\",\"mobileNumber\":\"+27590111533\",\"physicalAddress\":\"AAAAAAAAAA\"}";

    @Before
    public void setup() {
        person = new Person();
        person.setFirstname(DEFAULT_FIRST_NAME);
        person.setLastname(DEFAULT_LAST_NAME);
        person.setIdNumber(DEFAULT_ID_NUMBER);
        person.setMobileNumber(DEFAULT_MOBILE_NUMBER);
        person.setPhysicalAddress(DEFAULT_PHYSICAL_ADDRESS);
    }

    @Test
    public void createPersonGivenValidRequest() throws Exception {

    }

    @Test
    public void givenPersonIdWhenGetByIdCalledShouldReturnPerson() throws Exception {
        long id = 1;
        given(personService.getPerson(id)).willReturn(person);
        restPersonMockMvc.perform(get("/people/" + String.valueOf(id)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().json(jsonString));
    }

    // Verify valid format of IdNumber
    @Test
    public void givenAddPersonShouldVerifyValidFormatIdNumber() throws Exception {
        given(personService.addPerson(person)).willReturn(true);
        restPersonMockMvc.perform(post("/people").contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAddPersonWithInvalidIdNumberShouldVerifyValidFormatIdNumber() throws Exception {
        String invalidPersonString = "{\"id\":null,\"idNumber\":\"70005355\",\"firstname\":\"AAAAAAAAAA\",\"lastname\":\"AAAAAAAAAA\",\"mobileNumber\":\"+27590111533\",\"physicalAddress\":\"AAAAAAAAAA\"}";
        given(personService.addPerson(person)).willReturn(true);
        restPersonMockMvc.perform(post("/people").contentType(MediaType.APPLICATION_JSON).content(invalidPersonString))
                .andExpect(status().isBadRequest());
    }

    // Verify valid format of PhoneNumber
    @Test
    public void givenPhoneNumberShouldVerifyValidFormatThereOf() throws Exception {
        String invalidPersonString = "{\"id\":null,\"idNumber\":\"7000535578646\",\"firstname\":\"AAAAAAAAAA\",\"lastname\":\"AAAAAAAAAA\",\"mobileNumber\":\"+275901115\",\"physicalAddress\":\"AAAAAAAAAA\"}";
        given(personService.addPerson(person)).willReturn(true);
        restPersonMockMvc.perform(post("/people").contentType(MediaType.APPLICATION_JSON).content(invalidPersonString))
                .andExpect(status().isBadRequest());
    }

    // Validate for duplicate IdNumber
    @Test
    public void givenIdNumberShouldVerifyNoDuplicatesThereOf() throws Exception {
        // given(personService.addPerson(person)).willThrow(new DataIntegrityViolationException(
        //         "could not execute statement; SQL [n/a]; constraint [\"CONSTRAINT_INDEX_8 ON PUBLIC.PEOPLE(ID_NUMBER)"));

        // restPersonMockMvc.perform(post("/people").contentType(MediaType.APPLICATION_JSON).content(jsonString))
        //         .andExpect(status().isInternalServerError());
    }

    // Validate for duplicate Mobile Number
    @Test
    public void givenMobileNumberShouldVerifyNoDuplicatesThereOf() {

    }

    // Verify Required Fields IdNumber, PhoneNumber, FirstName, LastName,
    // physicalAddress
    @Test
    public void givenRequestForUpdatePersonShouldVerifyRequiredFieldsThereOf() {

    }

    // Verify for create new person
    @Test
    public void givenRequestForCreatePersonShouldVerifyPersonIsCreated() {

    }

    // Verify for removal of person
    @Test
    public void givenRequestForRemovePersonShouldVerifyPersonIsRemoved() {

    }

    // Verify form update of person
    @Test
    public void givenRequestForUpdatePersonShouldVerifyPersonIsUpdated() {

    }
}
