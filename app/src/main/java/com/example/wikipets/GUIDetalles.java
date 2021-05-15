package com.example.wikipets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wikipets.estructural.Pet;
import com.example.wikipets.estructural.TipoAnimal;
import com.example.wikipets.interfaces.CRUDPet;
import com.example.wikipets.servicios.ServicioFuncionalidades;
import com.example.wikipets.servicios.ServicioPet;

import java.util.ArrayList;

public class GUIDetalles extends AppCompatActivity implements CRUDPet {

    private ImageView imgDetalle;

    private TextView nombre;

    private TextView descripcion;

    private TextView fechaDescubrimiento;

    private TextView altura;

    private ImageButton btnFecha;

    private Spinner spnTipo;

    private Pet animal;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detalle_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;

        switch (item.getItemId()){
            case R.id.itemEditar:
                intent = new Intent(this, GUIEditar.class);
                intent.putExtra("ANIMAL", animal.getName());
                startActivity(intent);
                finish();
                return true;

            case R.id.itemEliminar:
                intent = new Intent(this, GUIEliminar.class);
                intent.putExtra("ANIMAL", animal.getName());
                startActivity(intent);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_u_i_detalles);

        try{
            Intent intent = getIntent();
            int cad = intent.getIntExtra("ANIMAL", -1);
            ServicioPet servicioPet = new ServicioPet(this);
            animal = null;


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
            ArrayList<String> typeAnimals;
            typeAnimals = TipoAnimal.getTypeAnimal();
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,typeAnimals);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnTipo.setAdapter(adapter);
            spnTipo.setEnabled(false);
            spnTipo.setSelection(TipoAnimal.getIndexSpinnerValue(spnTipo, String.valueOf(animal.getAnimalType())));
            //ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ServicioFuncionalidades.tiposAnimal);
            //adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //spnTipo.setAdapter(adaptador);
            //spnTipo.setEnabled(false);
            //spnTipo.setSelection(ServicioFuncionalidades.getIndexSpinnerValue(spnTipo, animal.getAnimalType()));

            btnFecha = (ImageButton) findViewById(R.id.btnFechaForm);
            btnFecha.setVisibility(View.GONE);


        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }




    }

    public void btnVolver_Click (View view) {
        finish();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showPets(ArrayList<Pet> pets) {

    }

    @Override
    public void showOnePet(Pet pet) {

    }
}