public class Quicksort<T extends Comparable<T>> implements IOrdenador<T> {

    private long tempoOrdenacao;

    @Override
    public T[] ordenar(T[] dados) {
        long inicio = System.currentTimeMillis();
        quicksort(dados, 0, dados.length - 1);
        tempoOrdenacao = System.currentTimeMillis() - inicio;
        return dados;
    }

    @Override
    public T[] ordenar(T[] dados, java.util.Comparator<T> comparador) {
        long inicio = System.currentTimeMillis();
        quicksort(dados, 0, dados.length - 1, comparador);
        tempoOrdenacao = System.currentTimeMillis() - inicio;
        return dados;
    }

    @Override
    public long getTempoOrdenacao() {
        return tempoOrdenacao;
    }

    private void quicksort(T[] dados, int inicio, int fim) {
        if (inicio < fim) {
            int pivo = particionar(dados, inicio, fim);
            quicksort(dados, inicio, pivo - 1);
            quicksort(dados, pivo + 1, fim);
        }
    }

    private int particionar(T[] dados, int inicio, int fim) {
        T pivo = dados[fim];
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {
            if (dados[j].compareTo(pivo) <= 0) {
                i++;
                trocar(dados, i, j);
            }
        }
        trocar(dados, i + 1, fim);
        return i + 1;
    }

    private void quicksort(T[] dados, int inicio, int fim, java.util.Comparator<T> comparador) {
        if (inicio < fim) {
            int pivo = particionar(dados, inicio, fim, comparador);
            quicksort(dados, inicio, pivo - 1, comparador);
            quicksort(dados, pivo + 1, fim, comparador);
        }
    }

    private int particionar(T[] dados, int inicio, int fim, java.util.Comparator<T> comparador) {
        T pivo = dados[fim];
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {
            if (comparador.compare(dados[j], pivo) <= 0) {
                i++;
                trocar(dados, i, j);
            }
        }
        trocar(dados, i + 1, fim);
        return i + 1;
    }

    private void trocar(T[] dados, int i, int j) {
        T temp = dados[i];
        dados[i] = dados[j];
        dados[j] = temp;
    }
}
