package com.example.wikipets.servicios;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.wikipets.estructural.Pet;
import com.example.wikipets.estructural.TipoAnimal;
import com.example.wikipets.interfaces.CRUDPet;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServicioPet {

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static CRUDPet crudPet;

    public ServicioPet(Context context) {
    }

    public static void setCrudPet(CRUDPet crudPet) {
        ServicioPet.crudPet = crudPet;
    }

    public static void addPets(Pet pet) {
        db.collection("pets")
                .document(String.valueOf(pet.getName()))
                .set(pet)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            crudPet.showMessage("Animal añadido satisfactoriamente.");
                        }else{
                            crudPet.showMessage("No se ha podido añadir al animal.");}
                    }
                });
    }

    public static void deletePet(String name){
        db.collection("pets")
                .document(String.valueOf(name))
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            crudPet.showMessage("Animal se borró el animal de la base de datos.");
                        }else{
                            crudPet.showMessage("No se ha podido borrar al animal de la base de datos.");}
                    }
                });
    }

    public static void deletePetLogic(String name){
        db.collection("pets")
                .document(String.valueOf(name))
                .update("status", "DL")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            crudPet.showMessage("Animal se borró logicamente.");
                        }else{
                            crudPet.showMessage("No se ha podido borrar al animal.");}
                    }
                });
    }

    public static void searchPetsByName(String name) {
        try {
            db.collection("pets")
                    .whereEqualTo("status", "AC")
                    .whereEqualTo("name", name)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                Pet pet = null;
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    pet = document.toObject(Pet.class);
                                }
                                crudPet.showOnePet(pet);
                            } else {
                                crudPet.showMessage("¡Ups! Al parecer la lista está vacia");
                            }
                        }
                    });
        }catch (Exception e){
            e.getMessage();
        }
    }

    private static int getQuantityPet(ArrayList<Pet> petsuwus, String tipoAnimal) {
        int count = 0;
        for(Pet uwu: petsuwus){
            if(uwu.getAnimalType().equals(tipoAnimal)){
                count++;
            }
        }
        return count;
    }

    public static void getQuantityPetsOfType() throws Exception{
        db.collection("pets")
                .whereEqualTo("status", "AC")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            ArrayList <Pet> pets = new ArrayList<>();
                            for(QueryDocumentSnapshot document :  task.getResult()){
                                Pet pet = document.toObject(Pet.class);
                                pets.add(pet);
                            }
                            pets.size();
                            ArrayList<String> animales = TipoAnimal.getTypeAnimal();
                            Map<String, Integer> typeAmountMap = new HashMap<>();
                            for(String uwu: animales){
                                int count = getQuantityPet(pets,uwu);
                                typeAmountMap.put(uwu,count);
                            }
                            crudPet.petByType(typeAmountMap);
                        }else{
                            crudPet.showMessage("¡Ups! Al parecer la lista está vacia");
                        }
                    }
                });
    }

    public static void getPets() {
        db.collection("pets")
                .whereEqualTo("status", "AC")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            ArrayList <Pet> pets = new ArrayList<>();
                            for(QueryDocumentSnapshot document :  task.getResult()){
                                Pet pet = document.toObject(Pet.class);
                                pets.add(pet);
                            }
                            crudPet.showPets(pets);
                        }else{
                            crudPet.showMessage("¡Ups! Al parecer la lista está vacia");
                        }
                    }
                });
    }

    public static void getPetsByStatus() {
        db.collection("pets")
                .whereEqualTo("status", "DL")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            ArrayList <Pet> pets = new ArrayList<>();
                            for(QueryDocumentSnapshot document :  task.getResult()){
                                Pet pet = document.toObject(Pet.class);
                                pets.add(pet);
                            }
                            crudPet.showPets(pets);
                        }else{
                            crudPet.showMessage("¡Ups! Al parecer la lista está vacia");
                        }
                    }
                });
    }

    public static void updatePet(Pet petsUpdate) {
        db.collection("pets")
                .document(String.valueOf(petsUpdate.getName()))
                .set(petsUpdate)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            crudPet.showMessage("Animal se actualizó satisfactoriamente.");
                        }else{
                            crudPet.showMessage("No se ha podido actualizar al animal.");}
                    }
                });
    }
}
