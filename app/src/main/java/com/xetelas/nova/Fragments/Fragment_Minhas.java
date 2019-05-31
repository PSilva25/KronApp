package com.xetelas.nova.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xetelas.nova.R;

import java.util.ArrayList;

public class Fragment_Minhas extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_minhas, container, false);

        ArrayList<String> caronas = preencher();
        ListView lv = view.findViewById(R.id.lista_minhas);

        ArrayAdapter<String> ad = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                caronas
        );
        lv.setAdapter(ad);

        return view;
    }

    private ArrayList<String> preencher(){
        ArrayList<String> dados = new ArrayList<>();
        dados.add("Minha");
        dados.add("Minha2");
        dados.add("Minha3");
        dados.add("Minha4");
        dados.add("MInha5");

        return dados;
    }
}
