package com.lab02.clinica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import static com.lab02.clinica.MainActivity.objeto;

public class RegistrarVisitas extends AppCompatActivity {

    public EditText etPeso;
    public EditText etTemperatura;
    public EditText etPresion;
    public EditText etNivel;
    public ImageButton btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_visitas);

        etPeso=findViewById(R.id.etPeso);
        etTemperatura=findViewById(R.id.etTemperatura);
        etPresion=findViewById(R.id.etPresion);
        etNivel=findViewById(R.id.etNivel);
        btnRegistrar=findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                if ( etPeso.getText().toString().length()>=0) {
                    String peso= etPeso.getText().toString();
                    if (etTemperatura.getText().toString().length()>=0) {
                        String temperatura= etTemperatura.getText().toString();
                        if (etPresion.getText().toString().length()>=0){
                            String presion= etPresion.getText().toString();
                            if (etNivel.getText().toString().length()>=0){
                                String nivel= etNivel.getText().toString();
                                Visita vis = new Visita(peso,temperatura,presion,nivel);
                                intent.putExtra(objeto,vis);
                                setResult(RESULT_OK,intent);
                                finish();
                            }else{
                                Toast.makeText(RegistrarVisitas.this, "Por favor, introduce bien el Nivel de Saturacion", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(RegistrarVisitas.this, "Por favor, introduce bien la Presion", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(RegistrarVisitas.this, "Por favor, introduce bien la Temperatura", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(RegistrarVisitas.this, "Por favor, introduce bien el Peso", Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}
