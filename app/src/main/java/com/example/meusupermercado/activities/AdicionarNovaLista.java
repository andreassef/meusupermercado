package com.example.meusupermercado.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.meusupermercado.adapter.ListaComprasAdapter;
import com.example.meusupermercado.model.Compras;
import com.example.meusupermercado.R;
import com.example.meusupermercado.model.ContainerCompras;

import java.util.ArrayList;
import java.util.List;

public class AdicionarNovaLista extends AppCompatActivity {
    private Button botaoInserir;
    private List<Compras> comprasList = new ArrayList<>();
    private Compras compras;
    private RecyclerView recyclerView;
    private ListaComprasAdapter listaAdapter;
    private TextView textTotal;

    private EditText editTipo, editItem, editQuantidade, editValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_nova_lista);
        botaoInserir = findViewById(R.id.botaoInserir);
        editTipo = findViewById(R.id.editTipo);
        editItem = findViewById(R.id.editItem);
        editQuantidade = findViewById(R.id.editQuantidade);
        editValor = findViewById(R.id.editValor);
        recyclerView = findViewById(R.id.recyclerViewItens);
        textTotal = findViewById(R.id.textTotal);

        //comprasFragment = new ComprasFragment(comprasList);
        //exibir o fragmento
        //FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //fragmentTransaction.replace(R.id.frameLayout, comprasFragment);
        //fragmentTransaction.commit();

        botaoInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //conteudo
                String tipo = editTipo.getText().toString();
                String item = editItem.getText().toString();
                int quantidade = Integer.parseInt(editQuantidade.getText().toString());
                double valor = Double.parseDouble(editValor.getText().toString());
                compras = new Compras(tipo, item, quantidade, valor);
                comprasList.add(compras);
                double total = 0;
                for (Compras compras : comprasList){
                    total = compras.getValor() + total;
                    textTotal.setText(String.valueOf(total));
                }
                onRestart();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        carregarList();
    }

    private void carregarList(){
        // RecyclerView
        listaAdapter = new ListaComprasAdapter(comprasList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listaAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_save:
                ContainerCompras containerCompras = new ContainerCompras(comprasList);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
