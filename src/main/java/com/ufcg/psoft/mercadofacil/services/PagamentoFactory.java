package com.ufcg.psoft.mercadofacil.services;


import com.ufcg.psoft.mercadofacil.enums.PagamentoEnum;
import com.ufcg.psoft.mercadofacil.model.PagamentoI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class PagamentoFactory {
    Map<PagamentoEnum, PagamentoI> estrategiasPagamento;

    @Autowired
    public PagamentoFactory(Set<PagamentoI> pagamentos){

        criarEstrategias(pagamentos);
    }

    public void criarEstrategias(Set<PagamentoI> pagamentos){
        this.estrategiasPagamento = new HashMap<>();
        pagamentos.forEach(pagamento -> estrategiasPagamento.put(pagamento.getNome(),pagamento));
    }

    public PagamentoI pegaEstrategia(PagamentoEnum nome){
        return this.estrategiasPagamento.get(nome);
    }
}
