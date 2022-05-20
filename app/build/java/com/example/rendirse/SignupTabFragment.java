package com.example.rendirse;

import static android.widget.Toast.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignupTabFragment extends Fragment {
Button fun;
private EditText mEditNombre;
private EditText mEditCorreo;
private EditText mEditPassword;

    private String name;
    private String correo;
    private String password;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container, false);
fun= root.findViewById(R.id.regi);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mEditNombre= root.findViewById(R.id.nom);
        mEditCorreo= root.findViewById(R.id.cor);
        mEditPassword= root.findViewById(R.id.con);

fun.setOnClickListener(new View.OnClickListener() {
    @Override
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
                                startActivity(new Intent(getActivity(),principal.class));

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
