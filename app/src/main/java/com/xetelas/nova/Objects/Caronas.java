package com.xetelas.nova.Objects;

import java.util.ArrayList;

public class Caronas {

    String Id,origem, destino, data, hora, coment, nome, order;

    public Caronas() {
    }

    @Override
    public String toString() {
        return "Usuário: " + nome + "\nOrigem: " + origem + "\nDestino: " +
                destino + "\nData: " + data + "\nHora: " + hora + "\nComentário: " + coment;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
