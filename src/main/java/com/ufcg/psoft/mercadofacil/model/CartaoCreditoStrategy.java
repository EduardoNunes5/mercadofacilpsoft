package com.ufcg.psoft.mercadofacil.model;

import com.ufcg.psoft.mercadofacil.enums.PagamentoEnum;

public class CartaoCreditoStrategy implements PagamentoI{
    @Override
    public double getValor(double valor) {
        return 0;
    }

    @Override
    public PagamentoEnum getNome() {
        return null;
    }
}
