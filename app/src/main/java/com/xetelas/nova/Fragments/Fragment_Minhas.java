package com.xetelas.nova.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.xetelas.nova.Objects.Caronas;
import com.xetelas.nova.R;

import java.util.ArrayList;

public class Fragment_Minhas extends Fragment {
    Caronas opa = new Caronas();
    ArrayList<Caronas> car = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_minhas, container, false);

        ListView lv = view.findViewById(R.id.lista_minhas);

        ArrayList<Caronas> todos = preenche();

        ArrayAdapter<Caronas> ad = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                todos
        );
        lv.setAdapter(ad);

        return view;
    }

    public ArrayList<Caronas> preenche(){
        car.add(new Caronas("Picos", "Teresina", "12/12", "12:30", "Vamo lá galerinha 1"));
        car.add(new Caronas("Picos", "Teresina", "12/12", "12:30", "Vamo lá galerinha 2"));
        car.add(new Caronas("Picos", "Teresina", "12/12", "12:30", "Vamo lá galerinha 3"));
        car.add(new Caronas("Picos", "Teresina", "12/12", "12:30", "Vamo lá galerinha 4"));
        car.add(new Caronas("Picos", "Teresina", "12/12", "12:30", "Vamo lá galerinha 5"));

        return car;
    }
}
