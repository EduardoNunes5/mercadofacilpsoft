package com.ufcg.psoft.mercadofacil.model;


import com.ufcg.psoft.mercadofacil.enums.PagamentoEnum;
import org.springframework.stereotype.Component;

@Component
public class BoletoStrategy implements PagamentoI{


    @Override
    public double getValor(double valor) {
        return valor;
    }

    @Override
    public PagamentoEnum getNome() {
        return PagamentoEnum.BOLETO;
    }
}
