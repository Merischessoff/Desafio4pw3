package com.example.desafio4appgas.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;


import com.example.desafio4appgas.model.Usuario;
import com.example.desafio4appgas.util.BDUtil;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {

    private BDUtil bdUtil;

    public UsuarioRepository(Context context){
        bdUtil =  new BDUtil(context);
    }

    public String insert(String nome, String cpf, String endereco, String email, String senha){
        ContentValues valores = new ContentValues();
        valores.put("NOME", nome);
        valores.put("CPF", cpf);
        valores.put("ENDERECO", endereco);
        valores.put("EMAIL", email);
        valores.put("SENHA", senha);
        long resultado = bdUtil.getConexao().insert("USUARIO", null, valores);
        if (resultado ==-1) {
            bdUtil.close();
            return "Erro ao inserir registro";
        }
        return "Registro Inserido com sucesso";
    }
    public Integer delete(String cpf){
        Integer linhasAfetadas = bdUtil.getConexao().delete("USUARIO","CPF = ?", new String[]{cpf});
        bdUtil.close();
        return linhasAfetadas;
    }

    @SuppressLint("Range")
    public List<Usuario> getAll(){
        List<Usuario> usuarios = new ArrayList<>();
        // monta a consulta
        StringBuilder stringBuilderQuery = new StringBuilder();
        stringBuilderQuery.append("SELECT NOME, CPF, ENDERECO, EMAIL,  SENHA ");
        stringBuilderQuery.append("FROM USUARIO ");
        stringBuilderQuery.append("ORDER BY NOME ");
        //consulta os registros que estão no BD
        Cursor cursor = bdUtil.getConexao().rawQuery(stringBuilderQuery.toString(), null);
        //aponta cursos para o primeiro registro
        cursor.moveToFirst();
        Usuario usuario = null;
        //Percorre os registros até atingir o fim da lista de registros
        while (!cursor.isAfterLast()){
            // Cria objetos do tipo tarefa
            usuario =  new Usuario();
            //adiciona os dados no objeto
            usuario.setCpf(cursor.getString(cursor.getColumnIndex("CPF")));
            usuario.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
            usuario.setEndereco(cursor.getString(cursor.getColumnIndex("ENDERECO")));
            usuario.setEmail(cursor.getString(cursor.getColumnIndex("EMAIL")));
            usuario.setSenha(cursor.getString(cursor.getColumnIndex("SENHA")));
            //adiciona o objeto na lista
            usuarios.add(usuario);
            //aponta para o próximo registro
            cursor.moveToNext();
        }
        bdUtil.close();
        //retorna a lista de objetos
        return usuarios;
    }

    /*private String nome;
    private String cpf;
    private String endereco;*/

    @SuppressLint("Range")
    public Usuario getUsuario(String cpf){
        Cursor cursor =  bdUtil.getConexao().rawQuery("SELECT * FROM USUARIO WHERE CPF = "+ cpf,null);
        cursor.moveToFirst();
        Usuario usuario = new Usuario();
        //adiciona os dados no objeto
        usuario.setCpf(cursor.getString(cursor.getColumnIndex("CPF")));
        usuario.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
        usuario.setEndereco(cursor.getString(cursor.getColumnIndex("ENDERECO")));
        usuario.setEmail(cursor.getString(cursor.getColumnIndex("EMAIL")));
        usuario.setSenha(cursor.getString(cursor.getColumnIndex("SENHA")));
        bdUtil.close();
        return usuario;
    }

    @SuppressLint("Range")
    public Usuario loginUsuario(String email, String senha){
        Log.d("UsuarioRepository", "método login()" + email + " " + senha);
        Cursor cursor =  bdUtil.getConexao().rawQuery("SELECT * FROM USUARIO WHERE EMAIL = '"+ email + "' AND SENHA = '" + senha + "' ",null);
        cursor.moveToFirst();
        Usuario usuario = new Usuario();
        //adiciona os dados no objeto
        if (!cursor.isAfterLast()) {
            usuario.setCpf(cursor.getString(cursor.getColumnIndex("CPF")));
            usuario.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
            usuario.setEndereco(cursor.getString(cursor.getColumnIndex("ENDERECO")));
            usuario.setEmail(cursor.getString(cursor.getColumnIndex("EMAIL")));
            usuario.setSenha(cursor.getString(cursor.getColumnIndex("SENHA")));
        }
        bdUtil.close();
        return usuario;
    }

    public int update(Usuario usuario){
        ContentValues contentValues =  new ContentValues();
        contentValues.put("CPF", usuario.getCpf());
        contentValues.put("NOME", usuario.getNome());
        contentValues.put("ENDERECO", usuario.getEndereco());
        contentValues.put("EMAIL", usuario.getEmail());
        contentValues.put("SENHA", usuario.getSenha());
        //atualiza o objeto usando a chave
        int retorno = bdUtil.getConexao().update("USUARIO", contentValues, "CPF = ?", new String[]{usuario.getCpf()});
        bdUtil.close();
        return retorno;
    }
}
