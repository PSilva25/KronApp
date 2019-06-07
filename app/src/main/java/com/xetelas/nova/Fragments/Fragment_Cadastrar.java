package com.xetelas.nova.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.xetelas.nova.Objects.Caronas;
import com.xetelas.nova.R;

public class Fragment_Cadastrar extends Fragment {

    Spinner de, para;
    EditText data, hora, coment;
    Fragment_Minhas opa = new Fragment_Minhas();

    private View view;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_cadastrar, container, false);

        de = view.findViewById(R.id.spinner_de);
        para = view.findViewById(R.id.spinner_para);
        data = view.findViewById(R.id.edit_Data);
        hora = view.findViewById(R.id.edit_Hora);
        coment = view.findViewById(R.id.edit_coment);

        final ArrayAdapter adapter = ArrayAdapter.createFromResource(getContext(), R.array.cidades, R.layout.support_simple_spinner_dropdown_item);

        de.setAdapter(adapter);
        para.setAdapter(adapter);

        button = view.findViewById(R.id.bot_cadastrar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String bora = de.getSelectedItem().toString();
                String indo = para.getSelectedItem().toString();
                String date = data.getText().toString();
                String time = hora.getText().toString();
                String com = coment.getText().toString();

                opa.car.add(new Caronas(bora, indo, date, time, com));

                Toast.makeText(getContext(), "Cadastro concluído!!", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
