# Avaliação A3
[![LICENSE](https://img.shields.io/npm/l/react)](https://github.com/phmeyreles/projetoDAO/blob/master/LICENSE) 

* 02/12/2023

* Pedro Henrique Meireles da Silva

* UNIFACS

* Análise e Desenvolvimento de Sistemas

* Sistemas computacionais e segurança

## Visão geral
* A avaliação envolve a análise de um código disponibilizado pelo professor. Achar vulnerabilidades e desenvolver melhorias.

## Especificações
* Linguagem utilizada: Java
 
* IDE utilizada: Eclipse

* Versão do Java: JDK 17

## Análise do código:
* O código tem como finalidade a realização de operações entre dois números binários, utilizando a classe Scanner para entrada e Integer.parseInt para converter os valores binários de string para inteiro. Em termos de estrutura, é bastante simples, envolvendo apenas uma classe e um pacote.

## Identificando vulnerabilidades:
### Injeção de Código: 
* A ausência de validação na entrada torna a aplicação mais suscetível a injeções de código.


### Estouro de Buffer:
* Não existem verificações para ter a garantia que operações aritméticas não resultem no estouro de buffer.


### Falta de classes:
* A falta de classes, deixar todo o controle de operações na classe Main, deixando o código menos coeso e delegando menos as funções.

### Mensagem de erro:
* A mensagem de erro pode informar ao usuário informações delicadas sobre a aplicação, então é necessário assegurar que ela seja genérica e não forneça detalhes caso ocorra.


### Na prática:

![Error1](https://raw.githubusercontent.com/pedromeirelessz/ProjetoFaculdadeA3/master/pastaImagens/Erro1.png)

![Error2](https://raw.githubusercontent.com/pedromeirelessz/ProjetoFaculdadeA3/master/pastaImagens/erro2.png)

* Nota-se que não há verificação para garantir que os números binários sejam realmente binários, além de expor o programa podendo mostrar informações específicas sobre a aplicação. Existe uma verificação apenas nas operações, porém elas não são tratadas de forma adequada, apesar de ter uma mensagem de erro genérica, o usuário ainda tem que fechar a aplicação e abrir novamente.

## Melhorias gerais:
### Encapsulamento da aplicação:
* O código original estava em uma única classe, agora com a classe entidades no pacote model.entities é feita a reutilização e organização do código, deixando o código mais coeso e delegando melhor as funções.


### Tratamento de exceções e validação para entradas:
* No código original não se tinha um tratamento de exceções adequado e validações da mesma, fazendo o programa quebrar, foi adicionado blocos de tratamento de exceções específicos, funções e métodos para assegurar que a aplicação lide contra entradas inválidas. Incluindo a verificação do número de entrada e a validação de operações.

### Aumento de números:
* No código original era possível fazer operação entre apenas 2 números binários, agora é possível fazer operações entre quantos números o usuário desejar.

## Explicando melhorias detalhadamente:
### Pacote application classe Main:



```Java

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

```

A instância da classe Entidades é criada e pode ser chamada no objeto entidades, podendo agora ser utilizado os métodos criados na classe entidades, deixando o código mais coeso e delegando melhor as funções. Neste escopo em específico, é utilizado um loop do-while para que o usuário digite a quantidade de números com os quais ele quer realizar operações. Utilizamos o (sc.nextInt()) para ler a entrada inteira digitada. Caso seja inserido algo que não seja um valor do tipo int, irá ser capturada a exceção InputMismatchException, então a aplicação irá exibir uma mensagem de erro e limpar o buffer do scanner com a reutilização (sc.nextLine())  ( erro de looping ). Na condição if, caso a quantidade escolhida seja menor ou igual a zero, irá exibir uma mensagem informando que não há números para operar e encerra o programa.


```Java

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


```

Funcionando a partir da quantidade de números da entrada. Com a variável operação sendo determinada com base no índice ‘i’. Caso o ‘i’ seja maior que 1, o programa irá chamar o método ‘obterOperacao’ da classe Entidades, para que seja feita a soma com o número anterior. Caso esteja ainda no 1 (a primeira operação), a operação padrão é definida como adição (+), e será somada com a variável ‘resultado’ e definimos ela como zero acima. Logo após irá entrar em um loop do-while, solicitando ao usuário que digite o número correspondente ao índice atual, dentro será utilizando um bloco try-catch para converter o número para um tipo long. Caso a conversão for bem-sucedida, o loop será interrompido com break. Então irá entrar a estrutura condicional if, invocando o método ‘verificarBinario’, colocando o número como parâmetro, logo após iremos atribuir a variável long ‘resultado’ que está fora do escopo do for, o resultado da operação do método ‘realizarOperacao’ que retorna um string, então utilizamos o método ‘Long.parseLong’ para converter para um tipo long, e utilizamos o ‘Long.toBinaryString’ para converter a variável ‘resultado’ para uma String binária, e a variável ‘numero’ apenas utilizamos o método ‘String.valueOf’ pois essa variável já passou pelo método ‘verificarBinario’ anteriormente, portanto a função ‘realizarOperacao’’ tem como parâmetros as variáveis ‘resultado’, ‘numero’ e ‘operacao’, caso o número que o usuário digitar não for binário, irá resultar no else, que irá dar um ‘i–’, repetindo o for e voltando no mesmo índice.

```Java


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

```

Após isso, o resultado final será exibido em uma variável tipo long em uma representação binária usando ‘Long.toBinaryString(resultado)’. Caso por algum motivo, mesmo assim uma exceção seja lançada, a aplicação irá exibir a mensagem "Erro inesperado" no console, evitando que seja mostrado detalhes fundamentais do código. Então teremos o bloco finally garantindo que o Scanner seja fechado.


### Pacote model.entities classe entidades ( Explicando metódos implementados ):

```Java
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
```


Verifica se o valor digitado pelo usuário é binário, se contém apenas os dígitos '0' e '1'. Convertendo o valor para uma string e usa uma expressão regular ([01]+) para garantir que a string contenha apenas '0' e '1'. Retorna true se for binário e false caso não seja.

```Java
	/**
	 * Verifica se um caractere de operação é válido (+, -, *, / ou x).
	 *
	 * O caractere de operação a ser verificado true se a operação é válida, false
	 * caso contrário
	 */
	public boolean operacaoValida(char operacao) {

		return operacao == '+' || operacao == '-' || operacao == '/' || operacao == '*' || operacao == 'x';
	}
```


Verifica se um caractere de operação digitado pelo usuário é válido. Ele retorna true se o caractere de operação for '+', '-', '/', '*' ou 'x', e false caso não seja, ele será parte de outros métodos.

```Java
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
```


Recebe duas variáveis tipo strings representando números binários, converte eles para números decimais, e recebe uma variável do tipo char e realiza uma operação matemática com base na escolha do usuário (adição, subtração, multiplicação ou divisão), logo após converte o resultado de volta para uma string binária antes de retorná-la.

```Java
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
```

Solicita ao usuário inserir uma operação válida (+, -, *, / ou x) para que seja feita a conta. Sendo usado a estrutura condicional de loop do-while e fazendo a chamada do método ‘operacaoValida’ como parâmetro, assim assegurando que o usuário digite uma entrada válida e o programa não quebre. Retornando o caractere de operação inserido pelo usuário.



## Youtube:
### O vídeo do projeto será postado no youtube, no seguinte link:  

* https://www.youtube.com/watch?v=eRth1DgIhT8

## Relatório do projeto

### Para baixar o relatório clique nesse link e em "View raw":

* https://github.com/pedromeirelessz/ProjetoFaculdadeA3/blob/master/DocumentoProjeto.docx

