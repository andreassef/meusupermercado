package com.example.meusupermercado.model;

import java.io.Serializable;

public class Compras implements Serializable {
    private Long id;
    private String item;
    private int quantidade;
    private double valor;

    public Long getId() {
        return id;
    }

    public void setId(Long tipo) {
        this.id = id;
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
