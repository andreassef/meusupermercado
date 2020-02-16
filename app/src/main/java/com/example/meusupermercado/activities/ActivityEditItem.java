package com.example.meusupermercado.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meusupermercado.R;
import com.example.meusupermercado.helper.ComprasDAO;
import com.example.meusupermercado.model.Compras;

public class ActivityEditItem extends AppCompatActivity {
    private Compras compras;
    private EditText editItem;
    private EditText editQuantidade;
    private EditText editValor;
    private Button btSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        editItem = findViewById(R.id.editItem2);
        editQuantidade = findViewById(R.id.editQuantidade2);
        editValor = findViewById(R.id.editTotal);
        btSalvar = findViewById(R.id.btSalvar);

        //recuperar dados de outra atividade
        compras = (Compras) getIntent().getSerializableExtra("item");
        if(compras != null){
            editItem.setText(compras.getItem());
            editQuantidade.setText(String.valueOf(compras.getQuantidade()));
            editValor.setText(String.valueOf(compras.getValor()));
        }

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ComprasDAO comprasDAO = new ComprasDAO(getApplicationContext());
                String itemAtual = editItem.getText().toString();
                String quantidadeAtual = editQuantidade.getText().toString();
                String valorAtual = editValor.getText().toString();
                if(compras != null){
                    if(!(itemAtual.isEmpty() && quantidadeAtual.isEmpty() && valorAtual.isEmpty())){
                        Compras itemAtualizado = new Compras();
                        itemAtualizado.setItem(itemAtual);
                        itemAtualizado.setQuantidade(Integer.parseInt(quantidadeAtual));
                        itemAtualizado.setValor(Double.parseDouble(valorAtual));
                        itemAtualizado.setId(compras.getId());
                        //atualizar
                        if(comprasDAO.atualizar(itemAtualizado)){
                            finish();
                            Toast.makeText(getApplicationContext(),"Sucesso ao atualizar item!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"Erro ao atualizar item!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}
