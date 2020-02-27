package com.example.meusupermercado.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.meusupermercado.R;
import com.example.meusupermercado.adapter.ListaComprasAdapter;
import com.example.meusupermercado.helper.ComprasDAO;
import com.example.meusupermercado.helper.RecyclerItemClickListener;
import com.example.meusupermercado.model.Compras;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button botaoInserir, botaoNovaLista;
    private List<Compras> comprasList = new ArrayList<>();
    private Compras compras;
    private RecyclerView recyclerView;
    private ListaComprasAdapter listaAdapter;
    private TextView textTotal;
    private ComprasDAO comprasDAO;

    private EditText editItem, editQuantidade, editValor;

    //itens
    private String item;
    private String quantidade;
    private String valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_nova_lista);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        botaoInserir = findViewById(R.id.btInserir);
        botaoNovaLista = findViewById(R.id.bt_nova_lista);
        editItem = findViewById(R.id.editItem);
        editQuantidade = findViewById(R.id.editQuantidade);
        editValor = findViewById(R.id.editValor);
        recyclerView = findViewById(R.id.recyclerViewItens);
        textTotal = findViewById(R.id.textTotal);


        botaoNovaLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                //configura título e mensagem
                dialog.setTitle("Nova lista");
                dialog.setMessage("Deseja criar uma nova lista?");
                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        comprasDAO = new ComprasDAO(getApplicationContext());
                        if(comprasDAO.deletarTudo()){
                            carregarList();
                            Toast.makeText(getApplicationContext(),"Sucesso ao criar nova lista!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"Erro ao criar nova lista!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.setNegativeButton("Não", null);
                dialog.create();
                dialog.show();
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(),
                recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Compras compraSelecionada = comprasList.get(position);
                        Intent intent = new Intent(MainActivity.this, ActivityEditItem.class);
                        intent.putExtra("itemEdit", compraSelecionada);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        //recuper item para deletar
                        compras = comprasList.get(position);
                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                        //configura título e mensagem
                        dialog.setTitle("Confirmar exclusão");
                        dialog.setMessage("Tem certeza que deseja excluir este item?");

                        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                comprasDAO = new ComprasDAO(getApplicationContext());
                                if(comprasDAO.deletar(compras)){
                                    carregarList();
                                    Toast.makeText(getApplicationContext(),"Sucesso ao excluir item!", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getApplicationContext(),"Erro ao excluir item!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        dialog.setNegativeButton("Não", null);

                        dialog.create();
                        dialog.show();
                    }

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    }
                }));
    }

    public void carregarList(){
        comprasDAO = new ComprasDAO(getApplicationContext());
        //listar produtos
        comprasList = comprasDAO.listar();
        // RecyclerView
        listaAdapter = new ListaComprasAdapter(comprasList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listaAdapter);
        double cont = 0;
        for(Compras compras : comprasList){
            cont = compras.total() + cont;
        }
        textTotal.setText(String.valueOf(cont));
    }

    @Override
    protected void onStart() {
        carregarList();
        super.onStart();
    }

    @Override
    protected void onResume() {
        botaoInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //conteudo
                item = editItem.getText().toString();
                quantidade = editQuantidade.getText().toString();
                valor = editValor.getText().toString();

                //validando campos
                if (!(item.isEmpty() || quantidade.isEmpty() || valor.isEmpty())) {
                    comprasDAO = new ComprasDAO(getApplicationContext());
                    compras = new Compras();
                    compras.setItem(item);
                    compras.setQuantidade(Integer.parseInt(quantidade));
                    compras.setValor(Double.parseDouble(valor));

                    if (comprasDAO.salvar(compras)) {
                        Toast.makeText(getApplicationContext(), "Sucesso ao salvar item!", Toast.LENGTH_SHORT).show();
                        onStart();
                        editItem.setText("");
                        editQuantidade.setText("");
                        editValor.setText("");
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Preencha os campos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        super.onResume();
    }
}
