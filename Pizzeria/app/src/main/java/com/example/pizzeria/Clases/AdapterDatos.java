package com.example.pizzeria.Clases;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizzeria.R;

import java.util.ArrayList;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolder>  implements  View.OnClickListener{

    private ArrayList<Pizza> listaPizzas;
    private View.OnClickListener listener;

    public AdapterDatos(ArrayList<Pizza> listaPizzas){
        this.listaPizzas=listaPizzas;
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener= listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!= null){
            listener.onClick(view);
        }
    }

    public class  ViewHolder extends  RecyclerView.ViewHolder{
        private TextView nametxt;

        public ViewHolder(final  View view){
            super(view);
            nametxt= view.findViewById(R.id.txtTitulo);
        }
    }

    @NonNull
    @Override
    public AdapterDatos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        //escuchando evento de seleccion
        itemView.setOnClickListener(this);
        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterDatos.ViewHolder holder, int position) {
        String name = listaPizzas.get(position).getNombre();
        holder.nametxt.setText(name);
    }

    @Override
    public int getItemCount() {
        return listaPizzas.size();
    }


}

