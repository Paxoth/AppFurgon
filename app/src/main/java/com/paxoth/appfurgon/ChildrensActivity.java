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
    ArrayList<Integer> estados = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_childrens);

        /*Obtenemos el estado de cada niño a medida que se va avanzando el recorrido en el mapa*/
        Intent in = getIntent();
        estados = in.getIntegerArrayListExtra("status");

        /*Datos de los apoderados que se le asignarán a los niños*/
        Apoderado lista_apoderado[] = new Apoderado[]{
                new Apoderado("Apoderado1","+56998019877"),
                new Apoderado("Apoderado2","+56987654321"),
                new Apoderado("Apoderado3","+56995972753"),
                new Apoderado("Apoderado4","+56972763482"),
                new Apoderado("Apoderado5","+56992398263"),
                new Apoderado("Apoderado6","+56976545687"),
                new Apoderado("Apoderado7","+56998767845"),
                new Apoderado("Apoderado8","+56998767854"),
                new Apoderado("Apoderado9","+56972653643"),
                new Apoderado("Apoderado10","+56992837845"),
                new Apoderado("Apoderado11","+56998767856"),
                new Apoderado("Apoderado12","+56987967523"),
                new Apoderado("Apoderado13","+56991981871"),
                new Apoderado("Apoderado14","+56982793847"),
                new Apoderado("Apoderado15","+56991234567"),
                new Apoderado("Apoderado16","+56981245326"),
                new Apoderado("Apoderado17","+56982749829"),
                new Apoderado("Apoderado18","+56991231234"),
                new Apoderado("Apoderado19","+56977767727"),
                new Apoderado("Apoderado20","+56962837497")
        };
        /*Generamos los datos de los niños con la información de los estados de los mapas*/


        Ninos ninos_data[] = new Ninos[]{
                new Ninos(R.mipmap.ic_launcher,"Niño1",new LatLng(-33.420215, -70.735511),estados.get(0),lista_apoderado[0]),
                new Ninos(R.mipmap.ic_launcher,"Niño2",new LatLng(-33.422955, -70.738505),estados.get(1),lista_apoderado[1]),
                new Ninos(R.mipmap.ic_launcher,"Niño3",new LatLng(-33.425498, -70.727776),estados.get(2),lista_apoderado[2]),
                new Ninos(R.mipmap.ic_launcher,"Niño4",new LatLng(-33.425086, -70.721081),estados.get(3),lista_apoderado[3]),
                new Ninos(R.mipmap.ic_launcher,"Niño5",new LatLng(-33.422794, -70.718881),estados.get(4),lista_apoderado[4]),
                new Ninos(R.mipmap.ic_launcher,"Niño6",new LatLng(-33.419248, -70.716650),estados.get(5),lista_apoderado[5]),
                new Ninos(R.mipmap.ic_launcher,"Niño7",new LatLng(-33.419821, -70.714043),estados.get(6),lista_apoderado[6]),
                new Ninos(R.mipmap.ic_launcher,"Niño8",new LatLng(-33.421621, -70.712305),estados.get(7),lista_apoderado[7]),
                new Ninos(R.mipmap.ic_launcher,"Niño9",new LatLng(-33.419750, -70.712315),estados.get(8),lista_apoderado[8]),
                new Ninos(R.mipmap.ic_launcher,"Niño10",new LatLng(-33.413696, -70.710095),estados.get(9),lista_apoderado[9]),
                new Ninos(R.mipmap.ic_launcher,"Niño11",new LatLng(-33.414359, -70.704097),estados.get(10),lista_apoderado[10]),
                new Ninos(R.mipmap.ic_launcher,"Niño12",new LatLng(-33.418227, -70.707477),estados.get(11),lista_apoderado[11]),
                new Ninos(R.mipmap.ic_launcher,"Niño13",new LatLng(-33.419687, -70.708882),estados.get(12),lista_apoderado[12]),
                new Ninos(R.mipmap.ic_launcher,"Niño14",new LatLng(-33.421621, -70.712251),estados.get(13),lista_apoderado[13]),
                new Ninos(R.mipmap.ic_launcher,"Niño15",new LatLng(-33.423233, -70.713785),estados.get(14),lista_apoderado[14]),
                new Ninos(R.mipmap.ic_launcher,"Niño16",new LatLng(-33.424191, -70.707294),estados.get(15),lista_apoderado[15]),
                new Ninos(R.mipmap.ic_launcher,"Niño17",new LatLng(-33.425821, -70.705395),estados.get(16),lista_apoderado[16]),
                new Ninos(R.mipmap.ic_launcher,"Niño18",new LatLng(-33.427629, -70.706865),estados.get(17),lista_apoderado[17]),
                new Ninos(R.mipmap.ic_launcher,"Niño19",new LatLng(-33.428623, -70.703046),estados.get(18),lista_apoderado[18]),
                new Ninos(R.mipmap.ic_launcher,"Niño20",new LatLng(-33.425901, -70.700127),estados.get(19),lista_apoderado[19]),

        };
        listaNinos = findViewById(R.id.listaNinos);
        NinosAdapter adapter = new NinosAdapter(this,R.layout.listview_children_row,ninos_data);
        View header = (View) getLayoutInflater().inflate(R.layout.listview_childrens_header_row,null);
        listaNinos.addHeaderView(header);
        listaNinos.setAdapter(adapter);
        int a = listaNinos.getScrollBarSize();
        listaNinos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView coords = view.findViewById(R.id.coords);
                Toast.makeText(ChildrensActivity.this, "Coordenada: "+coords.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
