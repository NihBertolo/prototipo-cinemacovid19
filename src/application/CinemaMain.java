package application;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;

import entities.Filmes;
import entities.Produtos;
import entities.Sala3D;
import entities.SalaStandard;
import entities.SalaVip;
import entities.Sessoes;
import entities.VendaIngresso;
import entities.Caixa;
import entities.EntradaIngressos;
import entities.EntradaProdutos;

public class CinemaMain {
	
	static ArrayList<Filmes> listaFilmes = new ArrayList<Filmes>();
	static ArrayList<Sessoes> listaSessoes = new ArrayList<Sessoes>();
	static ArrayList<Produtos> listaProdutos = new ArrayList<Produtos>();
	static ArrayList<EntradaProdutos> listaEntradas = new ArrayList<EntradaProdutos>();
	static ArrayList<EntradaIngressos> listaEntradasI = new ArrayList<EntradaIngressos>();
	
	static Scanner keyboard = new Scanner(System.in);
	static Caixa caixa = new Caixa();
	
	static int registroFilmes = 0;
	static int registroSessoes = 0;
	static double auxVenda = 0;
	static double auxVendaIngresso = 0;
	static int registroVendaProdutos = 0;
	static int registroVendaIngressos = 0;

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		
		int option;
        
        do {
			
            System.out.println("===========================================");
            System.out.println("      |       O que deseja fazer? =)      |");
            System.out.println("      |  1 - Venda de ingressos           |");
            System.out.println("      |  2 - Venda de produtos            |");
            System.out.println("      |  3 - Registros do Sistema         |");
            System.out.println("      |  4 - Exibir listas                |");
            System.out.println("      |  5 - Remover Filmes/sessões/prod  |");
            System.out.println("      |  6 - Configurações                |");
            System.out.println("      |  0 - Sair (ENCERRA PROGRAMA)      |");
            System.out.println("===========================================\n");
    	
           option = keyboard.nextInt();
            
        	switch (option) {
        		case 1:
        			double auxSaldoCaixa = 0;
        			System.out.println("### Sistema de venda de Ingressos ##");
        			keyboard.nextLine();
        			System.out.println("Digite o nome do cliente: ");
        			String nome = keyboard.nextLine();
        			System.out.println("Digite o CPF: ");
        			String cpf = keyboard.nextLine();
        			System.out.println("Digite a idade: ");
        			int idade = keyboard.nextInt();
        			VendaIngresso vIngresso = new VendaIngresso(nome,cpf,idade);
        			
        			printSessoes();
        			System.out.println("Escolha a sessão pelo ID:");
        			int selectSessao = keyboard.nextInt();
        			listaSessoes.get(selectSessao-1).controleLotacao();
        			if (listaSessoes.get(selectSessao-1).isLotacao() == true) {
        				System.out.println("Sessão lotada! Por favor escolha outra sessão.");
        			} else {
        			if (vIngresso.getIdade()<listaSessoes.get(selectSessao-1).getIdadeMinimaSessao()) {
        				System.out.println("Cliente não possuí a idade mínima para assistir ao filme.");
        			} else {
        				do {
        					listaSessoes.get(selectSessao-1).printPoltronas();
        					listaSessoes.get(selectSessao-1).reservarPoltronas();
        				} while (listaSessoes.get(selectSessao-1).isCntrlErro() == true);
        				
        				System.out.println("Poltronas registradas!");
        				listaSessoes.get(selectSessao-1).printPoltronas();
        			
        				auxVendaIngresso = listaSessoes.get(selectSessao-1).getTarifa()*listaSessoes.get(selectSessao-1).getControle();
        				
        				System.out.println("######## Ingresso de Mentirinha ;) ########");
        				System.out.println("Nome: " +vIngresso.getNome());
        				System.out.println("CPF: " +vIngresso.getCpf());
        				System.out.println("Filme: " +listaSessoes.get(selectSessao-1).getNomeFilmeSessao());
        				
        				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        				Date data = new Date();
        				Date dataOp = new Date();
        					
        				Calendar cal = Calendar.getInstance();
        				cal.set(Calendar.DAY_OF_WEEK, data.getDay());
        				data = cal.getTime();
        				
        				Calendar cal2 = Calendar.getInstance();
        				cal2.set(Calendar.DAY_OF_WEEK, data.getDay());
        				dataOp = cal2.getTime();
        				
        				System.out.println("Horário: " +sdf.format(data) +" " +listaSessoes.get(selectSessao-1).getHorario());
        				System.out.println("Poltronas reservadas: " +listaSessoes.get(selectSessao-1).getControle());
        				System.out.println("Valor a pagar R$: " +auxVendaIngresso);
        				
    					double saldo = 0;
    					Date dataEntrada = null;
    					String vendaIngressos = "";
    					
    					EntradaIngressos entradasI = new EntradaIngressos(saldo, dataEntrada, vendaIngressos);
    					
    					listaEntradasI.add(entradasI);
    					listaEntradasI.get(registroVendaIngressos).setDataOp(dataOp);
    					listaEntradasI.get(registroVendaIngressos).setVendaIngressos(listaSessoes.get(selectSessao-1).getNomeFilmeSessao() +"          " +listaSessoes.get(selectSessao-1).getControle() +"            "  +vIngresso.getNome());
    					
    					listaEntradasI.get(0).setSaldo(auxSaldoCaixa + auxVendaIngresso);
    					
    					caixa.setSaldo(caixa.getSaldo() + auxVendaIngresso);
    					
    					registroVendaIngressos++;
        			}
        			}
               		listaSessoes.get(selectSessao-1).setControle(0);
               		break;
        		case 2:
        			System.out.println("### Sistema de venda de produtos ###");
        			System.out.println("O que deseja fazer? \n 1- Vender Produto \n 2 - Adicionar Produto Estoque \n 3 - Remover Produto Estoque \n 3 - Alterar Preço");
        			int optionProduto = keyboard.nextInt();
        	
        			switch (optionProduto) {
        				case 1:
        					int optionVenda;
        					int queryVendaF = 0;
        					int idProduto = 0;
        					int qtdVendaP = 0;
        					String auxString = "";
        					String VendasP[] = new String[5];
        					double VendasPTotal[] = new double[5];
        					double somaVendas = 0;
        						do {
                					System.out.println("\n 1 - Adicionar Produto Venda \n 0- Finalizar");
                					optionVenda = keyboard.nextInt();
                					
                					switch(optionVenda) {               					
                					case 1:
                						
                						printProdutos();
                						System.out.println("Digite o código do produto a ser vendido:");
                						idProduto = keyboard.nextInt();
                						System.out.println("Digite a quantidade:"); 
                						qtdVendaP = keyboard.nextInt();
                						listaProdutos.get(idProduto-1).removeQtdEstoque(qtdVendaP);
                						double precoUn = listaProdutos.get(idProduto-1).getPreco();
                						auxVenda = precoUn*qtdVendaP;
        							
                						VendasP[queryVendaF] = listaProdutos.get(idProduto-1).getNomeProduto() +"       " +qtdVendaP;
                						VendasPTotal[queryVendaF] = auxVenda;    
                						
                  						queryVendaF++;
                						break;
                					case 0:
                    					for(int i=0; i<queryVendaF; i++) {
                    						System.out.println(VendasP[i]);
                    						auxString = auxString +"\n" + VendasP[i];
                    					}
                    					 for(int j=0; j <VendasPTotal.length; j++){
                    		                 somaVendas += VendasPTotal[j];
                    		            }
                    					System.out.println("Valor total a pagar R$: " +somaVendas);
                    					
                    					double saldo = 0;
                    					Date dataEntrada = null;
                    					String vendaProdutos = "";
                    					
                    					EntradaProdutos entrada = new EntradaProdutos(saldo, dataEntrada, vendaProdutos);
                    					
                    					listaEntradas.add(entrada);
                						Date dataOp = new Date();
                        				
                        				Calendar cal3 = Calendar.getInstance();
                        				cal3.set(Calendar.DAY_OF_WEEK, dataOp.getDay());
                        				dataOp = cal3.getTime();
                						
                						listaEntradas.get(registroVendaProdutos).setDataOp(dataOp);
                						for (int i=0; i<queryVendaF; i++) {		
                						listaEntradas.get(registroVendaProdutos).setVendaProdutos(auxString);
                						}
                						auxSaldoCaixa = listaEntradas.get(0).getSaldo();
                						listaEntradas.get(0).setSaldo(auxSaldoCaixa + somaVendas);
                						
                						caixa.setSaldo(caixa.getSaldo()+somaVendas);
                						
                						registroVendaProdutos++;
                    					queryVendaF = 0;
                    					Arrays.fill(VendasP, null);
                    					Arrays.fill(VendasPTotal, 0);
                					}
        					} while (optionVenda != 0);       					
        					break;
        				case 2:
        					System.out.println("Digite o código registrado do produto:");
        					idProduto = keyboard.nextInt();
        					System.out.println("Digite a quantidade a ser acrescentada no sistema:");
        					int qtdEntrada = keyboard.nextInt();
        					listaProdutos.get(idProduto-1).addQtdEstoque(qtdEntrada);
        					System.out.println(qtdEntrada +" produtos adicionado com sucesso! Total:" +listaProdutos.get(idProduto-1).getQtdEstoque() +listaProdutos.get(idProduto-1).getNomeProduto());
        					break;
        				case 3:
        					System.out.println("Digite o código registrado do produto:");
        					idProduto = keyboard.nextInt();
        					System.out.println("Digite a quantidade a ser removida no sistema:");
        					int qtdSaida = keyboard.nextInt();
        					listaProdutos.get(idProduto-1).removeQtdEstoque(qtdSaida);
        					System.out.println(qtdSaida +" produtos removidos com sucesso! Total:" +listaProdutos.get(idProduto-1).getQtdEstoque() +listaProdutos.get(idProduto-1).getNomeProduto());
        					break;
        				case 4:
        					System.out.println("Digite o código registrado do produto:");
        					idProduto = keyboard.nextInt();
        					System.out.println("Digite o novo preço do produto:");
        					double novoPreco = keyboard.nextDouble();
        					listaProdutos.get(idProduto-1).setPreco(novoPreco);
        					System.out.println("Preço atualizado!" +listaProdutos.get(idProduto-1).getNomeProduto() +"Valor R$: " +listaProdutos.get(idProduto-1).getPreco());
        					break;
        			}
        			break;
        		case 3:
        			System.out.println("### Configurações do programa ###");
        			System.out.println("Selecione uma opção: \n 1- Registrar filmes \n 2- Registrar Sessões \n 3- Registrar Produtos \n");
        			int optionConfig = keyboard.nextInt();
        			
        				switch (optionConfig) {
        				case 1:
        					registrarFilme();
        					break;
        				case 2:
        					registrarSessao();
        					break;
        				case 3:
        					registrarProduto();
        					break;
        				default:
        					System.out.println("Opção inválida.");
        					break;
        				}
        			break;
        		case 4:
					System.out.println("Escolha uma lista a ser exibida: \n 1- Filmes \n 2- Sessões \n 3- Produtos \n 4- Poltronas \n 5- Saldo Caixa \n 0- Retornar");
					int optionListas = keyboard.nextInt();
						switch (optionListas) {
							case 1:
								System.out.println("Filmes registrados:");
								printFilmes();
								break;
							case 2:
								System.out.println("Sessões registradas:");
								printSessoes();
								break;
							case 3:
								System.out.println("Produtos registrados:");
								printProdutos();
								break;
							case 4:
								printPoltronas();
								break;
							case 5:
								System.out.println("########## SALDO CAIXA ##########");
								System.out.println("| 1 - Consultar Saldo Atual     |");
								System.out.println("| 2 - Consultar Entradas        |");
								System.out.println("| 3 - Consultar Saídas          |");
								System.out.println("#################################");
								int optionCaixa = keyboard.nextInt();
								switch (optionCaixa) {
									case 1:
										System.out.println("Saldo:");
										System.out.println(caixa.getSaldo());
										break;
									case 2:
										System.out.println("Vendas do dia:");
										printEntradas();
										break;
									case 3:
										System.out.println("Em breve =3");
										break;
									default:
										System.out.println("Opção inválida.");
										break;
								}
								break;
							case 0:
								break;
							default:
								System.out.println("Opção inválida.");
						}
        			break;
        		case 5:
        			System.out.println("Selecione o que deseja remover: \n 1- Filmes \n 2- Sessões \n 3-Produtos");
        			int optionRemove = keyboard.nextInt();
        			switch(optionRemove) {
        				case 1:
        					removerFilme();
        					break;
        				case 2:
        					removerSessao();
        					break;
        				case 3:
        					removerProduto();
        					break;
        				default:
        					System.out.println("Opção inválida.");
        					break;
        			}
        			break;
        		case 6:
        			System.out.println("######### Configurações do Sistema #########");
        			System.out.println("1- Configurar Filmes \n 2- Configurar Sessões \n 3- Configurar Salas");
        			
        			int configOption = keyboard.nextInt();
        			
        			switch (configOption) {
        				case 1:
        					System.out.println("Configuração Filmes: \n 1- Alterar Nome \n 2- Alterar Gênero");
        					int optionFilmes = keyboard.nextInt();
        					switch (optionFilmes) {
        						case 1:
        							printFilmes();
        							System.out.println("Escolha o filme a ser alterado:");
        							int queryFilme = keyboard.nextInt();
        							System.out.println("Digite o novo nome do filme:");
        							keyboard.nextLine();
        							String nomeFilme = keyboard.nextLine();
        							listaFilmes.get(queryFilme-1).setNomeFilme(nomeFilme);
        							break;
        						case 2:
        							printFilmes();
        							System.out.println("Escolha o filme a ser alterado:");
        							queryFilme = keyboard.nextInt();
        							System.out.println("Digite o novo gênero do filme:");
        							keyboard.nextLine();
        							String genero = keyboard.nextLine();
        							listaFilmes.get(queryFilme-1).setGenero(genero);
        							break;
        						default:
        							System.out.println("Opção inválida.");
        							break;
        					}
        					break;
            			case 2:
            				System.out.println("Configuração Sessões: \n 1- Alterar Língua \n 2- Alterar horário \n 3- Alterar Valor");
            				int optionSessao = keyboard.nextInt();
            				switch (optionSessao) {
            					case 1:
            						printSessoes();
            						System.out.println("Escolha a sessão pelo ID:");
            						int querySessao = keyboard.nextInt();
            						System.out.println("A sessão será dublada (true/false)");
            						boolean dublado = keyboard.nextBoolean();
            						listaSessoes.get(querySessao-1).setDublado(dublado);
            						break;
            					case 2:
            						printSessoes();
            						System.out.println("Escolha a sessão pelo ID:");
            						querySessao = keyboard.nextInt();
            						System.out.println("Digite o novo horário da Sessão:");
            						keyboard.nextLine();
            						String horario = keyboard.nextLine();
            						listaSessoes.get(querySessao-1).setHorario(horario);
            						break;
            					case 3:
            						printSessoes();
            						System.out.println("Escolha a sessão pelo ID:");
            						querySessao = keyboard.nextInt();
            						System.out.println("Digite a nova tarifa base por minuto da sessão:");
            						double tarifa = keyboard.nextDouble();
            						listaSessoes.get(querySessao-1).setTarifa(tarifa);
            						break;
            				}
            				break;
            			case 3:
            				System.out.println("Configuração Salas: \n Alterar Fileiras/Colunas");
            				break;	
        			}
        			break;
        		case 0:
        			System.out.println("Obrigado por usar o programinha! =)");
        			break;
        		default:
        			System.out.println("Opção inválida.");
        	}
        	}  while (option != 0);
        }
	
	
	
	
	
	
	
	public static void registrarFilme () {
		
			if (registroFilmes == 4) {
				System.out.println("Limite de espaço atingido, para continuar remova um filme.");
			} else {
				System.out.println("### Registro Filmes ###");
				System.out.println(registroFilmes +"/" +4+" dos espaços disponíveis utilizados.");
				System.out.println("Digite o nome do filme:");
				keyboard.nextLine();
				String nomeFilme = keyboard.nextLine();
				System.out.println("Digite sua duração em minutos:");
				int duracao = keyboard.nextInt();
				System.out.println("Qual o gênero do filme?");
				keyboard.nextLine();
				String genero = keyboard.nextLine();
				System.out.println("Digite a idade mínima para o filme:");
				int idadeMinima = keyboard.nextInt();
				Filmes filmes = new Filmes(nomeFilme,duracao,genero,idadeMinima);
			
				System.out.println("Filme registrado!");
				filmes.printFilme();
			
				listaFilmes.add(filmes);
				registroFilmes ++;				
		}
	}
	
	public static void registrarSessao () {		
		
		if (registroSessoes == 8) {
			System.out.println("Limite de sessões atingido. Remova uma sessão para continuar.");
		} else {
			System.out.println("### Registro Sessões ###");
			System.out.println(registroSessoes + "/" +8+" dos espaços disponíveis utilizados.");
			printFilmes();
			System.out.println("Digite o ID do filme a ser exibido:");
			int idQuery = keyboard.nextInt();
			String nomeFilmeSessao = listaFilmes.get(idQuery-1).getNomeFilme();
			System.out.println("O filme será dublado? (true/false):");
			boolean dublado = keyboard.nextBoolean();
			System.out.println("Valor por minuto cobrado:");
			double tarifa = keyboard.nextDouble()*listaFilmes.get(idQuery-1).getDuracao();
			System.out.println("Escolha um horário: \n 14:00 \n 16:40 \n 19:30 \n 22:00");
			keyboard.nextLine();
			String horario = keyboard.nextLine();
			System.out.println("Escolha a sala de exibição: \n Standard \n 3D \n Vip");
			String tipoSala = keyboard.nextLine();

			
			int fileiras = 0;
			int colunas = 0;
			
			if (tipoSala.contentEquals("Standard")) {
				System.out.println("Digite a tarifa adicional da Sala:");
				double tarifaAdicional = keyboard.nextDouble();
				SalaStandard salaS = new SalaStandard (tarifaAdicional);
				tarifa += salaS.getTarifaAdicional();
				fileiras = salaS.getFileiras();
				colunas = salaS.getColunas();	
			} else if (tipoSala.contentEquals("3D")) {
				System.out.println("Digite a tarifa adicional da Sala:");
				double tarifaAdicional = keyboard.nextDouble();
				Sala3D sala3D = new Sala3D (tarifaAdicional);
				tarifa += sala3D.getTarifaAdicional();
				fileiras = sala3D.getFileiras();
				colunas = sala3D.getColunas();			
			} else if (tipoSala.contentEquals("Vip")) {
				System.out.println("Digite a tarifa adicional da Sala:");
				double tarifaAdicional = keyboard.nextDouble();
				SalaVip salaVIP = new SalaVip (tarifaAdicional);
				tarifa += salaVIP.getTarifaAdicional();
				fileiras = salaVIP.getFileiras();
				colunas = salaVIP.getColunas();	
			} else {
				System.out.println("Falha ao registrar tipo de sala, por favor tente novamente.");
			}
			
			int idadeMinimaSessao = listaFilmes.get(idQuery-1).getIdadeMinima();
			
			Sessoes sessoes = new Sessoes(nomeFilmeSessao,dublado,tarifa,horario,tipoSala,fileiras,colunas,idadeMinimaSessao);
			sessoes.gerarPoltronas();
			
			listaSessoes.add(sessoes);
			registroSessoes ++;
		}
	}
	
	public static void registrarProduto () {
		
		System.out.println("### Registro de Produtos ###");
		System.out.println("Digite o nome do produto:");
		keyboard.nextLine();
		String nomeProduto = keyboard.nextLine();
		System.out.println("Digite o tipo do produto:");
		String tipo = keyboard.nextLine();
		System.out.println("Digite o preço por unidade:");
		double preco = keyboard.nextDouble();
		System.out.println("Digite a quantidade inicial do produto no estoque:");
		int qtdEstoque = keyboard.nextInt();
		
		Produtos produtos = new Produtos(nomeProduto,tipo,preco,qtdEstoque);
		
		listaProdutos.add(produtos);			
	}
	
	public static void removerFilme () {
		Scanner keyboard = new Scanner(System.in);
		
			System.out.println("Digite o ID do filme a ser removido:");
			keyboard.nextLine();
			int idFilme = keyboard.nextInt();
		
			listaFilmes.remove(idFilme-1);	
			registroFilmes--;
		
		keyboard.close();
	}
	
	public static void removerSessao () {
		Scanner keyboard = new Scanner(System.in);
		
			System.out.println("Digite o ID da sessão a ser removida:");
			keyboard.nextLine();
			int idSessao = keyboard.nextInt();
		
			listaSessoes.remove(idSessao-1);
			registroSessoes--;
		
		keyboard.close();
	}
	
	public static void removerProduto () {
		Scanner keyboard = new Scanner(System.in);
		
			System.out.println("Digite o ID do produto a ser removido:");
			keyboard.nextLine();
			int idProduto = keyboard.nextInt();
		
			listaProdutos.remove(idProduto-1);
			
		keyboard.close();
	}
	
	public static void printFilmes () {
		System.out.println("  Nome:\t Duração:\t Gênero:\t Idade Mínima:");
		for(int i = 0;i<listaFilmes.size();i++){ 
		     System.out.println((i+1) +"\t" +listaFilmes.get(i).getNomeFilme() +"\t" +listaFilmes.get(i).getDuracao() +"\t" +listaFilmes.get(i).getGenero() +"\t" +listaFilmes.get(i).getIdadeMinima());  
		}
		
	}
	
	public static void printSessoes () {
		System.out.println("ID Sessao     Filme       Legenda  Valor Horário   Sala");
		for (int i = 0;i<listaSessoes.size();i++) {
				listaSessoes.get(i).controleLotacao();
				String auxDub = null;
				String auxLot = null;
			if (listaSessoes.get(i).isDublado() == true) {
				auxDub = "Dublado";
			} else {
				auxDub = "Legendado";
			}
			if (listaSessoes.get(i).isLotacao() == true) {
				auxLot = "Sessão Lotada";
			} else {
				auxLot = "Sessão aberta " +listaSessoes.get(i).getControleLotacao() +" Poltronas livres";
			}
			System.out.println((i+1) +"\t" +listaSessoes.get(i).getNomeFilmeSessao() +"\t" +auxDub +"\t" +listaSessoes.get(i).getTarifa() +"\t" +listaSessoes.get(i).getHorario() +"\t" +listaSessoes.get(i).getSala() +"\t" +auxLot );
		}
	}
	
	public static void printProdutos () {
		System.out.println("Código \t Nome do Produto   Tipo    Preço UN    Quantidade Estoque ");
		for (int i=0; i<listaProdutos.size(); i++) {
			System.out.println((i+1) +"\t " +listaProdutos.get(i).getNomeProduto() +"   " +listaProdutos.get(i).getTipo() +"    R$: " +listaProdutos.get(i).getPreco() +"   " +listaProdutos.get(i).getQtdEstoque());
		}
	}
	
	public static void printPoltronas () {
		printSessoes();
		System.out.println("Selecione uma sessão para exibir o mapa das poltronas:");
		int querySessao = keyboard.nextInt();
		System.out.println("Poltronas da sessão:");
		listaSessoes.get(querySessao-1).printPoltronas();
	}
	
	public static void printSaldo() {
		
	}
	
	public static void printEntradas() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		System.out.println("##### Entradas de Produtos #####");
		System.out.println("Código   Data Entrada      Venda     Quantidade");
		for (int i=0; i<listaEntradas.size(); i++) {
			System.out.println((i+1) +"\t" +sdf.format(listaEntradas.get(i).getDataOp()) +"   "  +listaEntradas.get(i).getVendaProdutos());
		}
		System.out.println("Saldo total de entradas de produtos R$:  " +listaEntradas.get(0).getSaldo());
		System.out.println("--------------------------------------");
		System.out.println("##### Entradas de Ingressos #####");
		System.out.println("Código    Data Entrada      Venda       Quant.Poltronas         Nome Cliente");
		for (int i=0; i<listaEntradasI.size(); i++) {
			System.out.println((i+1) +"      " +sdf.format(listaEntradasI.get(i).getDataOp()) +"    "   +listaEntradasI.get(i).getVendaIngressos());
		}
		System.out.println("Saldo total de entradas de ingressos R$:  " +listaEntradasI.get(0).getSaldo());
	}
	
}