package com.ufcg.psoft.mercadofacil.DTO;

public class LoteDTO {

    private int numeroDeItens;
    private String dataDeValidade;

    public LoteDTO() {
    }

    public LoteDTO(int numeroDeItens, String dataDeValidade) {
        super();
        this.dataDeValidade = dataDeValidade;
        this.numeroDeItens = numeroDeItens;
    }

    public int getNumeroDeItens() {
        return numeroDeItens;
    }

    public void setNumeroDeItens(int numeroDeItens) {
        this.numeroDeItens = numeroDeItens;
    }

    public String getDataDeValidade(){
        return this.dataDeValidade;
    }

    public void setDataDeValidade(String novaData){
        this.dataDeValidade = novaData;
    }
}
