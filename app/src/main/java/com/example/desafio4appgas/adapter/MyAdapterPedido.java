package com.example.desafio4appgas.adapter;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desafio4appgas.R;
import com.example.desafio4appgas.model.Pedido;
import com.example.desafio4appgas.model.Produto;

import java.util.ArrayList;
import java.util.List;


public class MyAdapterPedido extends RecyclerView.Adapter<MyAdapterPedido.MyViewHolder> {
    List<Pedido> listaPedidos = new ArrayList<>();
    String cpfUsuario = "";
    Context context;
    public MyAdapterPedido(List<Pedido> pedidos, String cpfUsuario) {
        this.listaPedidos = pedidos;
        this.cpfUsuario = cpfUsuario;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemList = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_card_icones_pedidos, viewGroup, false);

        this.context = viewGroup.getContext();
        return new MyViewHolder(itemList);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        //exibe os itens no Recycler
        Pedido p = listaPedidos.get(position);
        myViewHolder.pedido.setText("Pedido: " + Integer.toString(listaPedidos.get(position).getNumPedido()));
        myViewHolder.nome.setText(listaPedidos.get(position).getProduto().getNome());
        myViewHolder.caracteristicas.setText(listaPedidos.get(position).getProduto().getCaracteristicas());
        myViewHolder.quant.setText("Quant.: " + Integer.toString(listaPedidos.get(position).getQuantidade()));
    }

    @Override
    public int getItemCount() {
        //retorna a quantidade de itens que será exibida
        return listaPedidos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        //dados da pessoa que serão exibidos no recycler
        TextView nome;
        TextView caracteristicas;
        TextView pedido;
        TextView quant;

        //se usar adapter_card -> ajustar o ViewHolder para usar Button
        //Button btnDelete;
        //Button btnEdit;
        public MyViewHolder(View itemView){
            super(itemView);
            pedido = itemView.findViewById(R.id.textViewNumPedido);
            nome = itemView.findViewById(R.id.textViewNome);
            caracteristicas = itemView.findViewById(R.id.textViewCaracteristicas);
            quant = itemView.findViewById(R.id.textViewQuant);
        }
    }
}
