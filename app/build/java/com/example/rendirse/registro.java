package com.example.rendirse;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class registro extends Fragment {
    Button fun;

    private EditText mEditNombre;
    private EditText mEditCorreo;
    private EditText mEditPassword;

    private EditText mEditCi;
    private EditText mEditApellido;
    private EditText mEditFechaNac;
    private EditText mEditDomicilio;
    private EditText mEditTelefono;
    private EditText mEditRol;
    private EditText mEditEstado;

    private String nombre;
    private String correo;
    private String contraseña;

    private String ci;
    private String apellido;
    private String fecha;
    private String domicilio;
    private String telefono;
    private String rol;
    private String estado;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_registro, container, false);
        fun= root.findViewById(R.id.regi);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mEditNombre= root.findViewById(R.id.txtNombre);
        mEditCorreo= root.findViewById(R.id.txtCorreo);
        mEditPassword= root.findViewById(R.id.txtContraseña);

        mEditCi= root.findViewById(R.id.txtCi);
        mEditApellido= root.findViewById(R.id.txtApellido);
        mEditFechaNac= root.findViewById(R.id.txtFechaNac);
        mEditDomicilio= root.findViewById(R.id.txtDomicilio);
        mEditTelefono= root.findViewById(R.id.txtTelefono);
        mEditRol= root.findViewById(R.id.txtRol);
        mEditEstado= root.findViewById(R.id.txtEstado);

        fun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre=mEditNombre.getText().toString();
                correo=mEditCorreo.getText().toString();
                contraseña=mEditPassword.getText().toString();

                ci=mEditCi.getText().toString();
                apellido=mEditApellido.getText().toString();
                fecha=mEditFechaNac.getText().toString();
                domicilio=mEditDomicilio.getText().toString();
               telefono=mEditTelefono.getText().toString();
                rol=mEditRol.getText().toString();
                estado=mEditEstado.getText().toString();

                if (!nombre.isEmpty() && !correo.isEmpty() && !contraseña.isEmpty()
                        && !ci.isEmpty() && !apellido.isEmpty() && !fecha.isEmpty()
                        && !domicilio.isEmpty() && !telefono.isEmpty() && !rol.isEmpty()
                        && !estado.isEmpty()){
                    if (contraseña.length() >= 6){
                        registrarUsuario();
                    }
                    else
                    {
                        Toast.makeText(getActivity(),"El password debe tener al menos 6 caracteres",Toast.LENGTH_SHORT).show();

                    }
                }
                else{
                    Toast.makeText(getActivity(), "Debe completar los campos", Toast.LENGTH_SHORT).show();

                }


            }
        });

        return root;
    }

    private void registrarUsuario() {
        mAuth.createUserWithEmailAndPassword(correo, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Map<String, Object> map = new HashMap<>();
                    map.put("Ci", ci);
                    map.put("Nombres", nombre);
                    map.put("Apellidos", apellido);
                    map.put("Nacimiento", fecha);
                    map.put("Domicilio", domicilio);
                    map.put("Telefono", telefono);
                    map.put("Correo", correo);
                    map.put("Contraseña", contraseña);
                    map.put("Rol", rol);
                    map.put("Estado", estado);
                    String id = mAuth.getCurrentUser().getUid();
                    mDatabase.child("Estudiantes").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()){


                                Toast.makeText(getActivity(),"Registrado correctamente",Toast.LENGTH_SHORT).show();


                            }
                            else{
                                Toast.makeText(getActivity(), "Nose pudieron crear los datos correctamente", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });


                }
                else
                {
                    Toast.makeText(getActivity(), "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}