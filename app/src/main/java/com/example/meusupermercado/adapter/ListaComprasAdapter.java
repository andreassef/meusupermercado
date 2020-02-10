package com.example.meusupermercado.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meusupermercado.model.Compras;
import com.example.meusupermercado.R;

import java.util.List;

public class ListaComprasAdapter extends RecyclerView.Adapter<ListaComprasAdapter.MyViewHolder> {
    private List<Compras> comprasList;

    public ListaComprasAdapter(List<Compras> comprasList) {
        this.comprasList = comprasList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itensCompra = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_compra_adapter, parent, false);
        return new MyViewHolder(itensCompra);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Compras compras = comprasList.get(position);
        holder.item.setText(compras.getItem());
        holder.quantidade.setText(String.valueOf(compras.getQuantidade()));
        holder.valor.setText(String.valueOf(compras.getValor()));
    }

    @Override
    public int getItemCount() {
        return comprasList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView item, quantidade, valor;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.textItem);
            quantidade = itemView.findViewById(R.id.textQuantidade);
            valor = itemView.findViewById(R.id.textValor);
        }
    }
}
