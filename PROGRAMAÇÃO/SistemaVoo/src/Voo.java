import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Voo {
    private String numVoo;
    private LocalTime horaPartida;
    private LocalTime horaChegada;
    private Piloto piloto;
    private Piloto copiloto;
    private Destino origem;
    private Destino destino;
    private List<Passagem> passagens;
    private boolean cancelado;


    public Voo(String numVoo, LocalTime horaPartida, LocalTime horaChegada, Piloto piloto, Piloto copiloto, Destino origem, Destino destino, Passagem [] passagem) {
        this.numVoo = numVoo;
        this.horaPartida = horaPartida;
        this.horaChegada = horaChegada;
        this.origem = origem;
        this.destino = destino;
        this.piloto = piloto;
        this.copiloto = copiloto;
        this.passagens = passagens != null ? passagens : new ArrayList<>();
        this.cancelado = false;
    }

    public LocalTime getHoraPartida() {
        return horaPartida;
    }

    public LocalTime getHoraChegada() {
        return horaChegada;
    }

    public String getnumVoo() {
        return numVoo;
    }

    public Voo(String string, LocalDate of) {
        //TODO Auto-generated constructor stub
    }

    public void addPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public void addCopiloto(Piloto copiloto) {
        this.copiloto = copiloto;
    }

    public void addPassagem(Passagem passagem) {
        this.passagens.add(passagem);
    }

    public void removerPassagem(Passagem passagem) {
        this.passagens.remove(passagem);
    }

    public void alterarHoraPartida(LocalTime novaHorarioPartida) {
        this.horaPartida = novaHorarioPartida;
    }

    public void alterarHoraChegada(LocalTime novaHorarioChegada) {
        this.horaChegada = novaHorarioChegada;
    }

    public boolean cancelarVoo() {
        if (!this.cancelado) {
            this.cancelado = true;
            System.out.println("Voo " + numVoo + " cancelado.");
            return true;
        }
        System.out.println("Voo já está cancelado.");
        return false;
    }

    public String listaPassageiros() {
        if (this.passagens.isEmpty()) {
            return "Nenhum passageiro no voo.";
        }
    
        StringBuilder lista = new StringBuilder("Lista de passageiros:\n");
        for (Passagem passagem : passagens) {
            lista.append(passagem.getPassageiro().getNome()).append("\n");
        }
        return lista.toString();
    }

}
