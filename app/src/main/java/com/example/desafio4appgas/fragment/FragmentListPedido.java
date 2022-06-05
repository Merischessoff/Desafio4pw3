package com.example.desafio4appgas.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.desafio4appgas.R;
import com.example.desafio4appgas.adapter.MyAdapterPedido;
import com.example.desafio4appgas.adapter.MyAdapterProduto;
import com.example.desafio4appgas.dao.PedidoRepository;
import com.example.desafio4appgas.dao.ProdutoRepository;
import com.example.desafio4appgas.model.Pedido;
import com.example.desafio4appgas.model.Produto;

import java.util.List;


public class FragmentListPedido extends Fragment {
    private String cpfUsuario = "";
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_list_pedido, container, false);
    RecyclerView recyclerView = root.findViewById(R.id.recyclerViewPedido);

    Intent intent = getActivity().getIntent();
    cpfUsuario = intent.getStringExtra("cpfUsuario");

    MyAdapterPedido myAdapterPedido = new MyAdapterPedido(inicializaLista(getActivity().getBaseContext(), cpfUsuario), cpfUsuario);
        recyclerView.setAdapter(myAdapterPedido);
    //linha de código usada para otimizar o recycler
        recyclerView.setHasFixedSize(true);

    //configurar o gerenciador de layout
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

    //definindo o layout do recycler
        recyclerView.setLayoutManager(layoutManager);

        return root;
    }

    public List<Pedido> inicializaLista(Context context, String cpfUsuario) {
        PedidoRepository pedidoRepository = new PedidoRepository(context);
        List<Pedido> pedidos = pedidoRepository.getAll(cpfUsuario);
        ProdutoRepository produtoRepository = new ProdutoRepository(context);

        for (Pedido p: pedidos) {
            Produto aux = produtoRepository.getProduto(p.getProduto().getIdProduto());
            p.setProduto(aux);
        }
        return pedidos;
    }
}