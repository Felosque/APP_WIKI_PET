package com.example.wikipets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wikipets.estructural.Pet;
import com.example.wikipets.servicios.ServicioPet;

public class GUIBuscar extends AppCompatActivity {

    private TextView txtCodigo;
    private TextView txtMultiLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_u_i_buscar);

        txtCodigo = (TextView) findViewById(R.id.txtBusqueda);
        txtMultiLine = (TextView) findViewById(R.id.txtMultiLine);
    }

    public void btnBuscar_Click (View view) {

        try {
            String textBusqueda = txtCodigo.getText().toString();
            Pet busqueda = ServicioPet.searchPets(textBusqueda);

            if (busqueda != null){
                String texto = "MASCOTA N°-: " + busqueda.getId() + "\n" +
                        "Nombre: " + busqueda.getName() + "\n"+
                        "Fecha: " + busqueda.getDiscoveredDate().toString() + "\n"+
                        "Altura: " + busqueda.getHeight() + "\n"+
                        "Tipo: " + busqueda.getAnimalType() + "\n"+
                        "Descripción: " + busqueda.getDescription() + "\n\n";

                txtMultiLine.setText(texto);
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