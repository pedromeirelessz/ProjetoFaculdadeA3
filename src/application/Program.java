package application;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Entidades;

public class Program {

    public static void main(String[] args) {

        // Configuração da localização para entrada de números
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        try {
            // Instanciação da classe Entidades para realizar operações binárias
            Entidades entidades = new Entidades();

            // Variável para armazenar a escolha do usuário
            int escolha;

            do {
                try {
                    // Pergunta ao usuário a quantidade de números para fazer operações
                    System.out.println("Quantos números você quer fazer operações?");
                    escolha = sc.nextInt();
                    break; // Sai do loop se a entrada for um número válido

                } catch (InputMismatchException ex) {
                    System.out.println("Você inseriu uma entrada inválida. Tente novamente.");
                    sc.nextLine(); // Limpa o buffer do scanner
                }
            } while (true);

            // Caso o usuário digite o número 0 como entrada
            if (escolha <= 0) {
                System.out.println("Nenhum número para operar. Encerrando o programa.");
                return;
            }

            long resultado = 0;

         // Loop para operações binárias com base na escolha do usuário
            for (int i = 1; i <= escolha; i++) {
                // Determina se é a primeira operação ou uma subsequente
                char operacao = (i > 1) ? entidades.obterOperacao(sc, i) : '+';

                long numero;

                do {
                    // Solicita ao usuário inserir o número
                    System.out.printf("Digite o número [%d]: ", i);
                    String entrada = sc.next();

                    // Tenta converter a entrada para long
                    try {
                        numero = Long.parseLong(entrada);
                        break; // Sai do loop se a conversão for bem-sucedida
                    } catch (NumberFormatException ex) {
                        System.out.println("Você inseriu uma entrada inválida. Tente novamente.");
                    }
                } while (true);

                // Verifica se o número inserido é binário
                if (entidades.verificarBinario(numero)) {
                    // Realiza a operação e atualiza o resultado em binário
                    resultado = Long.parseLong(entidades.realizarOperacao(Long.toBinaryString(resultado), String.valueOf(numero), operacao), 2);
                } else {
                    System.out.println("Você colocou tipos errados, por favor, tente novamente.");
                    // Se a entrada não for válida, decrementa o índice para repetir a entrada do mesmo número.
                    i--;
                }
            }

            // Exibe o resultado final em binário
            System.out.println("Resultado final em binário: " + Long.toBinaryString(resultado));

        } catch (RuntimeException r) {
            // Trata erros inesperados durante a execução
            System.out.println("Erro inesperado");
        } finally {
            // Fecha o Scanner para evitar vazamentos de recursos
            sc.close();
        }
    }
}