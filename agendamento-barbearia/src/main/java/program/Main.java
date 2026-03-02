package program;

import entities.Agendar;
import entities.enums.CortesPadrao;
import entities.exeptions.FormatoDaData;
import entities.exeptions.HorarioPosteriorAoPermitido;
import entities.repository.Repositorio;
import entities.services.HorariosDisponiveis;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        Repositorio repositorio = new Repositorio();
        HorariosDisponiveis horariosDisponiveis = new HorariosDisponiveis();
        char escolha;

        try {
            System.out.println("******************************************************");
            System.out.println();
            System.out.println("BARBEARIA PIANCO");
            System.out.println();
            System.out.println("******************************************************");

            if (!horariosDisponiveis.testarListas()){
                return;
            }
            horariosDisponiveis.horariosDisponiveis();

            System.out.print("Deseja realizar seu agendamento? (s/n)");
            escolha = sc.next().toLowerCase().charAt(0);

            while (escolha != 's' && escolha != 'n'){
                System.out.println("Valor invalido, digite novamente:");
                escolha = sc.next().toLowerCase().charAt(0);
            }

            if (escolha == 's'){
                System.out.println("Escolha seu horario:");
                for (int i = 0; i < horariosDisponiveis.getListaDeHorarios().size(); i++){
                    System.out.println(i + 1 + "-" +
                            horariosDisponiveis.getListaDeHorarios()
                                    .get(i)
                                    .format(DateTimeFormatter.ofPattern("HH:mm")));
                }

                int horario = sc.nextInt();

                System.out.println("Agora escolha o tipo de corte");
                for (CortesPadrao corte: CortesPadrao.values()){
                    System.out.println(corte.getCorte());
                }

                sc.nextLine();
                String corte = sc.nextLine().toUpperCase();

                Agendar agendar = new Agendar(
                        horariosDisponiveis.getListaDeHorarios().get(horario-1),
                        CortesPadrao.valueOf(corte)
                );

                horariosDisponiveis.registrarAgendamento(agendar);
                repositorio.registrarAgendamento(agendar);

                System.out.println("******************************************************");

                horariosDisponiveis.horariosDisponiveis();

                System.out.println("******************************************************");

                horariosDisponiveis.horariosAgendados();
                System.out.println("******************************************************");
            }
            else {
                System.out.println("Certo, volte sempre!");
            }

        }catch (FormatoDaData formatoDaData){
            System.out.println("Erro: " + formatoDaData.getMessage());
        }

        sc.close();
    }
}
