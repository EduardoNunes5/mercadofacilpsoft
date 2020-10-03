package com.ufcg.psoft.mercadofacil.model;

import com.ufcg.psoft.mercadofacil.enums.PagamentoEnum;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ItemCompra {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private Produto produto;

    private int quantidade;


    public ItemCompra(){

    }

    public ItemCompra(Produto produto, int quantidade){
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
