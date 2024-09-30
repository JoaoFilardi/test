public class Destino {
    private String sigla;
    private String destino;

    public Destino(String sigla, String destino) {
        this.sigla = sigla;
        this.destino = destino;
    }

    public void atualizarDestino (String novaSigla, String novodestino) {
        this.sigla = novaSigla;
        this.destino = novodestino;
    }
    
}
