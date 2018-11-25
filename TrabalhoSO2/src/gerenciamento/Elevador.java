package gerenciamento;

/* 
 * Elevador
 * Classe que simula a execução de um elevador
 * construtor new Elevador(String nome, Gestor gestor)
 */
public class Elevador extends Thread {

	private IGestor gestor;
	
	public static final int SUBINDO = 1;
	public static final int PARADO = 0;
	public static final int DESCENDO = -1;
	
	public Elevador(String nome, IGestor gestor) {
		super(nome);
		this.gestor = gestor;
	}

	@Override
	public void run() {
		
	}
	
	
}
