package ru.appline.logic;

public class User {
    //сервлет, который будет хранить в себе пользователей
    //известно что у пользователя есть имя,фамилия,зп
    private String name;

    private String surname;

    private double salary;

    //конструктор
    public User(String name, String surname, double salary) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }

    //геттеры/сеттеры
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
