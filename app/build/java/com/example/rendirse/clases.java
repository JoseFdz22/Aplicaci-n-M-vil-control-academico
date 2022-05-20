package com.example.rendirse;

public class clases {
    String claseid,nombre,sexo;

    public clases(String claseid, String nombre, String sexo) {
        this.claseid = claseid;
        this.nombre = nombre;
        this.sexo = sexo;
    }

    public String getClaseid() {
        return claseid;
    }

    public void setClaseid(String claseid) {
        this.claseid = claseid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
