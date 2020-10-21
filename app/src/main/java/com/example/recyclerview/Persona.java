package com.example.recyclerview;

import java.util.ArrayList;
import java.util.List;

public class Persona {
    String nombre;
    String edad;
    int foto;
    int botonId;
    String descripcion;
    String link;

    public Persona(String nombre, String edad, int foto, int botonId, String descripcion,String link) {
        this.nombre = nombre;
        this.edad = edad;
        this.foto = foto;
        this.botonId = botonId;
        this.descripcion=descripcion;
        this.link=link;
    }
}
