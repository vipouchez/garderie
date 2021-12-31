package models;

import java.time.LocalDate;
import java.util.List;

public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private Address address;
    private LocalDate birthday;
    private String imageUrl;
    private String cinNumber;
    private String phoneNumber;

    private List<Assignement> assignements;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", address=" + address +
                ", birthday=" + birthday +
                ", imageUrl='" + imageUrl + '\'' +
                ", cinNumber='" + cinNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", assignements=" + assignements +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCinNumber() {
        return cinNumber;
    }

    public void setCinNumber(String cinNumber) {
        this.cinNumber = cinNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Assignement> getAssignements() {
        return assignements;
    }

    public void setAssignements(List<Assignement> assignements) {
        this.assignements = assignements;
    }
}
