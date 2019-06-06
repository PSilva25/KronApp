package com.xetelas.nova.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.xetelas.nova.Activities.ActivityCadastrar;
import com.xetelas.nova.Objects.Caronas;
import com.xetelas.nova.R;

import java.util.ArrayList;

public class Fragment_Cadastrar extends Fragment {

    Spinner de, para;
    EditText data, hora, coment;
    Caronas opa = new Caronas();
    Caronas uma;

    ArrayAdapter adapter;

    private View view;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_cadastrar, container, false);

        de = (Spinner) view.findViewById(R.id.spinner_de);
        para = (Spinner) view.findViewById(R.id.spinner_para);
        data = (EditText) view.findViewById(R.id.edit_Data);
        hora = (EditText) view.findViewById(R.id.edit_Hora);
        coment = (EditText) view.findViewById(R.id.edit_coment);

        final ArrayAdapter adapter = ArrayAdapter.createFromResource(getContext(), R.array.cidades, R.layout.support_simple_spinner_dropdown_item);

        de.setAdapter(adapter);
        para.setAdapter(adapter);

        button = (Button) view.findViewById(R.id.bot_cadastrar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String bora = de.getSelectedItem().toString();
                String indo = para.getSelectedItem().toString();
                String date = data.getText().toString();
                String time = hora.getText().toString();
                String com = coment.getText().toString();

                uma = new Caronas(bora, indo, date, time, com);
                opa.caronas.add(uma);

                Toast.makeText(getContext(), "Cadastro concluído!!", Toast.LENGTH_LONG).show();
                de.setAdapter(adapter);
                para.setAdapter(adapter);
                data.setText("");
                hora.setText("");
                coment.setText("");


                new Fragment_Minhas();

            }
        });

        return view;

    }

}
