package com.dc.stud_api.models;

import java.util.Date;

public class Student {
    private String _fname;
    private String _lname;
    private String _surname;
    private Date _birthdate;
    private Integer _group;
    private Integer _id;

    public Student(String fName, String lName, String surname, Date birthdate, Integer group) {
        _fname = fName;
        _lname = lName;
        _surname = surname;
        _birthdate = birthdate;
        _group = group;
    }

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

    public Integer getId() {
        return _id;
    }

    public void setId(Integer _id) {
        this._id = _id;
    }
}
