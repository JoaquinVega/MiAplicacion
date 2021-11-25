package com.vega.miaplicacion_23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vega.miaplicacion_23.sqlite.DbUsuario;

public class Editar extends AppCompatActivity implements View.OnClickListener{
    EditText ediUser, ediPass, ediNombre, ediApellido;
    Button btnEditActualizar, btnEditCancelar;
    int id = 0;
    Usuario u;
    DbUsuario db;
    Intent x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        ediUser = (EditText) findViewById(R.id.EditUser);
        ediPass = (EditText) findViewById(R.id.EditPass);
        ediNombre = (EditText) findViewById(R.id.EditNombre);
        ediApellido = (EditText) findViewById(R.id.EditApellido);
        btnEditActualizar = (Button) findViewById(R.id.btnEditActualizar);
        btnEditCancelar = (Button) findViewById(R.id.btnEditCancelar);
        btnEditActualizar.setOnClickListener(this);
        btnEditCancelar.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        id = b.getInt("Id");
        db = new DbUsuario(this);
        u = db.getUsuarioById(id);
        ediUser.setText(u.getUsuario());
        ediPass.setText(u.getPassword());
        ediNombre.setText(u.getNombre());
        ediApellido.setText(u.getApellido());

    }

    @Override
    public void onClick(View v) {
        switch  (v.getId()){
            case R.id.btnEditActualizar:
                u.setUsuario(ediUser.getText().toString());
                u.setPassword(ediPass.getText().toString());
                u.setNombre(ediNombre.getText().toString());
                u.setApellido(ediApellido.getText().toString());
                if (!u.isNull()){
                    Toast.makeText(this, "ERROR: Rellena los campos", Toast.LENGTH_LONG).show();
                }else if (db.updateUsuario(u)){
                    Toast.makeText(this, "Actualización Exitosa", Toast.LENGTH_LONG).show();
                    Intent i2 = new Intent( Editar.this, Inicio.class);
                    i2.putExtra("Id", u.getId());
                    startActivity(i2);
                    finish();
                }else{
                    Toast.makeText(this, "No se pudo actualizar los datos", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnEditCancelar:
                Intent i2 = new Intent( Editar.this, Inicio.class);
                i2.putExtra("Id", u.getId());
                startActivity(i2);
                finish();
                break;
        }
    }

}