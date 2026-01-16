package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);


        final Button addCityButton = findViewById(R.id.add_city_button);
        final Button confirmButton = findViewById(R.id.confirm_button);
        final EditText addCityField = findViewById(R.id.add_city_field);
        final LinearLayout inputContainer = findViewById(R.id.input_container);
        final Button deleteCityButton = findViewById(R.id.delete_city_button);

        addCityButton.setOnClickListener(v -> {
            inputContainer.setVisibility(View.VISIBLE);
            addCityButton.setVisibility(View.GONE);
        });

        confirmButton.setOnClickListener(v -> {
            String cityName = addCityField.getText().toString();

            if (!cityName.isEmpty()) {
                dataList.add(cityName);
                cityAdapter.notifyDataSetChanged();


                addCityField.setText("");
                inputContainer.setVisibility(View.GONE);
                addCityButton.setVisibility(View.VISIBLE);
            }
        });

        deleteCityButton.setOnClickListener(v -> {
            if (selectedPosition != -1) {
                dataList.remove(selectedPosition);
                cityAdapter.notifyDataSetChanged();
                selectedPosition = -1; // Reset selection after deletion
            }
        });


        cityList.setOnItemClickListener((adapterView, view, position, id) -> {
            selectedPosition = position;
        });
    }




}