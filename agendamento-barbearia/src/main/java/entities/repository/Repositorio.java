package entities.repository;

import entities.Agendar;
import entities.services.HorariosDisponiveis;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Repositorio {

    public Repositorio(){}

    public void registrarAgendamento(Agendar agendar){
        try (BufferedWriter registro = new BufferedWriter(new FileWriter("/home/jose/Área de trabalho/projeto-agendamento/agendamento-barbearia/repositorio/repositorio.csv",true))) {

            registro.write(
                    agendar.getCorte() + "," +
                        agendar.getDuracao() + "," +
                        agendar.getPreco() + "," +
                        agendar.getHoraAgendamento()
            );

            registro.newLine();

        }catch (IOException e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
