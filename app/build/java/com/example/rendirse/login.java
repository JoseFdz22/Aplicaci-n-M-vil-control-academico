package com.example.rendirse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {

    private EditText mEditNombre;
    private EditText mEditCorreo;
    private EditText mEditPassword;
    private Button btnRegistrar;

    private String name;
    private String correo;
    private String password;


    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mEditNombre= (EditText) findViewById(R.id.EditNombre);
        mEditCorreo= (EditText) findViewById(R.id.EditCorreo);
        mEditPassword= (EditText) findViewById(R.id.EditPassword);
        btnRegistrar=(Button) findViewById(R.id.btnRegistrar);


    }

    public void onClick(View view) {
        name=mEditNombre.getText().toString();
        correo=mEditCorreo.getText().toString();
        password=mEditPassword.getText().toString();

        if (!name.isEmpty() && !correo.isEmpty() && !password.isEmpty()){
            if (password.length() >= 6){
                registrarUsuario();
            }
            else
            {
                Toast.makeText(this,"El password debe tener al menos 6 caracteres",Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Debe completar los campos", Toast.LENGTH_SHORT).show();
        }



    }

    private void registrarUsuario() {
mAuth.createUserWithEmailAndPassword(correo, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()){
            Map<String, Object> map = new HashMap<>();
            map.put("name", name);
            map.put("correo", correo);
            map.put("password", password);
            String id = mAuth.getCurrentUser().getUid();

           mDatabase.child("estudiantes").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task2) {
                    if (task2.isSuccessful()){
startActivity(new Intent(login.this,menu.class));
finish();
                    }
                    else{
                        Toast.makeText(login.this, "Nose pudieron crear los datos correctamente", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
        else
        {
            Toast.makeText(login.this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
        }
    }
});
    }
}