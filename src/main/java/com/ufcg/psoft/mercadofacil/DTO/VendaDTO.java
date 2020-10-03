package com.ufcg.psoft.mercadofacil.DTO;

import com.ufcg.psoft.mercadofacil.model.ItemCarrinho;

public class VendaDTO {

    private int quantidade;

    public VendaDTO(){

    }

    public  VendaDTO(ItemCarrinho itemCarrinho){
        this.quantidade = itemCarrinho.getQuantidade();
    }

    public int getQuantidade() {
        return quantidade;
    }
}
