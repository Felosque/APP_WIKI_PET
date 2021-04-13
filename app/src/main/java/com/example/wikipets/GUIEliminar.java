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
import com.example.wikipets.servicios.ServicioFuncionalidades;
import com.example.wikipets.servicios.ServicioPet;

import java.util.ArrayList;

public class GUIEliminar extends AppCompatActivity {


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

        layoutBusqueda = findViewById(R.id.layoutBusqueda);
        layoutResultado = findViewById(R.id.layoutResultado);
        txtBusqueda = findViewById(R.id.txtBusqueda);

        if(!cad.isEmpty())
            txtBusqueda.setText(cad);
    }

    public void btnCancelar_Click(View view){
        txtBusqueda.setText("");
        visibleResultados(view,false);
    }

    public void btnBuscar_Click(View view){
        try{
            String textBusqueda = txtBusqueda.getText().toString();
            Pet busqueda = ServicioPet.searchPetsByName(textBusqueda);

            if (busqueda != null){
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(txtBusqueda.getWindowToken(), 0);
                visibleResultados(view,true);

                nombre = (TextView) findViewById(R.id.txtNombre);
                nombre.setEnabled(false);
                nombre.setText(busqueda.getName());

                descripcion = (TextView) findViewById(R.id.txtDescripcion);
                descripcion.setEnabled(false);
                descripcion.setText(busqueda.getDescription());

                fechaDescubrimiento = (TextView) findViewById(R.id.txtFecha);
                fechaDescubrimiento.setEnabled(false);
                fechaDescubrimiento.setText(ServicioFuncionalidades.dateToString(busqueda.getDiscoveredDate()));

                altura = (TextView) findViewById(R.id.txtAltura);
                altura.setEnabled(false);
                altura.setText(String.valueOf(busqueda.getHeight()));

                spnTipo = (Spinner) findViewById(R.id.spTipo);
                ArrayList<String> typeAnimals;
                TipoAnimal.loadAnimals();
                typeAnimals = TipoAnimal.getTypeAnimal();
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,typeAnimals);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnTipo.setAdapter(adapter);
                spnTipo.setEnabled(false);
                spnTipo.setSelection(TipoAnimal.getIndexSpinnerValue(spnTipo, busqueda.getAnimalType()));

                btnFecha = (ImageButton) findViewById(R.id.btnFechaForm);
                btnFecha.setVisibility(View.GONE);
            }else {
                Toast.makeText(this, "No se encontró ninguna mascota.", Toast.LENGTH_LONG ).show();
            }
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    public void btnEliminar_Click(View view){
        String textBusqueda = txtBusqueda.getText().toString();
        ServicioPet.deletePet(textBusqueda);
        Toast.makeText(this, "Se eliminó correctamente la mascota: " + textBusqueda, Toast.LENGTH_LONG ).show();
        txtBusqueda.setText("");
        visibleResultados(view,false);
    }

    public void btnVolver_Click (View view) {
        finish();
    }

    private void visibleResultados(View view, boolean pMostrar){
        if (pMostrar){
            layoutBusqueda.setVisibility(View.GONE);
            layoutResultado.setVisibility(View.VISIBLE);
        }else{
            layoutBusqueda.setVisibility(View.VISIBLE);
            layoutResultado.setVisibility(View.GONE);
        }
    }

}