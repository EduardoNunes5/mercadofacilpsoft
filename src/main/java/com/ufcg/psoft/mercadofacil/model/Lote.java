package com.ufcg.psoft.mercadofacil.model;

import exceptions.EstoqueInsuficienteException;
import exceptions.ObjetoInvalidoException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class Lote {

	@Id
	@GeneratedValue
    private long id;
	
	@OneToOne
    private Produto produto;
    private int numeroDeItens;
    private String dataDeValidade;

    public Lote() {
        this.id = 0;
    }

    public Lote(Produto produto, int numeroDeItens, String dataDeValidade) {
        super();
        this.produto = produto;
        this.numeroDeItens = numeroDeItens;
        this.dataDeValidade = dataDeValidade;
    }

    public Lote(long id, Produto produto, int numeroDeItens) {
        this.id = id;
        this.produto = produto;
        this.numeroDeItens = numeroDeItens;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getNumeroDeItens() {
        return numeroDeItens;
    }

    public void setNumeroDeItens(int numeroDeItens) {
        this.numeroDeItens = numeroDeItens;
    }

    public String getDataDeValidade() {
        return dataDeValidade;
    }

    public void setDataDeValidade(String dataDeValidade) {
        this.dataDeValidade = dataDeValidade;
    }

    public void reduzLote(int quantidade) throws EstoqueInsuficienteException{
        if((this.numeroDeItens - quantidade) < 0)
            throw new EstoqueInsuficienteException("mais itens do que o estoque possui");
        this.numeroDeItens -= quantidade;
        if(this.numeroDeItens <= 0)
            mudaSituacao();
    }

    private void mudaSituacao(){
        try {
            this.produto.mudaSituacao(2);
        } catch (ObjetoInvalidoException e) {
            e.printStackTrace();
        }
    }

    public void verificaLote(int quantidade) throws EstoqueInsuficienteException{
        if(this.numeroDeItens < quantidade){
            throw new EstoqueInsuficienteException("Estoque nao tem a quantidade de itens disponiveis");
        }
    }

    @Override
    public String toString() {
        return "Lote{" +
                "id=" + id +
                ", produto=" + produto.getId() +
                ", numeroDeItens=" + numeroDeItens + '\'' +
                ", DataDeValidade=" + dataDeValidade + '\'' +
                '}';
    }
}
