package gerenciamento;

import java.util.ArrayList;
import java.util.List;

public class GestorElevator implements IGestor {

	private List<Pedido> pedidos;
	private int pedidosRecebidos;
	
	public GestorElevator() {
		pedidos = new ArrayList<Pedido>();
		pedidosRecebidos = 0;
	}
	
	/*Remove o primeiro pedido na lista*/
	@Override
	public synchronized Pedido pegar() {
		if(pedidos.isEmpty()) return null;
		else {
			return pedidos.remove(0);
		}
	}
	
	/*Filtra os pedidos pelo sentido e pelo andar e pega o pedido com o andar mais proximo*/
	@Override
	public synchronized Pedido pegar(int andar, Sentido sentido) {
		if(pedidos.isEmpty()) return null;
		else if(sentido == Sentido.SUBINDO) {
			Pedido pedido =  pedidos
								.stream()
								.filter( p -> p.sentido == sentido && p.origem >= andar)
								.sorted( (x, y) -> y.origem - x.origem)
								.findFirst()
								.orElse(null);
			pedidos.remove(pedido);
			
			return pedido;
		}		
		else if(sentido == Sentido.DESCENDO) {
			
			Pedido pedido =  pedidos
								.stream()
								.filter( p -> p.sentido == sentido && p.origem <= andar)
								.sorted( (x, y) -> x.origem - y.origem)
								.findFirst()
								.orElse(null);
			pedidos.remove(pedido);
			
			return pedido;
		}
		else {
			return pegar();
		}
	}
	
	/*Insere pedido na lista*/
	@Override
	public synchronized void  inserir(Pedido pedido) {
		pedidos.add(pedido);
		pedidosRecebidos++;
	}

	@Override
	public boolean temPedido() {
		return !pedidos.isEmpty();
	}

	@Override
	public String relatorio() {
		String msg = "GestorElevator\n";
		msg += "Regra de distribuição: Elevador\n";
		msg += "Pedidos Recebidos: " + pedidosRecebidos;
		return msg;
	}

}
