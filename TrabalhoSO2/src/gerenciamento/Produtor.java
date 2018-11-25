package gerenciamento;

import java.util.ArrayList;
import java.util.List;

public class Produtor extends Thread{
	
	private List<Pedido> pedidos = new ArrayList();
	private IGestor gestor;
	private int agora;
	
	
	public Produtor(IGestor gestor) {
		pedidos = Leitor.ler("dados.csv");
		pedidos.sort( (x, y) -> x.instante - y.instante ); // ordena lista por ordem de chegada de pedidos
		this.gestor = gestor;
		agora = 0;
		start();
	}
	
	private synchronized List<Pedido> pegar() {
		List<Pedido> recebidos = new ArrayList();
		
		for(Pedido p : pedidos) {
			
			if(p.instante == agora) recebidos.add(p);
		}
		
		for(Pedido r : recebidos) {
			pedidos.remove(r);
		}
			
		return recebidos;
	}
	
	@Override
	public void run() {
		
		while(!pedidos.isEmpty()) {
			
			List<Pedido> recebidos = pegar();
			
			for(Pedido p : recebidos) {
				gestor.inserir(p);
				//System.out.println(p + " Solicitado");
			}
			
			try {
				
				Produtor.sleep(1);
				agora++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			
	}

}
