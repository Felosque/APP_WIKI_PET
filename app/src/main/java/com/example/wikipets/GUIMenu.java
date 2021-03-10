package com.example.wikipets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GUIMenu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_u_i_menu);
    }

    public void btnAdd_Click (View view) {
        Intent intent = new Intent(this, GUIAdicionar.class);
        startActivity(intent);
    }

    public void btnDel_Click (View view) {
        Intent intent = new Intent(this, GUIEliminar.class);
        intent.putExtra("ANIMAL", "");
        startActivity(intent);
    }

    public void btnBuscar_Click (View view) {
        Intent intent = new Intent(this, GUIBuscar.class);
        startActivity(intent);
    }

    public void btnEditar_Click (View view) {
        Intent intent = new Intent(this, GUIEditar.class);
        intent.putExtra("ANIMAL", "");
        startActivity(intent);
    }

    public void btnListar_Click (View view) {
        Intent intent = new Intent(this, GUIListar.class);
        startActivity(intent);
    }

    public void btnGrafica_Click (View view) {
        Intent intent = new Intent(this, GUIGrafica.class);
        startActivity(intent);
    }

}