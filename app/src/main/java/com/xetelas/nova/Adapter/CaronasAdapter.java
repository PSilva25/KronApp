package com.xetelas.nova.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xetelas.nova.Objects.Caronas;
import com.xetelas.nova.R;

import java.util.List;

public class CaronasAdapter extends BaseAdapter {


    private Context context;
    private List<Caronas> fragments;

    public CaronasAdapter(Context context, List<Caronas> fragments) {
        this.context = context;
        this.fragments = fragments;

    }

    @Override
    public Caronas getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.linha,null);
        TextView usuario1,origem1, destino1, data1, hora1, comentario1;

        usuario1 = view.findViewById(R.id.usuario);
        origem1 = view.findViewById(R.id.origem);
        destino1 = view.findViewById(R.id.destino);
        data1 = view.findViewById(R.id.data);
        hora1 = view.findViewById(R.id.hora);
        comentario1 = view.findViewById(R.id.comentario);

        usuario1.setText(fragments.get(position).getNome());
        origem1.setText("saindo de "+fragments.get(position).getOrigem());
        destino1.setText("para "+fragments.get(position).getDestino());
        data1.setText("no dia "+fragments.get(position).getData());
        hora1.setText("Ás "+ fragments.get(position).getHora());
        comentario1.setText("Informações adcionais "+fragments.get(position).getComent());


        view.setTag(fragments.get(position).getId());

        return view;
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }



}

