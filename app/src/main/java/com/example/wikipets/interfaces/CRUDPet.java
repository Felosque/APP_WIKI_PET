package com.example.wikipets.interfaces;

import com.example.wikipets.estructural.Pet;

import java.util.ArrayList;

public interface CRUDPet {

    public void showMessage(String message);
    public void showPets(ArrayList<Pet> pets);
    public void showOnePet(Pet pet);

}
