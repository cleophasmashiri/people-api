package com.example.peopleapi.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "people")
public class Person implements Serializable {

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long id;

    @NotNull
    @Pattern(regexp = "(^[0-9]{13}$)")
    @Column(name = "id_number", nullable = false, unique = true)
    private String idNumber;

    @NotNull
    @Size(min = 2)
    @Column(name = "firstname", nullable = false)
    private String firstname;

    @NotNull
    @Size(min = 2)
    @Column(name = "lastname", nullable = false)
    private String lastname;

    @NotNull
    @Pattern(regexp = "(^\\+27[0-9]{9}$)")
    @Column(name = "mobile_number", nullable = false, unique = true)
    private String mobileNumber;

    public Long getId() {
        return id;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    @Column(name = "physical_address")
    private String physicalAddress;;

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public Person() {
        idNumber = null;
        firstname = null;
        lastname = null;
        mobileNumber = null;
        physicalAddress = null;
    }

}
