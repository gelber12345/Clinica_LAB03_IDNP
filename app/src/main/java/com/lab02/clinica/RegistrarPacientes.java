package com.lab02.clinica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import static com.lab02.clinica.MainActivity.objeto;

public class RegistrarPacientes extends AppCompatActivity {

    public EditText etNombre;
    public EditText etDni;
    public EditText etDireccion;
    public EditText etCorreo;
    public ImageButton btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_pacientes);

        etNombre=findViewById(R.id.etNombre);
        etDni=findViewById(R.id.etDni);
        etDireccion=findViewById(R.id.etDireccion);
        etCorreo=findViewById(R.id.etCorreo);
        btnRegistrar=findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                if ( etNombre.getText().toString().length()>=0) {
                    String nombre= etNombre.getText().toString();
                    if (etDni.getText().toString().matches("[0-9]{8}")) {
                        String dni= etDni.getText().toString();
                        if (etDireccion.getText().toString().length()>=0){
                            String direccion= etDireccion.getText().toString();
                            if (etCorreo.getText().toString().matches("^(([^<>()\\[\\]\\\\.,;:\\s@”]+(\\.[^<>()\\[\\]\\\\.,;:\\s@”]+)*)|(“.+”))@((\\[[0–9]{1,3}\\.[0–9]{1,3}\\.[0–9]{1,3}\\.[0–9]{1,3}])|(([a-zA-Z\\-0–9]+\\.)+[a-zA-Z]{2,}))$")){
                                String correo= etCorreo.getText().toString();
                                Cliente cliente = new Cliente(nombre,dni,direccion,correo);
                                intent.putExtra(objeto,cliente);
                                setResult(RESULT_OK,intent);
                                finish();
                            }else{
                                Toast.makeText(RegistrarPacientes.this, "Por favor, introduce bien el Correo", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(RegistrarPacientes.this, "Por favor, introduce bien la Direccion", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(RegistrarPacientes.this, "Por favor, introduce bien el DNI", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(RegistrarPacientes.this, "Por favor, introduce bien el Nombre", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
