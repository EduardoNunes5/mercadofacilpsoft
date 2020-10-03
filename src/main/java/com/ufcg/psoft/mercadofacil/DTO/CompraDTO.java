package com.ufcg.psoft.mercadofacil.DTO;

import com.ufcg.psoft.mercadofacil.enums.PagamentoEnum;
import com.ufcg.psoft.mercadofacil.model.ItemCompra;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CompraDTO {

    private Date data;
    List<ItemCompraDTO> produtos;


    public CompraDTO(List<ItemCompra> produtos, Date data) {
        this.produtos = produtos.stream().map( produto -> new ItemCompraDTO( produto ))
                .collect(Collectors.toList());
        this.data = data;
    }

    public List<ItemCompraDTO> getProdutos() {
        return produtos;
    }

    public Date getData() {
        return data;
    }

    public void setProdutos(List<ItemCompraDTO> produtos) {
        this.produtos = produtos;
    }

    public void setData(Date data) {
        this.data = data;
    }

}
