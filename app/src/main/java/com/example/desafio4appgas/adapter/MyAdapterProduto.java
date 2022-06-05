package com.example.desafio4appgas.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desafio4appgas.model.Pedido;
import com.example.desafio4appgas.model.Produto;

import java.util.ArrayList;
import java.util.List;

import com.example.desafio4appgas.R;


public class MyAdapterProduto extends RecyclerView.Adapter<MyAdapterProduto.MyViewHolder> {
    List<Produto> listaProdutos = new ArrayList<>();
    String cpfUsuario = "";
    Context context;
    public MyAdapterProduto(List<Produto> produtos, String cpfUsuario) {
        this.listaProdutos = produtos;
        this.cpfUsuario = cpfUsuario;
    }

    public MyAdapterProduto(List<Pedido> inicializaLista) {
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
        Bundle bundle = new Bundle();
        bundle.putString("NOME", listaProdutos.get(position).getNome());
        bundle.putString("END", listaProdutos.get(position).getCaracteristicas());
        bundle.putString("CPF", cpfUsuario);
        myViewHolder.btnFazerPedido.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.nav_fragmentCadPedido, bundle));
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
        ImageButton btnFazerPedido;

        //se usar adapter_card -> ajustar o ViewHolder para usar Button
        //Button btnDelete;
        //Button btnEdit;
        public MyViewHolder(View itemView){
            super(itemView);
            //passa uma referência para os componentes que estão na interface
            nome = itemView.findViewById(R.id.textViewNome);
            caracteristicas = itemView.findViewById(R.id.textViewCaracteristicas);
            btnFazerPedido = itemView.findViewById(R.id.btnFazerPedido);
        }
    }
}
