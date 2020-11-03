package entities;

public class SalaStandard {

	private double tarifaAdicional = 0.00;
	private int fileiras = 4;
	private int colunas = 8;

	

	public SalaStandard(double tarifaAdicional) {
		this.tarifaAdicional = tarifaAdicional;
	}


	public double getTarifaAdicional() {
		return tarifaAdicional;
	}

	public void setTarifaAdicional(double tarifa, double tarifaAdicional, int duracao) {
		this.tarifaAdicional = tarifa + (tarifaAdicional*duracao);
	}

	public int getFileiras() {
		return fileiras;
	}

	public void addFileiras(int fileiras) {
		this.fileiras++;
	}

	public void removeFileiras(int fileiras) {
		this.fileiras--;
	}
	
	public int getColunas() {
		return colunas;
	}


	public void addColunas(int colunas) {
		this.colunas++;
	}
	
	public void removeColunas(int colunas) {
		this.colunas--;
	}

}
