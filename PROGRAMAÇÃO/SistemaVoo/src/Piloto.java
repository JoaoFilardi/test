import java.time.LocalDate;

public class Piloto {
    private String nome;
    private String numRegistro;
    private LocalDate validadeRegistro;

    public Piloto (String nome, String numRegistro, LocalDate validadeRegistro) {
        this.nome = nome;
        this.numRegistro = numRegistro;
        this.validadeRegistro = validadeRegistro;
    }

    public boolean verificarValidadeRegistro() {
        LocalDate hoje = LocalDate.now();
        return !hoje.isAfter(validadeRegistro);
    }

    public void atualizarDataRegistro (LocalDate novaData) {
        this.validadeRegistro = novaData;
    }
}


