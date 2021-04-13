package com.example.wikipets.servicios;

import com.example.wikipets.estructural.Pet;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class ServicioPersistencia {

    public static final int NAME_SIZE  = 20;
    public static final int DISCOVERED_DATE_SIZE  = 10;
    public static final int DESCRIPTION_SIZE  = 100;
    public static final int HEIGHT_SIZE  = 10;
    public static final int ANIMAL_TYPE_SIZE  = 20;
    public static final int ICON_SIZE  = 10;
    public static final int STATUS_SIZE  = 2;

    private static File directorio ;
    private static String nombreArchivo = "";

    public static File getDirectorio() {
        return directorio;
    }

    public static void setDirectorio(File pDirectorio) {
        ServicioPersistencia.directorio = pDirectorio;
    }

    public static String getNombreArchivo() {
        return nombreArchivo;
    }

    public static void setNombreArchivo(String nombreArchivo) {
        ServicioPersistencia.nombreArchivo = nombreArchivo;
    }

    private static String setStringSize(String cad, int tam){
        int dif = 0;
        if(cad.length() > tam){
            cad = cad.substring(0, tam);
            return cad;
        }
        dif = tam - cad.length();
        cad = cad + new String(new char[dif]).replace('\0', ' ');
        return cad;
    }


    public static void add(Pet pet) throws Exception {
        File archivo;
        RandomAccessFile raf;

        archivo = new File(directorio, nombreArchivo);

        try{
            raf = new RandomAccessFile(archivo,"rw");
            raf.seek(archivo.length());

            //Escritura
            raf.writeUTF( setStringSize(pet.getName(), NAME_SIZE) );
            raf.writeUTF( setStringSize(ServicioFuncionalidades.dateToString(pet.getDiscoveredDate()), DISCOVERED_DATE_SIZE) );
            raf.writeUTF( setStringSize(pet.getDescription(), DESCRIPTION_SIZE) );
            raf.writeUTF( setStringSize( "" + pet.getHeight(), HEIGHT_SIZE) );
            raf.writeUTF( setStringSize(pet.getAnimalType(), ANIMAL_TYPE_SIZE) );
            raf.writeUTF( setStringSize( "" + pet.getIcon(), ICON_SIZE) );
            pet.setStatus("AC");
            raf.writeUTF( setStringSize( pet.getStatus(), STATUS_SIZE) );
            raf.close();

        }catch(Exception e){
            throw e;
        }
    }

    public static ArrayList<Pet> findAll() throws Exception {
        ArrayList<Pet> pets = new ArrayList<>();

        File archivo;
        RandomAccessFile raf;
        archivo = new File(directorio, nombreArchivo);

        try{
            raf = new RandomAccessFile(archivo,"r");
            while(raf.getFilePointer() < archivo.length())
            {
                Pet onePet = new Pet();
                onePet.setName(raf.readUTF().trim());
                onePet.setDiscoveredDate(ServicioFuncionalidades.ParseFecha(raf.readUTF().trim()));
                onePet.setDescription(raf.readUTF().trim());
                onePet.setHeight(Double.parseDouble(raf.readUTF().trim()));
                onePet.setAnimalType("" + raf.readUTF().trim());
                onePet.setIcon(Integer.parseInt(raf.readUTF().trim()));
                onePet.setStatus(raf.readUTF().trim());
                if(onePet.getStatus().equals("AC")) {
                    pets.add(onePet);
                }
            }

            raf.close();
        }catch(Exception e){

            throw e;
        }
        return pets;
    }

}
