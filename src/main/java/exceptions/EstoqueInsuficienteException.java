package exceptions;

public class EstoqueInsuficienteException extends Exception{

    private static final long serialVersionUID = 1L;

    public EstoqueInsuficienteException(String erro){
        super("ExcecaoDados: " + erro);
    }
}
