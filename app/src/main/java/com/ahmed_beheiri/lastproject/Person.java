package com.ahmed_beheiri.lastproject;

/**
 * Created by ahmed_beheiri on 15/09/17.
 */

public class Person {
    private String Name;
    private int id;
    private String Type;
    private String gender;
    private int age;

    @Override
    public String toString() {
        return " Name is " + Name + "\n" +
                " id is " + id + "\n" +
                " Type is " + Type + "\n" +
                " gender is " + gender + "\n" +
                " age is " + age;
    }


    public Person(String name, String type, String gender, int age) {
        setName(name);
        setType(type);
        this.setGender(gender);
        this.setAge(age);
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
