package com.example.wikipets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wikipets.estructural.Estudiante;
import com.example.wikipets.servicios.ServicioEstudiantes;

public class GUIAdicionar extends AppCompatActivity {

    private TextView codigo;

    private TextView identificacion;

    private TextView nombres;

    private TextView apellidos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_u_i_adicionar);


    }

    public void btnRegitrar_Click (View view) {
        try{
            if(codigo.getText().toString().isEmpty()) {Toast.makeText(this, "El código no puede estar vacío.", Toast.LENGTH_LONG).show(); return;}
            if(identificacion.getText().toString().isEmpty()) {Toast.makeText(this, "La identificación no puede ser vacío.", Toast.LENGTH_LONG).show(); return;}
            if(nombres.getText().toString().isEmpty()) {Toast.makeText(this, "El nombre no puede estar vacío.", Toast.LENGTH_LONG).show(); return;}
            if(apellidos.getText().toString().isEmpty()) {Toast.makeText(this, "El apellido no puede estar vacío.", Toast.LENGTH_LONG).show(); return;}

            Estudiante est = new Estudiante(codigo.getText().toString(), identificacion.getText().toString(), nombres.getText().toString(), apellidos.getText().toString());
            ServicioEstudiantes.agregarEstudiante(est);
            Toast.makeText(this, "Se registró el estudiante correctamente", Toast.LENGTH_LONG).show();
            codigo.setText(""); identificacion.setText(""); nombres.setText(""); apellidos.setText("");
        }catch (Exception e){
            Toast.makeText(this, "Error al registrar estudiante " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void btnVolver_Click (View view) {
        finish();
    }
}