package com.example.meusupermercado.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.meusupermercado.R;
import com.example.meusupermercado.adapter.ListaComprasAdapter;
import com.example.meusupermercado.helper.ComprasDAO;
import com.example.meusupermercado.model.Compras;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button botaoInserir;
    private List<Compras> comprasList = new ArrayList<>();
    private Compras compras;
    private RecyclerView recyclerView;
    private ListaComprasAdapter listaAdapter;
    private TextView textTotal;

    private EditText editItem, editQuantidade, editValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_nova_lista);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        botaoInserir = findViewById(R.id.botaoInserir);
        editItem = findViewById(R.id.editItem);
        editQuantidade = findViewById(R.id.editQuantidade);
        editValor = findViewById(R.id.editValor);
        recyclerView = findViewById(R.id.recyclerViewItens);
        textTotal = findViewById(R.id.textTotal);

        botaoInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Objeto do CRUD
                ComprasDAO comprasDAO = new ComprasDAO(getApplicationContext());

                //conteudo
                String item = editItem.getText().toString();
                int quantidade = Integer.parseInt(editQuantidade.getText().toString());
                double valor = Double.parseDouble(editValor.getText().toString());

                //validando campos
                if(!(item.isEmpty() && String.valueOf(quantidade).isEmpty() && String.valueOf(valor).isEmpty())){
                    compras = new Compras();
                    compras.setItem(item);
                    compras.setQuantidade(quantidade);
                    compras.setValor(valor);
                    if(comprasDAO.salvar(compras)){
                        Toast.makeText(getApplicationContext(),"Sucesso ao salvar item!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Erro ao salvar item!", Toast.LENGTH_SHORT).show();
                    }
                }
                onRestart();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        carregarList();
    }

    private void carregarList(){
        //listar produtos
        ComprasDAO comprasDAO = new ComprasDAO(getApplicationContext());
        comprasList = comprasDAO.listar();
        // RecyclerView
        listaAdapter = new ListaComprasAdapter(comprasList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listaAdapter);
    }

}
