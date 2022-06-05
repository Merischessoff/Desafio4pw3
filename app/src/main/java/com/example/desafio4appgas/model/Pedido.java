package com.example.desafio4appgas.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pedido {
    private int numPedido = 0;
    private Pagamento pagamento = new Pagamento();
    private int quantidade = 0;
    private Produto produto = new Produto();
    private Usuario usuario = new Usuario();

    public Pedido() {
    }

    public Pedido(int numPedido, Pagamento pagamento, int quantidade, Produto produto, Usuario usuario) {
        this.numPedido = numPedido;
        this.pagamento = pagamento;
        this.quantidade = quantidade;
        this.produto = produto;
        this.usuario = usuario;
    }

    public int getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido)) return false;
        Pedido pedido = (Pedido) o;
        return getNumPedido() == pedido.getNumPedido() && getQuantidade() == pedido.getQuantidade() && Objects.equals(getPagamento(), pedido.getPagamento()) && Objects.equals(getProduto(), pedido.getProduto()) && Objects.equals(getUsuario(), pedido.getUsuario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumPedido(), getPagamento(), getQuantidade(), getProduto(), getUsuario());
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "numPedido=" + numPedido +
                ", pagamento=" + pagamento +
                ", quantidade=" + quantidade +
                ", produtos=" + produto +
                ", usuario=" + usuario +
                '}';
    }
}
