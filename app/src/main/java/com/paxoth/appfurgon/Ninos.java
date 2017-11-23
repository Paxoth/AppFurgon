package com.paxoth.appfurgon;

/*
 * Created by Paxoth on 08-11-17.
 * Clase que representa a la información de la Listview de los niños
 */

import com.google.android.gms.maps.model.LatLng;

public class Ninos {
    public int foto;
    public String nombreNino;
    public LatLng coords;
    public int status;
    public Apoderado padre;

    public Ninos(){
        super();
    }

    public Ninos(int foto, String nombreNino, LatLng coords, int status, Apoderado padre){
        super();
        this.foto = foto;
        this.nombreNino = nombreNino;
        this.coords = coords;
        this.status = status;
        this.padre = padre;
    }

    public Apoderado getPadre() {
        return padre;
    }
}
