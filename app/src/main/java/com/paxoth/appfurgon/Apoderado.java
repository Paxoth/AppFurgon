package com.paxoth.appfurgon;

/**
 * Created by Paxoth on 23-11-2017.
 */

public class Apoderado {
    public String nombre;
    public String telefono;

    public Apoderado(){
        super();
    }
    public Apoderado(String nombre, String telefono){
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }
}
