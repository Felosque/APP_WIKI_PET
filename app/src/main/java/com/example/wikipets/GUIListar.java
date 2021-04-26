package com.example.wikipets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wikipets.adaptadores.AdaptadorPet;
import com.example.wikipets.estructural.Pet;
import com.example.wikipets.servicios.ServicioFuncionalidades;
import com.example.wikipets.servicios.ServicioPet;

import java.util.ArrayList;

public class GUIListar extends AppCompatActivity {

    private TextView lista;

    private ListView listaPets;

    private Button btnListar;
    private int statusBtn = 0;

    private ServicioPet servicioPet;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_u_i_listar);

        servicioPet = new ServicioPet(this);

        btnListar = (Button) findViewById(R.id.btnListar);
        btnListar.setText("Listar Eliminados");

        ArrayList<String> animales;
        listaPets = (ListView) findViewById(R.id.lstListaAnimales);
        AdaptadorPet adaptadorPet = null;
        try {
            adaptadorPet = new AdaptadorPet(this, R.layout.item_list, servicioPet.getPets());
            listaPets.setAdapter(adaptadorPet);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG ).show();
        }

        listaPets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(), ""+i, Toast.LENGTH_SHORT).show();
                detallesAnimal(i);
            }
        });

        ListView listView = (ListView)findViewById(R.id.lstListaAnimales);
        listView.setOnTouchListener(new ListView.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                int action = event.getAction();
                switch (action){
                    case MotionEvent.ACTION_DOWN:
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                v.onTouchEvent(event);
                return true;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        AdaptadorPet adaptadorPet = null;
        try {
            adaptadorPet = new AdaptadorPet(this, R.layout.item_list, servicioPet.getPets());
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG ).show();
        }
        listaPets.setAdapter(adaptadorPet);
    }

    private void detallesAnimal(int idMascotaArray){
        Intent intent = new Intent(this, GUIDetalles.class);
        intent.putExtra("ANIMAL", idMascotaArray);
        startActivity(intent);
    }

    public void btnListar_Click (View view){
        if (statusBtn == 0){
            btnListar.setText("Listar Mascotas Activas");
            statusBtn = 1;

            AdaptadorPet adaptadorPet = null;
            try {
                adaptadorPet = new AdaptadorPet(this, R.layout.item_list, servicioPet.getPetsByStatus());
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG ).show();
            }
            listaPets.setAdapter(adaptadorPet);
        }
        else{
            btnListar.setText("Listar Mascotas Eliminadas");
            statusBtn = 0;

            AdaptadorPet adaptadorPet = null;
            try {
                adaptadorPet = new AdaptadorPet(this, R.layout.item_list, servicioPet.getPets());
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG ).show();
            }
            listaPets.setAdapter(adaptadorPet);
        }
    }

    public void btnVolver_Click (View view) {
        finish();
    }
}
