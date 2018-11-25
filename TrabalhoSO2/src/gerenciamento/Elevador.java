package gerenciamento;

import java.util.ArrayList;
import java.util.List;

/* 
 * Elevador
 * Classe que simula a execução de um elevador
 * construtor new Elevador(String nome, Gestor gestor, int deslocamento)
 */
public class Elevador extends Thread {

	private IGestor gestor;
	private Sentido sentido;
	private List<Pedido> embarcar;
	private List<Pedido> desembarcar;
	private boolean parar;
	private int andar;
	public int deslocamento;
	public int tempoDecorrido;
	
	public int pedidosRecebidos;
	public int movimentacoes;
	
	public Elevador(String nome, IGestor gestor, int deslocamento) {
		super(nome);
		this.gestor = gestor;
		this.andar = 0;
		sentido = Sentido.PARADO;
		parar = false;
		this.deslocamento = deslocamento;
		this.embarcar = new ArrayList<Pedido>();
		this.desembarcar = new ArrayList<Pedido>();
		this.tempoDecorrido = 0;
		this.pedidosRecebidos = 0;
		this.movimentacoes = 0;
		start();
	}
	
	public void parar() {
		this.parar = true;
	}
	
	public void subir() {
		if(andar < 17) {
			andar++;
			sentido = Sentido.SUBINDO;
			movimentacoes++;
			try {
				Elevador.sleep(deslocamento);
				this.tempoDecorrido += deslocamento;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Elevador " + this.getName() + " subiu ( " + andar(andar-1) + " -> " + andar(andar) + " )"  + " - Tempo decorrido: " + tempoDecorrido);
		}
		else sentido = Sentido.PARADO;
		
	}
	
	public void descer() {
		if (andar > 0) {
			andar--;
			sentido = Sentido.DESCENDO;
			movimentacoes++;
			try {
				Elevador.sleep(deslocamento);
				this.tempoDecorrido += deslocamento;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Elevador " + this.getName() + " desceu ( " + andar(andar+1) + " -> " + andar(andar) + " )"  + " - Tempo decorrido: " + tempoDecorrido );
		}
		else {
			sentido = Sentido.PARADO;
		}		
		
	}
	
	private void solicitarPedido() {
		if(gestor.temPedido()) {
			Pedido p = gestor.pegar(this.andar, this.sentido);
			if (p != null) {
				embarcar.add(p);
				pedidosRecebidos++;				
			}
		}
	}
	
	private void mover() {
				
		
		atenderAndar();
		
		embarcar.sort( (x, y) -> (x.origem - andar) - (y.origem - andar) );
		desembarcar.sort((x, y) -> (x.destino - andar) - (y.destino - andar));
		
		Pedido pegar = null;
		Pedido deixar = null;
		
		if(!embarcar.isEmpty())
			pegar = embarcar.get(0);
		
		if(!desembarcar.isEmpty())
			deixar = desembarcar.get(0);
		
		if(pegar != null && deixar != null) {
			if (pegar.sentido != deixar.sentido) {
				if(deixar.destino > andar) subir();
				else if (deixar.destino < andar) descer();
			}
			else {
				if(pegar.origem > andar) subir();
				else if(pegar.origem < andar) descer();
			}
		}
		else if(pegar != null) {
			if(pegar.origem > andar) subir();
			else if(pegar.origem < andar) descer();
		}
		else if (deixar != null) {
			if(deixar.destino > andar) subir();
			else if(deixar.destino < andar) descer();
		}
		
		solicitarPedido();
				
	}
	
	private void atenderAndar() {
		embarcar.sort( (x, y) -> (x.origem - andar) - (y.origem - andar) );
		desembarcar.sort((x, y) -> (x.destino - andar) - (y.destino - andar));
		
		boolean deixou = false;
		boolean pegou = false;
		Pedido pegar = null;
		Pedido deixar = null;
		
		if(!embarcar.isEmpty())
			pegar = embarcar.get(0);
		
		if(!desembarcar.isEmpty())
			deixar = desembarcar.get(0);
		
		if(deixar != null && deixar.destino == andar) {
			desembarcar.remove(deixar);
			deixou = true;
			System.out.println(deixar + " desceu do elevador " + this.getName()  + " - Tempo decorrido: " + tempoDecorrido);
		}
		
		if (pegar != null && pegar.origem == andar) {
			embarcar.remove(pegar);
			desembarcar.add(pegar);
			pegou = true;
			System.out.println(pegar + " embarcou no elevador " + this.getName() + " - Tempo decorrido: " + tempoDecorrido);
			
		}
		
	}

	private String andar(int andar) {
		String a = "";
		
		if (andar == 0) a = "T";
		else if(andar == 1) a = "P";
		else a = Integer.toString(andar - 1);
		
		return a;
	}
	
	
	public String relatorio() {
		String msg = "Elevador " + this.getName() + "\n";
		msg += "Tempo de Deslocamento: " + deslocamento + "\n";
		msg += "Pedidos Recebidos: " + pedidosRecebidos + "\n";
		msg += "Movimentações feitas: " + movimentacoes + "\n";
		msg += "Tempo decorrido: " + tempoDecorrido + "\n";
		msg += this.gestor.relatorio();
		return msg;		
	}
	
	@Override
	public void run() {
		
		while( !parar || temPedido() || gestor.temPedido() ) {
			if( temPedido()) {
				
				
				mover();
				
			}
			else {
				sentido = Sentido.PARADO;
				solicitarPedido();
				try {
					Elevador.sleep(1);
					this.tempoDecorrido += 1;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("Elevador " + this.getName() + " em manutenção.");
	}

	private boolean temPedido() {
		// TODO Auto-generated method stub
		return embarcar.size() != 0 || desembarcar.size() != 0;
	}
	
	
}
