package com.ufcg.psoft.mercadofacil.model;


import com.ufcg.psoft.mercadofacil.enums.PagamentoEnum;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Compra {

    @Id
    @GeneratedValue
    private long id;

    private Date data;

    private PagamentoEnum tipoPagamento;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="compra_id")
    List<ItemCompra> itens;

    private long carrinhoId;

    private double valorTotal;

    public Compra(){}

    public Compra(List<ItemCompra> itens, PagamentoEnum tipoPagamento, double valorTotal, long carrinhoId){
        this.data = new Date();
        this.itens = itens;
        this.tipoPagamento = tipoPagamento;
        this.valorTotal = valorTotal;
        this.carrinhoId = carrinhoId;
    }

    public long getId() {
        return id;
    }

    public List<ItemCompra> getItens() {
        return itens;
    }

    public Date getData() {
        return data;
    }

    public PagamentoEnum getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(PagamentoEnum tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}
