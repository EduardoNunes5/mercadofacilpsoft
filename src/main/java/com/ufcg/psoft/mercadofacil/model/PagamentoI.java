package com.ufcg.psoft.mercadofacil.model;

import com.ufcg.psoft.mercadofacil.enums.PagamentoEnum;

public interface PagamentoI {

    double getValor(double valor);

    PagamentoEnum getNome();
}
