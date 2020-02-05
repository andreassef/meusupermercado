package com.example.meusupermercado.model;

import java.util.List;

public class ContainerCompras {
    private List<Compras> comprasList;

    public ContainerCompras(List<Compras> list){
        this.comprasList = list;
    }

    public List<Compras> getComprasList() {
        return comprasList;
    }
}
