package com.paxoth.appfurgon;
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
            holder.nombreNino = (TextView) row.findViewById(R.id.texto);
            holder.nombreApoderado = (TextView) row.findViewById(R.id.category);
            if(data[position].status==1){
                holder.status = row.findViewById(R.id.imageView);
            }else if(data[position].status==2){
                holder.status = row.findViewById(R.id.imageView);
            }else
                holder.status = row.findViewById(R.id.imageView);
            row.setTag(holder);
        }else {
            holder = (NinosHolder) row.getTag();
        }
        /*Acá pasamos los datos*/
        Ninos ninos = data[position];
        holder.foto.setImageResource(ninos.foto);
        holder.nombreNino.setText(ninos.nombreNino);
        holder.nombreApoderado.setText(ninos.nombreApoderado);
        holder.status.setImageResource(ninos.status);
        return row;
    }

    /*Holder nos ayuda a mantener los datos*/
    static class NinosHolder{
        ImageView foto;
        ImageView status;
        TextView nombreNino;
        TextView nombreApoderado;
    }
}
