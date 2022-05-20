package com.example.rendirse.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rendirse.Models.Persona;
import com.example.rendirse.R;

import java.util.ArrayList;

public class ListViewPersonasAdapter extends BaseAdapter {

    Context context;
    ArrayList<Persona> personaDate;
    LayoutInflater layoutInflater;
    Persona personaModel;

    public ListViewPersonasAdapter(Context context, ArrayList<Persona> personaDate) {
        this.context = context;
        this.personaDate = personaDate;
        layoutInflater =(LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE
        );
    }

    @Override
    public int getCount() {
        return personaDate.size();
    }

    @Override
    public Object getItem(int position) {
        return personaDate.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View rowView= view;
        if(rowView == null){
            rowView= layoutInflater.inflate(R.layout.lista_personas, null, true);
        }
        TextView nombres = rowView.findViewById(R.id.nombres);
        TextView telefono = rowView.findViewById(R.id.telefono);
        TextView fecharegistro = rowView.findViewById(R.id.fecha);

        personaModel = personaDate.get(position);
        nombres.setText(personaModel.getNombres());
        telefono.setText(personaModel.getTelefono());
        fecharegistro.setText(personaModel.getFecharegistro());
        return rowView;
    }
}
