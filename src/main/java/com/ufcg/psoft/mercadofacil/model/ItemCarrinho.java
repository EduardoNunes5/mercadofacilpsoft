package com.ufcg.psoft.mercadofacil.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import exceptions.EstoqueInsuficienteException;

import javax.persistence.*;

@Entity
public class ItemCarrinho {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private Produto produto;

    @ManyToOne
    private Carrinho carrinho;

    private int quantidade;


    public ItemCarrinho(){

    }
    public ItemCarrinho(Produto produto, int quantidade){
        this.produto = produto;
        this.quantidade = quantidade;
    }


    public Produto getProduto() {
        return produto;
    }


    public int getQuantidade() {
        return quantidade;
    }

    public double getSubTotal(){
        return Double.parseDouble(produto.getPreco().toString()) * this.quantidade;
    }

    public String toString(){
        return produto.getNome() + " " + this.quantidade;
    }

    public void reduzLote(int quantidade) throws EstoqueInsuficienteException {
        try{
            this.produto.reduzLote(quantidade);
        }
        catch(EstoqueInsuficienteException e){
            throw new EstoqueInsuficienteException("Estoque insuficiente");
        }
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }
}
