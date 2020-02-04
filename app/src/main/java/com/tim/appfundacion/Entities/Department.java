package com.tim.appfundacion.Entities;

public class Department {
    private int id_department;
    private String name_department;

    public Department(int id, String name) {
        this.id_department = id;
        this.name_department = name;
    }

    public int getId() {
        return id_department;
    }

    public void setId(int id) {
        this.id_department = id;
    }

    public String getName() {
        return name_department;
    }

    public void setName(String name) {
        this.name_department = name;
    }

    @Override
    public String toString() {
        return "{"+ id_department +", "+ name_department +'}';
    }
}
