package ru.ykul.model;

public class User {

    private int id;
    private String surname;
    private String name;
    private String middleName;

    public User(int id, String surname, String name, String middleName) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
}
