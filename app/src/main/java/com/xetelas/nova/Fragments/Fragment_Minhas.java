package com.xetelas.nova.Fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
<<<<<<< HEAD
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
=======
>>>>>>> 9f2ba8f934ce166562758df55419ff7596d72958
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
<<<<<<< HEAD

import com.google.firebase.FirebaseApp;
=======
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
>>>>>>> 9f2ba8f934ce166562758df55419ff7596d72958
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
<<<<<<< HEAD
=======
import com.xetelas.nova.Adapter.CaronasAdapter;
import com.xetelas.nova.Adapter.CaronasAdapterMinhas;
>>>>>>> 9f2ba8f934ce166562758df55419ff7596d72958
import com.xetelas.nova.Objects.Caronas;
import com.xetelas.nova.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Objects;

<<<<<<< HEAD
import static com.facebook.AccessTokenManager.TAG;

public class Fragment_Minhas extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    Caronas opa = new Caronas();
    ArrayList<Caronas> car = new ArrayList<>();
    SwipeRefreshLayout mSwipeToRefresh;
    ListView lv;
=======
public class Fragment_Minhas extends Fragment {
    ListView lv;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();

    ArrayList<Caronas> dados = new ArrayList<>();
    Context context;
    CaronasAdapterMinhas ad;
>>>>>>> 9f2ba8f934ce166562758df55419ff7596d72958

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FirebaseApp.initializeApp(getContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        View view = inflater.inflate(R.layout.fragment_minhas, container, false);

<<<<<<< HEAD
        lv = view.findViewById(R.id.lista_minhas);

        mSwipeToRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_container);

        mSwipeToRefresh.setOnRefreshListener(this);

        mSwipeToRefresh.setColorSchemeResources(
                R.color.colorPrimary
        );

=======
        context=getContext().getApplicationContext();
        lv = view.findViewById(R.id.lista_minhas);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dados.clear();

                for (DataSnapshot objSnapshot:dataSnapshot.child(user.getDisplayName() + " - " + user.getUid()).child("Caronas").getChildren()){
                    Caronas car = new Caronas();
>>>>>>> 9f2ba8f934ce166562758df55419ff7596d72958

                    car.setNome((String) objSnapshot.child("usuario").getValue());
                    car.setOrigem((String) objSnapshot.child("origem").getValue());
                    car.setDestino((String) objSnapshot.child("destino").getValue());
                    car.setData((String) objSnapshot.child("data").getValue());
                    car.setId((String) objSnapshot.child("id").getValue());
                    car.setId_post((String) objSnapshot.child("id_post").getValue());
                    car.setHora((String) objSnapshot.child("hora").getValue());
                    car.setComent((String)objSnapshot.child("comentario").getValue());

<<<<<<< HEAD
    @Override
    public void onRefresh() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        mSwipeToRefresh.setRefreshing(false);
=======
                    Collections.sort(dados, car);

                    dados.add(car);
                }

                ad = new CaronasAdapterMinhas(context,dados);

                lv.setAdapter(ad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
>>>>>>> 9f2ba8f934ce166562758df55419ff7596d72958
    }
}
