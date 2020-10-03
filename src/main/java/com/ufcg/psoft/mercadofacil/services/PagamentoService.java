package com.ufcg.psoft.mercadofacil.services;


import com.ufcg.psoft.mercadofacil.enums.PagamentoEnum;
import com.ufcg.psoft.mercadofacil.model.PagamentoI;
import exceptions.TipoDePagamentoInvalido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoFactory estrategiasPagamento;

    public double getPagamento(PagamentoEnum tipoPagamento, double valor) throws TipoDePagamentoInvalido {
        PagamentoI pagamento = this.estrategiasPagamento.pegaEstrategia(getNomePagamento(tipoPagamento));
        return pagamento.getValor(valor);
    }

    public PagamentoEnum getNomePagamento(PagamentoEnum tipoPagamento) throws TipoDePagamentoInvalido {
        if(tipoPagamento == null){
            return PagamentoEnum.BOLETO;
        }
        return tipoPagamento;

    }
}
