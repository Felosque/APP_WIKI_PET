package com.example.wikipets.servicios;

import com.example.wikipets.estructural.Pet;
import com.example.wikipets.estructural.TipoAnimal;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServicioPet {

    //private static final ArrayList <Pet> pets = new ArrayList<Pet>();

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
            //pets.add(pet);
            ServicioPersistencia.add(pet);
        }
    }

    public static boolean deletePet(String name){
        return ServicioPersistencia.delete(name);
    }

    public static Pet searchPetsByName(String name) throws Exception {
        return ServicioPersistencia.findByName(name);
    }

    public static Pet searchPetsByIDArray(int pId) throws Exception {
        try {
            return ServicioPersistencia.findAll().get(pId);
        }catch (Exception e){
            throw new Exception("Error al cargar la mascota.");
        }
    }

    public static int getQuantityPet(String pType) throws Exception {
        int count = 0;
        for (Pet pet : ServicioPersistencia.findAll()){
            if (pet.getAnimalType().equals(pType))
                count++;
        }
        return count;
    }

    public static Map<String, Integer> getQuantityPetsOfType() throws Exception {
        Map<String, Integer> typeAmountMap = new HashMap<>();
        for (String type : TipoAnimal.getTypeAnimal()){
            int quantity = getQuantityPet(type);
            if(quantity > 0) {
                typeAmountMap.put(type, quantity);
            }
        }
        return  typeAmountMap;
    }

    public static ArrayList<Pet> getPets() throws Exception {
        return ServicioPersistencia.findAll();
    }

    public static ArrayList<String> getPetsByNames() throws Exception {
        ArrayList<String> listaDeAnimales = new ArrayList<>();
        for (Pet i : getPets()){
            listaDeAnimales.add(i.getName());
        }
        return listaDeAnimales;
    }

    public static boolean updatePet(Pet petsUpdate) throws Exception {
        try{
            return ServicioPersistencia.update(petsUpdate);
        }catch (Exception e){
            throw new Exception("Error al actualizar al animal. No se ha encontrado.");
        }
    }
}
