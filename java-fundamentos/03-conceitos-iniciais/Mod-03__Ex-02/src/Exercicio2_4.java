import java.util.stream.IntStream;

public class Exercicio2_4 {

	public static void main(String[] args) {
		
		//variável para controlar quantos números já foram impressos
		int cont = 0;
		
		//inicia o for com o i valendo 1
		for(int i = 1; cont < 10; i++) {
			
			//o código if verifica se o resto da divisão entre i e 9 é 0.
			//caso seja, o número é múltiplo de 9 e deve ser impresso
			if(i % 9 == 0) {
				System.out.println(i);
				
				//incrementa a quantidade de números impressos
				cont++;
			}
		}
		
		
		/* mais simples */
		for(int i = 1; i <= 10; i++) {
			System.out.println(i * 9);
		}
		
		/* usando stream*/		
		IntStream.range(1, 11).map(n -> n * 9).forEach(System.out::println);
	}
}
