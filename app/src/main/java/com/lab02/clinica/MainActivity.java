package com.lab02.clinica;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnPaciente;
    private Button btnVisita;
    private Button btnCorreo;
    private TextView txtTabla;
    private static final int REQUEST_CODE =1;
    private static final int REQUEST_CODE_2 =2;
    public static final String objeto="objeto";
    public Cliente cliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPaciente=findViewById(R.id.btnPaciente);
        btnVisita=findViewById(R.id.btnVisita);
        txtTabla=findViewById(R.id.txtTabla);
        btnCorreo=findViewById(R.id.btnCorreo);
        btnPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(),RegistrarPacientes.class),REQUEST_CODE);
            }
        });
        btnVisita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cliente == null){
                    Toast.makeText(MainActivity.this, "No hay un paciente registrado", Toast.LENGTH_LONG).show();
                }else{
                    startActivityForResult(new Intent(getApplicationContext(),RegistrarVisitas.class),REQUEST_CODE_2);
                }
            }
        });

        btnCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cliente == null){
                    Toast.makeText(MainActivity.this, "No hay un paciente registrado", Toast.LENGTH_LONG).show();
                }else {
                    Intent correoIntent = new Intent(Intent.ACTION_SEND);
                    correoIntent.setType("message/rfc22");
                    correoIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"example@gmail.com"});
                    correoIntent.putExtra(Intent.EXTRA_SUBJECT, "Correo de prueba");
                    correoIntent.putExtra(Intent.EXTRA_TEXT, "Aqui esta el mensaje");
                    try {
                        startActivity(Intent.createChooser(correoIntent, "Enviar Email"));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(getApplicationContext(), "No hay aplicacion", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE){
            if (resultCode==RESULT_OK){
                cliente = (Cliente) data.getExtras().getSerializable(objeto);
                txtTabla.setText(cliente.toString());
            }else if (resultCode==RESULT_CANCELED){
                if (cliente==null) {
                    txtTabla.setText("NO SE REGISTRO CLIENTE");
                }
            }
        }
        if(requestCode==REQUEST_CODE_2){
            if (resultCode==RESULT_OK){
                Visita visita;
                visita=(Visita) data.getExtras().getSerializable(objeto);
                cliente.setVisitas(visita);
                txtTabla.setText(cliente.toString());
            }else if (resultCode==RESULT_CANCELED) {
                if (cliente == null) {
                    txtTabla.setText("NO SE REGISTRO VISITA");
                }
            }
        }
    }
}
