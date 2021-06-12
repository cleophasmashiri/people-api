package com.example.peopleapi.rest;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

    @Test
    public void givenAddPersonShouldVerifyPersonCreated() throws Exception {
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

    @Test
    public void givenPhoneNumberShouldVerifyValidFormatThereOf() throws Exception {
        String invalidPersonString = "{\"id\":null,\"idNumber\":\"7000535578646\",\"firstname\":\"AAAAAAAAAA\",\"lastname\":\"AAAAAAAAAA\",\"mobileNumber\":\"+275901115\",\"physicalAddress\":\"AAAAAAAAAA\"}";
        given(personService.addPerson(person)).willReturn(true);
        restPersonMockMvc.perform(post("/people").contentType(MediaType.APPLICATION_JSON).content(invalidPersonString))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenIdNumberShouldVerifyNoDuplicatesThereOf() throws Exception {
        given(personService.addPerson(any(Person.class)))
        .willThrow(new InternalException(
                "could not execute statement; SQL [n/a]; constraint [\"CONSTRAINT_INDEX_8 ON PUBLIC.PEOPLE(ID_NUMBER)"));
        restPersonMockMvc.perform(post("/people").contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void givenRequestForUpdatePersonShouldVerifyRequiredFieldsThereOf() throws Exception {
        String jsonPersonStringWithNoFirstname = "{\"id\":null,\"idNumber\":\"7000535578646\",\"lastname\":\"AAAAAAAAAA\",\"mobileNumber\":\"+27590111533\",\"physicalAddress\":\"AAAAAAAAAA\"}";
        given(personService.addPerson(person)).willReturn(true);
        restPersonMockMvc.perform(post("/people").contentType(MediaType.APPLICATION_JSON).content(jsonPersonStringWithNoFirstname))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenRequestForRemovePersonShouldVerifyPersonIsRemoved() throws Exception {
        long id = 1;
        given(personService.getPerson(id)).willReturn(person);
        restPersonMockMvc.perform(delete("/people/" + String.valueOf(id)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenRequestForUpdatePersonShouldVerifyPersonIsUpdated() throws Exception {
        given(personService.updatePerson(person)).willReturn(true);
        restPersonMockMvc.perform(post("/people").contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().isOk());
    }
}
