package com.vega.miaplicacion_23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vega.miaplicacion_23.sqlite.DbUsuario;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText user,pass;
    Button btnEntrar, btnRegistrar;
    DbUsuario db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=(EditText)findViewById(R.id.txtUsuarioLogin);
        pass=(EditText) findViewById(R.id.txtPasswordLogin);
        btnEntrar=(Button) findViewById(R.id.btnEntrar);
        btnRegistrar=(Button)findViewById(R.id.btnRegistrar);
        btnEntrar.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);
        db = new DbUsuario(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEntrar:
                String u = user.getText().toString();
                String p = pass.getText().toString();
                if(u.equals("")&& p.equals("")) {
                    Toast.makeText(this, "Completa los campos", Toast.LENGTH_LONG).show();
                }else if(db.login(u,p)==1){ //Si es = 1 se puede entrar
                    Usuario ux = db.getUsuario(u,p);
                    Toast.makeText(this, "Datos correctos", Toast.LENGTH_LONG).show();
                    Intent i2 = new Intent( MainActivity.this, Inicio.class);
                    i2.putExtra("Id", ux.getId());
                    startActivity(i2);
                    finish();
                }else{
                    Toast.makeText(this, "Usuario y/o Password Incorrectos", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnRegistrar:
                Intent i = new Intent( MainActivity.this, Registrar.class);
                startActivity(i);
                break;
        }
    }
}