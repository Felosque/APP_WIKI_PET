package com.example.wikipets.estructural;

import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TipoAnimal {

    private static ArrayList<String> typeAnimal = new ArrayList<String>();

    public static ArrayList<String> getTypeAnimal() {
        ArrayList<String> animales = new ArrayList<>();
        animales.add("MAMIFERO");
        animales.add("AVE");
        animales.add("ANFIBIO");
        animales.add("REPTIL");
        animales.add("PECES");
        animales.add("ARACNIDOS");
        animales.add("MOLUSCOS");
        return animales;
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
