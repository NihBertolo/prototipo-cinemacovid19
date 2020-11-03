package entities;

import java.util.ArrayList;

public class Filmes {
	private String nomeFilme;
	protected int duracao;
	private String genero;
	private int idadeMinima;
	
	
	ArrayList<Filmes> listFilmes = new ArrayList<>();
	
	
	public Filmes(String nomeFilme, int duracao, String genero, int idadeMinima) {
		this.nomeFilme = nomeFilme;
		this.duracao = duracao;
		this.genero = genero;
		this.idadeMinima = idadeMinima;
	}


	public String getNomeFilme() {
		return nomeFilme;
	}


	public void setNomeFilme(String nomeFilme) {
		this.nomeFilme = nomeFilme;
	}


	public double getDuracao() {
		return duracao;
	}


	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}


	public int getIdadeMinima() {
		return idadeMinima;
	}


	public void setIdadeMinima(int idadeMinima) {
		this.idadeMinima = idadeMinima;
	}
	
	
	public void printFilme() {
		System.out.println("Nome: " +nomeFilme);
		System.out.println("Duração: " +duracao);
		System.out.println("Gênero: " +genero);
		System.out.println("Idade Mínima: " +idadeMinima);
	}
	
}
