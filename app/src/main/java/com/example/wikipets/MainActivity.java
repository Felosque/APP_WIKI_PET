package com.example.wikipets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.wikipets.estructural.Pet;
import com.example.wikipets.servicios.ServicioFuncionalidades;
import com.example.wikipets.servicios.ServicioPersistencia;
import com.example.wikipets.servicios.ServicioPet;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTheme(R.style.Theme_AppCompat_Light);
    }

    public void btnIngresar_Click (View view) {
        /*try {
            int mami = ServicioFuncionalidades.getImageTipoAnimal("MAMIFERO");
            int ave  = ServicioFuncionalidades.getImageTipoAnimal("AVE");
            int anfi = ServicioFuncionalidades.getImageTipoAnimal("ANFIBIO");
            int rep = ServicioFuncionalidades.getImageTipoAnimal("REPTIL");
            int pez = ServicioFuncionalidades.getImageTipoAnimal("PECES");
            int ara = ServicioFuncionalidades.getImageTipoAnimal("ARACNIDOS");
            int mol = ServicioFuncionalidades.getImageTipoAnimal("MOLUSCOS");
            ServicioPet.addPets(new Pet("Perro", new Date(), "La desc corta", 0.3, "MAMIFERO", mami));
            ServicioPet.addPets(new Pet("Loro", new Date(), "La desc corta", 0.3, "AVE", ave));
            ServicioPet.addPets(new Pet("Rana", new Date(), "La desc corta", 6, "ANFIBIO", anfi));
            ServicioPet.addPets(new Pet("Culebra", new Date(), "La desc larga más larga que mi lista de " +
                    "sdasdadsadasdasdasdasdasdasdada das dads ad sada sda sda sd ad sada  sda dsas da sda sd asda sda sda das " +
                    "ad sada sda sdads  ads das dads adasf lsand mnda,sm dn, amnsdm, ansdm ,asndm, andm sand, ansdm, nas,mdn a,msnd as" +
                    "asdasdnkasnd jkans djkansk djan ksdna ksndk jadk   adjksjkdan ksad k jdna d sadlas dasd añsf ads asfs d" +
                    "as ads dasd asd adafe gthf gjyguj ggh ghfhgfhg fh fgamigsaddadasdasdasuitos jajajaa jejjejejejejjee", 34, "REPTIL", rep));
            ServicioPet.addPets(new Pet("Piranicu", new Date(), "La desc corta", 34, "PECES", pez));
            ServicioPet.addPets(new Pet("Araña", new Date(), "La desc corta", 12, "ARACNIDOS", ara));
            ServicioPet.addPets(new Pet("Ostra", new Date(), "La desc corta", 2, "MOLUSCOS", mol));
        }catch (Exception e){

        }*/


        Intent intent = new Intent(this, GUIMenu.class);
        startActivity(intent);
    }


}