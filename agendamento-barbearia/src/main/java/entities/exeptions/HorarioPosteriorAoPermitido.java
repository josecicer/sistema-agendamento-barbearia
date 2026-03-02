package entities.exeptions;

public class HorarioPosteriorAoPermitido extends RuntimeException {
    public HorarioPosteriorAoPermitido(String message) {
        super(message);
    }
}
