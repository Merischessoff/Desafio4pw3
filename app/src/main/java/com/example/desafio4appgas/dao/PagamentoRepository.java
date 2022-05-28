package com.example.desafio4appgas.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.desafio4appgas.model.Pagamento;
import com.example.desafio4appgas.util.BDUtil;

import java.util.ArrayList;
import java.util.List;


public class PagamentoRepository {
    private BDUtil bdUtil;

    public PagamentoRepository(Context context){
        bdUtil =  new BDUtil(context);
    }

    public String insert(String tipoPagamento, Integer parcelas){
        ContentValues valores = new ContentValues();
        valores.put("TIPOPAGAMENTO", tipoPagamento);
        valores.put("PARCELAS", parcelas);
        long resultado = bdUtil.getConexao().insert("PAGAMENTO", null, valores);
        if (resultado ==-1) {
            bdUtil.close();
            return "Erro ao inserir registro";
        }
        return "Registro Inserido com sucesso";
    }

    public Integer delete(int codigo){
        Integer linhasAfetadas = bdUtil.getConexao().delete("PAGAMENTO","ID_PAGAMENTO = ?", new String[]{Integer.toString(codigo)});
        bdUtil.close();
        return linhasAfetadas;
    }

    @SuppressLint("Range")
    public List<Pagamento> getAll(){
        List<Pagamento> pagamentos = new ArrayList<>();
        // monta a consulta
        StringBuilder stringBuilderQuery = new StringBuilder();
        stringBuilderQuery.append("SELECT ID_PAGAMENTO, TIPOPAGAMENTO, PARCELAS");
        stringBuilderQuery.append("FROM PAGAMENTO ");
        stringBuilderQuery.append("ORDER BY ID_PAGAMENTO");
        //consulta os registros que estão no BD
        Cursor cursor = bdUtil.getConexao().rawQuery(stringBuilderQuery.toString(), null);
        //aponta cursos para o primeiro registro
        cursor.moveToFirst();
        Pagamento pagamento = null;
        //Percorre os registros até atingir o fim da lista de registros
        while (!cursor.isAfterLast()){
            // Cria objetos do tipo tarefa
            pagamento =  new Pagamento();
            //adiciona os dados no objeto
            pagamento.setIdPagamento(cursor.getInt(cursor.getColumnIndex("ID_PAGAMENTO")));
            pagamento.setTipoPagamento(cursor.getString(cursor.getColumnIndex("TIPOPAGAMENTO")));
            pagamento.setParcelas(cursor.getInt(cursor.getColumnIndex("PARCELAS")));
            //adiciona o objeto na lista
            pagamentos.add(pagamento);
            //aponta para o próximo registro
            cursor.moveToNext();
        }
        bdUtil.close();
        //retorna a lista de objetos
        return pagamentos;
    }

    @SuppressLint("Range")
    public Pagamento getPagamento(int codigo){
        Cursor cursor =  bdUtil.getConexao().rawQuery("SELECT * FROM PAGAMENTO WHERE ID_PAGAMENTO = "+ codigo,null);
        cursor.moveToFirst();
        Pagamento p = new Pagamento();
        p.setIdPagamento(cursor.getInt(cursor.getColumnIndex("ID_PAGAMENTO")));
        p.setTipoPagamento(cursor.getString(cursor.getColumnIndex("TIPOPAGAMENTO")));
        p.setParcelas(cursor.getInt(cursor.getColumnIndex("PARCELAS")));
        bdUtil.close();
        return p;
    }

    public int update(Pagamento pagamento){
        ContentValues contentValues =  new ContentValues();
        contentValues.put("TIPOPAGAMENTO", pagamento.getTipoPagamento());
        contentValues.put("PARCELAS", pagamento.getParcelas());
         //atualiza o objeto usando a chave
        int retorno = bdUtil.getConexao().update("PAGAMENTO", contentValues, "ID_PAGAMENTO = ?", new String[]{Integer.toString(pagamento.getIdPagamento())});
        bdUtil.close();
        return retorno;
    }
}
