import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class AplicacaoTeste {
	public static final String LISTA = "lista.txt";
	
	public static void main(String[] args) {
		try
		{
			List<String> lista = readFile();
			if(lista != null && lista.size() > 0) {
				System.out.println("Itens j� adicionados na lista:");
				for(String line : lista) {
					System.out.println(line);
				}
			}
			
			
			
			try(PrintWriter pw = new PrintWriter(new FileWriter(LISTA,true));
					Scanner inputKey = new Scanner(System.in)){
				while(true) {
					System.out.print("Digite um item: ");
					String item = inputKey.nextLine();
					if(item.trim().isEmpty()) {
						continue;
					}
					
					if(item.trim().equals("0")) {
						break;
					}
					
					pw.println(item.trim());
				}
				System.out.println("Fim da execu��o");
			}			
			
		} catch (FileNotFoundException e) {
			System.out.println("N�o foi poss�vel localizar o arquivo "+LISTA+".");
		} catch (IOException e) {
			System.out.println("N�o foi poss�vel localizar ler o arquivo "+LISTA+".");
		}
	}
	
	private static List<String> readFile() throws FileNotFoundException, IOException {
		
		File arq = new File(LISTA);
		if(!arq.exists()) {
			return null;
		}
		
		List<String> listItens = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(arq))) {
			String line;
			while((line = br.readLine()) != null) {				
				listItens.add(line.trim());
			}
		}
		return listItens;
	}
	
	
}
