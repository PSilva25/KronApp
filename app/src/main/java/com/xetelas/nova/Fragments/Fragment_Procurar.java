package com.xetelas.nova.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.xetelas.nova.Adapter.CaronasAdapter;
import com.xetelas.nova.Objects.Caronas;
import com.xetelas.nova.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Procurar extends Fragment {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FloatingActionButton fab, fabdelete;
    List<Caronas> dados = new ArrayList<>();
    List<Caronas> dados2 = new ArrayList<>();
    CaronasAdapter ad;

    Boolean isFilter = false;
    Dialog myDialog;
    TextView origem, destino, date;
    EditText data;
    Button cancel, filtro;
    Spinner de, para;
    Calendar myCalendar = Calendar.getInstance();
    ListView lv;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_procurar, container, false);
        fab = view.findViewById(R.id.fab);
        fabdelete = view.findViewById(R.id.delete);


        if (isFilter){
            fabdelete.show();
        }else {
            fabdelete.hide();
        }

        fabdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preencher();
                isFilter = false;
                fabdelete.hide();
            }
        });

        lv = view.findViewById(R.id.lista_geral);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog = new Dialog(getContext());
                ShowPopup();
            }
        });

        preencher();

        return view;
    }

    public void preencher (){
        FirebaseApp.initializeApp(getContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("user");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dados.clear();

                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){

                    Caronas car = new Caronas();

                    car.setNome((String) objSnapshot.child("usuario").getValue());
                    car.setOrigem((String) objSnapshot.child("origem").getValue());
                    car.setDestino((String) objSnapshot.child("destino").getValue());
                    car.setData((String) objSnapshot.child("data").getValue());
                    car.setId((String) objSnapshot.child("id").getValue());
                    car.setHora((String) objSnapshot.child("hora").getValue());
                    car.setComent((String)objSnapshot.child("comentario").getValue());

                    dados.add(car);
                }

                ad = new CaronasAdapter(getContext().getApplicationContext(), dados);

                lv.setAdapter(ad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void filtro(final String or, final String des, final String da){
        if(or.equals("-- Selecione --") && des.equals("-- Selecione --") && da.equals("")){
            Toast.makeText(getContext(), "Selecione um filtro...", Toast.LENGTH_SHORT).show();
        }else  {
            isFilter = true;
            fabdelete.show();
            dados2.clear();

            for (int x = 0; x < dados.size(); x++) {
                String prim = dados.get(x).getOrigem();
                String seg = dados.get(x).getDestino();
                String ter = dados.get(x).getData();

                if (!or.equals("-- Selecione --") && des.equals("-- Selecione --") && da.equals("")) {
                    if (prim.equals(or)) {
                        encherFiltro(x);
                    }
                } else if (or.equals("-- Selecione --") && !des.equals("-- Selecione --") && da.equals("")) {
                    if (seg.equals(des)) {
                        encherFiltro(x);
                    }
                } else if (or.equals("-- Selecione --") && des.equals("-- Selecione --") && !da.equals("")) {
                    if (ter.equals(da)) {
                        encherFiltro(x);
                    }
                } else if (!or.equals("-- Selecione --") && !des.equals("-- Selecione --") && da.equals("")) {
                    if (prim.equals(or) && seg.equals(des)) {
                        encherFiltro(x);
                    }
                } else if (!or.equals("-- Selecione --") && des.equals("-- Selecione --") && !da.equals("")) {
                    if (prim.equals(or) && ter.equals(da)) {
                        encherFiltro(x);
                    }
                } else if (or.equals("-- Selecione --") && !des.equals("-- Selecione --") && !da.equals("")) {
                    if (seg.equals(des) && ter.equals(da)) {
                        encherFiltro(x);
                    }
                }
            }

            ad = new CaronasAdapter(getContext().getApplicationContext(),dados2);

            lv.setAdapter(ad);
        }
    }

    public void encherFiltro (int x){
        Caronas car = new Caronas();

        car.setNome(dados.get(x).getNome());
        car.setOrigem(dados.get(x).getOrigem());
        car.setDestino(dados.get(x).getDestino());
        car.setData(dados.get(x).getData());
        car.setId(dados.get(x).getId());
        car.setHora(dados.get(x).getHora());
        car.setComent(dados.get(x).getComent());

        dados2.add(car);
    }

    public void ShowPopup() {

        myDialog.setContentView(R.layout.custom_popup);

        origem = myDialog.findViewById(R.id.id_de);
        destino = myDialog.findViewById(R.id.id_para);
        date = myDialog.findViewById(R.id.text_date);

        de = myDialog.findViewById(R.id.spinner_de);
        para = myDialog.findViewById(R.id.spinner_para);
        data = myDialog.findViewById(R.id.edit_Data);

        final ArrayAdapter adapter = ArrayAdapter.createFromResource(getContext(), R.array.cidades, R.layout.support_simple_spinner_dropdown_item);

        de.setAdapter(adapter);
        para.setAdapter(adapter);

        filtro = myDialog.findViewById(R.id.bot_filtrar);
        filtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtro(de.getSelectedItem().toString(), para.getSelectedItem().toString(), data.getText().toString());
                myDialog.dismiss();
            }
        });

        cancel = myDialog.findViewById(R.id.bot_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
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

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        myDialog.show();
    }

    private void updateLabel () {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt", "BR"));

        data.setText(sdf.format(myCalendar.getTime()));
    }
}