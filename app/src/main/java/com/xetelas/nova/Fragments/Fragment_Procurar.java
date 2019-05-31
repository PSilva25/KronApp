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

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Procurar extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_procurar, container, false);

        ArrayList<String> caronas = preencher();
        ListView lv = view.findViewById(R.id.lista_geral);

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
        dados.add("Teste");
        dados.add("Teste1");
        dados.add("Teste2");
        dados.add("Teste3");
        dados.add("Teste4");
        dados.add("Teste5");

        return dados;
    }

}
