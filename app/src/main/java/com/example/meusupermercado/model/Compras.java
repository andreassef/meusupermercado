package com.example.meusupermercado.model;

import java.io.Serializable;

public class Compras implements Serializable {
    private String tipo;
    private String item;
    private int quantidade;
    private double valor;

    public Compras(String tipo, String item, int quantidade, double valor) {
        this.tipo = tipo;
        this.item = item;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return getQuantidade() * valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
