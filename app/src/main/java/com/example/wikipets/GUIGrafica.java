package com.example.wikipets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.wikipets.servicios.ServicioPet;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GUIGrafica extends AppCompatActivity {


    PieChart pieChart;

    BarChart barChart;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_grafica_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.itemBar:
                showPieChart();
                return true;

            case R.id.itemPai:
                showBarChart();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_u_i_grafica);

        pieChart = findViewById(R.id.charPie);
        //showPieChart();

        barChart = findViewById(R.id.charBar);
        showBarChart();
    }


    private void showPieChart(){

        pieChart.setVisibility(View.VISIBLE);
        if (barChart.getVisibility() != View.GONE){
            barChart.setVisibility(View.GONE);
        }

        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        //initializing data
        Map<String, Integer> typeAmountMap = ServicioPet.getQuantityPetsOfType();

        //initializing colors for the entries
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#304567"));
        colors.add(Color.parseColor("#309967"));
        colors.add(Color.parseColor("#476567"));
        colors.add(Color.parseColor("#890567"));
        colors.add(Color.parseColor("#a35567"));
        colors.add(Color.parseColor("#ff5f67"));
        colors.add(Color.parseColor("#3ca567"));

        //input data and fit data into pie chart entry
        for(String type: typeAmountMap.keySet()){
            pieEntries.add(new PieEntry(typeAmountMap.get(type).intValue(), type));
        }

        //collecting the entries with label name
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "");
        //setting text size of the value
        pieDataSet.setValueTextSize(12f);

        pieDataSet.setValueTextColor(Color.WHITE);
        //providing color list for coloring different entries
        pieDataSet.setColors(colors);
        //grouping the data set from entry to chart
        PieData pieData = new PieData(pieDataSet);
        //showing the value of the entries, default true if not set
        //pieData.setDrawValues(true);

        pieChart.setData(pieData);

        Description description = new Description();
        description.setText("");
        pieChart.setDescription(description);
        pieChart.invalidate();
    }

    private void showBarChart(){

        barChart.setVisibility(View.VISIBLE);
        if (pieChart.getVisibility() != View.GONE) {
            pieChart.setVisibility(View.GONE);
        }

        ArrayList<BarDataSet> barDataSets = new ArrayList<>();
        BarData data = new BarData();

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#304567"));
        colors.add(Color.parseColor("#309967"));
        colors.add(Color.parseColor("#476567"));
        colors.add(Color.parseColor("#890567"));
        colors.add(Color.parseColor("#a35567"));
        colors.add(Color.parseColor("#ff5f67"));
        colors.add(Color.parseColor("#3ca567"));

        Map<String, Integer> typeAmountMap = ServicioPet.getQuantityPetsOfType();
        int i = 0;
        for (Map.Entry<String, Integer> entry : typeAmountMap.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            ArrayList<BarEntry> entries = new ArrayList<>();
            BarEntry barEntry = new BarEntry(i, entry.getValue());
            entries.add(barEntry);

            BarDataSet barDataSet = new BarDataSet(entries, entry.getKey());
            barDataSet.setColors(colors.get(i));

            data.addDataSet(barDataSet);
            i++;
        }

        Description description = new Description();
        description.setText("");
        barChart.setDescription(description);
        barChart.setData(data);
        barChart.invalidate();
    }

    public void btnVolver_Click (View view) {
        finish();
    }

}