package com.xetelas.nova.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xetelas.nova.Objects.Caronas;
import com.xetelas.nova.R;

import java.util.ArrayList;

public class Fragment_Minhas extends Fragment {
    Caronas opa = new Caronas();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_minhas, container, false);

        ListView lv = view.findViewById(R.id.lista_minhas);

        ArrayAdapter<Caronas> ad = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                opa.caronas
        );
        lv.setAdapter(ad);

        return view;
    }
}
