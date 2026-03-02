package entities.exeptions;

public class CorteNaoEspecificado extends RuntimeException {
    public CorteNaoEspecificado(String message) {
        super(message);
    }
}
