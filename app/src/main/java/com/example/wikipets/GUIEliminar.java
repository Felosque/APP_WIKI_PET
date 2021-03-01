package com.example.wikipets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wikipets.estructural.Pet;
import com.example.wikipets.servicios.ServicioFuncionalidades;
import com.example.wikipets.servicios.ServicioPet;

public class GUIEliminar extends AppCompatActivity {


    private LinearLayout layoutBusqueda;

    private LinearLayout layoutResultado;

    private TextView txtBusqueda;

    private TextView txtResultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_u_i_eliminar);

        layoutBusqueda = findViewById(R.id.layoutBusqueda);
        layoutResultado = findViewById(R.id.layoutResultado);
        txtBusqueda = findViewById(R.id.txtBusqueda);
        txtResultado = findViewById(R.id.txtMultiLine);

    }

    public void btnCancelar_Click(View view){
        txtBusqueda.setText("");
        visibleResultados(view,false);
    }

    public void btnBuscar_Click(View view){
        String textBusqueda = txtBusqueda.getText().toString();
        Pet busqueda = ServicioPet.searchPetsByName(textBusqueda);

        if (busqueda != null){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(txtBusqueda.getWindowToken(), 0);

            String texto = "MASCOTA N°-: " + busqueda.getId() + "\n" +
                    "Nombre: " + busqueda.getName() + "\n"+
                    "Fecha: " + ServicioFuncionalidades.dateToString(busqueda.getDiscoveredDate()) + "\n"+
                    "Altura: " + busqueda.getHeight() + "\n"+
                    "Tipo: " + busqueda.getAnimalType() + "\n"+
                    "Descripción: " + busqueda.getDescription() + "\n\n";

            txtResultado.setText(texto);
            visibleResultados(view,true);
        }else {
            Toast.makeText(this, "No se encontró ninguna mascota.", Toast.LENGTH_LONG ).show();
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