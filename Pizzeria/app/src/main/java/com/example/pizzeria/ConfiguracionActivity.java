package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;

public class ConfiguracionActivity extends AppCompatActivity {

    SwitchCompat conmutador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        conmutador= findViewById(R.id.conmutarTema);
        SharedPreferences preferences = getSharedPreferences("night",0);

        Boolean flag = preferences.getBoolean("night_mode",true);
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
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("night_mode",true);
                    editor.commit();
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    conmutador.setChecked(false);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("night_mode",false);
                    editor.commit();
                }
            }
        });

    }
}