package com.example.rendirse;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LOginTabFragment extends Fragment {


    EditText email,pass;
    TextView forget;
    Button sesion;

    private String corre="";
    private String contra="";


    private FirebaseAuth mAuth;
    float v=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment,container, false);

        mAuth= FirebaseAuth.getInstance();

        email= root.findViewById(R.id.email);
        pass= root.findViewById(R.id.pass);
        forget= root.findViewById(R.id.forget);
        sesion= root.findViewById(R.id.button);


        email.setTranslationY(300);
        pass.setTranslationY(300);
        sesion.setTranslationY(300);

        email.setAlpha(v);
        pass.setAlpha(v);
        sesion.setAlpha(v);

        email.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        pass.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        sesion.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
      sesion.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              corre= email.getText().toString();
              contra=pass.getText().toString();
              if (!corre.isEmpty() && !contra.isEmpty()){
                  loginUser();
              }
              else{
                  Toast.makeText(getActivity(), "Complete los campos", Toast.LENGTH_SHORT).show();
              }

          }
      });
        return root;
    }

    private void loginUser() {
        mAuth.signInWithEmailAndPassword(corre, contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(getActivity(),principal.class));
                    getActivity().finish();
                }
                else{
                    Toast.makeText(getActivity(), "Nose pudo iniciar sesion, compruebe los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null){
            startActivity(new Intent(getActivity(),principal.class));
            getActivity().finish();
        }
    }
}
