package com.example.desafio4appgas.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.desafio4appgas.model.Pagamento;
import com.example.desafio4appgas.model.Pedido;
import com.example.desafio4appgas.model.Produto;
import com.example.desafio4appgas.model.Usuario;
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

    public long insert(long id_pagamento, long id_produto, long quantidade, String cpf){
        ContentValues valores = new ContentValues();
        valores.put("ID_PAGAMENTO", id_pagamento);
        valores.put("QUANTIDADE", quantidade);
        valores.put("ID_PRODUTO", id_produto);
        valores.put("CPF", cpf);
        long resultado = bdUtil.getConexao().insert("PEDIDO", null, valores);
        if (resultado ==-1) {
            bdUtil.close();
            return resultado;
        }
        return resultado;
    }

    public Integer delete(int codigo){
        Pedido pAux = this.getPedido(codigo);
        bdUtil.getConexao().delete("PAGAMENTO","ID_PAGAMENTO = ?", new String[]{Integer.toString(pAux.getPagamento().getIdPagamento())});
        Integer linhasAfetadas = bdUtil.getConexao().delete("PEDIDO","NUM_PEDIDO = ?", new String[]{Integer.toString(codigo)});
        bdUtil.close();
        return linhasAfetadas;
    }

    @SuppressLint("Range")
    public List<Pedido> getAll(String cpf){
        List<Pedido> pedidos = new ArrayList<>();
        // monta a consulta
        StringBuilder stringBuilderQuery = new StringBuilder();
        stringBuilderQuery.append("SELECT NUM_PEDIDO, ID_PAGAMENTO, ID_PRODUTO, QUANTIDADE, CPF ");
        stringBuilderQuery.append("FROM PEDIDO WHERE CPF = '"+ cpf + "' ");
        stringBuilderQuery.append("ORDER BY NUM_PEDIDO");
        //consulta os registros que estão no BD
        Cursor cursor = bdUtil.getConexao().rawQuery(stringBuilderQuery.toString(), null);
        //aponta cursos para o primeiro registro
        cursor.moveToFirst();
        //Percorre os registros até atingir o fim da lista de registros
        while (!cursor.isAfterLast()){
            // Cria objetos do tipo tarefa
            Pedido pedido = new Pedido();
            int numProduto = cursor.getInt(cursor.getColumnIndex("ID_PRODUTO"));
            int numPagamento = cursor.getInt(cursor.getColumnIndex("ID_PAGAMENTO"));
            String cpfUsuario = cursor.getString(cursor.getColumnIndex("CPF"));
            pedido.setNumPedido(cursor.getInt(cursor.getColumnIndex("NUM_PEDIDO")));
            pedido.setQuantidade(cursor.getInt(cursor.getColumnIndex("QUANTIDADE")));
            pedido.getProduto().setIdProduto(numProduto);
            pedido.getUsuario().setCpf(cpfUsuario);
            pedido.getPagamento().setIdPagamento(numPagamento);
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
        Pedido pedido = new Pedido();
        int numProduto = cursor.getInt(cursor.getColumnIndex("ID_PRODUTO"));
        int numPagamento = cursor.getInt(cursor.getColumnIndex("ID_PAGAMENTO"));
        String cpfUsuario = cursor.getString(cursor.getColumnIndex("CPF"));
        pedido.setNumPedido(cursor.getInt(cursor.getColumnIndex("NUM_PEDIDO")));
        pedido.setQuantidade(cursor.getInt(cursor.getColumnIndex("QUANTIDADE")));
        pedido.getProduto().setIdProduto(numProduto);
        pedido.getUsuario().setCpf(cpfUsuario);
        pedido.getPagamento().setIdPagamento(numPagamento);
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
