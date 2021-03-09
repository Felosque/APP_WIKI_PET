package com.example.wikipets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wikipets.estructural.Pet;
import com.example.wikipets.servicios.ServicioFuncionalidades;
import com.example.wikipets.servicios.ServicioPet;

public class GUIDetalles extends AppCompatActivity {

    private ImageView imgDetalle;

    private TextView nombre;

    private TextView descripcion;

    private TextView fechaDescubrimiento;

    private TextView altura;

    private ImageButton btnFecha;

    private Spinner spnTipo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_u_i_detalles);

        try{
            Intent intent = getIntent();
            int cad = intent.getIntExtra("ANIMAL", -1);
            Pet animal = ServicioPet.searchPetsByIDArray(cad);


            imgDetalle = (ImageView) findViewById(R.id.imgDetalle);
            imgDetalle.setImageResource(animal.getIcon());

            nombre = (TextView) findViewById(R.id.txtNombre);
            nombre.setEnabled(false);
            nombre.setText(animal.getName());

            descripcion = (TextView) findViewById(R.id.txtDescripcion);
            descripcion.setEnabled(false);
            descripcion.setText(animal.getDescription());

            fechaDescubrimiento = (TextView) findViewById(R.id.txtFecha);
            fechaDescubrimiento.setEnabled(false);
            fechaDescubrimiento.setText(ServicioFuncionalidades.dateToString(animal.getDiscoveredDate()));

            altura = (TextView) findViewById(R.id.txtAltura);
            altura.setEnabled(false);
            altura.setText(String.valueOf(animal.getHeight()));

            spnTipo = (Spinner) findViewById(R.id.spTipo);
            ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ServicioFuncionalidades.tiposAnimal);
            adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnTipo.setAdapter(adaptador);
            spnTipo.setEnabled(false);
            spnTipo.setSelection(ServicioFuncionalidades.getIndexSpinnerValue(spnTipo, animal.getAnimalType()));

            btnFecha = (ImageButton) findViewById(R.id.btnFechaForm);
            btnFecha.setVisibility(View.GONE);


        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public void btnVolver_Click (View view) {
        finish();
    }
}