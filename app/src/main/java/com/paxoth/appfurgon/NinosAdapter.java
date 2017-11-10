package com.paxoth.appfurgon;


/**
 * Created by Paxoth on 08-11-17.
 * Adapter para enviar información entre activities a la ListView de niños
 */
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class NinosAdapter extends ArrayAdapter<Ninos> {

    Context context;
    int layoutResourceId;
    Ninos data[] = null;

    public NinosAdapter(Context context, int layoutResourceId, Ninos[] data){
        super(context,layoutResourceId,data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        NinosHolder holder = null;
        if(row==null){ /*Verificación de ver como viene el dato*/
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId,parent,false);
            holder = new NinosHolder();
            holder.foto = (ImageView) row.findViewById(R.id.foto);
            holder.nombreNino = (TextView) row.findViewById(R.id.nombreNino);
            holder.nombreApoderado = (TextView) row.findViewById(R.id.nombreApoderado);
            holder.coords = (TextView) row.findViewById(R.id.coords);
            //rellenar el arreglo en un estado incial
            //1-> recogido
            //2-> ausente
            //3-> en espera

            row.setTag(holder);
        }else {
            holder = (NinosHolder) row.getTag();
        }
        /*Acá pasamos los datos*/
        Ninos ninos = data[position];
        holder.foto.setImageResource(ninos.foto);
        holder.nombreNino.setText(ninos.nombreNino);
        holder.nombreApoderado.setText(ninos.nombreApoderado);
        holder.coords.setText("("+String.valueOf(ninos.coords.latitude)+","+String.valueOf(ninos.coords.longitude)+")");
        System.out.println("Adapter: estado["+position+"]: "+data[position].status);
        if(data[position].status==1){
            row.setBackgroundResource(R.color.Presente);
        }else if(data[position].status==2){
            row.setBackgroundResource(R.color.Ausente);
        }else
        {
            row.setBackgroundResource(R.color.Pendiente);
        }
        return row;
    }

    /*Holder nos ayuda a mantener los datos*/
    static class NinosHolder{
        ImageView foto;
        TextView nombreNino;
        TextView nombreApoderado;
        TextView coords;
    }
}
