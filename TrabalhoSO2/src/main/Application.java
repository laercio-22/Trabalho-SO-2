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

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//List<Pedido> pedidos = Leitor.ler("dados.csv");
		IGestor gestorElevator = new GestorElevator();
		IGestor gestorFifo = new GestorFifo();
		
		Produtor produtor1 = new Produtor(gestorElevator);
		
		Elevador e1 = new Elevador("1", gestorElevator, 30);
		Elevador e2 = new Elevador("2", gestorElevator, 30);
		Elevador e3 = new Elevador("3", gestorElevator, 30);
		
		while(produtor1.isAlive() || gestorElevator.temPedido()) {
			
		}
		
		e1.parar();
		e2.parar();
		e3.parar();
		e1.join();
		e2.join();
		e3.join();
		
		Produtor produtor2 = new Produtor(gestorFifo);		
		
		Elevador e4 = new Elevador("4", gestorFifo, 30);
		Elevador e5 = new Elevador("5", gestorFifo, 30);
		Elevador e6 = new Elevador("6", gestorFifo, 30);
		
		while(produtor2.isAlive() || gestorFifo.temPedido()) {
			
		}		
		
		e4.parar();
		e5.parar();
		e6.parar();		
		
		e4.join();
		e5.join();
		e6.join();
		
		System.out.println("--------------------------");
		System.out.println(e1.relatorio());
		System.out.println("--------------------------");
		
		System.out.println();
		System.out.println("--------------------------");
		System.out.println(e2.relatorio());
		System.out.println("--------------------------");
		
		System.out.println();
		System.out.println("--------------------------");
		System.out.println(e3.relatorio());
		System.out.println("--------------------------");
		
		System.out.println();
		System.out.println("--------------------------");
		System.out.println(e4.relatorio());
		System.out.println("--------------------------");
		
		System.out.println();
		System.out.println("--------------------------");
		System.out.println(e5.relatorio());
		System.out.println("--------------------------");
		
		System.out.println();
		System.out.println("--------------------------");
		System.out.println(e6.relatorio());
		System.out.println("--------------------------");
		
		System.out.println("Regra Elevador");
		System.out.println("Tempo total de trabalho: " + (e1.tempoDecorrido + e2.tempoDecorrido + e3.tempoDecorrido));
		System.out.println("Total de movimentacões: " + (e1.movimentacoes + e2.movimentacoes + e3.movimentacoes));
		
		System.out.println("Regra Fifo");
		System.out.println("Tempo total de trabalho: " + (e4.tempoDecorrido + e5.tempoDecorrido + e6.tempoDecorrido));
		System.out.println("Total de movimentacões: " + (e4.movimentacoes + e5.movimentacoes + e6.movimentacoes));
		
	}

}
