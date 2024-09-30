import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner (System.in);
        int T = 0;
        do {
            System.out.println("Escreva qual tipo de chá:");
            T = sc.nextInt();
        } while (T < 1 || T > 4);

        int [] respostas = new int[5];

        for (int i = 0; i < 5; i++) {
            respostas [i] = sc.nextInt();
        }

        int respCorretas = 0;

        for (int resposta : respostas) {
            if (resposta == T) {
                respCorretas++;
            }
        }

        System.out.println(respCorretas);
        
        sc.close();

    }
}
