package com.ufcg.psoft.mercadofacil.model;

import com.ufcg.psoft.mercadofacil.enums.PagamentoEnum;
import org.springframework.stereotype.Component;

@Component
public class PaypalStrategy implements PagamentoI {
    @Override
    public double getValor(double valor) {
        return valor * 1.02;
    }

    @Override
    public PagamentoEnum getNome() {
        return PagamentoEnum.PAYPAL;
    }
}
