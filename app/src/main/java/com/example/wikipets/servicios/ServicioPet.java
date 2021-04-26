package com.example.wikipets.servicios;

import android.content.Context;

import com.example.wikipets.estructural.Pet;
import com.example.wikipets.estructural.TipoAnimal;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServicioPet {

    //private static final ArrayList <Pet> pets = new ArrayList<Pet>();

    private static int numRegistros = 0;

    private ServicioPersistencia servicioPersistencia;

    public ServicioPet(Context context) {
        servicioPersistencia = new ServicioPersistencia(context);
    }

    public void addPets(Pet pet) throws Exception {
        try{
            servicioPersistencia.add(pet);
        }catch (Exception e){
            throw e;
        }
    }

    public boolean deletePet(String name){
        return servicioPersistencia.delete(name);
    }

    public boolean deletePetLogic(String name){
        return servicioPersistencia.deleteByStatus(name);
    }

    public Pet searchPetsByName(String name) throws Exception {
        Pet pet = null;
        try{
            pet = servicioPersistencia.findByName(name);
        }catch (Exception e) {
            throw e;
        }
        return pet;
    }

    public Pet searchPetsByIDArray(int pId) throws Exception {
        try {
            return servicioPersistencia.findAll().get(pId);
        }catch (Exception e){
            throw new Exception("Error al cargar la mascota.");
        }
    }

    public int getQuantityPet(String pType) throws Exception {
        int count = 0;
        for (Pet pet : servicioPersistencia.findAll()){
            if (pet.getAnimalType().equals(pType))
                count++;
        }
        return count;
    }

    public Map<String, Integer> getQuantityPetsOfType() throws Exception {
        Map<String, Integer> typeAmountMap = new HashMap<>();
        for (String type : TipoAnimal.getTypeAnimal()){
            int quantity = getQuantityPet(type);
            if(quantity > 0) {
                typeAmountMap.put(type, quantity);
            }
        }
        return  typeAmountMap;
    }

    public ArrayList<Pet> getPets() throws Exception {
        return servicioPersistencia.findAll();
    }

    public ArrayList<String> getPetsByNames() throws Exception {
        ArrayList<String> listaDeAnimales = new ArrayList<>();
        for (Pet i : getPets()){
            listaDeAnimales.add(i.getName());
        }
        return listaDeAnimales;
    }

    public boolean updatePet(Pet petsUpdate) throws Exception {
        try{
            return servicioPersistencia.update(petsUpdate.getName(), petsUpdate);
        }catch (Exception e){
            throw new Exception("Error al actualizar al animal. No se ha encontrado.");
        }
    }
}
