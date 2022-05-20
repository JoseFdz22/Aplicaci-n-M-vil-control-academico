package com.example.rendirse;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class datosPersonales extends Fragment {
    private TextView ci;
    private TextView nombre;
    private TextView correo;
    private TextView contraseña;
    private TextView Apellido;
    private TextView FechaNac;
    private TextView Domicilio;
    private TextView Telefono;
    private TextView Rol;
    private TextView Estado;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_datos_personales, container, false);
        mAuth= FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference();

        ci= root.findViewById(R.id.txtvCi);
        nombre= root.findViewById(R.id.txtvNombre);
        correo= root.findViewById(R.id.txtvCorreo);
        contraseña= root.findViewById(R.id.txtvContraseña);
        Apellido= root.findViewById(R.id.txtvApellido);
        FechaNac= root.findViewById(R.id.txtvFechaNac);
        Domicilio= root.findViewById(R.id.txtvDomicilio);
        Telefono= root.findViewById(R.id.txtvTelefono);
        Rol= root.findViewById(R.id.txtvRol);
        Estado= root.findViewById(R.id.txtvEstado);

        getUserInfo();

        return root;
    }
    private void getUserInfo(){
        String id = mAuth.getCurrentUser().getUid();
        mDatabase.child("Estudiantes").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String tci= snapshot.child("Ci").getValue().toString();
                    String tnombres= snapshot.child("Nombres").getValue().toString();
                    String tapellidos= snapshot.child("Apellidos").getValue().toString();
                    String tdomicilio= snapshot.child("Domicilio").getValue().toString();
                    String ttelefono= snapshot.child("Telefono").getValue().toString();
                    String tnacimiento= snapshot.child("Nacimiento").getValue().toString();
                    String tcorreo= snapshot.child("Correo").getValue().toString();
                    String tcontraseña= snapshot.child("Contraseña").getValue().toString();
                    String trol= snapshot.child("Rol").getValue().toString();
                    String testado= snapshot.child("Estado").getValue().toString();

                    ci.setText(tci);
                    nombre.setText(tnombres);
                    Apellido.setText(tapellidos);
                    Domicilio.setText(tdomicilio);
                    Telefono.setText(ttelefono);
                    FechaNac.setText(tnacimiento);
                    correo.setText(tcorreo);
                    contraseña.setText(tcontraseña);
                    Rol.setText(trol);
                    Estado.setText(testado);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}