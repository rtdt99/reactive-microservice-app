package com.project.reactive.reactiveservice.model.request;

public class CustomerRequest {
    public Integer custId;
    public String  firstName;
    public String  lastName;
    public String  gender;
    public String  city;

    public CustomerRequest() {
    }

    public CustomerRequest(Integer custId, String firstName, String lastName, String gender, String city) {
        this.custId = custId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.city = city;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
