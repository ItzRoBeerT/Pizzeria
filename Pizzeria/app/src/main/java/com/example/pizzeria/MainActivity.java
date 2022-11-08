package com.example.pizzeria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pizzeria.Clases.Herramientas;
import com.example.pizzeria.Clases.Servicio;
import com.example.pizzeria.Clases.Usuario;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "myFile";
    Herramientas tools = new Herramientas(this);
    Servicio servicio = new Servicio();
    Usuario user= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tools.quitarTitulo();
        setContentView(R.layout.activity_main);

        CheckBox remember  = findViewById(R.id.checkRecuerdame);
        TextView txt = findViewById(R.id.txtRegistro);

        //En caso de necesitar el nombre y contraseña del usuario guardados
        SharedPreferences pref = getSharedPreferences(FILE_NAME,MODE_PRIVATE);
        String username = pref.getString("username","");
        String passw = pref.getString("username","");



        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tools.cambiarActividad(RegistroActivity.class);
            }
        });

        //BOTON DE LOGUEARTE
        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean encontrado=false;
               TextView txtUsu= findViewById(R.id.txtUsuario);
               String usuario= txtUsu.getText().toString();
               TextView txtContra= findViewById(R.id.txtContraseña);
               String contra= txtContra.getText().toString();

               for(Usuario usu: servicio.getUsuarios()){
                    if(usu.getUser().equals(usuario) && usu.getPassword().equals(contra)){
                        encontrado= true;
                        user= usu;
                    }
               }
               if(encontrado==true){
                   if(remember.isChecked()){
                       recogerDatosSharedPref(user.getUser(),user.getPassword());
                       tools.cambiarActividad(LoggedActivity.class);
                   }else
                   tools.pasarValor(LoggedActivity.class,user);
               }else
                tools.crearDialogo("Usuario o contraseña incorrectos","¿Desea volverlo a intentar?");
            }
        });

        //ALGORITMO PARA RECORDARTE
        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        String check = preferences.getString("remember","");

        if(check.equals("true")){
            tools.cambiarActividad(LoggedActivity.class);
        }else if(check.equals("false")){
            Toast.makeText(this,"Porfavor inicie sesion",Toast.LENGTH_SHORT).show();
        }


        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor= preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();
                    Toast.makeText(MainActivity.this, "Guardado", Toast.LENGTH_SHORT).show();
                }else if(!compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor= preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                    Toast.makeText(MainActivity.this, "No guardado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void recogerDatosSharedPref(String usuario, String contra) {
        SharedPreferences.Editor editor = getSharedPreferences(FILE_NAME,MODE_PRIVATE).edit();
        editor.putString("usuario",usuario);
        editor.putString("contra",contra);
        editor.apply();
    }
}