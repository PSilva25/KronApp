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
import android.view.Menu;
import android.view.MenuInflater;
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
<<<<<<< HEAD
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.xetelas.nova.Objects.Caronas;
import com.xetelas.nova.R;

=======
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
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
>>>>>>> 9f2ba8f934ce166562758df55419ff7596d72958
import java.util.UUID;

public class Fragment_Cadastrar extends Fragment {

    AutoCompleteTextView de, para;
    EditText data, hora, coment;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();
    final Calendar myCalendar = Calendar.getInstance();
    String num = null;
    Dialog myDialog;
    EditText tell;

    String[] cities;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private View view;
    private Button button;

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

        button = view.findViewById(R.id.bot_cadastrar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

<<<<<<< HEAD
                FirebaseApp.initializeApp(getContext());
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference();

                Caronas dados = new Caronas();

                String x = UUID.randomUUID().toString().replace("-","");

                dados.setId(x);

                dados.setOrigem(de.getSelectedItem().toString());
                dados.setDestino(para.getSelectedItem().toString());
                dados.setData(data.getText().toString());
                dados.setHora(hora.getText().toString());
                dados.setComent(coment.getText().toString());



                Toast.makeText(getContext(), "Cadastro concluído!! id -> "+x, Toast.LENGTH_LONG).show();
                de.setAdapter(adapter);
                para.setAdapter(adapter);
                data.setText("");
                hora.setText("");
                coment.setText("");



            databaseReference.child("id").setValue(x);

            databaseReference.child("users").child("origem").setValue(dados.getOrigem());
            databaseReference.child("users").child("destino").setValue(dados.getDestino());
            databaseReference.child("users").child("data").setValue(dados.getData());
            databaseReference.child("users").child("hora").setValue(dados.getHora());
            databaseReference.child("users").child("comentario").setValue(dados.getComent());
=======
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
                    String x = UUID.randomUUID().toString().replace("-", "");

                    SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");

                    Date dat = new Date();

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(dat);
                    Date data_atual = cal.getTime();
                    String data_completa = dateFormat.format(data_atual);

                    Caronas dados = new Caronas();

                    dados.setId(data_completa + " - " + x);
                    dados.setOrigem(de.getText().toString());
                    dados.setDestino(para.getText().toString());
                    dados.setData(data.getText().toString());
                    dados.setHora(hora.getText().toString());
                    dados.setComent(coment.getText().toString());

                    de.setText("");
                    para.setText("");
                    data.setText("");
                    hora.setText("");
                    coment.setText("");

                    databaseReference.child(user.getDisplayName() + " - " + user.getUid()).child("Caronas").child(data_completa + " - " + x).child("id").setValue(user.getUid());
                    databaseReference.child(user.getDisplayName() + " - " + user.getUid()).child("Caronas").child(data_completa + " - " + x).child("id_post").setValue(data_completa + " - " + x);
                    databaseReference.child(user.getDisplayName() + " - " + user.getUid()).child("Caronas").child(data_completa + " - " + x).child("usuario").setValue(user.getDisplayName());
                    databaseReference.child(user.getDisplayName() + " - " + user.getUid()).child("Caronas").child(data_completa + " - " + x).child("origem").setValue(dados.getOrigem());
                    databaseReference.child(user.getDisplayName() + " - " + user.getUid()).child("Caronas").child(data_completa + " - " + x).child("destino").setValue(dados.getDestino());
                    databaseReference.child(user.getDisplayName() + " - " + user.getUid()).child("Caronas").child(data_completa + " - " + x).child("data").setValue(dados.getData());
                    databaseReference.child(user.getDisplayName() + " - " + user.getUid()).child("Caronas").child(data_completa + " - " + x).child("hora").setValue(dados.getHora());
                    databaseReference.child(user.getDisplayName() + " - " + user.getUid()).child("Caronas").child(data_completa + " - " + x).child("comentario").setValue(dados.getComent());

                    Toast.makeText(getContext(), "Cadastro concluído!", Toast.LENGTH_SHORT).show();
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
>>>>>>> 9f2ba8f934ce166562758df55419ff7596d72958
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

<<<<<<< HEAD

}
=======
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
            if (de.getText().toString().equals(cities[i])) {
                x = x + 1;
                break;
            }
        }

        for (int j = 0; j < cities.length; j++) {
            if (para.getText().toString().equals(cities[j])) {
                y = y + 1;
                break;
            }
        }

        z = x + y;

        if (para.getText().toString().equals(de.getText().toString())) {
            z = -1;
        }

        return z;
    }
}
>>>>>>> 9f2ba8f934ce166562758df55419ff7596d72958
