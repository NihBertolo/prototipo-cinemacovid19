package entities;

public class VendaIngresso {
	private String nome,cpf;
	private int idade;
	
	
	public VendaIngresso(String nome, String cpf, int idade) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.idade = idade;
	}


	public String getCpf() {
		return cpf;
	}


	public String getNome() {
		return nome;
	}


	public int getIdade() {
		return idade;
	}
	
	
}
