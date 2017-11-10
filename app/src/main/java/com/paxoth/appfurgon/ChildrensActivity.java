package com.paxoth.appfurgon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class ChildrensActivity extends AppCompatActivity {
    /*Activity que representará la lista de niños y sus datos para ver si ya se pasó a recoger, se encuentra en espera o no asistirá*/
    ListView listaNinos;
    ArrayList<Integer> estados = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_childrens);

        /*Obtenemos el estado de cada niño a medida que se va avanzando el recorrido en el mapa*/
        Intent in = getIntent();
        estados = in.getIntegerArrayListExtra("status");

        /*Generamos los datos de los niños con la información de los estados de los mapas*/
        Ninos ninos_data[] = new Ninos[]{
                new Ninos(R.mipmap.ic_launcher,"Niño1","Apoderado1",new LatLng(-33.420215, -70.735511),estados.get(0)),
                new Ninos(R.mipmap.ic_launcher,"Niño2","Apoderado2",new LatLng(-33.422955, -70.738505),estados.get(1)),
                new Ninos(R.mipmap.ic_launcher,"Niño3","Apoderado3",new LatLng(-33.425498, -70.727776),estados.get(2)),
                new Ninos(R.mipmap.ic_launcher,"Niño4","Apoderado4",new LatLng(-33.425086, -70.721081),estados.get(3)),
                new Ninos(R.mipmap.ic_launcher,"Niño5","Apoderado5",new LatLng(-33.422794, -70.718881),estados.get(4)),
                new Ninos(R.mipmap.ic_launcher,"Niño6","Apoderado6",new LatLng(-33.419248, -70.716650),estados.get(5)),
                new Ninos(R.mipmap.ic_launcher,"Niño7","Apoderado7",new LatLng(-33.419821, -70.714043),estados.get(6)),
                new Ninos(R.mipmap.ic_launcher,"Niño8","Apoderado8",new LatLng(-33.421621, -70.712305),estados.get(7)),
                new Ninos(R.mipmap.ic_launcher,"Niño9","Apoderado9",new LatLng(-33.419750, -70.712315),estados.get(8)),
                new Ninos(R.mipmap.ic_launcher,"Niño10","Apoderado10",new LatLng(-33.413696, -70.710095),estados.get(9)),
                new Ninos(R.mipmap.ic_launcher,"Niño11","Apoderado11",new LatLng(-33.414359, -70.704097),estados.get(10)),
                new Ninos(R.mipmap.ic_launcher,"Niño12","Apoderado12",new LatLng(-33.418227, -70.707477),estados.get(11)),
                new Ninos(R.mipmap.ic_launcher,"Niño13","Apoderado13",new LatLng(-33.419687, -70.708882),estados.get(12)),
                new Ninos(R.mipmap.ic_launcher,"Niño14","Apoderado14",new LatLng(-33.421621, -70.712251),estados.get(13)),
                new Ninos(R.mipmap.ic_launcher,"Niño15","Apoderado15",new LatLng(-33.423233, -70.713785),estados.get(14)),
                new Ninos(R.mipmap.ic_launcher,"Niño16","Apoderado16",new LatLng(-33.424191, -70.707294),estados.get(15)),
                new Ninos(R.mipmap.ic_launcher,"Niño17","Apoderado17",new LatLng(-33.425821, -70.705395),estados.get(16)),
                new Ninos(R.mipmap.ic_launcher,"Niño18","Apoderado18",new LatLng(-33.427629, -70.706865),estados.get(17)),
                new Ninos(R.mipmap.ic_launcher,"Niño19","Apoderado19",new LatLng(-33.428623, -70.703046),estados.get(18)),
                new Ninos(R.mipmap.ic_launcher,"Niño20","Apoderado20",new LatLng(-33.425901, -70.700127),estados.get(19)),

        };
        listaNinos = (ListView) findViewById(R.id.listaNinos);
        NinosAdapter adapter = new NinosAdapter(this,R.layout.listview_children_row,ninos_data);
        View header = (View) getLayoutInflater().inflate(R.layout.listview_childrens_header_row,null);
        listaNinos.addHeaderView(header);
        listaNinos.setAdapter(adapter);
        int a = listaNinos.getScrollBarSize();
        System.out.println("HOLII:"+a);
        listaNinos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView coords = (TextView)view.findViewById(R.id.coords);
                Toast.makeText(ChildrensActivity.this, "Coordenada: "+coords.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
