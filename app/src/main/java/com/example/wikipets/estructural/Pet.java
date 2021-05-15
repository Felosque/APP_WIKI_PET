package com.example.wikipets.estructural;

import com.example.wikipets.R;

import java.util.Date;

public class Pet {

    private String name;
    private Date discoveredDate;
    private String description;
    private double height;
    private String animalType;
    private int icon;
    private String status;

    public Pet(int id, String name, Date discoveredDate, String description, double height, String animalType) {
        this.name = name;
        this.discoveredDate = discoveredDate;
        this.description = description;
        this.height = height;
        this.animalType = animalType;
        this.icon = R.drawable.fault;
    }

    public Pet(String name, Date discoveredDate, String description, double height, String animalType, int pIcon) {
        this.name = name;
        this.discoveredDate = discoveredDate;
        this.description = description;
        this.height = height;
        this.animalType = animalType;
        this.icon = pIcon;
    }

    public Pet(String name, Date discoveredDate, String description, double height, String animalType) {
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

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public int getIcon() { return icon; }

    public void setIcon(int icon) { this.icon = icon; }

}
