package com.example.wikipets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wikipets.estructural.Pet;
import com.example.wikipets.estructural.TipoAnimal;
import com.example.wikipets.interfaces.CRUDPet;
import com.example.wikipets.servicios.ServicioFuncionalidades;
import com.example.wikipets.servicios.ServicioPet;

import java.util.ArrayList;
import java.util.Map;

public class GUIEliminar extends AppCompatActivity implements CRUDPet {


    private LinearLayout layoutBusqueda;

    private LinearLayout layoutResultado;

    private TextView txtBusqueda;

    private TextView nombre;

    private TextView descripcion;

    private TextView fechaDescubrimiento;

    private TextView altura;

    private ImageButton btnFecha;

    private Spinner spnTipo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_u_i_eliminar);

        Intent intent = getIntent();
        String cad = intent.getStringExtra("ANIMAL");

        ServicioPet.setCrudPet(this);

        layoutBusqueda = findViewById(R.id.layoutBusqueda);
        layoutResultado = findViewById(R.id.layoutResultado);
        txtBusqueda = findViewById(R.id.txtBusqueda);

        if(!cad.isEmpty())
            txtBusqueda.setText(cad);
    }

    public void btnCancelar_Click(View view){
        txtBusqueda.setText("");
        visibleResultados(false);
    }

    public void btnBuscar_Click(View view){
        try{
            String textBusqueda = txtBusqueda.getText().toString();
            ServicioPet.searchPetsByName(textBusqueda);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    public void btnEliminar_Click(View view){
        String textBusqueda = txtBusqueda.getText().toString();
        ServicioPet.deletePet(textBusqueda);
        txtBusqueda.setText("");
        visibleResultados(false);
    }

    public void btnEliminarLogico_Click(View view){
        String textBusqueda = txtBusqueda.getText().toString();
        ServicioPet.deletePetLogic(textBusqueda);
        txtBusqueda.setText("");
        visibleResultados(false);
    }

    public void btnVolver_Click (View view) {
        finish();
    }

    private void visibleResultados(boolean pMostrar){
        if (pMostrar){
            layoutBusqueda.setVisibility(View.GONE);
            layoutResultado.setVisibility(View.VISIBLE);
        }else{
            layoutBusqueda.setVisibility(View.VISIBLE);
            layoutResultado.setVisibility(View.GONE);
        }
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
        if (pet != null){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(txtBusqueda.getWindowToken(), 0);
            visibleResultados(true);

            nombre = (TextView) findViewById(R.id.txtNombre);
            nombre.setEnabled(false);
            nombre.setText(pet.getName());

            descripcion = (TextView) findViewById(R.id.txtDescripcion);
            descripcion.setEnabled(false);
            descripcion.setText(pet.getDescription());

            fechaDescubrimiento = (TextView) findViewById(R.id.txtFecha);
            fechaDescubrimiento.setEnabled(false);
            fechaDescubrimiento.setText(ServicioFuncionalidades.dateToString(pet.getDiscoveredDate()));

            altura = (TextView) findViewById(R.id.txtAltura);
            altura.setEnabled(false);
            altura.setText(String.valueOf(pet.getHeight()));

            spnTipo = (Spinner) findViewById(R.id.spTipo);
            ArrayList<String> typeAnimals;
            typeAnimals = TipoAnimal.getTypeAnimal();
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,typeAnimals);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnTipo.setAdapter(adapter);
            spnTipo.setEnabled(false);
            spnTipo.setSelection(TipoAnimal.getIndexSpinnerValue(spnTipo, String.valueOf(pet.getAnimalType())));

            btnFecha = (ImageButton) findViewById(R.id.btnFechaForm);
            btnFecha.setVisibility(View.GONE);
        }
    }

    @Override
    public void petByType(Map<String, Integer> pet) {

    }
}