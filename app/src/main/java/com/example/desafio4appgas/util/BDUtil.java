package com.example.desafio4appgas.util;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDUtil extends SQLiteOpenHelper {

    private static final String BASE_DE_DADOS = "DESAFIO4BUTANOGAS1";
    private static final int VERSAO = 1;

    public BDUtil(Context context){
        super(context,BASE_DE_DADOS,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder criarTabela = new StringBuilder();
        criarTabela.append(" CREATE TABLE PAGAMENTO (");
        criarTabela.append(" ID_PAGAMENTO INTEGER PRIMARY KEY AUTOINCREMENT, ");
        criarTabela.append(" TIPOPAGAMENTO TEXT NOT NULL, ");
        criarTabela.append(" PARCELAS INTEGER NOT NULL)");
        db.execSQL(criarTabela.toString());

        criarTabela = new StringBuilder();
        criarTabela.append(" CREATE TABLE PRODUTO (");
        criarTabela.append(" ID_PRODUTO INTEGER PRIMARY KEY AUTOINCREMENT, ");
        criarTabela.append(" NOME TEXT NOT NULL, ");
        criarTabela.append(" CARACTERISTICAS TEXT NOT NULL)");
        db.execSQL(criarTabela.toString());

        criarTabela = new StringBuilder();
        criarTabela.append(" INSERT INTO PRODUTO (");
        criarTabela.append(" NOME, ");
        criarTabela.append(" CARACTERISTICAS)");
        criarTabela.append(" VALUES('botijão 13 kilos', '13 kilos')");
        db.execSQL(criarTabela.toString());

        criarTabela = new StringBuilder();
        criarTabela.append(" CREATE TABLE PEDIDO (");
        criarTabela.append(" NUM_PEDIDO INTEGER PRIMARY KEY AUTOINCREMENT, ");
        criarTabela.append(" ID_PAGAMENTO INTEGER NOT NULL, ");
        criarTabela.append(" QUANTIDADE INTEGER NOT NULL, ");
        criarTabela.append(" CPF TEXT NOT NULL, ");
        criarTabela.append(" ID_PRODUTO INTEGER NOT NULL)");
        db.execSQL(criarTabela.toString());

        criarTabela = new StringBuilder();
        criarTabela.append(" CREATE TABLE USUARIO (");
        criarTabela.append(" CPF TEXT NOT NULL, ");
        criarTabela.append(" NOME TEXT NOT NULL, ");
        criarTabela.append(" EMAIL TEXT NOT NULL, ");
        criarTabela.append(" SENHA TEXT NOT NULL, ");
        criarTabela.append(" ENDERECO TEXT NOT NULL)");
        db.execSQL(criarTabela.toString());

        criarTabela = new StringBuilder();
        criarTabela.append(" INSERT INTO USUARIO (CPF, NOME, EMAIL, SENHA, ENDERECO)");
        criarTabela.append(" VALUES ('11111111111', 'meridiane', 'gonkaschessoff@gmail.com', '1111', 'endereco')");
        db.execSQL(criarTabela.toString());

    }

    /*Método abaixo é executado quando troa a versão do BD*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS PAGAMENTO ");
        db.execSQL("DROP TABLE IF EXISTS PRODUTO ");
        db.execSQL("DROP TABLE IF EXISTS PEDIDO ");
        db.execSQL("DROP TABLE IF EXISTS USUARIO ");
        onCreate(db);

    }

    /*Método usado para obter a conexão com o BD*/
    public SQLiteDatabase getConexao(){
        return this.getWritableDatabase();
    }
}