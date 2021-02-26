package com.example.wikipets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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


        }catch (Exception e){
            Toast.makeText(this, "Error al registrar estudiante " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void btnVolver_Click (View view) {
        finish();
    }
}