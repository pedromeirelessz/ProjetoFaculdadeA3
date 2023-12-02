package model.entities;

import java.util.Scanner;

public class Entidades {

	/**
	 * Verifica se um valor representado como Long é binário, contendo apenas '0' e
	 * '1'.
	 *
	 * valor O e 1 valor a ser verificado true se o valor é binário, false caso
	 * contrário
	 */
	public boolean verificarBinario(Long valor) {
		// Converte o long para uma string e verifica se contém apenas '0' e '1'
		String binarioStr = String.valueOf(valor);
		return binarioStr.matches("[01]+");
	}

	/**
	 * Verifica se um caractere de operação é válido (+, -, *, / ou x).
	 *
	 * O caractere de operação a ser verificado true se a operação é válida, false
	 * caso contrário
	 */
	public boolean operacaoValida(char operacao) {

		return operacao == '+' || operacao == '-' || operacao == '/' || operacao == '*' || operacao == 'x';
	}

	// Realiza a operação especificada entre dois números.

	public String realizarOperacao(String resultado, String numero, char operacao) {
	   
		long resultadoDecimal = Long.parseLong(resultado, 2);
	    long numeroDecimal = Long.parseLong(numero, 2);
	    long resultadofinal = 0;

	    switch (operacao) {
	        case '+':
	            resultadofinal = resultadoDecimal + numeroDecimal;
	            break;
	        case '-':
	            resultadofinal = resultadoDecimal - numeroDecimal;
	            break;
	        case '/':
	            resultadofinal = resultadoDecimal / numeroDecimal;
	            break;
	        case '*':
	        case 'x':
	            resultadofinal = resultadoDecimal * numeroDecimal;
	            break;
	        default:
	            // Este caso não deve ocorrer, pois verificamos a validade da operação antes.
	            resultadofinal = resultadoDecimal;
	    }

	    // Converte o resultado de volta para binário
	    return Long.toBinaryString(resultadofinal);
	}


	/**
	 * Solicita ao usuário inserir uma operação válida (+, -, *, / ou x) para um
	 * determinado número.
	 *
	 * Scanner para leitura de entrada do usuário e o indice para o
	 * qual a operação está sendo solicitada
	 */
	public char obterOperacao(Scanner sc, int indice) {
		char operacao;
		do {
			System.out.printf("Digite a operação (+, -, /, * ou x) para o número [%d]: ", indice);
			operacao = sc.next().charAt(0);
		} while (!operacaoValida(operacao));
		return operacao;
	}
}