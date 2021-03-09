package com.example.wikipets.servicios;

import com.example.wikipets.estructural.Pet;

import java.util.ArrayList;

public class ServicioPet {

    private static final ArrayList <Pet> pets = new ArrayList<Pet>();

    private static int numRegistros = 0;

    public ServicioPet() {

    }

    public static void addPets(Pet pet) throws Exception {

        Pet buscado = searchPetsByName(pet.getName());
        if (buscado != null) {
            throw new Exception("Ya existe un animal con ese nombre");
        }else {
            numRegistros++;
            pet.setId(numRegistros);
            pets.add(pet);
        }
    }

    public static boolean deletePet(String name){
        return pets.remove(searchPetsByName(name));
    }

    public static Pet searchPetsByName(String name) {
        Pet pet = null;
        for (Pet petx : pets) {
            if (petx.getName().toUpperCase().equals(name.toUpperCase())) {
                pet = petx;
                return pet;
            }
        }
        return pet;

    }

    public static Pet searchPetsByIDArray(int pId) throws Exception {
        try {
            return pets.get(pId);
        }catch (Exception e){
            throw new Exception("Error al cargar la mascota.");
        }
    }

    public static Pet searchPetsByID(int pId) {
        Pet pet = null;
        for (Pet petx : pets) {
            if (petx.getId() == pId) {
                pet = petx;
                return pet;
            }
        }
        return pet;
    }

    public static ArrayList<Pet> getPets(){
        return pets;
    }

    public static ArrayList<String> getPetsByNames(){
        ArrayList<String> listaDeAnimales = new ArrayList<>();
        for (Pet i : pets){
            listaDeAnimales.add(i.getName());
        }
        return listaDeAnimales;
    }

    public static boolean updatePet(Pet petsUpdate) throws Exception {

        for (Pet petx : pets) {
            if (petx.getName().toUpperCase().equals(petsUpdate.getName().toUpperCase()) && petx.getId() != petsUpdate.getId()) {
                throw new Exception("Se ha encontrado otro animal con el mismo nombre. ID: " + petx.getId());
            }
        }

        Pet pet = null;
        try{
            pet = searchPetsByID(petsUpdate.getId());
            pet.setName(petsUpdate.getName());
            pet.setAnimalType(petsUpdate.getAnimalType());
            pet.setDescription(petsUpdate.getDescription());
            pet.setDiscoveredDate(petsUpdate.getDiscoveredDate());
            pet.setHeight(petsUpdate.getHeight());
            return true;
        }catch (Exception e){
            throw new Exception("Error al actualizar al animal. No se ha encontrado.");
        }
    }
}
