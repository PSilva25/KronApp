package com.xetelas.nova.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.xetelas.nova.Objects.Caronas;
import com.xetelas.nova.R;

import java.util.UUID;

public class Fragment_Cadastrar extends Fragment {

    Spinner de, para;
    EditText data, hora, coment;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser  user= firebaseAuth.getCurrentUser();

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

                String x = UUID.randomUUID().toString().replace("-","");

                FirebaseApp.initializeApp(getContext());
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference().child("user").child(user.getUid()).child(x);

                Caronas dados = new Caronas();

                dados.setId(x);

                dados.setOrigem(de.getSelectedItem().toString());
                dados.setDestino(para.getSelectedItem().toString());
                dados.setData(data.getText().toString());
                dados.setHora(hora.getText().toString());
                dados.setComent(coment.getText().toString());

                de.setAdapter(adapter);
                para.setAdapter(adapter);
                data.setText("");
                hora.setText("");
                coment.setText("");

                databaseReference.child("id").setValue(x);
                databaseReference.child("origem").setValue(dados.getOrigem());
                databaseReference.child("destino").setValue(dados.getDestino());
                databaseReference.child("data").setValue(dados.getData());
                databaseReference.child("hora").setValue(dados.getHora());
                databaseReference.child("comentario").setValue(dados.getComent());

                Toast.makeText(getContext(),"Cadastro concluído!",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


}
