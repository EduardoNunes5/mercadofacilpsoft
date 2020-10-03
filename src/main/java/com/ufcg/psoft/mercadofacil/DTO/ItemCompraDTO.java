package com.ufcg.psoft.mercadofacil.DTO;

import com.ufcg.psoft.mercadofacil.model.ItemCompra;
import org.springframework.context.annotation.Conditional;

import java.math.BigDecimal;

public class ItemCompraDTO {

    private String nome;
    private String codigo;
    private BigDecimal preco;
    private int quantidade;
    private double subTotal;

    public ItemCompraDTO(String codigo, BigDecimal preco, String nome, int quantidade){
        this.codigo = codigo;
        this.preco = preco;
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public ItemCompraDTO(ItemCompra ic){
        this.codigo = ic.getProduto().getCodigoBarra();
        this.preco = ic.getProduto().getPreco();
        this.nome = ic.getProduto().getNome();
        this.quantidade = ic.getQuantidade();
    }

    public String getNome() {
        return nome;
    }
    public double getSubTotal(){
        return Double.parseDouble(this.preco.toString()) * quantidade;
    }

    public String getCodigo() {
        return codigo;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
