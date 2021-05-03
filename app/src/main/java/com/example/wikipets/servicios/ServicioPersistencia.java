package com.example.wikipets.servicios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.wikipets.estructural.Pet;
import com.example.wikipets.estructural.TipoAnimal;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;

public class ServicioPersistencia  extends SQLiteOpenHelper {

    public static final String DB_NAME = "WIKIPETS.db";
    public static final String TABLE_PET = "PETS";
    public static final String TABLE_TYPE = "TYPEANIMAL";

    public static final String TY_ID = "IDTYPE";
    public static final String AT_NAME = "NAME";
    public static final String AT_DATE = "DISCOVER_DATE";
    public static final String AT_DESCRIPTION = "DESCRIPTION";
    public static final String AT_HEIGHT = "HEIGHT";
    public static final String AT_ANIMAL_TYPE = "ANIMAL_TYPE";
    public static final String AT_ICON = "ICON";
    public static final String AT_STATUS = "STATUS";
    public static final String TY_NAME = "TYPE_ANIMAL";

    public ServicioPersistencia(Context context){
        super(context, DB_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String cadSQL = "";

        cadSQL = "CREATE TABLE " + TABLE_TYPE + " ("
                + TY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TY_NAME + " TEXT NOT NULL "
                + ")";

        db.execSQL(cadSQL);
        addTipoAnimal(db);

        cadSQL = "CREATE TABLE " + TABLE_PET + " ("
                + AT_NAME + " TEXT NOT NULL PRIMARY KEY, "
                + AT_DATE + " INTEGER NOT NULL, "
                + AT_DESCRIPTION + " TEXT NOT NULL, "
                + AT_HEIGHT + " REAL NOT NULL, "
                + AT_ANIMAL_TYPE + " INTEGER NOT NULL,"
                + AT_ICON + " INTEGER NOT NULL, "
                + AT_STATUS + " TEXT NOT NULL, "
                + "FOREIGN KEY(ANIMAL_TYPE) REFERENCES TYPEANIMAL(IDTYPE)"
                + ")";
        db.execSQL(cadSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String cadSQL = "";
        cadSQL = "DROP TABLE IF EXISTS " + TABLE_PET;
        db.execSQL(cadSQL);
        cadSQL = "DROP TABLE IF EXISTS " + TABLE_TYPE;
        db.execSQL(cadSQL);
    }

    public void addTipoAnimal(SQLiteDatabase db){
        ContentValues contentValues;
        contentValues = new ContentValues();
        contentValues.put(TY_NAME, "MAMIFERO");
        db.insert(TABLE_TYPE, null, contentValues);
        contentValues = new ContentValues();
        contentValues.put(TY_NAME, "AVE");
        db.insert(TABLE_TYPE, null, contentValues);
        contentValues = new ContentValues();
        contentValues.put(TY_NAME, "ANFIBIO");
        db.insert(TABLE_TYPE, null, contentValues);
        contentValues = new ContentValues();
        contentValues.put(TY_NAME, "REPTIL");
        db.insert(TABLE_TYPE, null, contentValues);
        contentValues = new ContentValues();
        contentValues.put(TY_NAME, "PECES");
        db.insert(TABLE_TYPE, null, contentValues);
        contentValues = new ContentValues();
        contentValues.put(TY_NAME, "ARACNIDOS");
        db.insert(TABLE_TYPE, null, contentValues);
        contentValues = new ContentValues();
        contentValues.put(TY_NAME, "MOLUSCOS");
        db.insert(TABLE_TYPE, null, contentValues);
    }

    public ArrayList<TipoAnimal> findAllType(){
        SQLiteDatabase db = this.getWritableDatabase();
        String cadSQL = "SELECT * FROM " + TABLE_TYPE;
        Cursor res = db.rawQuery(cadSQL, null);
        ArrayList<TipoAnimal> typeAnimal = new ArrayList<>();
        while(res.moveToNext()){
            TipoAnimal tipoAnimal = new TipoAnimal();
            tipoAnimal.setCodigo(res.getInt(0));
            tipoAnimal.setNombre(res.getString(1));
            typeAnimal.add(tipoAnimal);
        }
        return typeAnimal;
    }

    public TipoAnimal findTypeById(int iduwu){
        SQLiteDatabase db = this.getWritableDatabase();
        String cadSQL = "SELECT * FROM " + TABLE_TYPE + " WHERE IDTYPE " + " = " + iduwu;
        Cursor res = db.rawQuery(cadSQL, null);
        TipoAnimal typeAnimal = new TipoAnimal();
        while(res.moveToNext()){
            TipoAnimal tipoAnimal = new TipoAnimal();
            tipoAnimal.setCodigo(res.getInt(0));
            tipoAnimal.setNombre(res.getString(1));
            typeAnimal = tipoAnimal;
        }
        return typeAnimal;
    }

    public boolean add(Pet pet) throws Exception {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues;
        long result;

        contentValues = new ContentValues();
        contentValues.put(AT_NAME, pet.getName());
        contentValues.put(AT_DATE, ServicioFuncionalidades.persistDate(pet.getDiscoveredDate()) );
        contentValues.put(AT_DESCRIPTION, pet.getDescription() );
        contentValues.put(AT_HEIGHT, pet.getHeight() );
        contentValues.put(AT_ANIMAL_TYPE, pet.getAnimalType() );
        contentValues.put(AT_ICON, pet.getIcon() );
        contentValues.put(AT_STATUS, "AC");

        result = db.insertOrThrow(TABLE_PET, null, contentValues);

        if(result == -1){
            return false;
        }
        return true;
    }

    public ArrayList<Pet> findAll() throws Exception {
        SQLiteDatabase db = this.getWritableDatabase();
        String cadSQL = "SELECT * FROM " + TABLE_PET + " WHERE " + AT_STATUS + " = 'AC' ";
        Cursor res = db.rawQuery(cadSQL, null);
        ArrayList<Pet> pets = new ArrayList<>();

        if(res.getCount() <= 0){
           throw new Exception("No existen animales en la base de datos.");
        }

        while(res.moveToNext()){

            Pet newPet = new Pet();
            newPet.setName(res.getString(0));
            newPet.setDiscoveredDate(ServicioFuncionalidades.loadDate(res, 1));
            newPet.setDescription(res.getString(2));
            newPet.setHeight(res.getDouble(3));
            newPet.setAnimalType(res.getInt(4));
            newPet.setIcon(res.getInt(5));
            newPet.setStatus(res.getString(6));

            pets.add(newPet);
        }
        return pets;
    }

    public ArrayList<Pet> findAllByState() throws Exception {
        SQLiteDatabase db = this.getWritableDatabase();
        String cadSQL = "SELECT * FROM " + TABLE_PET + " WHERE " + AT_STATUS + " = 'DL' ";
        Cursor res = db.rawQuery(cadSQL, null);

        ArrayList<Pet> pets = new ArrayList<>();

        if(res.getCount() == 0){
            throw new Exception("No existen animales en la base de datos.");
        }

        while(res.moveToNext()){

            Pet newPet = new Pet();

            newPet.setName(res.getString(0));
            newPet.setDiscoveredDate(ServicioFuncionalidades.loadDate(res, 1));
            newPet.setDescription(res.getString(2));
            newPet.setHeight(res.getDouble(3));
            newPet.setAnimalType(res.getInt(4));
            newPet.setIcon(res.getInt(5));
            newPet.setStatus(res.getString(6));

            pets.add(newPet);
        }
        return pets;
    }

    public boolean deleteByStatus(String pName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues;

        contentValues = new ContentValues();
        contentValues.put(AT_STATUS, "DL");

        String condition = AT_NAME + " = '" + pName + "'";
        int columnsAfected = db.update(TABLE_PET, contentValues, condition, null);

        if (columnsAfected == 1){ return true; }
        else{ return false; }
    }

    public boolean delete(String pName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues;

        String condition = AT_NAME + "=?";
        String[] args = new String[]{pName};
        int columnsAfected = db.delete(TABLE_PET, condition, args);

        if (columnsAfected == 1){ return true; }
        else{ return false; }
    }

    public boolean update(String name, Pet pet){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues;

        contentValues = new ContentValues();
        contentValues.put(AT_DATE, ServicioFuncionalidades.persistDate(pet.getDiscoveredDate()) );
        contentValues.put(AT_DESCRIPTION, pet.getDescription() );
        contentValues.put(AT_HEIGHT, pet.getHeight() );
        contentValues.put(AT_ANIMAL_TYPE, pet.getAnimalType() );
        contentValues.put(AT_ICON, ServicioFuncionalidades.getImageTipoAnimal(String.valueOf(pet.getAnimalType())));
        contentValues.put(AT_STATUS, "AC");

        String condition = AT_NAME + " = '" + name + "'";
        int columnsAfected = db.update(TABLE_PET, contentValues, condition, null);

        if (columnsAfected == 1){
            return true;
        }else{
            return false;
        }
    }

    public Pet findByName(String pName) throws Exception {
        Pet pet = null;
        SQLiteDatabase db = this.getWritableDatabase();
        String cadSQL = "SELECT * FROM " + TABLE_PET + " WHERE " + AT_NAME + " = '" + pName + "';";
        Cursor res = db.rawQuery(cadSQL, null);

        if(res.getCount() == 0){
            throw new Exception("No se encontró el animal en la base de datos.");
        }

        while(res.moveToNext()){

            pet = new Pet();
            pet.setName(res.getString(0));
            pet.setDiscoveredDate(ServicioFuncionalidades.loadDate(res, 1));
            pet.setDescription(res.getString(2));
            pet.setHeight(res.getDouble(3));
            pet.setAnimalType(res.getInt(4));
            pet.setIcon(res.getInt(5));
            pet.setStatus(res.getString(6));
        }
        return pet;
    }

}
