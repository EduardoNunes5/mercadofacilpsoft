package com.ufcg.psoft.mercadofacil.model;




import exceptions.EstoqueInsuficienteException;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity
public class Carrinho {

    @Id
    private long id;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCarrinho> itensCarrinho;
    private double precoTotal;

    public Carrinho(){
        this.itensCarrinho = new ArrayList<>();
        this.id = 1;
    }

    public List<ItemCarrinho> getProdutos(){
        return this.itensCarrinho;
    }

    public ItemCarrinho addProduto(Produto produto, int quantidade) throws EstoqueInsuficienteException{
        try{
            produto.verificaLote(quantidade);
        }
        catch(EstoqueInsuficienteException msg){
            throw new EstoqueInsuficienteException("Erro de estoque");
        }
        ItemCarrinho itemCarrinho = new ItemCarrinho(produto, quantidade);
        itemCarrinho.setCarrinho(this);
        this.itensCarrinho.add(itemCarrinho);
        this.precoTotal += itemCarrinho.getSubTotal();
        return itemCarrinho;
    }

    public void finalizarCompra() throws EstoqueInsuficienteException{

        for(ItemCarrinho itemCarrinho : this.itensCarrinho){
            try{
                itemCarrinho.reduzLote(itemCarrinho.getQuantidade());
            }
            catch(EstoqueInsuficienteException e){
                throw new EstoqueInsuficienteException("Estoque insuficiente");
            }
        }
    }


    public long getId(){
        return this.id;
    }

    public double getPrecoTotal(){
        return this.precoTotal;
    }


    public void limpaCarrinho() {
        this.precoTotal = 0.0;
        this.itensCarrinho.clear();
    }

    public boolean isEmpty() {
        return this.itensCarrinho.isEmpty();
    }
}
