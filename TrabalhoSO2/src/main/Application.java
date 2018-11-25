package main;

import java.util.List;

import gerenciamento.Elevador;
import gerenciamento.GestorElevator;
import gerenciamento.GestorFifo;
import gerenciamento.IGestor;
import gerenciamento.Leitor;
import gerenciamento.Pedido;
import gerenciamento.Sentido;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Pedido> pedidos = Leitor.ler("dados.csv");
		IGestor gestor = new GestorElevator();
		
		pedidos.forEach( p -> gestor.inserir(p));
		
		System.out.println(gestor.pegar(7, Sentido.PARADO));
		System.out.println(gestor.pegar(8, Sentido.SUBINDO));
		System.out.println(gestor.pegar(15, Sentido.DESCENDO));
		System.out.println(gestor.pegar(0, Sentido.SUBINDO));
		
	}

}
