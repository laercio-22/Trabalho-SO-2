package gerenciamento;

/* 
 * Elevador
 * Classe que simula a execução de um elevador
 * construtor new Elevador(String nome, Gestor gestor)
 */
public class Elevador extends Thread {

	private IGestor gestor;
	private Sentido sentido;
	
	public Elevador(String nome, IGestor gestor) {
		super(nome);
		this.gestor = gestor;
		sentido = Sentido.PARADO;
	}

	@Override
	public void run() {
		
	}
	
	
}
