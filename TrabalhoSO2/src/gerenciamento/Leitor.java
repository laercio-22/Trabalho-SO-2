package gerenciamento;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Classe Leitor
 * Responsavel por ler o arquivo com dados para simular
 * pedidos aos elevadores
 * possui apenas o metodo estatico ler(String nomeDoArquivo)
 * */
public class Leitor {
	
	public static List<Pedido> ler(String nomeDoArquivo){
		
		//String arquivoCSV = "arquivo.csv";
	    BufferedReader br = null;
	    String linha = "";
	    String csvDivisor = ",";
	    List<Pedido> pedidos = new ArrayList();
	    
	    try {

	        br = new BufferedReader(new FileReader(nomeDoArquivo));
	        while ((linha = br.readLine()) != null) {

	            String[] dados = linha.split(csvDivisor);
	            pedidos.add(new Pedido(dados[0], dados[1], Integer.parseInt(dados[2])));	            

	        }

	    }catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (br != null) {
	            try {
	                br.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
		
		return pedidos;		
	}
}
