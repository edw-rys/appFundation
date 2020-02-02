package com.tim.appfundacion.Entities;

import java.util.Date;

public class DataWork {
    private Cargo cargo;
    private Department department;
    private String tipo_de_pago;
    private String salary;
    private Date date_of_admission;

    public DataWork() {
    }

    public DataWork(Cargo cargo, Department department, String tipo_de_pago, String salary, Date date_of_admission) {
        this.cargo = cargo;
        this.department = department;
        this.tipo_de_pago = tipo_de_pago;
        this.salary = salary;
        this.date_of_admission = date_of_admission;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getTipo_de_pago() {
        return tipo_de_pago;
    }

    public void setTipo_de_pago(String tipo_de_pago) {
        this.tipo_de_pago = tipo_de_pago;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Date getDate_of_admission() {
        return date_of_admission;
    }

    public void setDate_of_admission(Date date_of_admission) {
        this.date_of_admission = date_of_admission;
    }
}
