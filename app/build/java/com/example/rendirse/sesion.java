package com.example.rendirse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class sesion extends AppCompatActivity{

    TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton fb;
float v=0;
private String url;
   EditText mEditNombre;
    private EditText mEditCorreo;
    private EditText mEditPassword;


  String name;
    private String correo;
    private String password;


    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mEditNombre= findViewById(R.id.nom);
        mEditCorreo= findViewById(R.id.cor);
        mEditPassword= findViewById(R.id.con);

        tabLayout= findViewById(R.id.tab_layout);
        viewPager= findViewById(R.id.view_pager);
        fb= findViewById(R.id.fab_google);

        tabLayout.addTab(tabLayout.newTab().setText("Iniciar Sesion"));
        tabLayout.addTab(tabLayout.newTab().setText("Registrarse"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final loginAdapter adapter = new loginAdapter(getSupportFragmentManager(),this,tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        fb.setTranslationY(300);
        tabLayout.setTranslationY(300);
        fb.setAlpha(v);
        tabLayout.setAlpha(v);



        fb.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();

        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();

     tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
         @Override
         public void onTabSelected(TabLayout.Tab tab) {
             viewPager.setCurrentItem(tab.getPosition());

         }

         @Override
         public void onTabUnselected(TabLayout.Tab tab) {

         }

         @Override
         public void onTabReselected(TabLayout.Tab tab) {

         }
     });
url="https://www.facebook.com/profile.php?id=100006821100891/";
fb.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Uri uri =Uri.parse(url);
        Intent intent= new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
});
    }



}