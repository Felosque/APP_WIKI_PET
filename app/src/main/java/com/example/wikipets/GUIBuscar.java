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
import com.example.wikipets.servicios.ServicioFuncionalidades;
import com.example.wikipets.servicios.ServicioPet;

import java.util.ArrayList;

public class GUIBuscar extends AppCompatActivity {

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

        txtCodigo = (TextView) findViewById(R.id.txtBusqueda);
        textoBusqueda = (TextView) findViewById(R.id.textBusqueda);
        textoBusqueda.setVisibility(View.GONE);
        layoutFormulario = (LinearLayout) findViewById(R.id.layoutFormulario);
        layoutFormulario.setVisibility(View.GONE);
    }

    public void btnBuscar_Click (View view) {

        try {
            ServicioPet servicioPet = new ServicioPet(this);
            String textBusqueda = txtCodigo.getText().toString();
            Pet busqueda = servicioPet.searchPetsByName(textBusqueda);

            if (busqueda != null){
                layoutFormulario.setVisibility(View.VISIBLE);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(txtCodigo.getWindowToken(), 0);

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
                spnTipo.setSelection(TipoAnimal.getIndexSpinnerValue(spnTipo, String.valueOf(busqueda.getAnimalType())));

                btnFecha = (ImageButton) findViewById(R.id.btnFechaForm);
                btnFecha.setVisibility(View.GONE);

            }else {
                Toast.makeText(this, "No se encontró ningún mascota.", Toast.LENGTH_LONG ).show();
            }
        }catch (Exception e){
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }


    public void btnVolver_Click (View view) {
        finish();
    }
}