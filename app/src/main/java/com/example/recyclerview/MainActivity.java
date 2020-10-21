package com.example.recyclerview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private Button btnCancelar;

    private List<Persona> personas;
    private TypedArray imgs;
    private String[] actores;
    private String[] nacionalidad;
    private String[] descripcion;
    private String[] links;

    private void inicializarDatos(){
        personas = new ArrayList<>();
        imgs = getResources().obtainTypedArray(R.array.actores_img);
        nacionalidad=getResources().getStringArray(R.array.nacionalidad);
        actores = getResources().getStringArray(R.array.actores);
        descripcion=getResources().getStringArray(R.array.descripcion);
        links=getResources().getStringArray(R.array.links);
        for (int i=0 ; i< imgs.length();i++){
            personas.add(new Persona( actores[i],"Pais de nacimiento: "+nacionalidad[i], imgs.getResourceId(i,-1) ,i,descripcion[i],links[i]));
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        inicializarDatos();

        RVAdapter rvAdapter = new RVAdapter(personas,getApplicationContext());
        recyclerView.setAdapter(rvAdapter);
    }
}