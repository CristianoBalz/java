import java.util.stream.IntStream;

public class Exercicio2_2 {

	public static void main(String[] args) {
		
		//variável para aculular a soma dos valores (inicia em 0)
		int soma = 0;
		
		//for de 1 até 100
		for (int i = 1; i <= 100; i += 2) {
			//para cada iteração do for, soma recebe o seu valor anterior somado com o valor de i
			soma = soma + i;
		}
		
		System.out.println(soma);
		System.out.println(IntStream.range(1, 101)
				.filter(n -> n % 2 != 0)
				.reduce(0,(x,y) -> x + y ));
		
	}
}
