package com.example.desafio4appgas.model;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.desafio4appgas.dao.ProdutoRepository;
import com.example.desafio4appgas.dao.UsuarioRepository;
import android.content.Context;
import java.util.List;
import java.util.Objects;

public class Produto {
    private long idProduto = 0;
    private String nome = "";
    private String caracteristicas = "";

    public Produto(int idProduto, String nome, String caracteristicas) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.caracteristicas = caracteristicas;
    }
    public Produto() {}


    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return getIdProduto() == produto.getIdProduto() && Objects.equals(getNome(), produto.getNome()) && Objects.equals(getCaracteristicas(), produto.getCaracteristicas());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdProduto(), getNome(), getCaracteristicas());
    }

    @Override
    public String toString() {
        return "Produto{" +
                "idProduto=" + idProduto +
                ", nome='" + nome + '\'' +
                ", caracteristicas='" + caracteristicas + '\'' +
                '}';
    }
}
