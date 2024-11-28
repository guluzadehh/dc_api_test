package com.dc.stud_api.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateStudentRequest {
    @JsonProperty("first_name")
    @NotBlank(message = "First name must be set")
    private String _fname;

    @JsonProperty("last_name")
    @NotBlank(message = "Last name must be set")
    private String _lname;

    @JsonProperty("surname")
    private String _surname;

    @JsonProperty("birthdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Birthdate must be set")
    private Date _birthdate;

    @JsonProperty("group")
    @NotNull(message = "Group must be set")
    private Integer _group;

    public String getFname() {
        return _fname;
    }

    public void setFname(String _fname) {
        this._fname = _fname;
    }

    public String getLname() {
        return _lname;
    }

    public void setLname(String _lname) {
        this._lname = _lname;
    }

    public String getSurname() {
        return _surname;
    }

    public void setSurname(String _surname) {
        this._surname = _surname;
    }

    public Date getBirthdate() {
        return _birthdate;
    }

    public void setBirthdate(Date _birthdate) {
        this._birthdate = _birthdate;
    }

    public Integer getGroup() {
        return _group;
    }

    public void setGroup(Integer _group) {
        this._group = _group;
    }
}
