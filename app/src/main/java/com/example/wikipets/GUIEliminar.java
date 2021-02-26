package com.example.wikipets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class GUIEliminar extends AppCompatActivity {


    private LinearLayout layoutBusqueda;

    private LinearLayout layoutResultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_u_i_eliminar);

        layoutBusqueda = findViewById(R.id.layoutBusqueda);
        layoutResultado = findViewById(R.id.layoutResultado);


    }

    public void btnCancelar_Click(View view){
        visibleResultados(view,false);
    }

    public void btnBuscar_Click(View view){
        visibleResultados(view,true);
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