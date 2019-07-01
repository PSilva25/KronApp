package com.xetelas.nova.Objects;

import java.util.ArrayList;

public class Caronas {

    String Id,origem, destino, data, hora, coment;

    public ArrayList<Caronas> caronas = new ArrayList<>();

    public Caronas() {
    }

    public Caronas(String Id, String origem, String destino, String data, String hora, String coment) {
        this.Id = Id;
        this.origem = origem;
        this.destino = destino;
        this.data = data;
        this.hora = hora;
        this.coment = coment;
    }

    @Override
    public String toString() {
        return "Origem: " + origem + "\nDestino: " +
                destino + "\nData: " + data + "\nHora: " + hora + "\nComentário: " + coment;
    }

    public String getOrigem() {
        return origem;
    }

    public String getId() { return Id; }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }
    public String setId(String Id) { return Id;  }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }
}
