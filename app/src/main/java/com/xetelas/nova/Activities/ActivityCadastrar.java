package com.xetelas.nova.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.xetelas.nova.MainActivity;
import com.xetelas.nova.Objects.Caronas;
import com.xetelas.nova.Profile;
import com.xetelas.nova.R;
import java.util.ArrayList;

public class ActivityCadastrar extends AppCompatActivity {

    Spinner de, para;
    RadioGroup tipo;
    EditText data, hora, coment;
    Button botaocadastro;
    Caronas uma;
    ArrayList<Caronas> todas;
    AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

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
                String bora = de.getSelectedItem().toString();
                String indo = para.getSelectedItem().toString();
                String date = data.getText().toString();
                String time = hora.getText().toString();
                String com = coment.getText().toString();
                //uma = new Caronas(bora, indo, date, time, com);
                Toast.makeText(ActivityCadastrar.this, bora+indo+date+time+com, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void pegar (){
        String bora = de.getSelectedItem().toString();
        String indo = para.getSelectedItem().toString();
        String date = data.getText().toString();
        String time = hora.getText().toString();
        String com = coment.getText().toString();
        uma = new Caronas(bora, indo, date, time, com);
        Toast.makeText(ActivityCadastrar.this, bora+indo+date+time+com, Toast.LENGTH_SHORT).show();
        todas.add(uma);
    }

    public void popup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmar cadastro");
        builder.setMessage("Deseja cadastrar essa carona?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                pegar();
                Toast.makeText(ActivityCadastrar.this, "Carona cadastrada!", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(ActivityCadastrar.this, "Cancelado!", Toast.LENGTH_SHORT).show();
            }
        });

        alerta = builder.create();
        alerta.show();
    }
}
