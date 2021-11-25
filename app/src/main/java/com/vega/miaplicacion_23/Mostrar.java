package com.vega.miaplicacion_23;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.vega.miaplicacion_23.sqlite.DbUsuario;

import java.util.ArrayList;

public class Mostrar extends AppCompatActivity {
    ListView lista;
    DbUsuario db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        lista = (ListView) findViewById(R.id.lista);
        db = new DbUsuario(this);
        ArrayList<Usuario> l = db.selectUsuarios();
        ArrayList<String> list = new ArrayList<String>();
        for (Usuario u: l){
            list.add(u.getNombre()+" "+u.getApellido());
        }
        ArrayAdapter<String> a = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, list);
        lista.setAdapter(a);
    }
}