package com.example.meusupermercado.helper;

import com.example.meusupermercado.model.Compras;

import java.util.List;

public interface ICompras {
    public boolean salvar(Compras compras);
    public boolean atualizar(Compras compras);
    public boolean deletar(Compras compras);
    public List<Compras> listar();
}
