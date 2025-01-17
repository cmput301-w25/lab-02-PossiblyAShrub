package com.arolsen.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

    Button addButton;
    EditText addCityEditText;

    private void onClickAdd(View _view) {
        var name = addCityEditText.getText().toString();
        if (name.isBlank()) {
            // Don't add a new city with a blank name
            return;
        }

        dataList.add(name);
        cityAdapter.notifyDataSetChanged();

        addCityEditText.setText("");
    }

    private void onClickCity(AdapterView<?> _adapterView, View _view, int index, long _id) {
        dataList.remove(index);
        cityAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_list);
        addButton = findViewById(R.id.add);
        addCityEditText = findViewById(R.id.add_city_edit_text);

        String[] cities = {"Edmonton", "Paris", "London", "Ottawa"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        addButton.setOnClickListener(this::onClickAdd);
        cityList.setOnItemClickListener(this::onClickCity);
    }
}