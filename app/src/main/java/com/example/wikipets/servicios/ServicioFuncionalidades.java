package com.example.wikipets.servicios;

import android.widget.Spinner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServicioFuncionalidades {

    public static String tiposAnimal[] = {"MAMIFERO", "AVE",  "ANFIBIO", "REPTIL", "PECES", "ARACNIDOS", "MOLUSCOS"};

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

}
