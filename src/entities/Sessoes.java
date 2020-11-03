package entities;

import java.util.Scanner;
@SuppressWarnings("unused")

public class Sessoes {
	private String nomeFilmeSessao;
	private boolean dublado;
	private String tipoSala;
	private String horario;
	private double tarifa;
	private int idadeMinimaSessao;
	private int fileiras;
	private int colunas;
	private int [][] poltronas;
	
	private int controle;
	private boolean cntrlErro = false;
	private boolean lotacao = false;
	private int controleLotacao;
	
	public Sessoes(String nomeFilmeSessao, boolean dublado, double tarifa, String horario, String tipoSala, int fileiras, int colunas, int idadeMinimaSessao) {
		super();
		this.nomeFilmeSessao = nomeFilmeSessao;
		this.dublado = dublado;
		this.tarifa = tarifa;
		this.tipoSala = tipoSala;
		this.horario = horario;
		this.idadeMinimaSessao = idadeMinimaSessao;
		this.fileiras = fileiras;
		this.colunas = colunas;
		this.poltronas = new int [fileiras][colunas];
	}
	
	public double getTarifa() {
		return tarifa;
	}
	
	public void setTarifa(double tarifa) {
		this.tarifa = tarifa;
	}
	
	public String getNomeFilmeSessao() {
		return nomeFilmeSessao;
	}

	public void setNomeFilmeSessao(String nomeFilmeSessao) {
		this.nomeFilmeSessao = nomeFilmeSessao;
	}

	public boolean isDublado() {
		return dublado;
	}

	public void setDublado(boolean dublado) {
		this.dublado = dublado;
	}

	public String getHorario() {
		return horario;
	}

	public int getIdadeMinimaSessao() {
		return idadeMinimaSessao;
	}
	
	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getSala() {
		return tipoSala;
	}

	public void setSala(String tipoSala) {
		this.tipoSala = tipoSala;
	}
	
	public void setFileiras(int fileiras) {
		this.fileiras = fileiras;
	}

	public void setColunas(int colunas) {
		this.colunas = colunas;
	}
	
	public int getPoltronas(int i, int j) {
		return this.poltronas[i][j];
	}
	
	public int getControle() {
		return controle;
	}
	
	public int getControleLotacao () {
		return controleLotacao;
	}
	
	public void setControle(int controle) {
		this.controle = controle;
	}	
	
	public boolean isCntrlErro() {
		return cntrlErro;
	}

	public boolean isLotacao() {
		return lotacao;
	}
	
	public void setCntrlErro(boolean cntrlErro) {
		this.cntrlErro = cntrlErro;
	}

	//@SuppressWarnings("null")
	public void gerarPoltronas() {
		for (int i=0; i<poltronas.length; i++) {
			for (int j=0; j<poltronas[i].length; j++) {
				this.poltronas[i][j] = 0;
			}
		}
	}
	
	public void printPoltronas() {
		for (int i = 0; i <poltronas.length; i++) {
			for (int j = 0; j <poltronas[i].length; j++) {
				System.out.print(this.getPoltronas(i,j) + "\t");
			}
			System.out.print("\n");
		}	
	
	}
	
	public void controleLotacao() {
		controleLotacao = 0;
		for (int i=0; i<poltronas.length; i++) {
			for (int j=0; j<poltronas[i].length; j++) {
				if(poltronas[i][j] == 0) {
					controleLotacao++;
					lotacao = false;
					} else {
						lotacao = true;
					}
				}
			}
		System.out.println(controleLotacao +" Poltronas restantes.");
	}
	
	public void reservarPoltronas() {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Insira a quantidade de poltronas a serem reservadas:");
		controle = keyboard.nextInt();
		for (int k=0 ; k<controle; k++) {
		System.out.println("Escolha a posição 'FILEIRAS/COLUNAS' vazia da poltrona a ser reservada:");
		int i= keyboard.nextInt();
		int j= keyboard.nextInt();
		if (this.poltronas[i][j] != 1 && this.poltronas[i][j] != 2) {
			this.poltronas[i][j] = 3;
			cntrlErro = false;
		} else if (this.poltronas[i][j] == 2){
				System.out.println("Erro. Devido às recomendações da OMS sobre o COVID-19, deve-se manter distanciamento entre pessoas.");
				cntrlErro = true;
		} else {
				System.out.println("Erro. Essa poltrona já está reservada para outra pessoa.");
				cntrlErro = true;
				controle = 0;
			}
		}
		
		for (int m=0; m<poltronas.length; m++) {
			for (int n=0; n<poltronas[m].length; n++) {
				if(poltronas[m][n] ==3) {
					if(n>0 && poltronas[m][n-1] != 3) {
						this.poltronas[m][n-1] = 2;
					}
					if(n<poltronas[m].length-1 && poltronas[m][n+1] !=3) {
						this.poltronas[m][n+1] = 2;
					}
				}
			}
		}
		
		
		for (int m=0; m<poltronas.length; m++) {
			for (int n=0; n<poltronas[m].length; n++) {
				if(poltronas[m][n] == 3) {
					this.poltronas[m][n] = 1;
				}
			}
		}
		
	}

}
