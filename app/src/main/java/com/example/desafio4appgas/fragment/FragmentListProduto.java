package com.example.desafio4appgas.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.desafio4appgas.R;
import com.example.desafio4appgas.adapter.MyAdapterProduto;
import com.example.desafio4appgas.dao.ProdutoRepository;
import com.example.desafio4appgas.model.Produto;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class FragmentListProduto extends Fragment{
    private String cpfUsuario;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list_produto, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        //configurar o adapter - que formata que o layout de cada item do recycler
        Intent intent = getActivity().getIntent();
        cpfUsuario = intent.getStringExtra("cpfUsuario");

        MyAdapterProduto myAdapterProduto = new MyAdapterProduto(inicializaLista(getActivity().getBaseContext()), cpfUsuario);
        recyclerView.setAdapter(myAdapterProduto);
        //linha de código usada para otimizar o recycler
        recyclerView.setHasFixedSize(true);

        //configurar o gerenciador de layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        //definindo o layout do recycler
        recyclerView.setLayoutManager(layoutManager);

        return root;
    }

    public static List<Produto> inicializaLista(Context context) {
        ProdutoRepository produtoRepository = new ProdutoRepository(context);
        List<Produto> produtos = produtoRepository.getAll();
        return produtos;
    }

}
