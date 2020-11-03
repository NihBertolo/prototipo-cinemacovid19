package entities;

import java.util.Date;

public class EntradaProdutos extends Caixa {
	private String vendaProdutos;
	
	public EntradaProdutos(double saldo, Date dataOp, String vendaProdutos) {
		super(saldo, dataOp);
		this.vendaProdutos = vendaProdutos;
	}
	

	public String getVendaProdutos() {
		return vendaProdutos;
	}
	public void setVendaProdutos(String vendaProdutos) {
		this.vendaProdutos = vendaProdutos;
	}

	
}
