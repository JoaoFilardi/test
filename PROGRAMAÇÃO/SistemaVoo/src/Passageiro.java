public class Passageiro {
    private String nome;
    private String rg;
    private String telefone;
    private String endereco;

    public Passageiro (String nome, String rg, String telefone, String endereco) {
        this.nome = nome;
        this.rg = rg;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getNumDocumento() {
        return rg;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }
    
    public void atualizarDados(String novoTelefone, String novoEndereço) {
        this.telefone = novoTelefone;
        this.endereco = novoEndereço;
    }
}
