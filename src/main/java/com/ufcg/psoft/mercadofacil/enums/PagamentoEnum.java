package com.ufcg.psoft.mercadofacil.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import exceptions.TipoDePagamentoInvalido;

public enum PagamentoEnum {

    BOLETO("Boleto"),
    PAYPAL("PayPal"),
    CARTAO_DE_CREDITO("Cartão de crédito");

    private String tipoPagamento;
    PagamentoEnum(String tipoPagamento){
        this.tipoPagamento = tipoPagamento;
    }


    @JsonCreator
    public static PagamentoEnum fromJson(@JsonProperty("tipoPagamento") String name) throws TipoDePagamentoInvalido{
        return valueOf(name);
    }

    @JsonValue
    public String getTipoPagamento(){
        System.out.println("Tipo pagamento::: " + this.tipoPagamento);
        return this.tipoPagamento;
    }

    @Override
    public String toString() {
        return tipoPagamento;
    }
}
