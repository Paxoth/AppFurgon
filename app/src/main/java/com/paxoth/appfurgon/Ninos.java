package com.paxoth.appfurgon;

/*
 * Created by Paxoth on 08-11-17.
 * Clase que representa a la información de la Listview de los niños
 */

import com.google.android.gms.maps.model.LatLng;

public class Ninos {
    public int foto;
    public String nombreNino;
    public String nombreApoderado;
    public LatLng coords;
    public int status;

    public Ninos(){
        super();
    }

    public Ninos(int foto, String nombreNino, String nombreApoderado, LatLng coords, int status){
        super();
        this.foto = foto;
        this.nombreNino = nombreNino;
        this.nombreApoderado = nombreApoderado;
        this.coords = coords;
        this.status = status;
    }
}
