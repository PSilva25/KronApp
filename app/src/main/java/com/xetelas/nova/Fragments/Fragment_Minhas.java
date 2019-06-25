package com.xetelas.nova.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.xetelas.nova.Adapter.CaronasAdapter;
import com.xetelas.nova.Objects.Caronas;
import com.xetelas.nova.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class Fragment_Minhas extends Fragment {
    ListView lv;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();

    ArrayList<Caronas> dados = new ArrayList<>();
    CaronasAdapter ad;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FirebaseApp.initializeApp(getContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("user");

        View view = inflater.inflate(R.layout.fragment_minhas, container, false);

        lv = view.findViewById(R.id.lista_minhas);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dados.clear();
                String opa;

                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    opa = objSnapshot.child("id").getValue().toString();

                    if(opa.equals(user.getUid())){
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
                }

                ad = new CaronasAdapter(getContext().getApplicationContext(), dados);

                lv.setAdapter(ad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }
}
