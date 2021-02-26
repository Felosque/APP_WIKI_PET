package com.example.wikipets.estructural;

public class Estudiante {

    private String codigo;

    private String documentoIdentificacion;

    private String nombre;

    private String Apellido;

    public Estudiante(String codigo, String documentoIdentificacion, String nombre, String apellido) {
        this.codigo = codigo;
        this.documentoIdentificacion = documentoIdentificacion;
        this.nombre = nombre;
        Apellido = apellido;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDocumentoIdentificacion() {
        return documentoIdentificacion;
    }

    public void setDocumentoIdentificacion(String documentoIdentificacion) {
        this.documentoIdentificacion = documentoIdentificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }


    @Override
    public String toString() {
        return "Estudiante{" +
                "codigo='" + codigo + '\'' +
                ", documentoIdentificacion='" + documentoIdentificacion + '\'' +
                ", nombre='" + nombre + '\'' +
                ", Apellido='" + Apellido + '\'' +
                '}';
    }
}
