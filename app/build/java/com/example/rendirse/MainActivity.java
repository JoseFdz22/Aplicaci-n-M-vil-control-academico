package com.example.rendirse;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
EditText txtNombre;
Spinner spinSexo;

private DatabaseReference clases;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clases= FirebaseDatabase.getInstance().getReference("clases");
        txtNombre =(EditText) findViewById(R.id.txtnombre);
spinSexo=(Spinner) findViewById(R.id.spinerSexo);

    }
    public void registrarClase(){
        String nombre= txtNombre.getText().toString();
        if (!TextUtils.isEmpty(nombre)){

            String id= clases.push().getKey();
            clases campo=new clases(id, nombre,"hola");
            clases.child("Lecciones").child(id).setValue(campo);
            Toast.makeText(this,"Registrado", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Debe intorducir", Toast.LENGTH_LONG).show();
        }





    }

    public void onClick(View view) {
        registrarClase();
    }
}