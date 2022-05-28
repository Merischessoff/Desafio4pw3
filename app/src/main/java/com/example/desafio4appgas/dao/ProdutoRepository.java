package com.example.desafio4appgas.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.desafio4appgas.model.Produto;
import com.example.desafio4appgas.util.BDUtil;

import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {

    private BDUtil bdUtil;

    public ProdutoRepository(Context context){
        bdUtil =  new BDUtil(context);
    }

    public String insert(String nome, String caracteristicas){
        ContentValues valores = new ContentValues();
        valores.put("NOME", nome);
        valores.put("CARACTERISTICAS", caracteristicas);
        long resultado = bdUtil.getConexao().insert("PRODUTO", null, valores);
        if (resultado ==-1) {
            bdUtil.close();
            return "Erro ao inserir registro";
        }
        return "Registro Inserido com sucesso";
    }

    public Integer delete(int codigo){
        Integer linhasAfetadas = bdUtil.getConexao().delete("PRODUTO","ID_PRODUTO = ?", new String[]{Integer.toString(codigo)});
        bdUtil.close();
        return linhasAfetadas;
    }

    @SuppressLint("Range")
    public List<Produto> getAll(){
        List<Produto> produtos = new ArrayList<>();
        // monta a consulta
        StringBuilder stringBuilderQuery = new StringBuilder();
        stringBuilderQuery.append("SELECT ID_PRODUTO, NOME, CARACTERISTICAS ");
        stringBuilderQuery.append("FROM PRODUTO ");
        stringBuilderQuery.append("ORDER BY NOME ");
        //consulta os registros que estão no BD
        Cursor cursor = bdUtil.getConexao().rawQuery(stringBuilderQuery.toString(), null);
        //aponta cursos para o primeiro registro
        cursor.moveToFirst();
        Produto produto = null;
        //Percorre os registros até atingir o fim da lista de registros
        while (!cursor.isAfterLast()){
            // Cria objetos do tipo tarefa
            produto =  new Produto();
            //adiciona os dados no objeto
            produto.setIdProduto(cursor.getInt(cursor.getColumnIndex("ID_PRODUTO")));
            produto.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
            produto.setCaracteristicas(cursor.getString(cursor.getColumnIndex("CARACTERISTICAS")));
            //adiciona o objeto na lista
            produtos.add(produto);
            //aponta para o próximo registro
            cursor.moveToNext();
        }
        bdUtil.close();
        //retorna a lista de objetos
        return produtos;
    }

    @SuppressLint("Range")
    public Produto getProduto(int codigo){
        Cursor cursor =  bdUtil.getConexao().rawQuery("SELECT * FROM PRODUTO WHERE ID_PRODUTO = "+ codigo,null);
        cursor.moveToFirst();
        Produto p = new Produto();
        p.setIdProduto(cursor.getInt(cursor.getColumnIndex("ID_PRODUTO")));
        p.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
        p.setCaracteristicas(cursor.getString(cursor.getColumnIndex("CARACTERISTICAS")));
        bdUtil.close();
        return p;
    }

    public int update(Produto produto){
        ContentValues contentValues =  new ContentValues();
        contentValues.put("NOME", produto.getNome());
        contentValues.put("CARACTERISTICAS", produto.getCaracteristicas());
        //atualiza o objeto usando a chave
        int retorno = bdUtil.getConexao().update("PRODUTO", contentValues, "ID_PRODUTO = ?", new String[]{Integer.toString(produto.getIdProduto())});
        bdUtil.close();
        return retorno;
    }
}
