package com.example.wikipets.estructural;

import java.util.Date;

public class Pet {

    private int id;
    private String name;
    private Date discoveredDate;
    private String description;
    private double height;
    private ANIMAL_TYPE animalType;

    public Pet(int id, String name, Date discoveredDate, String description, double height, Pet.ANIMAL_TYPE animalType) {
        this.name = name;
        this.discoveredDate = discoveredDate;
        this.description = description;
        this.height = height;
        this.animalType = animalType;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDiscoveredDate() {
        return discoveredDate;
    }

    public void setDiscoveredDate(Date discoveredDate) {
        this.discoveredDate = discoveredDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public ANIMAL_TYPE getAnimalType() {
        return animalType;
    }

    public void setAnimalType(ANIMAL_TYPE animalType) {
        this.animalType = animalType;
    }

    public enum ANIMAL_TYPE{
        AVE,
        MAMIFERO,
        ANFIBIO,
        REPTIL,
        PECES,
        ARACNIDOS,
        MOLUSCOS
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", discoveredDate='" + discoveredDate + '\'' +
                ", description='" + description + '\'' +
                ", height=" + height +
                ", animalType=" + animalType +
                '}';
    }
}
