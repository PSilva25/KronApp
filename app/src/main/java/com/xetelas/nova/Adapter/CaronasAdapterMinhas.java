package com.xetelas.nova.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.xetelas.nova.Fragments.Fragment_Minhas;
import com.xetelas.nova.Objects.Caronas;
import com.xetelas.nova.R;

import java.util.List;

public class CaronasAdapterMinhas extends BaseAdapter {


    private Context context;
    private List<Caronas> fragments;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();

    public CaronasAdapterMinhas(Context context, List<Caronas> fragments) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.linha2,null);
        TextView origem, or, destino, des, data, da, hora, ho, comentario, coment2;
        ImageView button;

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

        button = view.findViewById(R.id.del);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference desertRef = databaseReference.child(fragments.get(position).getId_post());

                desertRef.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Uh-oh, an error occurred!
                    }
                });
            }
        });

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

