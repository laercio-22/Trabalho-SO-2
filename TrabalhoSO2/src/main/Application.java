package main;

import java.util.List;

import gerenciamento.Gestor;
import gerenciamento.Leitor;
import gerenciamento.Pedido;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Pedido> pedidos = Leitor.ler("dados.csv");
		Gestor gestor = new Gestor();
		
		pedidos.forEach( p -> gestor.inserirPedido(p));
		
		while (gestor.temPedido() ) {
			System.out.println( gestor.pegarPedidoDescendo() );
		}
		
	}

}
