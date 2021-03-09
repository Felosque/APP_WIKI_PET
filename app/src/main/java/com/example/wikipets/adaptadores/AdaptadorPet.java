package com.example.wikipets.adaptadores;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.wikipets.R;
import com.example.wikipets.estructural.Pet;

import java.util.List;

public class AdaptadorPet extends ArrayAdapter<Pet> {

    private Context context;

    private int resource;

    private List<Pet> pets;

    public AdaptadorPet(@NonNull Context context, int resource, @NonNull List<Pet> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.pets = objects;
    }

    public View getView(int posicion, View view , ViewGroup parent){
        PetsHolder holder = null;

        if(view == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view  = inflater.inflate(resource,parent,false);
            holder = new PetsHolder();
            holder.setImagen((ImageView) view.findViewById(R.id.lsvImagenList));
            holder.setNombre((TextView) view.findViewById(R.id.lsvNombre));
            holder.setAltura((TextView) view.findViewById(R.id.lsvAltura));
            holder.setDescripcion((TextView) view.findViewById(R.id.lsvDescripcion));
            view.setTag(holder);
        }else{
            holder = (PetsHolder) view.getTag();
        }

        Pet pet  = pets.get(posicion);
        holder.setImagen(pet.getIcon());
        holder.setNombre(pet.getName());
        holder.setAltura("" + pet.getHeight());
        holder.setDescripcion(pet.getDescription());
        return view;
    }


    static class PetsHolder{
        private ImageView imagen;
        private TextView nombre;
        private TextView altura;
        private TextView descripcion;

        public ImageView getImagen() {
            return imagen;
        }
        public void setImagen(ImageView imagen) {
            this.imagen = imagen;
        }
        public void setImagen(int imagen){
            this.imagen.setImageResource(imagen);
        }

        public TextView getNombre() { return nombre; }
        public void setNombre(TextView nombre) { this.nombre = nombre; }
        public void setNombre(String texto){ this.nombre.setText( texto); }

        public TextView getAltura() { return altura; }
        public void setAltura(TextView altura) { this.altura = altura; }
        public void setAltura(String texto){
            this.altura.setText(texto + " metros");
        }

        public TextView getDescripcion() { return descripcion; }
        public void setDescripcion(TextView descripcion) { this.descripcion = descripcion; }
        public void setDescripcion(String texto){ this.descripcion.setText(texto); }
    }


}
