package exceptions;

public class TipoDePagamentoInvalido extends Exception{

    private static final long serialVersionUID = 1L;

    public TipoDePagamentoInvalido(String erro){
        super("ExcecaoDados: " + erro);
    }
}
