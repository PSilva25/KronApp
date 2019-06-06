package com.xetelas.nova.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.xetelas.nova.Objects.Caronas;
import com.xetelas.nova.R;

import java.util.ArrayList;

public class ActivityCadastrar extends AppCompatActivity {

    Spinner de, para;
    RadioGroup tipo;
    EditText data, hora, coment;
    Button botaocadastro, teste;
    Caronas uma;
    ArrayList<Caronas> todas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        Toast.makeText(this,"ajava",Toast.LENGTH_LONG).show();

        de = findViewById(R.id.spinner_de);
        para = findViewById(R.id.spinner_para);
        data = findViewById(R.id.edit_Data);
        hora = findViewById(R.id.edit_Hora);
        coment = findViewById(R.id.edit_coment);



        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.cidades, R.layout.support_simple_spinner_dropdown_item);
        de.setAdapter(adapter);
        para.setAdapter(adapter);

        botaocadastro = findViewById(R.id.bot_cadastrar);


        botaocadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pegar();
            }
        });


        teste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coment.setText("aaaaaaaaaaaaaaaaa");
            }
        });

    }




    public void pegar (){
        Toast.makeText(getApplicationContext(),"ajava",Toast.LENGTH_LONG).show();
        String bora = de.getSelectedItem().toString();
        String indo = para.getSelectedItem().toString();
        String date = data.getText().toString();
        String time = hora.getText().toString();
        String com = coment.getText().toString();
        uma = new Caronas(bora, indo, date, time, com);
        todas.add(uma);

        coment.setText("aaaaaaaaaaaaaaaaa");

    }

}
