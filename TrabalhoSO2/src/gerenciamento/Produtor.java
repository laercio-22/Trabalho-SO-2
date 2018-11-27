package gerenciamento;

import java.util.ArrayList;
import java.util.List;

public class Produtor extends Thread{
	
	private List<Pedido> pedidos = new ArrayList();
	private IGestor gestor;
	private int agora;
	
	
	public Produtor(IGestor gestor, String nomeArquivo) {
		pedidos = Leitor.ler(nomeArquivo);
		//pedidos.sort( (x, y) -> x.instante - y.instante ); // ordena lista por ordem de chegada de pedidos
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
			
			Pedido p = pedidos.remove(0);
			try {
				Produtor.sleep(p.instante);
				agora += p.instante;
				gestor.inserir(p);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			
	}

}
