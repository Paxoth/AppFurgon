package com.paxoth.appfurgon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ChildrensActivity extends AppCompatActivity {


    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_childrens);

        Ninos ninos_data[] = new Ninos[]{
                new Ninos(R.drawable.nina1,"Pedro Tapia","Maria Gonzalez",R.drawable.naranjo),
                new Ninos(R.drawable.nina2,"Leonor Gacitua","Loreto Alavarado",R.drawable.rojo),
                new Ninos(R.drawable.nino1,"Pilar Muñoz","Fabiola Lara",R.drawable.verde),
                new Ninos(R.drawable.nina2,"Marcelo Joerquera","Mario Jorquera",R.drawable.verde),
                new Ninos(R.drawable.nino1,"Carlos Reyes","Pricila Lopez",R.drawable.naranjo),
                new Ninos(R.drawable.nino2,"Gabriela Nuñez","Juana Riquelme",R.drawable.verde),
                new Ninos(R.drawable.nino2,"Cristian Medina","Jorge Medina",R.drawable.naranjo),
                new Ninos(R.drawable.nina2,"Sofia Contreras","Ignacia Cáceres",R.drawable.naranjo),
                new Ninos(R.drawable.nina1,"Fernanda Casanova","Loreto Cortez",R.drawable.naranjo),
                new Ninos(R.drawable.nino1,"Patricio Fernandez","Emilia Fernandez",R.drawable.verde),
                new Ninos(R.drawable.nina2,"Matias Jimenez","Macarena Vargas",R.drawable.naranjo),
                new Ninos(R.drawable.nina1,"Francinsca Iturra","Teresa Iturra",R.drawable.verde),
                new Ninos(R.drawable.nino2,"Ana Morales","Daniela Urrutia",R.drawable.naranjo),

        };
        lista = (ListView) findViewById(R.id.listaNinos);
        NinosAdapter adapter = new NinosAdapter(this,R.layout.listview_children_row,ninos_data);
        View header = (View) getLayoutInflater().inflate(R.layout.listview_childrens_header_row,null);
        lista.addHeaderView(header);
        lista.setAdapter(adapter);

    }

}
