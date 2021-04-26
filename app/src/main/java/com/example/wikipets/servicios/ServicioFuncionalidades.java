package com.example.wikipets.servicios;

import android.database.Cursor;
import android.widget.Spinner;

import com.example.wikipets.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServicioFuncionalidades {

    public static Date ParseFecha(String fecha)
    {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        }
        catch (ParseException ex)
        {
            System.out.println(ex);
        }
        return fechaDate;
    }


    public static String dateToString(Date date){
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String today = formatter.format(date);
        return today;
    }


    public static int getIndexSpinnerValue(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }
        return 0;
    }

    public static Long persistDate(Date date) {
        if (date != null) {
            return date.getTime();
        }
        return null;
    }

    public static Date loadDate(Cursor cursor, int index) {
        if (cursor.isNull(index)) {
            return null;
        }
        return new Date(cursor.getLong(index));
    }

    public static int getImageTipoAnimal(String tipo){
        switch (tipo){
            case "MAMIFERO":
                return R.drawable.mamifero;
            case "AVE":
                return R.drawable.ave;
            case "ANFIBIO":
                return R.drawable.anfibio;
            case "REPTIL":
                return R.drawable.reptil;
            case "PECES":
                return R.drawable.peces;
            case "ARACNIDOS":
                return R.drawable.aracnido;
            case "MOLUSCOS":
                return R.drawable.molusco;
        }
        return R.drawable.fault;
    }

}
