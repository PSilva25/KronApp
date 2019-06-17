package com.xetelas.nova.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
import java.util.ArrayList;

public class Fragment_Minhas extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    SwipeRefreshLayout mSwipeToRefresh;
    ListView lv;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();

    ArrayList<Caronas> dados = new ArrayList<>();
    ArrayAdapter<Caronas> ad;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FirebaseApp.initializeApp(getContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        View view = inflater.inflate(R.layout.fragment_minhas, container, false);

        lv = view.findViewById(R.id.lista_minhas);

        databaseReference.child("user").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dados.clear();

                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){

                    Caronas car = new Caronas();

                    car.setOrigem((String) objSnapshot.child("origem").getValue());
                    car.setDestino((String) objSnapshot.child("destino").getValue());
                    car.setData((String) objSnapshot.child("data").getValue());
                    car.setId((String) objSnapshot.child("id").getValue());
                    car.setHora((String) objSnapshot.child("hora").getValue());
                    car.setComent((String)objSnapshot.child("comentario").getValue());

                    dados.add(car);

                }

                ad = new ArrayAdapter<>(
                        getContext(),
                        android.R.layout.simple_list_item_1,
                        dados
                );

                lv.setAdapter(ad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference().child(user.getUid());

        mSwipeToRefresh = view.findViewById(R.id.swipe_refresh_container);

        mSwipeToRefresh.setOnRefreshListener(this);

        mSwipeToRefresh.setColorSchemeResources(
                R.color.colorPrimary
        );

        return view;
    }

    @Override
    public void onRefresh() {

        Toast.makeText(getContext(),"Atualizado!",Toast.LENGTH_SHORT).show();


        mSwipeToRefresh.setRefreshing(false);
    }
}
