package main;

import java.util.List;

import gerenciamento.Elevador;
import gerenciamento.GestorElevator;
import gerenciamento.GestorFifo;
import gerenciamento.IGestor;
import gerenciamento.Leitor;
import gerenciamento.Pedido;
import gerenciamento.Produtor;
import gerenciamento.Sentido;

public class Application {

	private static String  relatorio = "";
	
	
	
	public static void executar(IGestor gestor, String nomeArquivo, String nomeRelatorio) throws InterruptedException {
		
		Produtor produtor = new Produtor(gestor, nomeArquivo);
		Elevador e1 = new Elevador("1", gestor, 30);
		Elevador e2 = new Elevador("2", gestor, 30);
		Elevador e3 = new Elevador("3", gestor, 30);
		
		while(produtor.isAlive() || gestor.temPedido()) {
			
		}
		
		e1.parar();
		e2.parar();
		e3.parar();
		e1.join();
		e2.join();
		e3.join();
		
		relatorio += nomeRelatorio + "\n";
		
		relatorio += "--------------------------\n";
		relatorio += e1.relatorio() + "\n";
		relatorio += "--------------------------\n";
		
		relatorio += "--------------------------\n";
		relatorio += e2.relatorio()+"\n";
		relatorio += "--------------------------\n";
		
		relatorio += "--------------------------\n";
		relatorio += e3.relatorio()+"\n";
		relatorio += "--------------------------\n";
		
		relatorio += "--------------------------\n";
		relatorio += "Tempo Total de trabalho: " + (e1.tempoDecorrido + e2.tempoDecorrido + e3.tempoDecorrido) + "\n";
		relatorio +="Total de movimentacões: " + (e1.movimentacoes + e2.movimentacoes + e3.movimentacoes)+"\n";
	}
	
	public static void main(String[] args) throws InterruptedException {		
		
		
		executar(new GestorElevator(), "dados.csv", "Primeira Execução");
		
		executar(new GestorFifo(), "dados.csv", "Primeira Execução");
		
		
		System.out.println(relatorio);
		
		
	}

}
