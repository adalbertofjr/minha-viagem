package com.adalbertofjr.minhaviagem.dominio;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by AdalbertoF on 27/01/2016.
 */
public class Gasto {
    private long id;
    private Date data;
    private String categoria;
    private String descricao;
    private Double valor;
    private String local;
    private int viagemId;

    public Gasto() {
    }

    public Gasto(long id, Date data, String categoria, String descricao, Double valor, String local, int viagemId) {
        this.id = id;
        this.data = data;
        this.categoria = categoria;
        this.descricao = descricao;
        this.valor = valor;
        this.local = local;
        this.viagemId = viagemId;
    }

    protected Gasto(Parcel in) {
        id = in.readLong();
        categoria = in.readString();
        descricao = in.readString();
        local = in.readString();
        viagemId = in.readInt();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getViagemId() {
        return viagemId;
    }

    public void setViagemId(int viagemId) {
        this.viagemId = viagemId;
    }
}
