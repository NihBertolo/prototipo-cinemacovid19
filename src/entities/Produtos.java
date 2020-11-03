package entities;

public class Produtos {
	private String nomeProduto;
	private String tipo;
	private double preco;
	private int qtdEstoque;
	
	public Produtos(String nomeProduto, String tipo, double preco, int qtdEstoque) {
		super();
		this.nomeProduto = nomeProduto;
		this.tipo = tipo;
		this.preco = preco;
		this.qtdEstoque = qtdEstoque;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}


	public void addQtdEstoque(int qtdEntrada) {
		this.qtdEstoque += qtdEntrada;
	}

	public void removeQtdEstoque(int qtdSaida) {
		this.qtdEstoque -= qtdSaida;
	}
	
	public int getQtdEstoque() {
		return qtdEstoque;
	}
	
	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

}
