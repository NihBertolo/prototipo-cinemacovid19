package entities;

import java.util.Date;

public class Caixa {
	private double saldo;
	private Date dataOp;
	
	public Caixa() {
		
	}
	
	public Caixa(double saldo, Date dataOp) {
		super();
		this.saldo = saldo;
		this.dataOp = dataOp;
	}


	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Date getDataOp() {
		return dataOp;
	}

	public void setDataOp(Date date) {
		this.dataOp = date;
	}
	
	
	
}
