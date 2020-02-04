package com.tim.appfundacion.Entities;

public class Cargo {
    private int id_cargo;
    private String nombre_cargo;

    public Cargo(int id_cargo, String name) {
        this.id_cargo = id_cargo;
        this.nombre_cargo = name;
    }

    public int getId() {
        return id_cargo;
    }

    public void setId(int id) {
        this.id_cargo = id;
    }

    public String getName() {
        return nombre_cargo;
    }

    public void setName(String name) {
        this.nombre_cargo = name;
    }

    @Override
    public String toString() {
        return "{" + id_cargo + ", " + nombre_cargo +  '}';
    }
}
