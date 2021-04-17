package com.example.wikipets.servicios;

import com.example.wikipets.estructural.Pet;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;

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

    public static int getRegisterSize(){
        return (NAME_SIZE + 2) + (DISCOVERED_DATE_SIZE + 2) + (DESCRIPTION_SIZE + 2) + (HEIGHT_SIZE + 2)
                + (ANIMAL_TYPE_SIZE + 2)  + (ICON_SIZE + 2) + (STATUS_SIZE + 2);
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

    public static boolean delete(String pName){
        /*boolean delete = false;
        File archivo;
        RandomAccessFile raf;
        archivo = new File(directorio, nombreArchivo);*/

        try {
            /*raf = new RandomAccessFile(archivo, "rw");
            Long pos = getPointerPosByName(pName);
            if(pos != null) {
                pos += (getRegisterSize() - (STATUS_SIZE + 2));
                raf.seek(pos);
                raf.writeUTF(setStringSize("DL", STATUS_SIZE));
                delete = true;
            }
            raf.close();*/

            Pet petDelete = new Pet();
            petDelete.setName("NONE");
            petDelete.setDiscoveredDate(new Date());
            petDelete.setDescription("NONE");
            petDelete.setHeight(-1);
            petDelete.setAnimalType("NONE");
            petDelete.setIcon(-1);
            petDelete.setStatus("DL");
            return update(pName, petDelete);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean update(String name, Pet pet){
        boolean update = false;
        File archivo;
        RandomAccessFile raf;
        archivo = new File(directorio, nombreArchivo);

        try {
            raf = new RandomAccessFile(archivo, "rw");
            Long pos = getPointerPosByName(name);
            if(pos != null) {
                raf.seek(pos);
                raf.writeUTF( setStringSize(pet.getName(), NAME_SIZE) );
                raf.writeUTF( setStringSize(ServicioFuncionalidades.dateToString(pet.getDiscoveredDate()), DISCOVERED_DATE_SIZE) );
                raf.writeUTF( setStringSize(pet.getDescription(), DESCRIPTION_SIZE) );
                raf.writeUTF( setStringSize( "" + pet.getHeight(), HEIGHT_SIZE) );
                raf.writeUTF( setStringSize(pet.getAnimalType(), ANIMAL_TYPE_SIZE) );
                pet.setIcon(ServicioFuncionalidades.getImageTipoAnimal(pet.getAnimalType()));
                raf.writeUTF( setStringSize( "" + pet.getIcon(), ICON_SIZE) );
                raf.writeUTF( setStringSize( pet.getStatus(), STATUS_SIZE) );
                update = true;
            }
            raf.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return update;
    }

    public static Pet findByName(String pName) throws IOException {

        File archivo;
        RandomAccessFile raf;
        archivo = new File(directorio, nombreArchivo);
        Pet onePet = null;

        try {
            raf = new RandomAccessFile(archivo, "rw");
            Long pos = getPointerPosByName(pName);
            if(pos != null) {
                raf.seek(getPointerPosByName(pName));
                onePet = new Pet();
                onePet.setName(raf.readUTF().trim());
                onePet.setDiscoveredDate(ServicioFuncionalidades.ParseFecha(raf.readUTF().trim()));
                onePet.setDescription(raf.readUTF().trim());
                onePet.setHeight(Double.parseDouble(raf.readUTF().trim()));
                onePet.setAnimalType("" + raf.readUTF().trim());
                onePet.setIcon(Integer.parseInt(raf.readUTF().trim()));
                onePet.setStatus(raf.readUTF().trim());
            }
            raf.close();
        }catch (Exception e){
            throw e;
        }

        if (onePet != null && onePet.getStatus().equals("DL")){
            onePet = null;
        }
        return onePet;
    }

    public static Long getPointerPosByName(String pName){
        File archivo;
        RandomAccessFile raf;
        archivo = new File(directorio, nombreArchivo);
        try{
            raf = new RandomAccessFile(archivo,"rw");
            int iterator = 0;
            while(raf.getFilePointer() < archivo.length()){
                String name = raf.readUTF().trim();
                pName = pName.trim();
                if (pName.equals(name.trim())){
                    return raf.getFilePointer() - NAME_SIZE - 2;
                }
                else{
                    iterator++;
                    raf.seek( iterator * getRegisterSize());
                }
            }
            raf.close();
        }catch(Exception e){
            System.out.println("Error : " + e);
        }
        return null;
    }

}
