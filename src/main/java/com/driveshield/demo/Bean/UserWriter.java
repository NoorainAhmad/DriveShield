package com.driveshield.demo.Bean;

import java.util.Date;

public class UserWriter {

    private String name;
    private Date date_of_birth;
    private String gender;
    private String address;
    private Date dateOfJoin;
    private String underwriterId;
    private String password;
    private String role;
    private boolean isAdmin;
    private boolean isdeleted;

    public Date getDateOfJoin() {
        return dateOfJoin;
    }

    public void setDateOfJoin(Date dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }

    public boolean isIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUnderwriterId() {
        return underwriterId;
    }

    public void setUnderwriterId(String underwriterId) {
        this.underwriterId = underwriterId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Default constructor
    public UserWriter() {
    }

    public UserWriter(String name, Date date_of_birth, String gender, String address, Date dateOfJoin,
            String underwriterId, String password, boolean isAdmin, boolean isdeleted) {
        super();
        this.name = name;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
        this.address = address;
        this.dateOfJoin = dateOfJoin;
        this.underwriterId = underwriterId;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isdeleted = isdeleted;
    }

    public UserWriter(String underwriterId, String name, Date date_of_birth, String gender, String address,
            Date dateOfJoin, boolean isAdmin, boolean isdeleted) {
        this.underwriterId = underwriterId;
        this.name = name;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
        this.address = address;
        this.dateOfJoin = dateOfJoin;
        this.isAdmin = isAdmin;
        this.isdeleted = isdeleted;
    }

    public UserWriter(String underwriterId, String name, Date date_of_birth, String gender, String address,
            Date dateOfJoin) {
        this.underwriterId = underwriterId;
        this.name = name;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
        this.address = address;
        this.dateOfJoin = dateOfJoin;
    }

    public UserWriter(String name, String underwriterId, String password, boolean isAdmin) {
        this.underwriterId = underwriterId;
        this.name = name;
        this.isAdmin = isAdmin;
        this.password = password;
    }

    public UserWriter(String name, String password, boolean isAdmin) {

        this.name = name;
        this.isAdmin = isAdmin;
        this.password = password;
    }

    public UserWriter(String name, String underwriterId, String password, boolean isAdmin, String role) {
        this.underwriterId = underwriterId;
        this.name = name;
        this.isAdmin = isAdmin;
        this.password = password;
        this.role = role;
    }

}
