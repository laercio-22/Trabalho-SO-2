package main;

import java.util.List;

import gerenciamento.Leitor;
import gerenciamento.Pedido;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Pedido> pedidos = Leitor.ler("dados.csv");
		
		pedidos.forEach( p -> System.out.println(p));
	}

}
