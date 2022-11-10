package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

public class ConfiguracionActivity extends AppCompatActivity {

    SwitchCompat conmutador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        conmutador= findViewById(R.id.conmutarTema);
        SharedPreferences preferenciasConmutar = getSharedPreferences("night",0);

        Boolean flag = preferenciasConmutar.getBoolean("night_mode",true);
        if (flag){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            conmutador.setChecked(true);
        }

        conmutador.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    conmutador.setChecked(true);
                    SharedPreferences.Editor editor = preferenciasConmutar.edit();
                    editor.putBoolean("night_mode",true);
                    editor.commit();
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    conmutador.setChecked(false);
                    SharedPreferences.Editor editor = preferenciasConmutar.edit();
                    editor.putBoolean("night_mode",false);
                    editor.commit();
                }
            }
        });

        Button btnVolver = findViewById(R.id.btnVolverConfig);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}