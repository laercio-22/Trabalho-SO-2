package gerenciamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Gestor {
	
	List<Pedido> pedidos;
	
	public Gestor() {
		pedidos = new ArrayList<Pedido>();
	}
	
	/*Remove o primeiro pedido na lista*/
	public Pedido pegarPedido() {
		if(pedidos.isEmpty()) return null;
		else {
			return pedidos.remove(0);
		}
	}
	
	/*Insere pedido na lista*/
	public void inserirPedido(Pedido pedido) {
		pedidos.add(pedido);
	}
	
	/*Remove o primeiro pedido na lista com o sentido descendo. Caso não tenha subindo remove o primeiro*/
	public Pedido pegarPedidoDescendo() {
		if(pedidos.isEmpty()) return null;
		else {
			Pedido pedido =  pedidos
								.stream()
								.filter( p -> p.sentido == Pedido.DESCENDO)
								.findFirst()
								.orElse(pegarPedido());
			pedidos.remove(pedido);
			
			return pedido;
		}		
	}
	
	/*Remove o primeiro pedido na lista com o sentido subindo. Caso não tenha subindo remove o primeiro*/
	public Pedido pegarPedidoSubindo() {
		if(pedidos.isEmpty()) return null;
		else {
			Pedido pedido =  pedidos
								.stream()
								.filter( p -> p.sentido == Pedido.SUBINDO)
								.findFirst()
								.orElse(pegarPedido());
			pedidos.remove(pedido);
			
			return pedido;
		}		
	}

	/*Verifica se existe pedido na lista*/
	public boolean temPedido() {
		// TODO Auto-generated method stub
		return !pedidos.isEmpty();
	}
}
