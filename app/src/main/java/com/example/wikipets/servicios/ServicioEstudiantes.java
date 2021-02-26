package com.example.wikipets.servicios;

import java.util.ArrayList;

import com.example.wikipets.estructural.Estudiante;

public class ServicioEstudiantes {

    private static ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();

    public ServicioEstudiantes(){
        estudiantes.add(new Estudiante("2220171044", "123123123", "Luis Felipe", "Londoño"));
        estudiantes.add(new Estudiante("2220171033", "321321321", "Andrés Felipe", "Novoa"));
        estudiantes.add(new Estudiante("1123240042", "838382313", "Laura Cristina", "Cardona"));
        /*System.out.println(buscarEstudiante("2220171044").toString());
        System.out.println( darEstudiantes().size() );
        System.out.println( eliminarEstudiante("2220171033") );
        System.out.println( darEstudiantes().size() );
        System.out.println( actualizarEstudiante(new Estudiante("2220171044", "123123123", "Alfonso", "Otoño") ) );
        System.out.println(buscarEstudiante("2220171044").toString());*/
    }

    public static Estudiante buscarEstudiante(String pCodigo) {
        Estudiante est = null;
        for (Estudiante estudiante : estudiantes){
            if (estudiante.getCodigo().equals(pCodigo)) {
                est = estudiante;
                return est;
            }
        }
        return est;
    }

    public static ArrayList<Estudiante> darEstudiantes(){
        return estudiantes;
    }

    public static void agregarEstudiante(Estudiante estudiante){
        estudiantes.add(estudiante);
    }

    public static boolean eliminarEstudiante(String pCodigo){
        return estudiantes.remove(buscarEstudiante(pCodigo));
    }

    public static boolean actualizarEstudiante(Estudiante estudianteActualizado){
        Estudiante est = null;
        try{
            est = buscarEstudiante(estudianteActualizado.getCodigo());
            est = estudianteActualizado;
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
