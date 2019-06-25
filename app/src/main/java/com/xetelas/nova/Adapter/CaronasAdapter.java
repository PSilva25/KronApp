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
        TextView usuario,origem, or, destino, des, data, da, hora, ho, comentario, coment2;

        usuario = view.findViewById(R.id.usuario);
        origem = view.findViewById(R.id.origem);
        or = view.findViewById(R.id.origem2);
        destino = view.findViewById(R.id.destino);
        des = view.findViewById(R.id.destino2);
        data = view.findViewById(R.id.data);
        da = view.findViewById(R.id.data2);
        hora = view.findViewById(R.id.hora);
        ho = view.findViewById(R.id.hora2);
        comentario = view.findViewById(R.id.comentario);
        coment2 = view.findViewById(R.id.comentario2);

        usuario.setText(fragments.get(position).getNome());
        origem.setText("Origem: ");
        or.setText(fragments.get(position).getOrigem());
        destino.setText("Destino: ");
        des.setText(fragments.get(position).getDestino());
        data.setText("Data: ");
        da.setText(fragments.get(position).getData());
        hora.setText("Hora: ");
        ho.setText(fragments.get(position).getHora());
        comentario.setText("Informações adicionais:");
        coment2.setText(fragments.get(position).getComent());

        view.setTag(fragments.get(position).getId());

        return view;
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }



}

