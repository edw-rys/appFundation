package com.tim.appfundacion.Entities;

public class Nacionality {
    private int id_nacionality;
    private String name_nacionality;

    public Nacionality(int id_nacionality, String name_nacionality) {
        this.id_nacionality = id_nacionality;
        this.name_nacionality = name_nacionality;
    }

    public int getId_nacionality() {
        return id_nacionality;
    }

    public void setId_nacionality(int id_nacionality) {
        this.id_nacionality = id_nacionality;
    }

    public String getName_nacionality() {
        return name_nacionality;
    }

    public void setName_nacionality(String name_nacionality) {
        this.name_nacionality = name_nacionality;
    }

    @Override
    public String toString() {
        return "{" +id_nacionality +", " + name_nacionality + '}';
    }
}
