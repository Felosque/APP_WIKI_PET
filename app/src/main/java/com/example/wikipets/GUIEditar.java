package com.example.wikipets;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wikipets.estructural.Pet;
import com.example.wikipets.estructural.TipoAnimal;
import com.example.wikipets.servicios.ServicioFuncionalidades;
import com.example.wikipets.servicios.ServicioPet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class GUIEditar extends AppCompatActivity {

    //Panel busqueda
    private TextView txtBusqueda;

    //Panel Edición
    private int idMascota;

    private TextView nombre;

    private TextView descripcion;

    private TextView fechaDescubrimiento;

    private TextView altura;

    private Calendar c;
    private DatePickerDialog dpd;

    private Spinner spnTipo;

    //Layouts edicion
    private LinearLayout layoutBusqueda;

    private LinearLayout layoutResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_u_i_editar);

        Intent intent = getIntent();
        String cad = intent.getStringExtra("ANIMAL");

        nombre = (TextView) findViewById(R.id.txtNombre);
        descripcion = (TextView) findViewById(R.id.txtDescripcion);
        fechaDescubrimiento = (TextView) findViewById(R.id.txtFecha);
        altura = (TextView) findViewById(R.id.txtAltura);
        txtBusqueda = findViewById(R.id.txtBusqueda);
        idMascota = -1;

        layoutBusqueda = findViewById(R.id.layoutBusqueda);
        layoutResultado = findViewById(R.id.layoutEdicion);

        spnTipo = (Spinner) findViewById(R.id.spTipo);
        ArrayList<String> typeAnimals;
        TipoAnimal.loadAnimals();
        typeAnimals = TipoAnimal.getTypeAnimal();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,typeAnimals);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTipo.setAdapter(adapter);

        if(!cad.isEmpty())
            txtBusqueda.setText(cad);
    }

    public void btnVolver_Click (View view) {
        finish();
    }

    public void btnFecha_Clic(View view){
        mostrarCalendario(view);
    }

    public void btnCancelar_Click(View view){
        txtBusqueda.setText("");
        visibleResultados(view,false);
    }

    public void btnActualizar_Click(View view){
        try {

            Date textoFecha = ServicioFuncionalidades.ParseFecha(fechaDescubrimiento.getText().toString());
            if(textoFecha.after(new Date())) {Toast.makeText(this, "No se puede poner una fecha superior a hoy.", Toast.LENGTH_LONG).show(); return;}
            if(nombre.getText().toString().isEmpty()) {Toast.makeText(this, "El nombre no puede estar vacío.", Toast.LENGTH_LONG).show(); return;}
            if(fechaDescubrimiento.getText().toString().isEmpty()) {Toast.makeText(this, "El nombre no puede estar vacío.", Toast.LENGTH_LONG).show(); return;}
            if(altura.getText().toString().isEmpty()) {Toast.makeText(this, "Debe tener una altura", Toast.LENGTH_LONG).show(); return;}
            if(descripcion.getText().toString().isEmpty()) {
                descripcion.setText("Ninguna");
            }

            String textoNombre = nombre.getText().toString();
            String textoDes = descripcion.getText().toString();
            Double textoAltura = Double.parseDouble(altura.getText().toString());
            String textoTipo = spnTipo.getSelectedItem().toString();

            Pet nuevoPet = new Pet(idMascota, textoNombre, textoFecha, textoDes, textoAltura, textoTipo);
            ServicioPet.updatePet(nuevoPet);
            Toast.makeText(this, "Se actualizó correctamente la mascota ID: " + idMascota, Toast.LENGTH_LONG).show();
            visibleResultados(view,false);
        }catch (Exception e)
        {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG ).show();
        }

    }

    public void btnBuscar_Click(View view){
        try {
            String textBusqueda = txtBusqueda.getText().toString();
            Pet busqueda = ServicioPet.searchPetsByName(textBusqueda);

            if (busqueda != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(txtBusqueda.getWindowToken(), 0);

                this.idMascota = busqueda.getId();
                this.nombre.setText(busqueda.getName());
                this.altura.setText("" + busqueda.getHeight());
                this.descripcion.setText(busqueda.getDescription());
                this.fechaDescubrimiento.setText(ServicioFuncionalidades.dateToString(busqueda.getDiscoveredDate()));
                this.spnTipo.setSelection(ServicioFuncionalidades.getIndexSpinnerValue(spnTipo, busqueda.getAnimalType()));
                visibleResultados(view, true);
            } else {
                Toast.makeText(this, "No se encontró ninguna mascota.", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){

        }
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

    private void mostrarCalendario(View view){
        c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = 2021;

        dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                fechaDescubrimiento.setText(mDay + "/" + (mMonth+1) + "/" + mYear);
            }
        }, day, month, year);
        dpd.updateDate(year, month, day);
        dpd.show();
    }

}