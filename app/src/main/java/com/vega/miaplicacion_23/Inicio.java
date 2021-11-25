package com.vega.miaplicacion_23;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vega.miaplicacion_23.sqlite.DbUsuario;

public class Inicio extends AppCompatActivity implements View.OnClickListener {
    Button btnEditar, btnEliminar, btnMostrar, btnSalir, btnUbicacion, btnHorarios;
    TextView nombre;
    int id = 0;
    Usuario u;
    DbUsuario db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        nombre = (TextView) findViewById(R.id.nombreUsuario);
        btnEditar = (Button) findViewById(R.id.btnEditar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        btnMostrar = (Button) findViewById(R.id.btnMostrar);
        btnSalir = (Button) findViewById(R.id.btnSalir);
        btnUbicacion = (Button) findViewById(R.id.btnUbicacion);
        btnHorarios = (Button) findViewById(R.id.btnHorarios);
        btnHorarios.setOnClickListener(this);
        btnEditar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
        btnMostrar.setOnClickListener(this);
        btnSalir.setOnClickListener(this);
        btnUbicacion.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        id = b.getInt("Id");
        db = new DbUsuario(this);
        u = db.getUsuarioById(id);
        nombre.setText("Holaa!" +" "+u.getNombre()+" "+u.getApellido());

    }

    @Override
    public void onClick(View v) {
        switch  (v.getId()){
            case R.id.btnEditar:
                Intent a = new Intent( Inicio.this, Editar.class);
                a.putExtra("Id", id);
                startActivity(a);
                break;
            case R.id.btnEliminar:
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setMessage("¿Estás seguro que quieres eliminar esta cuenta?");
                b.setCancelable(false);
                b.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (db.deleteUsuario(id)){
                            Toast.makeText(Inicio.this, "Se eliminó correctamente", Toast.LENGTH_LONG).show();
                            Intent a = new Intent( Inicio.this, MainActivity.class);
                            startActivity(a);
                            finish();
                        }else{
                            Toast.makeText(Inicio.this, "No se logró eliminar la cuenta", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                b.show();
                break;
            case R.id.btnMostrar:
                Intent c = new Intent( Inicio.this, Mostrar.class);
                startActivity(c);
                break;
            case R.id.btnSalir:
                Intent i2 = new Intent( Inicio.this, MainActivity.class);
                startActivity(i2);
                finish();
                break;
            case R.id.btnUbicacion:
                Intent m = new Intent( Inicio.this, MapsActivity.class);
                startActivity(m);
                break;
            case R.id.btnHorarios:
                Intent horario = new Intent( Inicio.this, Horario.class);
                startActivity(horario);
                break;
        }
    }
}