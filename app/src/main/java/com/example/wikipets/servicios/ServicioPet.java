package com.example.wikipets.servicios;

import com.example.wikipets.estructural.Pet;

import java.util.ArrayList;

public class ServicioPet {

    private static final ArrayList <Pet> pets = new ArrayList<Pet>();

    public ServicioPet() {

    }

    public static void addPets(Pet pet){
        pet.setId(pets.size() + 1);
        pets.add(pet);
    }

    public static boolean deletePet(String name){
        return pets.remove(searchPets(name));
    }

    public static Pet searchPets(String name) {
        Pet pet = null;

        for (Pet petx : pets) {
            if (petx.getName().equals(name)) {
                pet = petx;
                return pet;
            }
        }
        return pet;

    }

    public static ArrayList<Pet> getPets(){
        return pets;
    }

    public static boolean updatePet(Pet petsUpdate){
        Pet pet = null;
        try{
            pet = searchPets(petsUpdate.getName());
            pet.setAnimalType(petsUpdate.getAnimalType());
            pet.setDescription(petsUpdate.getDescription());
            pet.setDiscoveredDate(petsUpdate.getDiscoveredDate());
            pet.setHeight(petsUpdate.getHeight());
            return true;
        }catch (Exception e){
            e.getMessage();
            return false;
        }
    }
}
