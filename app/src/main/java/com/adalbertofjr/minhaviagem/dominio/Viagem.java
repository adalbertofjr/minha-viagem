package com.adalbertofjr.minhaviagem.dominio;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by AdalbertoF on 27/01/2016.
 */
public class Viagem{
    private Long id;
    private String destino;
    private int tipoViagem;
    private Date dataChegada;
    private Date dataPartida;
    private double orcamento;
    private int quantidadePessoas;


    public Viagem(){

    }

    public Viagem(Long id, String destino, int tipoViagem, Date dataChegada, Date dataPartida, double orcamento, int quantidadePessoas) {
        this.id = id;
        this.destino = destino;
        this.tipoViagem = tipoViagem;
        this.dataChegada = dataChegada;
        this.dataPartida = dataPartida;
        this.orcamento = orcamento;
        this.quantidadePessoas = quantidadePessoas;
    }


    protected Viagem(Parcel in) {
        destino = in.readString();
        tipoViagem = in.readInt();
        orcamento = in.readDouble();
        quantidadePessoas = in.readInt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getTipoViagem() {
        return tipoViagem;
    }

    public void setTipoViagem(int tipoViagem) {
        this.tipoViagem = tipoViagem;
    }

    public Date getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(Date dataChegada) {
        this.dataChegada = dataChegada;
    }

    public Date getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(Date dataPartida) {
        this.dataPartida = dataPartida;
    }

    public double getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(double orcamento) {
        this.orcamento = orcamento;
    }

    public int getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public void setQuantidadePessoas(int quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }


}
