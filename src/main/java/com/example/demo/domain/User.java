package com.example.demo.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String login;

    private String name;

    private String surname;

    private String email;

    private String password;


    public User() {
    }

    public User(String login, String name, String surname, String email, String password) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public void change(User other) {
        setEmail(other.getEmail());
        setName(other.getName());
        setSurname(other.getSurname());
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}
