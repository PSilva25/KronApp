package com.xetelas.nova.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.xetelas.nova.Objects.Caronas;
import com.xetelas.nova.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Fragment_Cadastrar extends Fragment {

    AutoCompleteTextView de, para;
    EditText data, hora, coment;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, databaseref,databaserefcont,databasetell;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();
    final Calendar myCalendar = Calendar.getInstance();
    Fragment_Procurar pegaId = new Fragment_Procurar();
    String num = null;
    Dialog myDialog;
    EditText tell;

    String telefone="";

    String contadora = "0";

    String[] cities;

    private View view;
    private Button button;
    long maxid=0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FirebaseApp.initializeApp(getContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        view = inflater.inflate(R.layout.activity_cadastrar, container, false);

        de = view.findViewById(R.id.spinner_de);
        para = view.findViewById(R.id.spinner_para);
        data = view.findViewById(R.id.edit_Data);
        hora = view.findViewById(R.id.edit_Hora);
        coment = view.findViewById(R.id.edit_coment);

        cities = getResources().getStringArray(R.array.cidades);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, cities);

        de.setAdapter(adapter);
        para.setAdapter(adapter);

        databaseref = firebaseDatabase.getReference().child(user.getDisplayName() + " - " + user.getUid()).child("Caronas");

        databaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    maxid = (dataSnapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaserefcont = firebaseDatabase.getReference().child("total_caronas");

        databaserefcont.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){



                    contadora = dataSnapshot.getValue().toString();

                    Toast.makeText(getContext(),"aaaaaaaaaaaaaaaaa",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        databasetell = firebaseDatabase.getReference().child(user.getDisplayName() + " - " + user.getUid()).child("telefone");

        databasetell.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()){

                    ShowPopup();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        button = view.findViewById(R.id.bot_cadastrar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int z;
                z = verify();

                if (de.getText().toString().equals("") || de.getText().toString().equals("") || data.toString().equals("") || hora.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "PREENCHA OS CAMPOS OBRIGATORIOS *  ", Toast.LENGTH_LONG).show();
                } else if (z == -1) {
                    Toast.makeText(getContext(), "ORIGEM E DESTINO PRECISAM SER DIFERENTES", Toast.LENGTH_LONG).show();
                } else if (z == 1 || z == 0) {
                    Toast.makeText(getContext(), "CIDADE NAO ENCONTRADA", Toast.LENGTH_LONG).show();
                }
                if (z == 2) {

                    Caronas dados = new Caronas();

                    dados.setId(String.valueOf(maxid+1));
                    dados.setOrigem(de.getText().toString().trim());
                    dados.setDestino(para.getText().toString().trim());
                    dados.setData(data.getText().toString());
                    dados.setHora(hora.getText().toString());
                    dados.setComent(coment.getText().toString());
                    long contadora1 = Long.valueOf(contadora);
                    de.setText("");
                    para.setText("");
                    data.setText("");
                    hora.setText("");
                    coment.setText("");

                    databaseReference.child("total_caronas").setValue(String.valueOf(contadora1+1));

                    databaseReference.child(user.getDisplayName() + " - " + user.getUid()).child("Caronas").child(String.valueOf(contadora1+1)).child("id").setValue(user.getUid());
                    databaseReference.child(user.getDisplayName() + " - " + user.getUid()).child("Caronas").child(String.valueOf(contadora1+1)).child("id_post").setValue(String.valueOf(contadora1+1));
                    databaseReference.child(user.getDisplayName() + " - " + user.getUid()).child("Caronas").child(String.valueOf(contadora1+1)).child("usuario").setValue(user.getDisplayName());
                    databaseReference.child(user.getDisplayName() + " - " + user.getUid()).child("Caronas").child(String.valueOf(contadora1+1)).child("origem").setValue(dados.getOrigem());
                    databaseReference.child(user.getDisplayName() + " - " + user.getUid()).child("Caronas").child(String.valueOf(contadora1+1)).child("destino").setValue(dados.getDestino());
                    databaseReference.child(user.getDisplayName() + " - " + user.getUid()).child("Caronas").child(String.valueOf(contadora1+1)).child("data").setValue(dados.getData());
                    databaseReference.child(user.getDisplayName() + " - " + user.getUid()).child("Caronas").child(String.valueOf(contadora1+1)).child("hora").setValue(dados.getHora());
                    databaseReference.child(user.getDisplayName() + " - " + user.getUid()).child("Caronas").child(String.valueOf(contadora1+1)).child("comentario").setValue(dados.getComent());

                    Toast.makeText(getContext(), "CADSTRO REALIZADO!! SUA CARONA FOI PUBLICADA COM SUCESSO!", Toast.LENGTH_SHORT).show();
                }
            }

        });

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt", "BR"));
                data.setText(sdf.format(myCalendar.getTime()));
            }

        };

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        hora.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Selecione a hora:");
                mTimePicker.show();
            }
        });


        return view;
    }

    public void ShowPopup() {
        myDialog.setContentView(R.layout.tell_popup);
        tell = myDialog.findViewById(R.id.edit_tell);

        Button filtro = myDialog.findViewById(R.id.bot_addtell);
        filtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = tell.getText().toString();
                databaseReference.child(user.getDisplayName() + " - " + user.getUid()).child("telefone").setValue(num);
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        myDialog.show();
    }

    public String verificaTell() {
        final String[] num = new String[0];

        databaseReference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    num[0] = (String) objSnapshot.child("telefone").getValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return num[0];
    }

    public boolean isTelefone(String numeroTelefone) {
        return numeroTelefone.matches(".((10)|([1-9][1-9]).)\\s9?[6-9][0-9]{3}-[0-9]{4}") ||
                numeroTelefone.matches(".((10)|([1-9][1-9]).)\\s[2-5][0-9]{3}-[0-9]{4}");
    }

    public int verify() {
        int x = 0;
        int y = 0;
        int z = 0;

        for (int i = 0; i < cities.length; i++) {
            if (de.getText().toString().trim().equals(cities[i])) {
                x = x + 1;
                break;
            }
        }

        for (int j = 0; j < cities.length; j++) {
            if (para.getText().toString().trim().equals(cities[j])) {
                y = y + 1;
                break;
            }
        }

        z = x + y;

        if (para.getText().toString().trim().equals(de.getText().toString().trim())) {
            z = -1;
        }

        return z;
    }
}