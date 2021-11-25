package com.vega.miaplicacion_23;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Horario extends AppCompatActivity {

    List<ListElement> elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario);

        init();
    }

    public void init() {
        elements = new ArrayList<>();
        elements.add(new ListElement("#775447","Lun a Vier","Conce a Coronel","07:15 a 20:15"));
        elements.add(new ListElement("#778227","Sáb","Concepción","07:30 a 18:30"));


        ListAdapter listAdapter = new ListAdapter(elements, this);

        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }

}