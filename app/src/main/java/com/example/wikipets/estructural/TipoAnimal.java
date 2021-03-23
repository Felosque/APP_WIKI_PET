package com.example.wikipets.estructural;

import android.widget.Spinner;

import java.util.ArrayList;

public class TipoAnimal {

    private static ArrayList<String> typeAnimal = new ArrayList<String>();

    private static void addTypeAnimals(String type){typeAnimal.add(type);}

    public static void loadAnimals(){
        addTypeAnimals("MAMIFERO");
        addTypeAnimals("AVE");
        addTypeAnimals("ANFIBIO");
        addTypeAnimals("REPTIL");
        addTypeAnimals("PECES");
        addTypeAnimals("ARACNIDOS");
        addTypeAnimals("MOLUSCOS");
    }

    public static ArrayList<String> getTypeAnimal() {
        return typeAnimal;
    }

    public static int getIndexSpinnerValue(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }
        return 0;
    }
}