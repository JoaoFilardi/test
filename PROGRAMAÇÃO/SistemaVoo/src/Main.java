import java.time.LocalTime;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Criar Pilotos
        Piloto piloto1 = new Piloto("Carlos Silva", "P12345", LocalDate.of(2025, 5, 10));
        Piloto copiloto1 = new Piloto("João Pereira", "P67890", LocalDate.of(2024, 7, 20));

        // Criar Destinos
        Destino origem = new Destino("GRU", "São Paulo");
        Destino destino = new Destino("GIG", "Rio de Janeiro");

        // Criar Passageiros
        Passageiro passageiro1 = new Passageiro("Maria Souza", "123456789", "99999-9999", "Rua A, 123");
        Passageiro passageiro2 = new Passageiro("José Santos", "987654321", "88888-8888", "Rua B, 456");
        Passageiro passageiro3 = new Passageiro("Ana Costa", "654321987", "77777-7777", "Rua C, 789");

        // Criar Passagens
        Passagem passagem1 = new Passagem("12A", 15.0, passageiro1);
        Passagem passagem2 = new Passagem("12B", 10.0, passageiro2);
        Passagem passagem3 = new Passagem("12C", 8.0, passageiro3);

        // Criar lista de passagens
        ArrayList<Passagem> listaPassagens = new ArrayList<>();
        listaPassagens.add(passagem1);
        listaPassagens.add(passagem2);
        listaPassagens.add(passagem3);

        // Criar voo
        Voo voo1 = new Voo("AB123", LocalTime.of(10, 0), LocalTime.of(14, 0), piloto1, copiloto1, origem, destino, listaPassagens.toArray(new Passagem[0]));

        // Imprimir dados iniciais do voo e passageiros
        System.out.println("Dados iniciais do voo:");
        System.out.println("Número do voo: " + voo1.getnumVoo());
        System.out.println("Hora de partida: " + voo1.getHoraPartida());
        System.out.println("Hora de chegada: " + voo1.getHoraChegada());
        System.out.println("Passageiros:");
        System.out.println(voo1.listaPassageiros());

        // Alterar horários do voo
        voo1.alterarHoraPartida(LocalTime.of(11, 30));
        voo1.alterarHoraChegada(LocalTime.of(15, 30));

        // Remover passageiros
        voo1.removerPassagem(passagem2); // Remove José Santos

        // Imprimir dados atualizados do voo e passageiros
        System.out.println("\nDados atualizados do voo:");
        System.out.println("Número do voo: " + voo1.getnumVoo());
        System.out.println("Hora de partida: " + voo1.getHoraPartida());
        System.out.println("Hora de chegada: " + voo1.getHoraChegada());
        System.out.println("Passageiros restantes:");
        System.out.println(voo1.listaPassageiros());
    }
}
