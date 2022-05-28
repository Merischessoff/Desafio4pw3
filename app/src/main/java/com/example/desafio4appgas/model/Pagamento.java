package com.example.desafio4appgas.model;

import java.util.Objects;

public class Pagamento {
    private int idPagamento;
    private String tipoPagamento;
    private int parcelas;

    public Pagamento(int idPagamento, String tipoPagamento, int parcelas) {
        idPagamento = idPagamento;
        this.tipoPagamento = tipoPagamento;
        this.parcelas = parcelas;
    }

    public Pagamento() {
    }

    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pagamento)) return false;
        Pagamento pagamento = (Pagamento) o;
        return getIdPagamento() == pagamento.getIdPagamento() && getParcelas() == pagamento.getParcelas() && Objects.equals(getTipoPagamento(), pagamento.getTipoPagamento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdPagamento(), getTipoPagamento(), getParcelas());
    }
}
