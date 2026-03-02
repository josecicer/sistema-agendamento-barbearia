package entities;

import entities.enums.CortesPadrao;
import entities.exeptions.FormatoDaData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Agendar {
    private LocalDateTime horaAgendamento;
    private CortesPadrao tipoDeCorte;
    private final DateTimeFormatter formatador = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

    public Agendar(LocalDateTime horaAgendada,CortesPadrao tipoDeCorte) {

        if (horaAgendada.isBefore(LocalDateTime.now())){
            throw new FormatoDaData("Data anterior e invalida!");
        }
        this.horaAgendamento = horaAgendada;
        this.tipoDeCorte = tipoDeCorte;
    }


    public String getCorte() {
        return tipoDeCorte.getCorte();
    }

    public int getDuracao() {
        return tipoDeCorte.getDuracao();
    }

    public Double getPreco() {
        return tipoDeCorte.getPreco();
    }

    public String guardarNoBanco() {
        String resultado = getCorte() + "," + getDuracao() + "," + String.format("%.2f",getPreco());
        return resultado;
    }

    public LocalDateTime getHoraAgendamento() {
        return horaAgendamento;
    }

    @Override
    public String toString() {
        return "horaAgendamento=" + horaAgendamento.format(formatador) + ", tipo de corte: " + tipoDeCorte.getCorte() + ", duracao: " + tipoDeCorte.getDuracao() +
                ", valor do corte: " + tipoDeCorte.getPreco();
    }
}
