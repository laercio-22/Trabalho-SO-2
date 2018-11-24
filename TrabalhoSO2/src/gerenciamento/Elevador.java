package gerenciamento;

/* 
 * Elevador
 * Classe que simula a execução de um elevador
 * construtor new Elevador(String nome, Gestor gestor)
 */
public class Elevador extends Thread {

	private Gestor gestor;
	
	public Elevador(String nome, Gestor gestor) {
		super(nome);
		this.gestor = gestor;
	}

	@Override
	public void run() {
		
	}
	
	
}
