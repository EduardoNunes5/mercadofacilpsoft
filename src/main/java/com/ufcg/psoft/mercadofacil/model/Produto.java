package com.ufcg.psoft.mercadofacil.model;

import java.math.BigDecimal;
import java.util.Optional;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.Nullable;
import com.ufcg.psoft.mercadofacil.model.Produto;

import exceptions.EstoqueInsuficienteException;
import exceptions.ObjetoInvalidoException;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Produto {

	@Id
	@GeneratedValue
	private Long id;

	private String nome;

	private BigDecimal preco;

	private String codigoBarra;

	private String fabricante;

	private String categoria;

	private String descricao;

	@OneToOne(mappedBy = "produto")
	@JsonIgnore
	private Lote lote;

	@JsonIgnore
	public  int situacao; // usa variaveis estaticas abaixo
	/* situacoes do produto */
	public static final int DISPONIVEL = 1;
	public static final int INDISPONIVEL = 2;

	public Produto() {
		this.preco = new BigDecimal(0);
	}

	public Lote getLote(){
		return this.lote;
	}

	public Produto(long id, String nome, String codigoBarra, String fabricante,
			String nomeCategoria, String descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = new BigDecimal(0);
		this.codigoBarra = codigoBarra;
		this.fabricante = fabricante;
		this.categoria = nomeCategoria;
		this.situacao = Produto.INDISPONIVEL;
		this.descricao = descricao;
	}

	public long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

	public String getDescricao(){
		return this.descricao;
	}

	public void setDescricao(String novaDescricao){
		this.descricao = novaDescricao;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return this.preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void reduzLote(int quantidade) throws EstoqueInsuficienteException{
		try {
			this.lote.reduzLote(quantidade);
		} catch (EstoqueInsuficienteException e) {
			throw new EstoqueInsuficienteException("Erro no estoque");
		}
	}

	public void verificaLote(int quantidade) throws EstoqueInsuficienteException{
		try{
			this.lote.verificaLote(quantidade);
		}
		catch (EstoqueInsuficienteException e){
			throw new EstoqueInsuficienteException("Erro de estoque");
		}
	}

	public void mudaFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void mudaCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void mudaSituacao(int situacao) throws ObjetoInvalidoException {
		switch (situacao) {
		case 1:
			this.situacao = Produto.DISPONIVEL;
			break;
		case 2:
			this.situacao = Produto.INDISPONIVEL;
			break;

		default:
			throw new ObjetoInvalidoException("Situacao Invalida");
		}
	}


	public int getSituacao() throws ObjetoInvalidoException {
		return this.situacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fabricante == null) ? 0 : fabricante.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (fabricante == null) {
			if (other.fabricante != null)
				return false;
		} else if (!fabricante.equals(other.fabricante))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	
	public String toString() {
		return this.id + " " + this.nome;
	}
	
}