package com.xetelas.nova.Objects;

import java.util.Comparator;

<<<<<<< HEAD
public class Caronas {

    String Id,origem, destino, data, hora, coment;
=======
public class Caronas implements Comparator<Caronas> {
>>>>>>> 9f2ba8f934ce166562758df55419ff7596d72958

    String Id, id_post, origem, destino, data, hora, coment, nome, tell;

    public Caronas() {}

    public String getId_post() {
        return id_post;
    }

<<<<<<< HEAD
    public Caronas(String Id, String origem, String destino, String data, String hora, String coment) {
        this.Id = Id;
        this.origem = origem;
        this.destino = destino;
        this.data = data;
        this.hora = hora;
        this.coment = coment;
=======
    public void setId_post(String id_post) {
        this.id_post = id_post;
    }

    public String getTell() {
        return tell;
>>>>>>> 9f2ba8f934ce166562758df55419ff7596d72958
    }

    public void setTell(String tell) {
        this.tell = tell;
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

    public int compare(Caronas o1, Caronas o2) {
        return o1.getId_post().compareTo(o2.getId());
    }
}
