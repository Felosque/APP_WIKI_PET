package com.example.wikipets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.example.wikipets.estructural.Estudiante;
import com.example.wikipets.servicios.ServicioEstudiantes;

public class GUIListar extends AppCompatActivity {

    private TextView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_u_i_listar);

        lista = (TextView) findViewById(R.id.txtMultiLine2);
    }

    public void btnListar_Click (View view){
        try {
            ArrayList<Estudiante> busqueda = ServicioEstudiantes.darEstudiantes();
            if (busqueda != null){
                String txtBusqueda = "";
                for (int i = 0; i < busqueda.size(); i++){
                    txtBusqueda += "Estudiante: \n" +
                            "Código: " + busqueda.get(i).getCodigo() + "\n"+
                            "N° Identificación: " + busqueda.get(i).getDocumentoIdentificacion() + "\n"+
                            "Nombres: " + busqueda.get(i).getNombre() + "\n"+
                            "Apellidos: " + busqueda.get(i).getApellido() + "\n\n";
                }
                lista.setText(txtBusqueda);
            }else {
                lista.setText("");
                Toast.makeText(this, "No se encontró ningún estudiante.", Toast.LENGTH_LONG ).show();
            }
        }catch (Exception e){
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    public void btnVolver_Click (View view) {
        finish();
    }
}