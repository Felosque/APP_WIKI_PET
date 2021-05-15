package com.example.wikipets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

public class GUIBuscar extends AppCompatActivity implements CRUDPet {

    private TextView txtCodigo;

    private TextView textoBusqueda;

    private LinearLayout layoutFormulario;

    private TextView nombre;

    private TextView descripcion;

    private TextView fechaDescubrimiento;

    private TextView altura;

    private ImageButton btnFecha;

    private Spinner spnTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_u_i_buscar);

        ServicioPet.setCrudPet(this);

        txtCodigo = (TextView) findViewById(R.id.txtBusqueda);
        textoBusqueda = (TextView) findViewById(R.id.textBusqueda);
        textoBusqueda.setVisibility(View.GONE);
        layoutFormulario = (LinearLayout) findViewById(R.id.layoutFormulario);
        layoutFormulario.setVisibility(View.GONE);
    }

    public void btnBuscar_Click (View view) {

        try {
            String textBusqueda = txtCodigo.getText().toString();
            ServicioPet.searchPetsByName(textBusqueda);
        }catch (Exception e){
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG ).show();
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
        if (pet != null) {
            layoutFormulario.setVisibility(View.VISIBLE);
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(txtCodigo.getWindowToken(), 0);

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
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, typeAnimals);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnTipo.setAdapter(adapter);
            spnTipo.setEnabled(false);
            spnTipo.setSelection(TipoAnimal.getIndexSpinnerValue(spnTipo, String.valueOf(pet.getAnimalType())));

            btnFecha = (ImageButton) findViewById(R.id.btnFechaForm);
            btnFecha.setVisibility(View.GONE);
        }
    }
}