package com.xetelas.nova.Objects;

import java.util.ArrayList;

public class Caronas {
    String origem, destino, data, hora, coment;

    public ArrayList<Caronas> caronas = new ArrayList<>();

    public Caronas() {
    }

    public Caronas(String origem, String destino, String data, String hora, String coment) {
        this.origem = origem;
        this.destino = destino;
        this.data = data;
        this.hora = hora;
        this.coment = coment;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

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
