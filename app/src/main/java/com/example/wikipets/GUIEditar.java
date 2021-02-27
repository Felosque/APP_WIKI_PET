package com.example.wikipets;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.wikipets.servicios.ServicioFuncionalidades;

import java.util.Calendar;

public class GUIEditar extends AppCompatActivity {

    //Panel busqueda
    private TextView txtBusqueda;

    //Panel Edición
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

        nombre = (TextView) findViewById(R.id.txtNombre);
        descripcion = (TextView) findViewById(R.id.txtDescripcion);
        fechaDescubrimiento = (TextView) findViewById(R.id.txtFecha);
        altura = (TextView) findViewById(R.id.txtAltura);
        txtBusqueda = findViewById(R.id.txtBusquedaE);

        layoutBusqueda = findViewById(R.id.layoutBusqueda);
        layoutResultado = findViewById(R.id.layoutEdicion);

        spnTipo = (Spinner) findViewById(R.id.spTipo);
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ServicioFuncionalidades.tiposAnimal);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTipo.setAdapter(adaptador);
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

    public void btnBuscar_Click(View view){
        visibleResultados(view,true);
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
        dpd.show();
    }

}