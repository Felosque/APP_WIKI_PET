package com.example.wikipets.estructural;

import com.example.wikipets.R;

import java.util.Date;

public class Pet {

    private int id;
    private String name;
    private Date discoveredDate;
    private String description;
    private double height;
    private int animalType;
    private int icon;
    private String status;

    public Pet(int id, String name, Date discoveredDate, String description, double height, int animalType) {
        this.name = name;
        this.discoveredDate = discoveredDate;
        this.description = description;
        this.height = height;
        this.animalType = animalType;
        this.id = id;
        this.icon = R.drawable.fault;
    }

    public Pet(String name, Date discoveredDate, String description, double height, int animalType, int pIcon) {
        this.name = name;
        this.discoveredDate = discoveredDate;
        this.description = description;
        this.height = height;
        this.animalType = animalType;
        this.id = id;
        this.icon = pIcon;
    }

    public Pet(String name, Date discoveredDate, String description, double height, int animalType) {
        this.name = name;
        this.discoveredDate = discoveredDate;
        this.description = description;
        this.height = height;
        this.animalType = animalType;
        this.icon = R.drawable.fault;
    }

    public Pet() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public int getAnimalType() {
        return animalType;
    }

    public void setAnimalType(int animalType) {
        this.animalType = animalType;
    }

    public int getIcon() { return icon; }

    public void setIcon(int icon) { this.icon = icon; }

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
