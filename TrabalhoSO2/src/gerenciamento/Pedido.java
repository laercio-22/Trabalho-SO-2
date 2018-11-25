package gerenciamento;

public class Pedido {
	public int origem;
	public int destino;
	public int instante;
	public Sentido sentido;
	
	public static final int SUBINDO = 1;
	public static final int DESCENDO = -1;
	
	public Pedido(String origem, String destino, int instante) {
		
		if("T".equals(origem))
			this.origem = 0;
		else if("P".equals(origem))
			this.origem = 1;
		else 
			this.origem = Integer.parseInt(origem) + 1;
		
		if("T".equals(destino))
			this.destino = 0;
		else if("P".equals(destino))
			this.destino = 1;
		else 
			this.destino = Integer.parseInt(destino) + 1;
		
		this.instante = instante;
		this.sentido = sentido(this.origem, this.destino);
	}

	private Sentido sentido(int origem, int destino) {
		// TODO Auto-generated method stub
		return (destino - origem) > 0 ? Sentido.SUBINDO : Sentido.DESCENDO;
	}
	
	private String andar(int andar) {
		String a = "";
		
		if (andar == 0) a = "T";
		else if(andar == 1) a = "P";
		else a = Integer.toString(andar - 1);
		
		return a;
	}
	
	private String sentido(Sentido sentido) {
		String s = "";
		
		if(sentido == Sentido.SUBINDO) s = "Subindo";
		else s = "Descendo";
		
		return s;
	}

	@Override
	public String toString() {
		return "Pedido [origem=" + andar(origem) + ", destino=" + andar(destino) + ", instante=" + instante + ", sentido=" + sentido(sentido)
				+ "]";
	}
	
	
}
