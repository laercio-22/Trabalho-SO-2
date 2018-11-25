package gerenciamento;

import java.util.ArrayList;
import java.util.List;

public class Produtor extends Thread{
	
	List<Pedido> pedidos = new ArrayList();
	
	public Produtor() {
		pedidos = Leitor.ler("dados.csv");
		start();
	}
	
	@Override
	public void run() {
		
		
		try {
		
			for(Pedido pedido:pedidos) {
				System.out.println(pedido.origem);
				Produtor.sleep(pedido.destino);
		
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			
	}

}
