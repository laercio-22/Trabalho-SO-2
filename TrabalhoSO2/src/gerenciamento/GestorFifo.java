package gerenciamento;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class GestorFifo implements IGestor {

	private Queue<Pedido> pedidos;
	private int pedidosRecebidos;
	
	public GestorFifo() {
		pedidos = new LinkedBlockingQueue<Pedido>();
	}
	
	@Override
	public synchronized Pedido pegar() {
		if(pedidos.isEmpty()) return null;
		else {
			return pedidos.poll();
		}
	}

	@Override
	public synchronized Pedido pegar(int andar, Sentido sentido) {
		// TODO Auto-generated method stub
		return pegar();
	}

	@Override
	public synchronized void inserir(Pedido pedido) {
		pedidos.add(pedido);
		pedidosRecebidos++;
	}

	@Override
	public boolean temPedido() {
		// TODO Auto-generated method stub
		return !pedidos.isEmpty();
	}

	@Override
	public String relatorio() {
		String msg = "GestorFifo\n";
		msg += "Regra de distribuição: FIFO\n";
		msg += "Pedidos Recebidos: " + pedidosRecebidos;
		return msg;
	}

}
