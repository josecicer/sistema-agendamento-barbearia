package entities.services;

import entities.Agendar;
import entities.exeptions.HorarioPosteriorAoPermitido;
import entities.exeptions.ListaVazia;
import interfaces.VerificarLista;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HorariosDisponiveis implements VerificarLista {

    private List<LocalDateTime> listaDeHorarios = new ArrayList<>();
    private List<LocalDateTime> listaDeConfirmados = new ArrayList<>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    private LocalDateTime horarioAgora = LocalDateTime.now().plusMinutes(30).withMinute(0).withSecond(0).withNano(0);
    private LocalDate dataAtual = LocalDate.now();
    private LocalTime horaFixaFinal = LocalTime.of(18, 30);
    private LocalTime horaFixaInicial = LocalTime.of(8, 30);
    private LocalDateTime horarioInicial = LocalDateTime.of(LocalDate.now(), horaFixaInicial);
    private LocalDateTime horarioFinal = LocalDateTime.of(LocalDate.now(), horaFixaFinal);

    public HorariosDisponiveis() {
    }

    public List<LocalDateTime> getListaDeHorarios() {
        return new ArrayList<>(listaDeHorarios);
    }

    public List<LocalDateTime> getListaDeConfirmados() {
        return new ArrayList<>(listaDeConfirmados);
    }

    private void horarios() {

        while (horarioAgora.isAfter(horarioInicial) && horarioAgora.isBefore(horarioFinal)) {
            listaDeHorarios.add(horarioAgora);
            horarioAgora = horarioAgora.plusMinutes(30);
        }
        if (listaDeHorarios.isEmpty()) {
            throw new ListaVazia("sem horarios disponiveis");
        } else if (LocalDateTime.now().isAfter(listaDeHorarios.getFirst())) {
            listaDeHorarios.removeFirst();
        }

    }

    public void horariosDisponiveis() {

        System.out.println("horarios disponiveis na data de hoje: " + dataAtual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        for (LocalDateTime horarios : getListaDeHorarios()) {
            System.out.println(horarios.format(formatter));
        }

    }

    public void horariosAgendados() {

        System.out.println("horarios Agendados na data de hoje: " + dataAtual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        for (LocalDateTime horarios : getListaDeConfirmados()) {
            System.out.println(horarios.format(DateTimeFormatter.ofPattern("HH:mm")));
        }

    }

    public void registrarAgendamento(Agendar agendar) {
        if (agendar.getHoraAgendamento().isAfter(listaDeHorarios.getLast())) {
            throw new HorarioPosteriorAoPermitido("Horario de agendamento nao permitido");
        }
        for (int i = 0; i < listaDeHorarios.size(); i++) {
            if (agendar.getHoraAgendamento().isEqual(listaDeHorarios.get(i))) {
                if (agendar.getDuracao() > 30) {

                    try {
                        listaDeConfirmados.add(listaDeHorarios.get(i + 1));
                        listaDeConfirmados.add(listaDeHorarios.get(i));
                        listaDeHorarios.remove(i + 1);
                        listaDeHorarios.remove(i);

                    } catch (IndexOutOfBoundsException index) {
                        System.out.println("Erro: tipo de corte não permitido devido o horario final");
                    }

                } else {
                    listaDeConfirmados.add(listaDeHorarios.get(i));
                    listaDeHorarios.remove(i);

                }
            }
        }
    }

    @Override
    public boolean testarListas() {
        try {
            horarios();
            return true;
        } catch (ListaVazia listaVazia) {
            System.out.println("Erro: " + listaVazia.getMessage());
        }
        return false;
    }
}
