package com.example.desafio4appgas.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desafio4appgas.model.Produto;

import java.util.ArrayList;
import java.util.List;

import com.example.desafio4appgas.R;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<Produto> listaProdutos = new ArrayList<>();
    Context context;
    public MyAdapter(List<Produto> produtos) {
        this.listaProdutos = produtos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //chamado para criar as visualizações - somente as primeiras que aparecem para o usuário
        //convertendo o XML em uma visualização
        //cria um objeto do tipo view
        View itemList = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_card_icones, viewGroup, false);
        //se usar adapter_card -> ajustar o ViewHolder para usar Button
        //retorna o itemList que é passado para o construtor da MyViewHolder
        this.context = viewGroup.getContext();
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        //exibe os itens no Recycler
        Produto p = listaProdutos.get(position);
        myViewHolder.nome.setText(p.getNome());
        myViewHolder.caracteristicas.setText(p.getCaracteristicas());
        myViewHolder.btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Bundle bundle = new Bundle();
        bundle.putString("NOME", listaProdutos.get(position).getNome());
        bundle.putString("END", listaProdutos.get(position).getCaracteristicas());
        myViewHolder.btnAdicionar.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.nav_fragmentListProduto, bundle));
    }

    @Override
    public int getItemCount() {
        //retorna a quantidade de itens que será exibida
        return listaProdutos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        //dados da pessoa que serão exibidos no recycler
        TextView nome;
        TextView caracteristicas;
        ImageButton btnAdicionar;

        //se usar adapter_card -> ajustar o ViewHolder para usar Button
        //Button btnDelete;
        //Button btnEdit;
        public MyViewHolder(View itemView){
            super(itemView);
            //passa uma referência para os componentes que estão na interface
            nome = itemView.findViewById(R.id.textViewNome);
            caracteristicas = itemView.findViewById(R.id.textViewCaracteristicas);
            btnAdicionar = itemView.findViewById(R.id.btnAdicionar);
        }
    }
}