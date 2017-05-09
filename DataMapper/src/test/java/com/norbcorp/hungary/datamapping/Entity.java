package com.norbcorp.hungary.datamapping;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by nor on 2017.05.06..
 */
public class Entity {

    private String name="Test";
    private Integer age=10;
    private Date dateOfRegistration;
    private String description=null;
    private List<String> strings=new LinkedList<String>();

    {
        strings.add("asdkjfasdf");
        strings.add("Rasdfa");
        strings.add("asdk");
    }

    public List<String> getStrings(){;
        return strings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }
}
