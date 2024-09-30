public class Passagem {
    private String numAssento;
    private double volumeBagagem;
    private Passageiro passageiro;

    public Passagem (String numAssento, double volumeBagagem, Passageiro passageiro) {
        this.numAssento = numAssento;
        this.volumeBagagem = volumeBagagem;
        this.passageiro = passageiro;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }
    
    public void atualizarAssento(String novoAssento) {
        this.numAssento = novoAssento;
    }

    public void atualizarBagagem(double novaBagagem) {
        this.volumeBagagem = novaBagagem;
    }
}
