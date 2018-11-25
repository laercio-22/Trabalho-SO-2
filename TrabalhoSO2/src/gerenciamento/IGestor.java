package gerenciamento;

public interface IGestor {
	
	public Pedido pegar();
	
	public Pedido pegar(int andar, Sentido sentido);
	
	public void inserir(Pedido pedido);
	
	public boolean temPedido();
	
	public String relatorio();
}
