package com.example.wikipets;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wikipets.estructural.Pet;
import com.example.wikipets.servicios.ServicioFuncionalidades;
import com.example.wikipets.servicios.ServicioPet;

import java.util.Calendar;
import java.util.Date;

public class GUIAdicionar extends AppCompatActivity {

    private TextView nombre;

    private TextView descripcion;

    private TextView fechaDescubrimiento;

    private TextView altura;

    private Calendar c;
    private DatePickerDialog dpd;

    private Spinner spnTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_u_i_adicionar);

        c = Calendar.getInstance();
        nombre = (TextView) findViewById(R.id.txtNombre);
        descripcion = (TextView) findViewById(R.id.txtDescripcion);
        fechaDescubrimiento = (TextView) findViewById(R.id.txtFecha);
        altura = (TextView) findViewById(R.id.txtAltura);

        spnTipo = (Spinner) findViewById(R.id.spTipo);
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ServicioFuncionalidades.tiposAnimal);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTipo.setAdapter(adaptador);
    }

    public void btnFecha_Clic(View view){
        mostrarCalendario(view);
    }


    public void btnRegitrar_Click (View view) {
        try{
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
            int imagenTipo = ServicioFuncionalidades.getImageTipoAnimal(textoTipo);

            Pet nuevoPet = new Pet(textoNombre, textoFecha, textoDes, textoAltura, textoTipo, imagenTipo);
            ServicioPet.addPets(nuevoPet);
            Toast.makeText(this, "Se registró correctamente la mascota", Toast.LENGTH_LONG).show();

            nombre.setText("");
            descripcion.setText("");
            altura.setText("");
            spnTipo.setSelection(0);

        }catch (Exception e){
            Toast.makeText(this, "Error al registrar la mascota: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void mostrarCalendario(View view){

        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                fechaDescubrimiento.setText(mDay + "/" + (mMonth+1) + "/" + mYear);
            }
        }, day, month, year);
        dpd.updateDate(year, month, day);
        dpd.show();
    }

    public void btnVolver_Click (View view) {
        finish();
    }
}