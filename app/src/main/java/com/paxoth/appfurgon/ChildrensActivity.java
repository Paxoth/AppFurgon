package com.paxoth.appfurgon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.model.LatLng;

public class ChildrensActivity extends AppCompatActivity {
    ListView listaNinos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_childrens);
        Ninos ninos_data[] = new Ninos[]{
                new Ninos(R.mipmap.ic_launcher,"Niño1","Apoderado1",new LatLng(-33.420215, -70.735511),1),
                new Ninos(R.mipmap.ic_launcher,"Niño2","Apoderado2",new LatLng(-33.422955, -70.738505),2),
                new Ninos(R.mipmap.ic_launcher,"Niño3","Apoderado3",new LatLng(-33.425498, -70.727776),3),
                new Ninos(R.mipmap.ic_launcher,"Niño4","Apoderado1",new LatLng(-33.425086, -70.721081),2),
                new Ninos(R.mipmap.ic_launcher,"Niño5","Apoderado4",new LatLng(-33.422794, -70.718881),1),
                new Ninos(R.mipmap.ic_launcher,"Niño6","Apoderado5",new LatLng(-33.419248, -70.716650),1),
                new Ninos(R.mipmap.ic_launcher,"Niño7","Apoderado6",new LatLng(-33.419821, -70.714043),3),
                new Ninos(R.mipmap.ic_launcher,"Niño8","Apoderado7",new LatLng(-33.421621, -70.712305),1),
                new Ninos(R.mipmap.ic_launcher,"Niño9","Apoderado8",new LatLng(-33.419750, -70.712315),3),
                new Ninos(R.mipmap.ic_launcher,"Niño10","Apoderado9",new LatLng(-33.413696, -70.710095),1),
                new Ninos(R.mipmap.ic_launcher,"Niño11","Apoderado10",new LatLng(-33.414359, -70.704097),1),
                new Ninos(R.mipmap.ic_launcher,"Niño12","Apoderado11",new LatLng(-33.418227, -70.707477),1),
                new Ninos(R.mipmap.ic_launcher,"Niño13","Apoderado12",new LatLng(-33.419687, -70.708882),1),
                new Ninos(R.mipmap.ic_launcher,"Niño14","Apoderado13",new LatLng(-33.421621, -70.712251),3),
                new Ninos(R.mipmap.ic_launcher,"Niño15","Apoderado14",new LatLng(-33.423233, -70.713785),1),
                new Ninos(R.mipmap.ic_launcher,"Niño16","Apoderado15",new LatLng(-33.424191, -70.707294),2),
                new Ninos(R.mipmap.ic_launcher,"Niño17","Apoderado16",new LatLng(-33.425821, -70.705395),2),
                new Ninos(R.mipmap.ic_launcher,"Niño18","Apoderado17",new LatLng(-33.427629, -70.706865),1),
                new Ninos(R.mipmap.ic_launcher,"Niño19","Apoderado18",new LatLng(-33.428623, -70.703046),1),
                new Ninos(R.mipmap.ic_launcher,"Niño20","Apoderado19",new LatLng(-33.425901, -70.700127),1),

        };
        listaNinos = (ListView) findViewById(R.id.listaNinos);
        NinosAdapter adapter = new NinosAdapter(this,R.layout.listview_children_row,ninos_data);
        View header = (View) getLayoutInflater().inflate(R.layout.listview_childrens_header_row,null);
        listaNinos.addHeaderView(header);
        listaNinos.setAdapter(adapter);

        listaNinos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView coords = (TextView)view.findViewById(R.id.coords);
                Toast.makeText(ChildrensActivity.this, "Coordenada: "+coords.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
