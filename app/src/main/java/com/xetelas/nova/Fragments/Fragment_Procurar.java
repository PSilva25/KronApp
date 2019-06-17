package com.xetelas.nova.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.xetelas.nova.Objects.Caronas;
import com.xetelas.nova.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Procurar extends Fragment {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<Caronas> dados = new ArrayList<>();
    ArrayAdapter<Caronas> ad;
    ListView lv;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_procurar, container, false);

        lv = view.findViewById(R.id.lista_geral);

        FirebaseApp.initializeApp(getContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("user");

        return view;
    }

    private void preencher(){

        databaseReference.child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dados.clear();

                for(DataSnapshot objSnapshot: dataSnapshot.getChildren()){

                    Caronas car = new Caronas();

                    car.setOrigem((String) objSnapshot.child("origem").getValue());
                    car.setDestino((String) objSnapshot.child("destino").getValue());
                    car.setData((String) objSnapshot.child("data").getValue());
                    car.setId((String) objSnapshot.child("id").getValue());
                    car.setHora((String) objSnapshot.child("hora").getValue());
                    car.setComent((String)objSnapshot.child("comentario").getValue());

                    dados.add(car);
                }

                ad = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dados);

                lv.setAdapter(ad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        preencher();
    }
}
