package entities;

import java.util.Date;

public class EntradaIngressos extends Caixa {
	private String vendaIngressos;
	
	public EntradaIngressos(double saldo, Date dataOp, String vendaIngressos) {
		super(saldo, dataOp);
		this.vendaIngressos = vendaIngressos;
	}	

	public String getVendaIngressos() {
		return vendaIngressos;
	}
	public void setVendaIngressos(String vendaIngressos) {
		this.vendaIngressos = vendaIngressos;
	}

	
}
