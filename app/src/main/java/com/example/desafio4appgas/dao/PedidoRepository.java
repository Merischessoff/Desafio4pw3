package com.example.desafio4appgas.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.desafio4appgas.model.Pedido;
import com.example.desafio4appgas.util.BDUtil;

import java.util.ArrayList;
import java.util.List;

public class PedidoRepository {
    private BDUtil bdUtil;
    private PagamentoRepository pagamentoRepository;
    private ProdutoRepository produtoRepository;
    private UsuarioRepository usuarioRepository;

    public PedidoRepository(Context context){
        bdUtil =  new BDUtil(context);
    }

    public String insert(int num_pedido, int id_pagamento, int id_produto, int quantidade){
        ContentValues valores = new ContentValues();
        valores.put("NUM_PEDIDO", num_pedido);
        valores.put("ID_PAGAMENTO", id_pagamento);
        valores.put("QUANTIDADE", quantidade);
        valores.put("ID_PRODUTO", id_produto);
        long resultado = bdUtil.getConexao().insert("PEDIDO", null, valores);
        if (resultado ==-1) {
            bdUtil.close();
            return "Erro ao inserir registro";
        }
        return "Registro Inserido com sucesso";
    }

    public Integer delete(int codigo){
        Pedido pAux = this.getPedido(codigo);
        bdUtil.getConexao().delete("PAGAMENTO","ID_PAGAMENTO = ?", new String[]{Integer.toString(pAux.getPagamento().getIdPagamento())});
        Integer linhasAfetadas = bdUtil.getConexao().delete("PEDIDO","NUM_PEDIDO = ?", new String[]{Integer.toString(codigo)});
        bdUtil.close();
        return linhasAfetadas;
    }

    @SuppressLint("Range")
    public List<Pedido> getAll(){
        List<Pedido> pedidos = new ArrayList<>();
        // monta a consulta
        StringBuilder stringBuilderQuery = new StringBuilder();
        stringBuilderQuery.append("SELECT NUM_PEDIDO, ID_PAGAMENTO, ID_PRODUTO, QUANTIDADE ");
        stringBuilderQuery.append("FROM PEDIDO ");
        stringBuilderQuery.append("ORDER BY NUM_PEDIDO");
        //consulta os registros que estão no BD
        Cursor cursor = bdUtil.getConexao().rawQuery(stringBuilderQuery.toString(), null);
        //aponta cursos para o primeiro registro
        cursor.moveToFirst();
        Pedido pedido = null;
        //Percorre os registros até atingir o fim da lista de registros
        while (!cursor.isAfterLast()){
            // Cria objetos do tipo tarefa
            pedido =  new Pedido();
            //adiciona os dados no objeto
            pedido.setNumPedido(cursor.getInt(cursor.getColumnIndex("NUM_PEDIDO")));
            pedido.setQuantidade(cursor.getInt(cursor.getColumnIndex("QUANTIDADE")));
            pedido.setPagamento(pagamentoRepository.getPagamento(cursor.getInt(cursor.getColumnIndex("ID_PAGAMENTO"))));
            pedido.setProdutos(produtoRepository.getProduto(cursor.getInt(cursor.getColumnIndex("ID_PRODUTO"))));
            //adiciona o objeto na lista
            pedidos.add(pedido);
            //aponta para o próximo registro
            cursor.moveToNext();
        }
        bdUtil.close();
        //retorna a lista de objetos
        return pedidos;
    }

    @SuppressLint("Range")
    public Pedido getPedido(int codigo){
        Cursor cursor =  bdUtil.getConexao().rawQuery("SELECT * FROM PEDIDO WHERE NUM_PEDIDO = "+ codigo,null);
        cursor.moveToFirst();
        Pedido pedido=  new Pedido();
        //adiciona os dados no objeto
        pedido.setNumPedido(cursor.getInt(cursor.getColumnIndex("NUM_PEDIDO")));
        pedido.setQuantidade(cursor.getInt(cursor.getColumnIndex("QUANTIDADE")));
        pedido.setPagamento(pagamentoRepository.getPagamento(cursor.getInt(cursor.getColumnIndex("ID_PAGAMENTO"))));
        pedido.setProdutos(produtoRepository.getProduto(cursor.getInt(cursor.getColumnIndex("ID_PRODUTO"))));
        pedido.setUsuario(usuarioRepository.getUsuario(cursor.getString(cursor.getColumnIndex("CPF"))));
        bdUtil.close();
        return pedido;
    }

    public int update(Pedido pedido){
        ContentValues contentValues =  new ContentValues();
        contentValues.put("QUANTIDADE", pedido.getQuantidade());
        //atualiza o objeto usando a chave
        int retorno = bdUtil.getConexao().update("PEDIDO", contentValues, "NUM_PEDIDO = ?", new String[]{Integer.toString(pedido.getNumPedido())});
        bdUtil.close();
        return retorno;
    }
}
