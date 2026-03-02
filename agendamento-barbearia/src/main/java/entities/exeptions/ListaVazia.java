package entities.exeptions;

public class ListaVazia extends RuntimeException {
    public ListaVazia(String message) {
        super(message);
    }
}
