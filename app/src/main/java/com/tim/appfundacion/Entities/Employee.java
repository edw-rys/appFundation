package com.tim.appfundacion.Entities;

import java.util.Date;

public class Employee {
    private int id_employee;
    private String DNI;
    private String name;
    private String last_name;
    private String telf;
    private Date birthDate;
    private String gender;
    private Nacionality nacionality;


    // Trabajo
    private DataWork dataWork;
    // datos académicos
    private String level_academic;
    private String title_academic;

    public Employee() {
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getTelf() {
        return telf;
    }

    public void setTelf(String telf) {
        this.telf = telf;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Nacionality getNacionality() {
        return nacionality;
    }

    public void setNacionality(Nacionality nacionality) {
        this.nacionality = nacionality;
    }

    public DataWork getDataWork() {
        return dataWork;
    }

    public void setDataWork(DataWork dataWork) {
        this.dataWork = dataWork;
    }

    public String getLevel_academic() {
        return level_academic;
    }

    public void setLevel_academic(String level_academic) {
        this.level_academic = level_academic;
    }

    public String getTitle_academic() {
        return title_academic;
    }

    public void setTitle_academic(String title_academic) {
        this.title_academic = title_academic;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id_employee=" + id_employee +
                ", DNI='" + DNI + '\'' +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", telf='" + telf + '\'' +
                ", birthDate=" + birthDate +
                ", gender='" + gender + '\'' +
                ", nacionality=" + nacionality +
                ", dataWork=" + dataWork +
                ", level_academic='" + level_academic + '\'' +
                ", title_academic='" + title_academic + '\'' +
                '}';
    }
}
