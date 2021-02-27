package com.example.wikipets.servicios;

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

}
