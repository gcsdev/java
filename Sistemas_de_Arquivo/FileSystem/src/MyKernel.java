/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import operatingSystem.Kernel;
import utility.Atributos;

/**
 * Kernel desenvolvido pelo aluno. Outras classes criadas pelo aluno podem ser
 * utilizadas, como por exemplo: - Arvores; - Filas; - Pilhas; - etc...
 * 
 * @author Gustavo Funções a serem implementadas batch_______() dump_______ ()
 *         cat_______(ok) cd_______ (ok) chmod_______(ok) cp_______(ok)
 *         createfile(ok) info______(ok) ls _______(ok) mkdir_____(ok)
 *         mv________(ok) rm_______(0k) rmdir______(ok)
 */

public class MyKernel implements Kernel {
	HashMap<String, Atributos> hash = new HashMap<String, Atributos>();
	private String lastPath = "/";
	String comandosTexto = "criaDiretorioRaiz \n";
	ArrayList<String> comandosUtilizados = new ArrayList<String>();

	public MyKernel() {
		criaDiretorioRaiz();

	}

	public String ls(String parameters) {
		// variavel result deverah conter o que vai ser impresso na tela apos
		// comando do usuário

		String result = "";
		ArrayList<String> diretoriosInseridos = new ArrayList<String>();

		System.out.println("Chamada de Sistema: ls");
		System.out.println("\tParametros: " + parameters);
		// inicio da implementacao do aluno

		Set key = hash.keySet();
		Iterator i = key.iterator();

		// Caso o parâmetro seja nulo

		if (parameters.equals("")) {

			while (i.hasNext()) {
				String caminho = String.valueOf(i.next());
				System.out.println(caminho);
				String[] dir = caminho.split("/");
				int quantDiretorios = dir.length;
				// System.out.println(quantDiretorios);

				// Impede excessão java.lang.ArrayIndexOutOfBoundsException
				if (lastPath.equals("/")) {
					if (quantDiretorios > 0) {
						String diretorio = dir[1];
						System.out.println("Diretorios: " + diretorio);
						// Monta o resultado contendo apenas os diretorios ainda
						// não listados e diferentes do "."e ".."
						if (!diretorio.equals(".")
								&& !diretorio.equals("..")
								&& !verificaDiretoriosInseridos(diretorio,
										diretoriosInseridos)
								&& !diretorio.equals("")) {
							result = result + diretorio + "\n";
							diretoriosInseridos.add(diretorio);
						}
					}
					// System.out.println("Result:"+result);
				} else {
					// System.out.println("Entrou!!!");
					String[] dirCorrente = lastPath.split("/");
					int quantDiretoriosCorrente = dirCorrente.length;
					// Monta o resultado para listar os conteudos sem os
					// diretorios "."e ".."
					if ((quantDiretorios == dirCorrente.length + 1)
							&& (dir[dirCorrente.length - 1]
									.equals(dirCorrente[dirCorrente.length - 1]))) {
						if (!dir[quantDiretoriosCorrente].equals(".")
								&& !dir[quantDiretoriosCorrente].equals("..")) {
							result += dir[quantDiretoriosCorrente] + "\n";
						}
					}
				}
			}
			// Caso chamada contenha parametro não nulo
		} else {
			if (parameters.equals("-l")) {

				if (lastPath.equals("/")) {
					result += retornoParametroLsLRaiz();
				} else {
					result += retornoParametroLsL(lastPath);
					// result += retornoParametroLsL(lastPath+"/"+parameters);
				}
				System.out.println("Entrou ls -l");
				// caso a chamada do método contenha -l e o caminho com
				// parametro (ls -l /home/user......)
			} else {
				int contador;
				String[] separaParametros = parameters.split(" ");
				// Caso passe o parâmetro ls -l /home/dir1....
				if (separaParametros[0].equals("-l")) {
					String[] parametros = parameters.split(" ");
					result += retornoParametroLsL(parametros[1]);
					// Cso seja passado o parâmetro ls /home/dir1/dir4....
				} else {
					String caminho = retornaCaminhoAPartirParametro(parameters);
					String[] separaDiretorio = caminho.split("/");
					System.out.println("Caminho: " + caminho);

					while (i.hasNext()) {
						contador = 0;
						String chaveAtual = String.valueOf(i.next());
						String[] particionaChave = chaveAtual.split("/");

						for (int k = 1; k < separaDiretorio.length; k++) {
							if (particionaChave.length >= separaDiretorio.length) {
								if (particionaChave.length == separaDiretorio.length + 1) {
									if (separaDiretorio[k]
											.equals(particionaChave[k])) {
										contador++;
									}
								}
							}

						}
						if ((contador == separaDiretorio.length - 1)
								&& !verificaDiretoriosInseridos(
										particionaChave[particionaChave.length - 1],
										diretoriosInseridos)
								&& !particionaChave[particionaChave.length - 1]
										.equals(".")
								&& !particionaChave[particionaChave.length - 1]
										.equals("..")) {
							result = result
									+ particionaChave[particionaChave.length - 1]
									+ "\n";
							diretoriosInseridos
									.add(particionaChave[particionaChave.length - 1]);
						}

					}

				}

			}

		}
		return result;
	}

	/**
	 * Método utilizado para criar diretórios o aninhmamento de diretórios só é
	 * possível se o diretorio pai existir
	 * 
	 * @param parameters
	 *            - diretorio ou sequencia de diretórios a serem criados
	 * */

	public String mkdir(String parameters) {
		// variavel result deverah conter o que vai ser impresso na tela apos
		// comando do usuário
		retornaChave();
		boolean cont = true;
		String result = "";
		String caminhoAbsoluto = "";
		String[] diretorios = parameters.split("/");
		System.out.println("Chamada de Sistema: mkdir");
		System.out.println("\tParametros: " + parameters);

		// inicio da implementacao do aluno

		if (lastPath.equals("/")) {
			caminhoAbsoluto += lastPath + parameters;
		} else {
			caminhoAbsoluto += lastPath + "/" + parameters;
		}
		System.out.println("Caminho Absoluto" + caminhoAbsoluto);

		if (verificaCaminhoAbsoluto(caminhoAbsoluto)) {
			return "mkdir: " + diretorios[diretorios.length - 1]
					+ ": Diretório já existe(Nenhum diretório foi criado).";
		} else {

			// Verifico se deve ser criado apenas um diretório ou diretorios
			// aninhados
			if (((diretorios.length == 1) && (lastPath.equals("/")))) {
				System.out.println("Entrou1");
				Atributos arq = new Atributos(diretorios[0], "Aluno", "755",
						tempoAtual(), tempoAtual(), tempoAtual(), lastPath,
						"diretorio", "");
				hash.put(lastPath + diretorios[0], arq);
				criaDiretorioPonto(lastPath, diretorios[0]);
				comandosTexto = comandosTexto + "mkdir " + parameters + "\n";
				comandosUtilizados.add("mkdir " + parameters);
				// caminhoAbsoluto = lastPath + parameters;
			
				cont = false;
				return "";
			} else {
				// caminhoAbsoluto="/"+caminhoAbsoluto;

				if (verificaCaminhoRelativo(caminhoAbsoluto) && cont) {
					System.out.println("Entrou2");

					Atributos dir = new Atributos(
							diretorios[diretorios.length - 1], "Aluno", "755",
							tempoAtual(), tempoAtual(), tempoAtual(), lastPath,
							"diretorio", "");

					hash.put(caminhoAbsoluto, dir);
					comandosTexto = comandosTexto + "mkdir " + parameters
							+ "\n";
					comandosUtilizados.add("mkdir " + parameters);
					System.out.println("Chave passada para diretorio"
							+ caminhoAbsoluto);

					if (!lastPath.equals("/")) {
						criaDiretorioPonto(lastPath + "/", parameters);
					} else {

						criaDiretorioPonto("/", parameters);
					}

					return "";
				} else {
					System.out.println("Caminho Absoluto:" + caminhoAbsoluto);
					return "Não foi possível criar diretório: "
							+ diretorios[diretorios.length - 1];
				}
			}

		}

		// fim da implementacao do aluno

	}

	public String cd(String parameters) {
		// variavel result deverah conter o que vai ser impresso na tela apos
		// comando do usuário
		String result = "";
		String caminhoProcessado = lastPath;
		boolean existePonto = false;
		String currentDir = "";
		System.out.println("Chamada de Sistema: cd");
		System.out.println("\tParametros: " + parameters);

		// inicio da implementacao do aluno

		if (parameters.equals(".")) {
			currentDir = retornaDiretorioPonto(lastPath);
			caminhoProcessado = currentDir; // caminho processado = lastPath
											// recebe /dir/di1 ....
			if (!lastPath.equals("/")) {
				currentDir = currentDir + "/"; // monta o diretorio corrente
												// /dir/dir1/
			}
		} else {
			if (parameters.equals("..")) {
				currentDir = retornaDiretorioPontoPonto(lastPath);
				caminhoProcessado = currentDir;

				if (!lastPath.equals("/")) {
					if (!currentDir.equals("/")) {
						currentDir = currentDir + "/";
					}
				}
			} else {
				if (parameters.equals("/")) {

					if (verificaCaminhoAbsoluto(parameters)) {
						currentDir = lastPath;
						caminhoProcessado = currentDir;
					} else {
						result = "shell: " + caminhoProcessado + ": "
								+ "Diretório não existe";
						caminhoProcessado += lastPath;
					}

				} else {
					String[] parametrosPassados = parameters.split("/");
					int tam = parametrosPassados.length;
					// Verifica se existe diretorios "." e ".." no parametro
					// passado
					for (int i = 0; i < tam; i++) {
						if (parametrosPassados[i].equals(".")
								|| parametrosPassados[i].equals("..")) {
							existePonto = true;
						}
					}
					// Caso não haja "." e ".."- apenas o caminho absoluto deve
					// ser verificado
					if (!existePonto) {
						String caminhoVerificado = "";

						if (lastPath.equals("/")) {
							caminhoVerificado = lastPath + parameters;
						} else {
							caminhoVerificado = lastPath + "/" + parameters;
						}

						if (verificaCaminhoAbsoluto(caminhoVerificado)) {
							caminhoProcessado = caminhoVerificado;
							currentDir = caminhoProcessado + "/";
							// lastPath=lastPath+parameters;
						}

						// Caso haja diretórios "." e ".." aninhados (cd
						// ../home/bin/../lib )
					} else {

						for (int i = 0; i < tam; i++) {
							if (!parametrosPassados[i].equals(" ")) {
								if (parametrosPassados[i].equals(".")) {

									// caminhoProcessado=lastPath+"/"+parametrosPassados[i];
									caminhoProcessado = retornaDiretorioPonto(caminhoProcessado);
									lastPath = caminhoProcessado;

								} else {
									if (parametrosPassados[i].equals("..")) {
										caminhoProcessado = retornaDiretorioPontoPonto(caminhoProcessado);
										lastPath = caminhoProcessado;

									} else {
										if (!lastPath.equals("/")) {
											caminhoProcessado += "/"
													+ parametrosPassados[i];
											lastPath = caminhoProcessado;

										} else {
											caminhoProcessado += parametrosPassados[i];
											lastPath = caminhoProcessado;

										}

									}
								}
							}

							if (i == tam - 1) {
								if (!verificaCaminhoAbsoluto(caminhoProcessado)) {
									lastPath = caminhoProcessado;
									currentDir = caminhoProcessado + "/";
								} else {
									currentDir = caminhoProcessado + "/";
								}

							}

						}

					}

				}
			}

		}

		// indique o diretório atual. Por exemplo... /
		// currentDir = "/";

		// setando parte gráfica do diretorio atual
		operatingSystem.fileSystem.FileSytemSimulator.currentDir = currentDir;
		System.out.println("LastPath recebendo caminho processado:"
				+ caminhoProcessado);
		lastPath = caminhoProcessado;
		System.out.println("LastPath :" + lastPath);
		// fim da implementacao do aluno

		return result;
	}

	public String rmdir(String parameters) {
		// variavel result deverah conter o que vai ser impresso na tela apos
		// comando do usuário
		String chaveRemovida = "";
		String result = "";
		System.out.println("Chamada de Sistema: rmdir");
		System.out.println("\tParametros: " + parameters);

		int cont = 0;
		boolean controle = true;
		boolean condicao = false;
		retornaChave();
		// inicio da implementacao do aluno

		boolean t = String.valueOf(parameters.charAt(0)).equals("/");

		if (t) {
			String[] separaDiretorios = parameters.split("/");

			// Caso o caminho absoluto tenha sido passado /home/di1/dir2

			// verifica se é um diretorio
			if (verificaSeEDiretorio(parameters)) {
				Set key = hash.keySet(); // pegando todas as chaves da hash
				Iterator i = key.iterator();
				while (i.hasNext()) {

					cont = 0;
					String chaveCorrente = String.valueOf(i.next());
					String[] separaChaveCorrente = chaveCorrente.split("/");

					// Caso o diretorio tenha um diretorio a mais que o corrente
					if (separaDiretorios.length + 1 == separaChaveCorrente.length) {
						System.out
								.println("Analise -------------------------------------------:");
						if (!separaChaveCorrente[separaChaveCorrente.length - 1]
								.equals(".")
								&& !separaChaveCorrente[separaChaveCorrente.length - 1]
										.equals("..")) {
							for (int k = 1; k < separaDiretorios.length; k++) {
								// Contador somado caso cada uma dos caracteres
								// sejam iguais
								if (separaDiretorios[k]
										.equals(separaChaveCorrente[k])
										&& controle) {
									System.out.println("--- "
											+ separaDiretorios[k] + "---"
											+ separaChaveCorrente[k]);
									cont++;
								}
							}

							if (cont == separaDiretorios.length - 1) {
								controle = false;

							}

						} else {
							// Estratégia para não modificar o hashmap quando
							// estou percorrendo
							System.out
									.println("Entrou não possui diretorio diferente de "
											+ ". e "
											+ "..|-Condicao="
											+ condicao);
							if (controle) {
								condicao = true;
							}
						}

					}
				}
				if (controle == false && condicao == true) {
					condicao = false;
				}

				if ((cont != separaDiretorios.length - 1) && (cont != 0)
						|| (!condicao)) {
					System.out.println("Condicao: " + condicao + " Cont: "
							+ cont + " ");

					result = "bash: rmdir: "
							+ separaDiretorios[separaDiretorios.length - 1]
							+ " não está vazio";
				} else {
					chaveRemovida = parameters;
					System.out.println("Condicao: " + condicao + " Cont: "
							+ cont + " ");
					System.out.println("Entrou Remover2----" + chaveRemovida);
					hash.remove(chaveRemovida + "/..");
					hash.remove(chaveRemovida + "/.");
					hash.remove(chaveRemovida);
					comandosTexto = comandosTexto + "rmdir " + parameters
							+ "\n";
					comandosUtilizados.add("rmdir " + parameters);
				}

				// Caso não seja diretorio
			} else {
				result = "bash: rmdir"
						+ separaDiretorios[separaDiretorios.length - 1]
						+ "Diretórionão existe ";
			}

		} else {
			if (lastPath.equals("/")) {
				parameters = lastPath + parameters;
			} else {
				parameters = lastPath + "/" + parameters;
			}

			String[] separaDiretorios = parameters.split("/");
			// Caso o caminho absoluto tenha sido passado /home/di1/dir2

			// verifica se é um diretorio
			if (verificaSeEDiretorio(parameters)) {
				Set key = hash.keySet(); // pegando todas as chaves da hash
				Iterator i = key.iterator();
				while (i.hasNext()) {

					cont = 0;
					String chaveCorrente = String.valueOf(i.next());
					String[] separaChaveCorrente = chaveCorrente.split("/");

					// Caso o diretorio tenha um diretorio a mais que o corrente
					if (separaDiretorios.length + 1 == separaChaveCorrente.length) {
						System.out
								.println("Analise -------------------------------------------:");
						if (!separaChaveCorrente[separaChaveCorrente.length - 1]
								.equals(".")
								&& !separaChaveCorrente[separaChaveCorrente.length - 1]
										.equals("..")) {
							for (int k = 1; k < separaDiretorios.length; k++) {
								// Contador somado caso cada uma dos caracteres
								// sejam iguais
								if (separaDiretorios[k]
										.equals(separaChaveCorrente[k])
										&& controle) {
									System.out.println("--- "
											+ separaDiretorios[k] + "---"
											+ separaChaveCorrente[k]);
									cont++;
								}
							}

							if (cont == separaDiretorios.length - 1) {
								controle = false;

							}

						} else {
							// Estratégia para não modificar o hashmap quando
							// estou percorrendo
							System.out
									.println("Entrou não possui diretorio diferente de "
											+ ". e "
											+ "..|-Condicao="
											+ condicao);
							if (controle) {
								condicao = true;
							}
						}

					}
				}
				if (controle == false && condicao == true) {
					condicao = false;
				}

				if ((cont != separaDiretorios.length - 1) && (cont != 0)
						|| (!condicao)) {
					System.out.println("Condicao: " + condicao + " Cont: "
							+ cont + " ");

					result = "bash: rmdir: "
							+ separaDiretorios[separaDiretorios.length - 1]
							+ " não está vazio";
				} else {
					chaveRemovida = parameters;
					System.out.println("Condicao: " + condicao + " Cont: "
							+ cont + " ");
					System.out.println("Entrou Remover2----" + chaveRemovida);
					hash.remove(chaveRemovida + "/..");
					hash.remove(chaveRemovida + "/.");
					hash.remove(chaveRemovida);
					comandosTexto = comandosTexto + "rmdir " + parameters
							+ "\n";
					comandosUtilizados.add("rmdir " + parameters);
				}

				// Caso não seja diretorio
			} else {
				result = "bash: rmdir "
						+ separaDiretorios[separaDiretorios.length - 1]
						+ " Diretórionão existe ";
			}

		}

		retornaChave();

		// fim da implementacao do aluno

		return result;
	}

	public String cp(String parameters) {
		// variavel result deverah conter o que vai ser impresso na tela apos
		// comando do usuário
		String result = "";
		System.out.println("Chamada de Sistema: cp");
		System.out.println("\tParametros: " + parameters);
		String caminhoOrigem = "";
		String caminhoDestino = "";
		int cont;
		Set key = hash.keySet();
		Iterator i = key.iterator();
		// inicio da implementacao do aluno

		String[] separaParametros = parameters.split(" ");

		caminhoOrigem = separaParametros[separaParametros.length - 2];
		caminhoDestino = separaParametros[separaParametros.length - 1];

		caminhoOrigem = retornaCaminhoAPartirParametro(caminhoOrigem);
		caminhoDestino = retornaCaminhoAPartirParametro(caminhoDestino);

		String[] separaDiretorioOrigem = caminhoOrigem.split("/");
		String[] separaDiretorioDestino = caminhoDestino.split("/");

		System.out.println("Origem: " + caminhoOrigem);
		System.out.println("Destino: " + caminhoDestino);

		// verificar se possui parametro -R

		if (separaParametros.length == 3) {

			if (verificaCaminhoAbsoluto(caminhoDestino)
					&& verificaCaminhoAbsoluto(caminhoOrigem)) {
				if (verificaSeEDiretorio(caminhoOrigem)
						&& verificaSeEDiretorio(caminhoDestino)) {

					ArrayList<String> chavesCopiadas = new ArrayList<String>();

					while (i.hasNext()) {
						System.out.println("Entrou while");
						cont = 0;
						String chaveAtual = String.valueOf(i.next());
						String[] particionaChaveAtual = chaveAtual.split("/");
						// Pegar as chaves relacionadas a origem
						for (int k = 1; k < separaDiretorioOrigem.length; k++) {
							if (particionaChaveAtual.length >= separaDiretorioOrigem.length) {
								if (separaDiretorioOrigem[k]
										.equals(particionaChaveAtual[k])) {
									cont++;
								}
							}

						}

						if (cont == separaDiretorioOrigem.length - 1) {
							System.out.println("Chave Atual: " + chaveAtual);
							chavesCopiadas.add(chaveAtual);
						}
					}
					String modo = hash.get(caminhoOrigem).getModo();
					// movendo diretorio atravéz de concatenação de String
					Iterator c = chavesCopiadas.iterator();
					while (c.hasNext()) {
						String chaveCopia = String.valueOf(c.next());
						Atributos dir = new Atributos(
								separaDiretorioOrigem[separaDiretorioOrigem.length - 1],
								"Aluno", modo, tempoAtual(), tempoAtual(),
								tempoAtual(), "", "diretorio", "");
						System.out.println("Caminho de destino: "
								+ caminhoDestino);
						System.out.println("chavecópia: " + chaveCopia);

						hash.put(caminhoDestino + chaveCopia, dir);

					}
					comandosTexto = comandosTexto + "cp " + parameters + "\n";
					comandosUtilizados.add("cp " + parameters);

					// Caso tente uma operação recursiva usando diretorio
				} else {
					result = "bash: cp: Operação inválida. Tire o parâmetro -R ";
				}
				// Caso um dos dois diretórios não exixta
			} else {
				result = "bash: cp: Diretório não exixte. Nenhuma cópia foi realizada.";
			}
			// Caso não possua parâmetro -R. Válido apenas para cópia de
			// diretorios vazios.
		} else {

			if (verificaCaminhoAbsoluto(caminhoOrigem)
					&& verificaCaminhoAbsoluto(caminhoDestino)) {
				// verifica se o caminho de destino é diretorio
				if (verificaSeEDiretorio(caminhoDestino)) {
					//

					ArrayList<String> chavesCopiadas = new ArrayList<String>();

					while (i.hasNext()) {
						System.out.println("Entrou while");
						cont = 0;
						String chaveAtual = String.valueOf(i.next());
						String[] particionaChaveAtual = chaveAtual.split("/");
						// Pegar as chaves relacionadas a origem
						for (int k = 1; k < separaDiretorioOrigem.length; k++) {
							if (particionaChaveAtual.length >= separaDiretorioOrigem.length) {
								if (separaDiretorioOrigem[k]
										.equals(particionaChaveAtual[k])) {
									cont++;
								}
							}

						}

						if (cont == separaDiretorioOrigem.length - 1) {
							System.out.println("Chave Atual: " + chaveAtual);
							chavesCopiadas.add(chaveAtual);
						}
					}

					if (chavesCopiadas.size() > 3) {
						result = "bash: cp: Operação inválida utilize o parâmetro -R.";
					} else {
						String modo = hash.get(caminhoOrigem).getModo();
						Iterator c = chavesCopiadas.iterator();
						String conteudo = "";
						// verifica se é texto
						if (!verificaSeEDiretorio(caminhoOrigem)) {
							conteudo = hash.get(caminhoOrigem).getConteudo();
							while (c.hasNext()) {
								String chaveCopia = String.valueOf(c.next());
								Atributos dir = new Atributos(
										separaDiretorioOrigem[separaDiretorioOrigem.length - 1],
										"Aluno", modo, tempoAtual(),
										tempoAtual(), tempoAtual(), "",
										"texto", conteudo);
								System.out.println("Caminho de destino: "
										+ caminhoDestino);
								System.out.println("chavecópia: " + chaveCopia);
								hash.put(caminhoDestino + chaveCopia, dir);
							}
							comandosTexto = comandosTexto + "cp " + parameters
									+ "\n";
							comandosUtilizados.add("cp " + parameters);
						} else {

							while (c.hasNext()) {
								String chaveCopia = String.valueOf(c.next());
								Atributos dir = new Atributos(
										separaDiretorioOrigem[separaDiretorioOrigem.length - 1],
										"Aluno", modo, tempoAtual(),
										tempoAtual(), tempoAtual(), "",
										"diretorio", "");
								System.out.println("Caminho de destino: "
										+ caminhoDestino);
								System.out.println("chavecópia: " + chaveCopia);

								hash.put(caminhoDestino + chaveCopia, dir);
							}
							comandosTexto = comandosTexto + "cp " + parameters
									+ "\n";
							comandosUtilizados.add("cp " + parameters);
						}
					}
					// cópia para arquivo de texto é inválido
				} else {
					result = "bash: cp: Operação inválida. Nenhum arquivo foi copiado";
				}

				// caso não exista caminho absoluto associado
			} else {
				result = "bash: cp: Diretório não exixte. Nenhuma cópia foi realizada.";
			}

		}

		// fim da implementacao do aluno

		return result;
	}

	public String mv(String parameters) {
		// variavel result deverah conter o que vai ser impresso na tela apos
		// comando do usuário
		String result = "";
		System.out.println("Chamada de Sistema: mv");
		System.out.println("\tParametros: " + parameters);
		Set key = hash.keySet();
		Iterator i = key.iterator();
		int cont;
		// inicio da implementacao do aluno

		String[] separaParametros = parameters.split(" ");

		if (separaParametros.length == 2) {
			String caminhoOrigem = separaParametros[0];
			String caminhoDestino = separaParametros[1];
			caminhoOrigem = retornaCaminhoAPartirParametro(caminhoOrigem);
			caminhoDestino = retornaCaminhoAPartirParametro(caminhoDestino);
			// System.out.println("Origem: "+caminhoOrigem);
			// System.out.println("Destino: "+caminhoDestino);
			String[] separaDiretorioCaminhoOrigem = caminhoOrigem.split("/");
			String[] separaDiretorioCaminhoDestino = caminhoDestino.split("/");
			// if (verificaCaminhoAbsoluto(caminhoOrigem) &&
			// verificaCaminhoAbsoluto(caminhoDestino)){
			if (verificaCaminhoAbsoluto(caminhoDestino)) {
				// Verifica se a origem equivale a um diretorio

				// Verifica se o destino equivale a um diretorio
				if (verificaSeEDiretorio(caminhoDestino)) {
					// Array criado para armazenar as chaves que irao ser
					// movidas
					ArrayList<String> chavesMovidas = new ArrayList<String>();
					while (i.hasNext()) {
						System.out.println("Entrou while");
						cont = 0;
						String chaveAtual = String.valueOf(i.next());
						String[] particionaChaveAtual = chaveAtual.split("/");
						// Pegar as chaves relacionadas a origem
						for (int k = 1; k < separaDiretorioCaminhoOrigem.length; k++) {
							if (particionaChaveAtual.length >= separaDiretorioCaminhoOrigem.length) {
								if (separaDiretorioCaminhoOrigem[k]
										.equals(particionaChaveAtual[k])) {
									cont++;
								}
							}

						}

						if (cont == separaDiretorioCaminhoOrigem.length - 1) {
							System.out.println("Chave Atual: " + chaveAtual);
							chavesMovidas.add(chaveAtual);
						}
					}
					String modo = hash.get(caminhoOrigem).getModo();
					// movendo diretorio atravéz de concatenação de String
					Iterator c = chavesMovidas.iterator();
					while (c.hasNext()) {
						String chaveExcluida = String.valueOf(c.next());
						Atributos dir = new Atributos(
								separaDiretorioCaminhoOrigem[separaDiretorioCaminhoOrigem.length - 1],
								"Aluno", modo, tempoAtual(), tempoAtual(),
								tempoAtual(), "", "diretorio", "");
						hash.put(caminhoDestino + chaveExcluida, dir);
						hash.remove(chaveExcluida);

					}
					comandosTexto = comandosTexto + "mv " + parameters + "\n";
					comandosUtilizados.add("mv " + parameters);

					// Caso caminho de destino não seja diretorio. Neste caso só
					// é válido renomear
				} else {

					if (!verificaSeEDiretorio(caminhoOrigem)) {
						ArrayList<String> chavesMovidas = new ArrayList<String>();
						while (i.hasNext()) {
							System.out.println("Entrou while");
							cont = 0;
							String chaveAtual = String.valueOf(i.next());
							String[] particionaChaveAtual = chaveAtual
									.split("/");
							// Pegar as chaves relacionadas a origem
							for (int k = 1; k < separaDiretorioCaminhoOrigem.length; k++) {
								if (particionaChaveAtual.length >= separaDiretorioCaminhoOrigem.length) {
									if (separaDiretorioCaminhoOrigem[k]
											.equals(particionaChaveAtual[k])) {
										cont++;
									}
								}

							}

							if (cont == separaDiretorioCaminhoOrigem.length - 1) {
								System.out
										.println("Chave Atual: " + chaveAtual);
								chavesMovidas.add(chaveAtual);
							}
						}

						String novoArquivo = "/";
						for (int g = 1; g < separaDiretorioCaminhoDestino.length - 1; g++) {
							if (g != separaDiretorioCaminhoDestino.length - 2) {
								novoArquivo = novoArquivo
										+ separaDiretorioCaminhoDestino[g]
										+ "/";
							} else {
								novoArquivo = novoArquivo
										+ separaDiretorioCaminhoDestino[g];
							}
							System.out.println("Caminho sem a chave:"
									+ novoArquivo);
							String modo = hash.get(caminhoOrigem).getModo();

							// movendo diretorio atravéz de concatenação de
							// String
							Iterator c = chavesMovidas.iterator();
							while (c.hasNext()) {
								String chaveExcluida = String.valueOf(c.next());
								// Pegar conteudo do arquivo antigo
								String conteudo = hash.get(caminhoDestino)
										.getConteudo();

								Atributos dir = new Atributos(
										separaDiretorioCaminhoOrigem[separaDiretorioCaminhoOrigem.length - 1],
										"Aluno", modo, tempoAtual(),
										tempoAtual(), tempoAtual(), "",
										"texto", conteudo);
								hash.put(novoArquivo + chaveExcluida, dir);
								hash.remove(chaveExcluida);
								hash.remove(caminhoDestino);
							}
							comandosTexto = comandosTexto + "mv " + parameters
									+ "\n";
							comandosUtilizados.add("mv " + parameters);

						}

					} else {

						result = "bash: mv: [diretorio] [diretorio] || [arquivo texto] [diretorio] || [arquivo texto][arquivo texto]";
					}

				}

			} else {
				result = "bash: mv: Nenhum arquivo foi movido. Forneça um caminho válido.";
			}

		} else {
			result = "bash: mv: mv [origem] [destino]. Nenhum arquivo foi movido";

		}

		// fim da implementacao do aluno

		return result;
	}

	public String rm(String parameters) {
		// variavel result deverah conter o que vai ser impresso na tela apos
		// comando do usuário
		String result = "";
		System.out.println("Chamada de Sistema: rm");
		System.out.println("\tParametros: " + parameters);
		Set key = hash.keySet();
		Iterator i = key.iterator();
		// inicio da implementacao do aluno

		String[] separaParametros = parameters.split(" ");
		String diretorio = "";

		if (separaParametros.length == 2) {
			diretorio = retornaCaminhoAPartirParametro(separaParametros[1]);
		} else {
			diretorio = retornaCaminhoAPartirParametro(separaParametros[separaParametros.length - 1]);
		}

		System.out.println("Diretorio: " + diretorio);
		String[] separaDiretorio = diretorio.split("/");

		if (separaParametros.length == 2) {
			if (verificaCaminhoAbsoluto(diretorio)) {
				if (verificaSeEDiretorio(diretorio)) {
					ArrayList<String> chavesExcluidas = new ArrayList<String>();

					while (i.hasNext()) {
						int cont = 0;
						String chaveAtual = String.valueOf(i.next());
						String[] particionaChaveAtual = chaveAtual.split("/");

						for (int k = 1; k < separaDiretorio.length; k++) {
							if (particionaChaveAtual.length >= separaDiretorio.length) {
								if (separaDiretorio[k]
										.equals(particionaChaveAtual[k])) {
									cont++;
								}
							}
						}
						if (cont == separaDiretorio.length - 1) {
							chavesExcluidas.add(chaveAtual);
						}
					}

					Iterator c = chavesExcluidas.iterator();

					while (c.hasNext()) {
						hash.remove(c.next());

					}
					comandosTexto = comandosTexto + "rm " + parameters + "\n";
					comandosUtilizados.add("rm " + parameters);

				} else {
					result = "bash: rm: "
							+ separaDiretorio[separaDiretorio.length - 1]
							+ ": Retire o parâmetro -R. Neunhum diretório excluído.";
				}

			} else {
				result = "bash: rm: "
						+ separaDiretorio[separaDiretorio.length - 1]
						+ "Diretório não existe";
			}

			// Caso tenha apenas o caminho
		} else {
			String chaveRetirada = "";
			boolean condicao = false;
			// Caso exista o arquivo requisitado
			if (verificaCaminhoAbsoluto(diretorio)) {
				if (!verificaSeEDiretorio(diretorio)) {
					while (i.hasNext()) {
						String chaveAtual = String.valueOf(i.next());
						if (diretorio.equals(chaveAtual)) {
							condicao = true;
							chaveRetirada = chaveAtual;
						}
					}

					// caso seja um diretorio
				} else {
					result = "bash: rm: "
							+ separaDiretorio[separaDiretorio.length - 1]
							+ ": Use parâmetro -R. Nenhum diretório foi excluído.";
				}
				// Caso não exista arquivo
			} else {
				result = "bash: rm: "
						+ separaDiretorio[separaDiretorio.length - 1]
						+ ": Arquivo não existe.";
			}

			hash.remove(chaveRetirada);
			comandosTexto = comandosTexto + "rm " + parameters + "\n";
			comandosUtilizados.add("rm " + parameters);

		}

		// fim da implementacao do aluno

		return result;
	}

	public String chmod(String parameters) {
		// variavel result deverah conter o que vai ser impresso na tela apos
		// comando do usuário
		String result = "";
		System.out.println("Chamada de Sistema: chmod");
		System.out.println("\tParametros: " + parameters);
		Set key = hash.keySet(); // pegando todas as chaves da hash
		Iterator i = key.iterator();

		// inicio da implementacao do aluno

		String[] separaParametros = parameters.split(" ");
		int quantParametros = separaParametros.length;

		String caminhoModificado = "";

		// Aruma o diretorio Absoluto
		if (String.valueOf(separaParametros[quantParametros - 1].charAt(0))
				.equals(".")) {
			caminhoModificado = separaParametros[quantParametros - 1]
					.substring(1,
							separaParametros[quantParametros - 1].length());
			System.out.println(caminhoModificado);
		} else {
			if (String.valueOf(separaParametros[quantParametros - 1].charAt(0))
					.equals("/")) {
				caminhoModificado = separaParametros[quantParametros - 1];
				System.out.println(caminhoModificado);
			} else {
				if (lastPath.equals("/")) {
					caminhoModificado = lastPath
							+ separaParametros[quantParametros - 1];
					System.out.println(caminhoModificado);
				} else {
					caminhoModificado = lastPath + "/"
							+ separaParametros[quantParametros - 1];
					System.out.println(caminhoModificado);
				}
			}
		}

		String[] separaDiretorios = caminhoModificado.split("/");
		String caminhoIncrementado = caminhoModificado;
		// Verificar se exixte caminho absoluto
		if (verificaCaminhoAbsoluto(caminhoModificado)) {
			// Veirificar se é possui parametro -R
			if (quantParametros == 3) {
				if (separaParametros[separaParametros.length - 3].equals("-R")) {
					System.out.println("Possui parâmetro : -R");
					if (verificaSeEDiretorio(caminhoModificado)) {

						while (i.hasNext()) {
							int cont = 0;
							String chaveAtual = String.valueOf(i.next());
							String[] particionaChaveAtual = chaveAtual
									.split("/");
							System.out.println("Tamanho :"
									+ separaDiretorios.length);
							System.out.println("Tamanho :"
									+ particionaChaveAtual.length);
							for (int k = 1; k < separaDiretorios.length; k++) {
								if (particionaChaveAtual.length >= separaDiretorios.length) {
									if (separaDiretorios[k]
											.equals(particionaChaveAtual[k])) {
										cont++;
									}
								}
							}
							if (cont == separaDiretorios.length - 1) {
								hash.get(chaveAtual)
										.setModo(
												separaParametros[separaParametros.length - 2]);
								comandosTexto = comandosTexto + "chmod "
										+ parameters + "\n";
								comandosUtilizados.add("chmod " + parameters);
							}
						}
					} else {
						result = "bash: chmod :"
								+ separaDiretorios[separaDiretorios.length - 1]
								+ " Diretório não existe";
					}
					// Caso possua apenas dois parâmetros
				} else {
					result = "bash: chmod : Parâmetro passado incorretamente";
				}
			} else {
				while (i.hasNext()) {
					String chaveAtual = String.valueOf(i.next());
					System.out.println("Chave Atual: " + chaveAtual);
					if (caminhoModificado.equals(chaveAtual)
							|| (caminhoModificado + "/.").equals(chaveAtual)
							|| (caminhoModificado + "/..").equals(chaveAtual)) {
						System.out.println("Deverá ser modificado para: "
								+ separaParametros[0]);
						hash.get(chaveAtual).setModo(separaParametros[0]);
						comandosTexto = comandosTexto + "chmod " + parameters
								+ "\n";
						comandosUtilizados.add("chmod " + parameters);
						System.out.println(" realizou while");
					}

				}

			}

		} else {
			result = " bash: chmod: "
					+ separaDiretorios[separaDiretorios.length - 1]
					+ " Arquivo não exixte ";
		}

		// fim da implementacao do aluno

		return result;
	}

	public String retornaCaminhoAPartirParametro(String caminho) {
		System.out.println("Entrou retorna :" + caminho);
		String caminhoModificado = "";
		// caso o caminho comece por .
		if (String.valueOf(caminho.charAt(0)).equals(".")) {

			// caso o caminho inicie com ".."
			if (String.valueOf(caminho.charAt(1)).equals(".")) {

				caminhoModificado = retornaDiretorioPontoPonto(lastPath);
				if (!lastPath.equals("/")) {
					caminhoModificado = caminhoModificado
							+ caminho.substring(3);
				}

				// caso o caminho inicie com "."

			} else {
				caminhoModificado = caminho.substring(1, caminho.length());
			}
		} else {
			// caso o caminho comece por /
			if (String.valueOf(caminho.charAt(0)).equals("/")) {
				caminhoModificado = caminho;
				// caso o caminho comece por..
			} else {

				if (lastPath.equals("/")) {
					caminhoModificado = lastPath + caminho;

				} else {
					caminhoModificado = lastPath + "/" + caminho;

				}
			}

		}

		return caminhoModificado;

	}

	public String createfile(String parameters) {
		// variavel result deverah conter o que vai ser impresso na tela apos
		// comando do usuário
		String result = "";
		System.out.println("Chamada de Sistema: createfile");
		System.out.println("\tParametros: " + parameters);

		// inicio da implementacao do aluno

		String[] separaParametros = parameters.split(" ", 2); // separa caminho
																// do conteudo
																// do texto

		String caminho = separaParametros[0];
		String conteudoArqTexto = separaParametros[1];

		System.out.println(caminho);
		System.out.println(conteudoArqTexto);

		String[] seqDiretorios = caminho.split("/");

		String novoCaminho = "";

		if (seqDiretorios[0].equals(".")) {
			if (lastPath.equals("/")) {
				novoCaminho = lastPath;
			} else {
				novoCaminho = lastPath + "/";
			}

			// monta caminho sem ./
			for (int i = 1; i < seqDiretorios.length; i++) {
				if (i != seqDiretorios.length - 1) {
					novoCaminho = novoCaminho + seqDiretorios[i] + "/";
				} else {
					novoCaminho = novoCaminho + seqDiretorios[i];
				}
				// verifica se existe este arquivo e os diretorios anteriores,
				// caso esteja aninhado
				if (!verificaCaminhoAbsoluto(novoCaminho)) {
					if (verificaCaminhoRelativo(novoCaminho)) {
						Atributos arq = new Atributos(
								seqDiretorios[seqDiretorios.length - 1],
								"Aluno", "755", tempoAtual(), tempoAtual(),
								tempoAtual(), "", "texto", conteudoArqTexto);
						System.out.println("Caminho inserido" + novoCaminho);
						hash.put(novoCaminho, arq);
						comandosTexto = comandosTexto + "createfile "
								+ parameters + "\n";
						comandosUtilizados.add("createfile " + parameters);
					} else {
						result = "createfile: "
								+ seqDiretorios[seqDiretorios.length - 1]
								+ ": Nenhum arquivo foi criado";
					}

				} else {
					result = "cretefile:"
							+ seqDiretorios[seqDiretorios.length - 1]
							+ ": Arquivo já existe (Nenhum arquivo foi criado).";
				}
			}

		} else {
			if (seqDiretorios[0].equals("")) {
				novoCaminho = "/";

				for (int i = 1; i < seqDiretorios.length; i++) {
					if (i == seqDiretorios.length - 1) {
						novoCaminho = novoCaminho + seqDiretorios[i];
					} else {
						novoCaminho = novoCaminho + seqDiretorios[i] + "/";
					}
				}

				System.out.println("NovoCaminho" + novoCaminho);
				if (!verificaCaminhoAbsoluto(novoCaminho)) {
					if (verificaCaminhoRelativo(novoCaminho)) {
						Atributos arq = new Atributos(
								seqDiretorios[seqDiretorios.length - 1],
								"Aluno", "755", tempoAtual(), tempoAtual(),
								tempoAtual(), "", "texto", conteudoArqTexto);
						hash.put(novoCaminho, arq);
						comandosTexto = comandosTexto + "createfile "
								+ parameters + "\n";
						comandosUtilizados.add("createfile " + parameters);
					} else {
						result = "createfile: "
								+ seqDiretorios[seqDiretorios.length - 1]
								+ ": Nenhum arquivo foi criado";
					}

				} else {
					result = "cretefile:"
							+ seqDiretorios[seqDiretorios.length - 1]
							+ ": Arquivo já existe (Nenhum arquivo foi criado).";
				}

			}

		}

		// fim da implementacao do aluno

		return result;
	}

	public String cat(String parameters) {
		// variavel result deverah conter o que vai ser impresso na tela apos
		// comando do usuário
		String result = "";
		System.out.println("Chamada de Sistema: cat");
		System.out.println("\tParametros: " + parameters);

		// inicio da implementacao do aluno

		String[] separaDiretorios = parameters.split("/");

		if (separaDiretorios[0].equals("")) {
			// verificando se existe arquivo
			if (verificaCaminhoAbsoluto(parameters)) {
				if (!verificaSeEDiretorio(parameters)) {
					result = hash.get(parameters).getConteudo();
				}

			} else {
				result = "bash:"
						+ separaDiretorios[separaDiretorios.length - 1]
						+ ": Arquivo não existe.";
			}

		} else {
			if (separaDiretorios[0].equals(".")) {

			} else {
				String caminho = "";
				if (lastPath.equals("/")) {
					caminho = lastPath + parameters;
				} else {
					caminho = lastPath + "/" + parameters;
				}
				// verificar se existe arquivo
				if (verificaCaminhoAbsoluto(caminho)) {
					if (!verificaSeEDiretorio(caminho)) {
						result = hash.get(caminho).getConteudo();
					} else {
						result = "bash:"
								+ separaDiretorios[separaDiretorios.length - 1]
								+ ": Arquivo não existe.";
					}
				} else {
					result = "bash:"
							+ separaDiretorios[separaDiretorios.length - 1]
							+ ": Arquivo não existe.";
				}

			}

		}

		// fim da implementacao do aluno

		return result;
	}

	public String batch(String parameters) {
		// variavel result deverah conter o que vai ser impresso na tela apos
		// comando do usuário
		String result = "";
		System.out.println("Chamada de Sistema: batch");
		System.out.println("\tParametros: " + parameters);
		FileReader reader;
		// inicio da implementacao do aluno
		try {
			reader = new FileReader(new File(
					"C:\\Users\\StackHolder\\Desktop\\" + parameters));
			BufferedReader leitor = new BufferedReader(reader);
			String linha = "";
			try {
				while ((linha = leitor.readLine()) != null) {
					String[] separaParametros = linha.split(" ", 2);

					switch (separaParametros[0]) {

					case "mkdir":
						mkdir(separaParametros[1]);
						break;
					case "rmdir":
						rmdir(separaParametros[1]);
						break;
					case "rm":
						rm(separaParametros[1]);
						break;
					case "mv":
						mv(separaParametros[1]);
						break;
					case "createfile":
						createfile(separaParametros[1]);
						break;
					case "cp":
						cp(separaParametros[1]);
						break;
					case "chmod":
						chmod(separaParametros[1]);
						break;
					case "criaDiretorioRaiz":
						criaDiretorioRaiz();
						break;

					}
					// mkdir (separaParametros[1]);
					System.out.println(linha);

				}
				leitor.close();
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// fim da implementacao do aluno

		return result;
	}

	public String dump(String parameters) {
		// variavel result deverah conter o que vai ser impresso na tela apos
		// comando do usuário
		String result = "";
		System.out.println("Chamada de Sistema: dump");
		System.out.println("\tParametros: " + parameters);
		FileWriter writer;
		// inicio da implementacao do aluno

		try {
			writer = new FileWriter(new File(
					"C:\\Users\\StackHolder\\Desktop\\" + parameters), false);
			PrintWriter saida = new PrintWriter(writer);

			String texto = "";

			Iterator i = comandosUtilizados.iterator();
			while (i.hasNext()) {
				texto = texto + String.valueOf(i.next()) + "\n";
			}
			// saida.println(texto);
			saida.println(comandosTexto);
			saida.close();
			writer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// fim da implementacao do aluno

		return result;
	}

	public String info() {
		// variavel result deverah conter o que vai ser impresso na tela apos
		// comando do usuário
		String result = "";
		System.out.println("Chamada de Sistema: info");
		System.out.println("\tParametros: sem parametros");

		// nome do aluno
		String name = "Gustavo Souza";
		// numero de matricula
		String registration = "2011.1.08.041";
		// versao do sistema de arquivos
		String version = "0.1";

		result += "Nome do Aluno:        " + name;
		result += "\nMatricula do Aluno:   " + registration;
		result += "\nVersao do Kernel:     " + version;

		return result;
	}

	public String tempoAtual() {

		SimpleDateFormat tempoAtual = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		return tempoAtual.format(new Date());
	}

	/**
	 * Método utilizado para criar diretorio . e diretorio ..
	 * 
	 * @param caminho
	 *            - caminho absoluto
	 * @param novoDiretorio
	 *            - diretorio a ser criado
	 */

	public void criaDiretorioPonto(String caminho, String novoDiretorio) {
		Atributos arq1 = new Atributos(".", "Aluno", "755", tempoAtual(),
				tempoAtual(), tempoAtual(), caminho + novoDiretorio,
				"diretorio", "");
		hash.put(caminho + novoDiretorio + "/.", arq1);
		Atributos arq2 = new Atributos("..", "Aluno", "755", tempoAtual(),
				tempoAtual(), tempoAtual(), caminho + novoDiretorio,
				"diretorio", "");
		hash.put(caminho + novoDiretorio + "/..", arq1);
	}

	/**
	 * Método utilizado para verificar se já existe um caminho absoluto
	 * 
	 * @param caminhoAbsoluto
	 *            - caminho absoluto a ser analizado
	 * @return existeCaminho - "true" caso haja um caminho absoluto associado -
	 *         false do contrário
	 * */

	public boolean verificaCaminhoAbsoluto(String caminhoAbsoluto) {

		boolean existeCaminho = false;
		Set caminhos = hash.keySet();
		Iterator i = caminhos.iterator();

		while (i.hasNext()) {
			if (i.next().equals(caminhoAbsoluto)) {
				existeCaminho = true;
			}
		}

		System.out.println("Veirficando se exixte" + caminhoAbsoluto
				+ "Retorno" + existeCaminho);
		return existeCaminho;
	}

	/** Método utilizado para criar o diretorio Raiz "/" */
	public void criaDiretorioRaiz() {
		Atributos dir = new Atributos("/", "Aluno", "755", tempoAtual(),
				tempoAtual(), tempoAtual(), "", "diretorio", "");
		hash.put("/", dir);
		criaDiretorioPonto("", "");
	}

	/**
	 * Método utilizado para verificar a exixtencia de outros diretorios
	 * passados como parâmetro no comando mkdir*
	 * 
	 * @param caminhos
	 *            - string contendo o caminho absoluto
	 * @return caminhoExistente - "true" caso exista o diretorio,"false" caso
	 *         contrário
	 * */

	public boolean verificaCaminhoRelativo(String caminhos) {
		boolean caminhoExistente = false;
		String[] diretorio = caminhos.split("/");
		int numeroDiretorios = diretorio.length;

		System.out.println("Caminho passado" + caminhos);
		System.out.println("Qunatidade de diretorio: " + numeroDiretorios);

		String caminhoRelativo = "/" + diretorio[1];

		System.out.println("Caminho Relativo Inicial:" + caminhoRelativo);

		int cont = 0;
		for (int i = 2; i < numeroDiretorios; i++) {
			System.out.println(caminhoRelativo);
			Set key = hash.keySet();
			Iterator it = key.iterator();

			while (it.hasNext()) {
				if (it.next().equals(caminhoRelativo)) {
					cont++;
				}

			}
			caminhoRelativo += "/" + diretorio[i];
		}
		if (cont == numeroDiretorios - 2) {
			caminhoExistente = true;
		}

		System.out.println(caminhoExistente);
		return caminhoExistente;

	}

	public boolean verificaDiretoriosInseridos(String diretorio,
			ArrayList<String> array) {

		int cont = 0;
		for (int i = 0; i < array.size(); i++) {
			if (diretorio.equals(array.get(i))) {
				cont++;
			}
		}
		if (cont == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Método utilizado para gerar listagem de diretórios (ls -l) a partir de
	 * diretorios acima da raiz
	 * 
	 * @param caminho
	 *            - caminho absoluto
	 * */

	public String retornoParametroLsL(String caminho) {
		String dirOuArquivo = "";
		String lista = "";
		String[] diretorioUnico = caminho.split("/");
		int quantDiretoriosCorrente = diretorioUnico.length;
		Set key = hash.keySet();
		Iterator i = key.iterator();

		while (i.hasNext()) {
			String keyHash = String.valueOf(i.next());
			String[] dirChave = keyHash.split("/");
			int quantDirChave = dirChave.length;

			if (!verificaSeEDiretorio(keyHash)) {
				dirOuArquivo = "";
				dirOuArquivo = dirOuArquivo + "-";
			} else {
				dirOuArquivo = "";
				dirOuArquivo = dirOuArquivo + "d";
			}

			if ((dirChave.length == diretorioUnico.length + 1)
					&& (dirChave[diretorioUnico.length - 1]
							.equals(diretorioUnico[diretorioUnico.length - 1]))) {
				System.out.println(quantDirChave);
				System.out.println(quantDiretoriosCorrente);

				if ((!dirChave[quantDiretoriosCorrente].equals("."))
						&& (!dirChave[quantDiretoriosCorrente].equals(".."))
						&& (!dirChave[quantDiretoriosCorrente].equals(""))) {
					System.out.println("Modo"
							+ String.valueOf(hash.get(keyHash).getModo()));
					lista = lista + dirOuArquivo;
					lista = lista
							+ geraListaPermissoes(String.valueOf(hash.get(
									keyHash).getModo()))
							+ " "
							+ String.valueOf(hash.get(keyHash).getDataCriacao())
							+ " " + dirChave[quantDiretoriosCorrente] + "\n";
				}
			}

		}

		return lista;

	}

	/**
	 * Método utilizado para retornar listagem de diretórios comparâmetro -l
	 * apartir do diretorio raiz ls -l /......
	 * */

	public String retornoParametroLsLRaiz() {
		String lista = "";
		Set key = hash.keySet();
		Iterator i = key.iterator();
		String dirOuArquivo = "d";
		ArrayList<String> diretoriosEscolhidos = new ArrayList<String>();

		while (i.hasNext()) {

			String dirs = String.valueOf(i.next());
			String[] diretorios = dirs.split("/");
			int quantDiretorios = diretorios.length;
			System.out.println("Numero de diretorios" + quantDiretorios);

			if (!verificaSeEDiretorio(dirs)) {
				dirOuArquivo = "-";
				System.out
						.println("Entrou##################################################################");
			}

			if (quantDiretorios > 0) {
				String diretorioEscolhido = diretorios[1];

				if (!verificaDiretoriosInseridos(diretorioEscolhido,
						diretoriosEscolhidos)
						&& !diretorioEscolhido.equals(".")
						&& !diretorioEscolhido.equals("..")
						&& !diretorioEscolhido.equals("")) {
					System.out
							.println(String.valueOf(hash.get(dirs).getModo()));
					lista = lista + dirOuArquivo;
					lista = lista
							+ geraListaPermissoes(String.valueOf(hash.get(dirs)
									.getModo())) + " "
							+ String.valueOf(hash.get(dirs).getDataCriacao())
							+ " " + diretorios[1] + "\n";
					diretoriosEscolhidos.add(diretorios[1]);
				}
			}
		}

		return lista;

	}

	/**
	 * Método utilizado para retornar o diretório ponto do diretório corrente
	 * 
	 * @param caminhoAbsoluto
	 *            - caminho contendo o diretorio corrente
	 * @return caminhoAbsolutoAbaixo diretorio ponto equivalente
	 * */

	public String retornaDiretorioPontoPonto(String caminhoAbsoluto) {
		String caminhoAbsolutoAbaixo = "/";

		if (caminhoAbsoluto.equals("/")) {
			caminhoAbsolutoAbaixo = "/";
		} else {
			String[] diretorio = caminhoAbsoluto.split("/");
			int tam = diretorio.length;
			for (int i = 1; i < tam; i++) {
				if (i == tam - 2) {
					caminhoAbsolutoAbaixo += diretorio[i];
				} else {
					if (i != tam - 1) {
						caminhoAbsolutoAbaixo += diretorio[i] + "/";
					}
				}
			}
		}

		System.out.println(caminhoAbsolutoAbaixo);
		return caminhoAbsolutoAbaixo;
	}

	public String retornaDiretorioPonto(String caminho) {
		if (caminho.equals("/")) {
			return caminho;
		} else {
			return caminho;
		}

	}

	/**
	 * Método utilizado para verificar se o arquivo é diretório ou simplesmente
	 * arquivos de texto
	 * 
	 * @param caminho
	 *            - passa o caminho absoluto mais o nome do arquivo
	 * @return true -caso seja diretorio e false caso contrário
	 * */

	public boolean verificaSeEDiretorio(String caminho) {
		boolean eDiretorio = false;
		Set chaves = hash.keySet();
		Iterator i = chaves.iterator();

		while (i.hasNext()) {
			String chaveAtual = String.valueOf(i.next());
			if (caminho.equals(chaveAtual)) {
				if (String.valueOf(hash.get(chaveAtual).getTipo()).equals(
						"diretorio")) {
					eDiretorio = true;
				}
			}

		}
		return eDiretorio;

	}

	/**
	 * Método utilizado para gerar lista de permissões (Leitura,
	 * Escrita,Execução) a partir dos parâmetros de numeração passado
	 * 
	 * @param num
	 *            - numeração indicativa de permissão
	 * @return listaPermissao - formato de permissões para Root/Usuário/Grupo
	 * */

	public String geraListaPermissoes(String num) {
		String listaPermissao = "";

		for (int i = 0; i < 3; i++) {
			System.out.println("Entrou montagem de permissoes"
					+ String.valueOf(num.charAt(i)));
			switch (String.valueOf(num.charAt(i))) {
			case "0":
				listaPermissao += "---";
				break;
			case "1":
				listaPermissao += "--x";
				break;
			case "2":
				listaPermissao += "-w-";
				break;
			case "3":
				listaPermissao += "-wx";
				break;
			case "4":
				listaPermissao += "r--";
				break;
			case "5":
				listaPermissao += "r-x";
				break;
			case "6":
				listaPermissao += "rw-";
				break;
			case "7":
				listaPermissao += "rwx";
				break;
			}
		}

		System.out.println("Lista de Permissões: " + listaPermissao);
		return listaPermissao;

	}

	public void retornaChave() {
		Set key = hash.keySet();
		Iterator i = key.iterator();
		System.out.println("----------------");
		while (i.hasNext()) {
			System.out.println((i.next()));

		}
		System.out.println("----------------");

	}

}
