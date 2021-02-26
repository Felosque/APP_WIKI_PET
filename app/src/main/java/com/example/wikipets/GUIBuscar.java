package com.example.wikipets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
            /*Estudiante busqueda = ServicioEstudiantes.buscarEstudiante(txtCodigo.getText().toString());
            if (busqueda != null){
                String txtBusqueda = "Estudiante: \n" +
                                    "Código: " + busqueda.getCodigo() + "\n"+
                                    "N° Identificación: " + busqueda.getDocumentoIdentificacion() + "\n"+
                                    "Nombres: " + busqueda.getNombre() + "\n"+
                                    "Apellidos: " + busqueda.getApellido() + "\n";
                txtMultiLine.setText(txtBusqueda);
            }else {
                txtMultiLine.setText("");
                Toast.makeText(this, "No se encontró ningún estudiante.", Toast.LENGTH_LONG ).show();
            }*/
        }catch (Exception e){
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }


    public void btnVolver_Click (View view) {
        finish();
    }
}