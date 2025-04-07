import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class AppOficina {

    static final int MAX_PEDIDOS = 100;
    static Produto[] produtos;
    static Produto[] produtosOrdenadosIdentif;  // Array sorted by product ID
    static Produto[] produtosOrdenadosDescr;    // Array sorted by product description
    static int quantProdutos = 0;
    static String nomeArquivoDados = "src/produtos.txt";
    static IOrdenador<Produto> ordenador;
    static Scanner teclado;

    static <T extends Number> T lerNumero(String mensagem, Class<T> classe) {
        System.out.print(mensagem + ": ");
        T valor;
        try {
            valor = classe.getConstructor(String.class).newInstance(teclado.nextLine());
        } catch (Exception e) {
            return null;
        }
        return valor;
    }

    static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void pausa() {
        System.out.println("Tecle Enter para continuar.");
        teclado.nextLine();
    }

    static void cabecalho() {
        limparTela();
        System.out.println("XULAMBS COMÉRCIO DE COISINHAS v0.2\n================");
    }

    static int exibirMenuPrincipal() {
        cabecalho();
        System.out.println("1 - Procurar produto");
        System.out.println("2 - Filtrar produtos por preço máximo");
        System.out.println("3 - Ordenar produtos");
        System.out.println("4 - Embaralhar produtos");
        System.out.println("5 - Listar produtos");
        System.out.println("6 - Ordenar por preço (com desempate por descrição)");
        System.out.println("0 - Finalizar");
        return lerNumero("Digite sua opção", Integer.class);
    }

    static int exibirMenuOrdenadores() {
        cabecalho();
        System.out.println("1 - Bolha");
        System.out.println("2 - Inserção");
        System.out.println("3 - Seleção");
        System.out.println("4 - Mergesort");
        System.out.println("5 - Quicksort");
        System.out.println("0 - Finalizar");
        return lerNumero("Digite sua opção", Integer.class);
    }

    static int exibirMenuComparadores() {
        cabecalho();
        System.out.println("1 - Padrão");
        System.out.println("2 - Por código");
        return lerNumero("Digite sua opção", Integer.class);
    }

    static Produto[] carregarProdutos(String nomeArquivo){
        Scanner dados;
        Produto[] dadosCarregados;
        try{
            dados = new Scanner(new File(nomeArquivo));
            int tamanho = Integer.parseInt(dados.nextLine());
            dadosCarregados = new Produto[tamanho];
            while (dados.hasNextLine()) {
                Produto novoProduto = Produto.criarDoTexto(dados.nextLine());
                dadosCarregados[quantProdutos] = novoProduto;
                quantProdutos++;
            }
            dados.close();
        }catch (FileNotFoundException fex){
            System.out.println("Arquivo não encontrado. Produtos não carregados");
            dadosCarregados = null;
        }
        return dadosCarregados;
    }

    static Produto localizarProduto() {
        cabecalho();
        System.out.println("Localizando um produto");
        System.out.println("1 - Buscar por código");
        System.out.println("2 - Buscar por descrição");
        int opcao = lerNumero("Digite sua opção", Integer.class);

        if (opcao == 1) {
            int codigo = lerNumero("Digite o código do produto", Integer.class);
            return buscaBinariaPorCodigo(codigo);
        } else if (opcao == 2) {
            System.out.print("Digite a descrição do produto: ");
            String descricao = teclado.nextLine();
            return buscaBinariaPorDescricao(descricao);
        }
        return null;
    }

    static Produto buscaBinariaPorCodigo(int codigo) {
        int inicio = 0;
        int fim = quantProdutos - 1;
        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;
            int codigoMeio = produtosOrdenadosIdentif[meio].hashCode();
            if (codigoMeio == codigo) return produtosOrdenadosIdentif[meio];
            if (codigoMeio < codigo) inicio = meio + 1;
            else fim = meio - 1;
        }
        return null;
    }

    static Produto buscaBinariaPorDescricao(String descricao) {
        int inicio = 0;
        int fim = quantProdutos - 1;
        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;
            String descricaoMeio = produtosOrdenadosDescr[meio].descricao;
            int comparacao = descricaoMeio.compareTo(descricao);
            if (comparacao == 0) return produtosOrdenadosDescr[meio];
            if (comparacao < 0) inicio = meio + 1;
            else fim = meio - 1;
        }
        return null;
    }

    private static void mostrarProduto(Produto produto) {
        cabecalho();
        String mensagem = "Dados inválidos";
        if(produto!=null){
            mensagem = String.format("Dados do produto:\n%s", produto);
        }
        System.out.println(mensagem);
    }

    private static void filtrarPorPrecoMaximo(){
        cabecalho();
        System.out.println("Filtrando por valor máximo:");
        double valor = lerNumero("valor", Double.class);
        StringBuilder relatorio = new StringBuilder();
        for (int i = 0; i < quantProdutos; i++) {
            if(produtos[i].valorDeVenda() < valor)
                relatorio.append(produtos[i]+"\n");
        }
        System.out.println(relatorio.toString());
    }

    static void ordenarProdutos(){
        cabecalho();
        int opcao = exibirMenuOrdenadores();
        switch (opcao) {
            case 1 -> ordenador = new Bubblesort<>();
            case 2 -> ordenador = new InsertSort<>();
            case 3 -> ordenador = new SelectionSort<>();
            case 4 -> ordenador = new Mergesort<>();
            case 5 -> ordenador = new Quicksort<>();
        }
        if(ordenador != null){
            opcao = exibirMenuComparadores();
            switch (opcao) {
                case 2 -> produtos = ordenador.ordenar(produtos, (a,b) -> (a.hashCode() - b.hashCode()));
                default -> produtos = ordenador.ordenar(produtos);
            }
            System.out.println("Tempo gasto: " + ordenador.getTempoOrdenacao() + " ms.");
            atualizarCopiasOrdenadas();
        }
        ordenador = null;
    }

    static void ordenarPorPrecoDescricao() {
        cabecalho();
        System.out.println("Ordenando produtos por preço (com desempate por descrição)");
        ordenador = new Quicksort<>();
        produtos = ordenador.ordenar(produtos, (a, b) -> {
            int comparaPreco = Double.compare(a.valorDeVenda(), b.valorDeVenda());
            if (comparaPreco != 0)
                return comparaPreco;
            return a.descricao.compareTo(b.descricao);
        });
        System.out.println("Tempo gasto: " + ordenador.getTempoOrdenacao() + " ms.");
        atualizarCopiasOrdenadas();
        ordenador = null;
    }

    static void atualizarCopiasOrdenadas() {
        ordenador = new Mergesort<>();
        produtosOrdenadosIdentif = ordenador.ordenar(Arrays.copyOf(produtos, produtos.length), (a, b) -> (a.hashCode() - b.hashCode()));
        produtosOrdenadosDescr = ordenador.ordenar(Arrays.copyOf(produtos, produtos.length));
        ordenador = null;
    }

    static void embaralharProdutos(){
        Collections.shuffle(Arrays.asList(produtos));
    }

    static void verificarSubstituicao(Produto[] dadosOriginais, Produto[] copiaDados){
        cabecalho();
        System.out.print("Deseja sobrescrever os dados originais pelos ordenados (S/N)?");
        String resposta = teclado.nextLine().toUpperCase();
        if(resposta.equals("S"))
            dadosOriginais = Arrays.copyOf(copiaDados, copiaDados.length);
    }

    static void listarProdutos(){
        cabecalho();
        for (int i = 0; i < quantProdutos; i++) {
            System.out.println(produtos[i]);
        }
    }

    public static void main(String[] args) {
        teclado = new Scanner(System.in);
        produtos = carregarProdutos(nomeArquivoDados);
        ordenador = new Mergesort<>();
        produtosOrdenadosIdentif = ordenador.ordenar(produtos, (a,b) -> (a.hashCode()-b.hashCode()));
        produtosOrdenadosDescr = ordenador.ordenar(produtos);
        ordenador = null;

        int opcao;
        do {
            opcao = exibirMenuPrincipal();
            switch (opcao) {
                case 1 -> mostrarProduto(localizarProduto());
                case 2 -> filtrarPorPrecoMaximo();
                case 3 -> ordenarProdutos();
                case 4 -> embaralharProdutos();
                case 5 -> listarProdutos();
                case 6 -> ordenarPorPrecoDescricao();
                case 0 -> System.out.println("FLW VLW OBG VLT SMP.");
            }
            pausa();
        } while (opcao != 0);
        teclado.close();
    }
}
