/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import controle.RetornoMinimax;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;

/**
 *
 * @author acer note
 */
public class No {

    private String[] esquerda = {"9", "18", "27", "36"};
    private String[] direita = {"1", "10", "19", "28"};
    private String[] baixo = {"1", "2", "3", "4"};
    private String[] acima = {"33", "34", "35", "36"};
    // Movimento mov = new Movimento();
    int posicao[][] = new int[8][8];
    boolean ehMax;
    ArrayList<No> filho = new ArrayList<>();
    //  No prox;
    private int possibilidadePecasJogadas = 0;

    public No constroiArvore(int tab[][], int profundidade) {
        No T = new No();
        /*
         *  Cria a raiz da arvore e inicializa 
         */

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                T.posicao[i][j] = tab[i][j];

            }
        }
        T.ehMax = true;
        T.filho = null;
        expandeArvore(T, 0, profundidade);
       
        return T;
    }//constroiArvore

    private void expandeArvore(No T, int nivel, int profundidade) {
        No q;
       
        if (nivel < profundidade) {

            q = geraFilhos(T);
            T.filho = q.filho;
            
            

            int k = 0;
            while (k < q.filho.size() - 1) {
                if (T.ehMax) {
                    q.filho.get(k).ehMax = false;
                } else {
                    q.filho.get(k).ehMax = true;
                }
                q.filho.get(k).filho = null;
                //q.filho.remove(k);
                expandeArvore(q.filho.get(k), nivel + 1, profundidade);
                // q = q.filho.get(++k);
                k++;
            }
        }
    }//expandeArvore

    private No geraFilhos(No T) {
        No gFilhos = new No();
        // System.out.println("QUANTIDADE DE VALORES T =" + T.posicao.length);
        int tabAtual[][] = new int[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tabAtual[i][j] = T.posicao[i][j];
                //    System.out.println("Gera filhos posicao:"+i+", "+j +" "+ tabAtual[i][j]);
            }
        }

        //verificar possiveis filhos
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String indiceJogada = ConverteMatrizEmIndice(null, i + "," + j);
                if (!indiceJogada.equals("100")) {
                    if (tabAtual[i][j] == -1 || tabAtual[i][j] == -11) {
                        ArrayList<int[][]> aux = verificaAdjacentes(tabAtual[i][j], tabAtual, indiceJogada);
                        //  System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
                        //         System.out.println(aux.size());
                        for (int k = 0; k < aux.size(); k++) {
                            No novoNo = new No();
                                                   
                            novoNo.posicao = aux.get(k);
                            gFilhos.filho.add(novoNo);
    

                            //      System.out.println("mais um filho veio da posicao i = "+i+", j = "+j);
                        }//for
                    }
                }
            }//for i
        }//for j
        //    System.out.println(gFilhos.filho.size()+"<<<FILHOS");
        //gFilhos.filho.add();       
        return gFilhos;
    }//geraFilhos

    RetornoMinimax minMax(No T, int jogador, No melhor, int valor) throws CloneNotSupportedException {
        No auxMelhor = null;
        boolean paiMax;
        int val = 0;

        if (T.filho == null || T.filho.size() == 0) {//T é folha
            //      System.out.println("ENTROU MINMAX");
            valor = avaliacaoEstatica(T.posicao, jogador);
//            System.out.println("valor filho null "+valor);
            melhor = T;

        } else {
            int k = 0;
        //       System.out.println("K---> " + k);
            //   System.out.println(T.filho.size());
            //  p = T.filho.get(k);
            paiMax = T.ehMax;

          //  System.out.println("É MAX ? = " + T.ehMax);

            //primeira    
//            System.out.println("t.filho ");
            RetornoMinimax m = minMax(T.filho.get(k), jogador, melhor, valor);
            valor = m.value;
            melhor = m.AUXILIAR;


            melhor = T.filho.get(k);
            //    System.out.println("");
            //p = T.filho.get(++k);
            k++;
            //p = T.filho.get(k);
         //   System.out.println("t filhos" + T.filho.size());
            while (k < T.filho.size()) {
              //  System.out.println("k--> " + k);
                m = minMax(T.filho.get(k), jogador, auxMelhor, val);
                auxMelhor = m.AUXILIAR;
                val = m.value;
                // System.out.println("auxMelhor "+ auxMelhor.posicao[3][2]);
                // System.out.println("valll "+ val);
                if (paiMax) {
                    if (val > valor) {
                        valor = val;
                        melhor = T.filho.get(k);
                    }
                } else {
                    if (valor > val) {
                        valor = val;
                        melhor = T.filho.get(k);
                    }
                }
                k++;
            }

        }
        RetornoMinimax retorno = new RetornoMinimax();
        retorno.value = valor;
        retorno.AUXILIAR = melhor;
        return retorno;
    }//minMax


    /**Método utilizado para a determinação de valores para cada nó 
     *sendo que a partir deste valor será escolhido o no corrente - jogada a ser realizada
     */
    
    private int avaliacaoEstatica(int[][] posicao, int jogador) {
        int funcaoAvaliacao = 0;
//        System.out.println("ENTROU AVALICAO ESTATICA");
//        System.out.println("POSICOES QUE ENTROU NA AVALIACAO ESTATICA");

        //parametros utilizados para calculo da avaliação estática
        int quantidadePecasBrancas=0;
        int quantidadePecasPretas=0;
        int quantidadeDamasBrancas=0;
        int quantidadeDamasPretas=0;
//        int pesoPecasBrancas=0;     
//        int pesoPecasPretas=0;     

        //calcula o número de posiçoes peca branca
        quantidadePecasBrancas = numeroPecas(posicao,-1);
    //    System.out.println("Quantidade pecas BRANCAS:"+ quantidadePecasBrancas);
//        //calcula o número de posições peca preta 
        quantidadePecasPretas = numeroPecas(posicao,1);
//        System.out.println("Quantidade pecas PRETAS:"+ quantidadePecasPretas);
//        //calcula o número de posições dama branca
        quantidadeDamasBrancas = numeroPecas(posicao,-11);
//        System.out.println("Quantidade DAMAS BRANCAS:"+ quantidadeDamasBrancas);
//        //calcula o número de posições dama Preta
        quantidadeDamasPretas = numeroPecas(posicao,11);
//        System.out.println("Quantidade DAMAS PRETAS:"+ quantidadeDamasPretas);
        //calcula o peso  total das pecas brancas
        //pesoPecasBrancas = calculaPesoPecasBrancas(posicao);
        //System.out.println("PESO PECAS BRANCAS: "+pesoPecasBrancas);
        //calcula o peso  total das pecas pretas
        // pesoPecasPretas = calculaPesoPecasPretas(posicao);
        //System.out.println("PESO PECAS BRANCAS: "+pesoPecasPretas);

        int scorePreta = 0;
        int scoreBranca = 0;

        //    funcaoAvaliacao = ((pesoPecasBrancas) / (pesoPecasPretas));

        char cor;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (posicao[i][j] == 1) {
                    cor = 'P';
                    int auxPeso = calculaValor(cor, ConverteMatrizEmIndice(null, +i + "," + j));
                    scorePreta = scorePreta + auxPeso;

                } else if (posicao[i][j] == -1) {
                    cor = 'B';
                    int auxPeso = calculaValor(cor, ConverteMatrizEmIndice(null, +i + "," + j));
                    scoreBranca = scoreBranca + auxPeso;
                } else if (posicao[i][j] == 11) {
                    cor = 'D';
                    int auxPeso = calculaValor(cor, ConverteMatrizEmIndice(null, +i + "," + j));
                    scorePreta = scorePreta + auxPeso;

                } else if (posicao[i][j] == -11) {
                    cor = 'D';
                    int auxPeso = calculaValor(cor, ConverteMatrizEmIndice(null, +i + "," + j));
                    scoreBranca = scoreBranca + auxPeso;

                } else {//qndo a psociao é 0 faz nada
                }

            }//or j
            //  System.out.println(" ");
        }//for i


//        System.out.println("scoreBranca________" + scoreBranca);
//        System.out.println("scorePreta________" + scorePreta);
//        System.out.println("________");
//        if (scoreBranca == scorePreta){
//            funcaoAvaliacao = 0;
//        }else{
        funcaoAvaliacao = scoreBranca*(quantidadePecasBrancas + 5*quantidadeDamasBrancas) - scorePreta*(quantidadePecasPretas + 5*quantidadeDamasPretas)  ;
        //    }
        // int k = (int) (Math.random() * 20) + 1;
      //  System.out.println("avalia " + funcaoAvaliacao);
        //  System.out.println("AVALIACAO ESTATICA");
        // return k;
        return funcaoAvaliacao;
    }//avaliacaoEstatica

    private int calculaValor(char corPeca, String posicao) {
        int valor = 0;

        if (corPeca == 'B') {
            int ps = Integer.parseInt(posicao);
            if (ps >= 6 && ps <= 9) {//prestes a virar dama
                valor = 7;
            } else {
                valor = 5;
            }

        } else if (corPeca == 'P') {
            int ps = Integer.parseInt(posicao);
            if (ps >= 28 && ps <= 31) {
                valor = 7;//prestes a virar dama
            } else {
                valor = 5;//normal
            }
        } else {//dama
            valor = 10;
        }

        return valor * forcaTabuleiro(posicao);
    }//calculaValor

    
    /**Método utilizado para atribuir peso a posicionamentos do tabuleiro*/
    private int forcaTabuleiro(String pos) {
        int forca = 0;
        switch (pos) {
            case "1":
                forca = 4;
                break;
            case "2":
                forca = 4;
                break;
            case "3":
                forca = 4;
                break;
            case "4":
                forca = 4;
                break;
            case "6":
                forca = 3;
                break;
            case "7":
                forca = 3;
                break;
            case "8":
                forca = 3;
                break;
            case "9":
                forca = 4;
                break;
            case "10":
                forca = 4;
                break;
            case "11":
                forca = 2;
                break;
            case "12":
                forca = 2;
                break;
            case "13":
                forca = 3;
                break;
            case "15":
                forca = 3;
                break;
            case "16":
                forca = 1;
                break;
            case "17":
                forca = 2;
                break;
            case "18":
                forca = 4;
                break;
            case "19":
                forca = 4;
                break;
            case "20":
                forca = 2;
                break;
            case "21":
                forca = 1;
                break;
            case "22":
                forca = 3;
                break;
            case "24":
                forca = 3;
                break;
            case "25":
                forca = 2;
                break;
            case "26":
                forca = 2;
                break;
            case "27":
                forca = 4;
                break;
            case "28":
                forca = 4;
                break;
            case "29":
                forca = 3;
                break;
            case "30":
                forca = 3;
                break;
            case "31":
                forca = 3;
                break;
            case "33":
                forca = 4;
                break;
            case "34":
                forca = 4;
                break;

            case "35":
                forca = 4;

                break;
            case "36":
                forca = 4;
                break;
        }
        return forca;
    }//forcaTabuleiro

    /**Método utilizado para retornar a quantidade de posicoes ocupadas por pecas
     *@param posicao - matriz contendo o mapeamento de posicoes ocupadas
     * @param marcacao - peca a ser verificada a quantidade: -1 brancas; 1 pretas; -11 damas Brancas;11 damas Pretas
     * @return quantidade - numero de posicoes ocupadas pelas pecas
     */
    public int numeroPecas(int[][] posicao, int marcacao) {
        int quantidadePecas = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (posicao[i][j] == marcacao) {
                    quantidadePecas++;
                }
            }
        }
        return quantidadePecas;
    }

    /**Método utilizado para retornar o peso das quantidade total de pecas brancas 
     *Para esta análise é verificado a peca em questão, se é Dama ( pontua-se com 5) ou não (pontua-se com 3) e a 
     * posicao ocupada por cada uma delas (posições estratégicas   recebem pontuacao 4)
     * @param posicao - posições ocupadas por tabuleiros
     */
    public int calculaPesoPecasBrancas(int[][] posicao) {
        int pesoBrancas = 0;
        boolean condicaoCaptura = false;

        for (int i = 0; i < 8; i++) {
            condicaoCaptura = false;
            for (int j = 0; j < 8; j++) {
                condicaoCaptura = false;
                if (i != 7 && posicao[i][j] == -1) {
                    if (j != 0 && j != 7) {
                        //verifico se a jogada da diagonal é adversario
                        if ((posicao[i + 1][j - 1] == 1) || (posicao[i + 1][j + 1] == 1)) {
                            condicaoCaptura = true;
                      //      System.out.println("ENTROU CONDICAO CAPTURA");
                      //      System.out.println("Posicao: " + posicao[i][j]);
                        }
                    } else {
                        if (j == 0) {
                            if ((posicao[i + 1][j + 1] == 1)) {
                                condicaoCaptura = true;
                            }
                        } else {

                            if ((posicao[i + 1][j - 1] == 1)) {
                                condicaoCaptura = true;
                            }
                        }

                    }
                }

                //verifica se é peca apenas ou dama - pontua-se com 3 + peso correspondente a sua localização
                if (posicao[i][j] == -1) {

                    if (i == 0 || i == 7 || j == 0 || j == 7) {

                        if (i == 0 || i == 7) {
                            if (condicaoCaptura) {
                                pesoBrancas = pesoBrancas + 14 * 4;
                            } else {
                                pesoBrancas = pesoBrancas + 7 * 4;

                            }

                        } else {
                            if (condicaoCaptura) {
                                pesoBrancas = pesoBrancas + 6 * 4;
                            } else {
                                pesoBrancas = pesoBrancas + 3 * 4;
                            }
                        }
                    } else {
                        if (i == 1 || i == 6 || j == 1 || j == 6) {
                            if (i == 6) {
                                if (condicaoCaptura) {
                                    pesoBrancas = pesoBrancas + 12 * 3;
                                } else {
                                    pesoBrancas = pesoBrancas + 6 * 3;
                                }
                            } else {
                                if (condicaoCaptura) {
                                    pesoBrancas = pesoBrancas + 6 * 3;
                                } else {
                                    pesoBrancas = pesoBrancas + 3 * 3;
                                }
                            }
                        } else {
                            if (i == 2 || i == 5 || j == 2 || j == 5) {

                                if (i == 5) {
                                    if (condicaoCaptura) {
                                        pesoBrancas = pesoBrancas + 10 * 2;
                                    } else {
                                        pesoBrancas = pesoBrancas + 5 * 2;
                                    }
                                } else {
                                    if (condicaoCaptura) {
                                        pesoBrancas = pesoBrancas + 6 * 2;
                                    } else {
                                        pesoBrancas = pesoBrancas + 3 * 2;
                                    }
                                }

                            } else {
                                if (i == 3 || i == 4 || j == 3 || j == 4) {
                                    if (i == 3 || i == 4) {
                                        if (condicaoCaptura) {
                                            pesoBrancas = pesoBrancas + 8 * 2;
                                        } else {
                                            pesoBrancas = pesoBrancas + 4 * 2;
                                        }
                                    } else {

                                        if (condicaoCaptura) {
                                            pesoBrancas = pesoBrancas + 8 * 2;
                                        } else {
                                            pesoBrancas = pesoBrancas + 4 * 2;
                                        }
                                    }
                                }

                            }
                        }
                    }
                } else {//caso seja Dama - pontua-se com 5+ peso correspondente a sua localização
                    if (posicao[i][j] == -11) {
                        if (i == 0 || i == 7 || j == 0 || j == 7) {
                            pesoBrancas = pesoBrancas + 5 * 4;
                        } else {
                            if (i == 1 || i == 6 || j == 1 || j == 6) {
                                pesoBrancas = pesoBrancas + 5 * 3;
                            } else {
                                if (i == 2 || i == 5 || j == 2 || j == 5) {
                                    pesoBrancas = pesoBrancas + 5 * 2;
                                } else {
                                    if (i == 3 || i == 4 || j == 3 || j == 4) {
                                        pesoBrancas = pesoBrancas + 5 * 1;
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
        return pesoBrancas;
    }

    public int calculaPesoPecasPretas(int[][] posicao) {
        int pesoPretas = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                //verifica se é peca apenas ou dama - pontua-se com 3 + peso correspondente a sua localização
                if (posicao[i][j] == 1) {

                    if (i == 0 || i == 7 || j == 0 || j == 7) {
                        pesoPretas = pesoPretas + 3 * 4;
                    } else {
                        if (i == 1 || i == 6 || j == 1 || j == 6) {
                            pesoPretas = pesoPretas + 3 * 3;
                        } else {
                            if (i == 2 || i == 5 || j == 2 || j == 5) {
                                pesoPretas = pesoPretas + 3 * 2;
                            } else {
                                if (i == 3 || i == 4 || j == 3 || j == 4) {
                                    pesoPretas = pesoPretas + 3 * 1;
                                }

                            }
                        }
                    }
                } else {//caso seja Dama - pontua-se com 5+ peso correspondente a sua localização
                    if (posicao[i][j] == 11) {
                        if (i == 0 || i == 7 || j == 0 || j == 7) {
                            pesoPretas = pesoPretas + 5 * 4;
                        } else {
                            if (i == 1 || i == 6 || j == 1 || j == 6) {
                                pesoPretas = pesoPretas + 5 * 3;
                            } else {
                                if (i == 2 || i == 5 || j == 2 || j == 5) {
                                    pesoPretas = pesoPretas + 5 * 2;
                                } else {
                                    if (i == 3 || i == 4 || j == 3 || j == 4) {
                                        pesoPretas = pesoPretas + 5 * 1;
                                    }

                                }
                            }
                        }
                    }

                }
            }
        }
        return pesoPretas;
    }

//Observacao parametro indiceJogada não esta sendo utilizado por enquanto
    public void proximaJogada(int tab[][], int profundidade, char jogador, int novoTab[][], String indiceJogada) throws CloneNotSupportedException {
//        System.out.println("ENTROU CHAMADA DE ARVORE - TESTE");
        No raiz, melhor = new No();
        int valor = 0;
//        System.out.println("CHAMADA DA ARVORE");
//        System.out.println("VALOR PROFUNDIDADE - MÉTODO PROXIMA JOGADA="+profundidade);
        raiz = constroiArvore(tab, profundidade);
        // System.out.println("Raiz"+ raiz.filho.size());
        
        for(int k=0;k<raiz.filho.size();k++){
         System.out.println("FILHO");
                              for (int n = 0; n < 8; n++) {
            for (int m = 0; m < 8; m++) {
                //novoTab[i][j] = melhor.posicao[i][j];
                System.out.print(raiz.filho.get(k).posicao[n][m] + " ");
            }
            System.out.println(" ");
        }
        }
        //  System.out.println("CONSTRUI ARVORE CORRETAMENTE");
        RetornoMinimax R = minMax(raiz, jogador, melhor, valor);
        valor = R.value;
        melhor = R.AUXILIAR;

        System.out.println("MELHOR FILHO ESCOLHIDO");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //novoTab[i][j] = melhor.posicao[i][j];
                System.out.print(melhor.posicao[i][j] + " ");
            }
            System.out.println(" ");
        }

        //   System.out.println("SAIU");

        // System.out.println("NOVO TAB");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //novoTab[i][j] = melhor.posicao[i][j];
                novoTab[i][j] = melhor.posicao[i][j];
                // System.out.println("I = " + i + ", J = " + j + "  = " + novoTab[i][j]);
            }
        }
    }//proximaJogada

    private ArrayList<int[][]> verificaAdjacentes(int posicao, int tabAtual[][], String indiceJogada) {
        ArrayList<int[][]> filhos = new ArrayList<int[][]>();

        //int tabNovo[][] = new int[8][8];
        //ver possiveis posicoes
        // String indiceJogada = ConverteMatrizEmIndice(null, i + "," + j);
        ArrayList<String> posicoesJogadas = retornaPosicaoJogadaASerRealizada(indiceJogada, false, 1);
        ArrayList<String> posicoesJogadasComendoEsq = listaDePosicoesPecaBrancaParaCapturaEsquerda(indiceJogada);
        ArrayList<String> posicoesJogadasComendoDir = listaDePosicoesPecaBrancaParaCapturaDireita(indiceJogada);


//            System.out.println("jogada>>>" +indiceJogada);
//            System.out.println("Esquerda");
//        System.out.println(""+posicoesJogadasComendoEsq.size());
//            for(int i=0;i<posicoesJogadasComendoEsq.size();i++){
//                System.out.print( posicoesJogadasComendoEsq.get(i)+", ");
//            }
//            System.out.println("");         
//            System.out.println("Direita");
//            for(int i=0;i<posicoesJogadasComendoDir.size();i++){
//                System.out.print( posicoesJogadasComendoDir.get(i)+", ");
//            }
//            System.out.println("");
//            System.out.println("\n\n\n\n\n");
//            System.out.println("indice jogada:" + indiceJogada);
//            for (int m = 0; m < posicoesJogadas.size(); m++) {
//               // pegadonindice i e j de posicoes a serem jogadas     
//                System.out.println("posicao a ser jogada" + posicoesJogadas.get(m));
//            }
        //branca -1
        //preta   1

        // for(int k=0; k< posicoesJogadasComendoDir.size(); k++){
        int[] linhaColum = retornaLinhaColuna(indiceJogada);

        if (tabAtual[linhaColum[0]][linhaColum[1]] == -1 || tabAtual[linhaColum[0]][linhaColum[1]] == -11) {//eh peao ou dama

            if (posicoesJogadasComendoDir.size() > 0) {

                Collections.sort(posicoesJogadasComendoDir);
                int pos = posicoesJogadasComendoDir.size() - 1;

                int aSerComida = (Integer.parseInt(posicoesJogadasComendoDir.get(pos))) + 5;
                int comeu = (Integer.parseInt(posicoesJogadasComendoDir.get(pos)));

                int[] linhaColunaComeu = retornaLinhaColuna(String.valueOf(comeu));
                int[] linhaColunaComida = retornaLinhaColuna(String.valueOf(aSerComida));

                if ((tabAtual[linhaColunaComida[0]][linhaColunaComida[1]] == 1 || tabAtual[linhaColunaComida[0]][linhaColunaComida[1]] == 11) && tabAtual[linhaColunaComeu[0]][linhaColunaComeu[1]] == 0) {
//                 System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n\n\n");
//                System.out.println("comida...comeu...indice......"+ aSerComida +" "+ comeu+ " " +indiceJogada);
//                    System.out.println(  posicoesJogadasComendoDir.get(pos));
//                    System.out.println("\n\n\n\n\n\n\n\n\n\nn\n\n\n\\n"+posicoesJogadasComendoDir.get(0));
                    //  System.out.println("criou + DIREITA");
                    int tabul[][] = criaTabNovo(indiceJogada, posicoesJogadasComendoDir.get(pos), tabAtual);
                    filhos.add(removePecaComidaTab(tabul, String.valueOf(aSerComida)));
                    //  System.out.println("ADD "+ aSerComida+ ", "+indiceJogada);
                }
            }

            if (posicoesJogadasComendoEsq.size() > 0) {
                Collections.sort(posicoesJogadasComendoEsq);
                int pos = posicoesJogadasComendoEsq.size() - 1;
                int comida = (Integer.parseInt(posicoesJogadasComendoEsq.get(pos))) + 4;
                int comeu = (Integer.parseInt(posicoesJogadasComendoEsq.get(pos)));
                int[] linhaColunaComeu = retornaLinhaColuna(String.valueOf(comeu));
                int[] linhaColunaComida = retornaLinhaColuna(String.valueOf(comida));

                if ((tabAtual[linhaColunaComida[0]][linhaColunaComida[1]] == 1 || tabAtual[linhaColunaComida[0]][linhaColunaComida[1]] == 11) && tabAtual[linhaColunaComeu[0]][linhaColunaComeu[1]] == 0) {
//                System.out.println("\n\n\n");
//                System.out.println("comeu... "+ comeu+ " comida... "+ comida +" indice......"   +indiceJogada);
//               System.out.println(  posicoesJogadasComendoDir.get(pos));

//                     System.out.println(  posicoesJogadasComendoEsq.get(pos));
//                    System.out.println("\n\n\n\n\n\n\n\n\n\nn\n\n\n\\n"+posicoesJogadasComendoEsq.get(0));

                    //   System.out.println("criou + DIREITA");
                    int tabul[][] = criaTabNovo(indiceJogada, posicoesJogadasComendoEsq.get(pos), tabAtual);
                    filhos.add(removePecaComidaTab(tabul, String.valueOf(comida)));
//        System.out.println("ADD "+ comida+ ", "+indiceJogada);
                }
            }

            // System.out.println("possiblidades jogadas------------->" + this.possibilidadePecasJogadas);
            for (int m = 0; m < posicoesJogadas.size(); m++) {
                //pegadonindice i e j de posicoes a serem jogadas     
                //   System.out.println("posicoes a ser jogada"+posicoesJogadas.get(m));       
                //ate aki ok
                int[] linhaColuna = retornaLinhaColuna(posicoesJogadas.get(m));
                int[] coordenadaIndice = retornaLinhaColuna(indiceJogada);

                //  System.out.println("linha "+linhaColuna[0]+" coluna "+linhaColuna[1]);
                //System.out.println("tab "+ tabAtual[linhaColuna[0]][linhaColuna[1]]);
                if (tabAtual[linhaColuna[0]][linhaColuna[1]] == 0 && (tabAtual[coordenadaIndice[0]][coordenadaIndice[1]] == -1 || tabAtual[coordenadaIndice[0]][coordenadaIndice[1]] == -11)) {
//                      System.out.println("tab "+ tabAtual[linhaColuna[0]][linhaColuna[1]]);
//                System.out.println("criou + NORMAL");
                    filhos.add(criaTabNovo(indiceJogada, posicoesJogadas.get(m), tabAtual));
                }
            }//for numero de possivesis jogadas
            //   System.out.println("TAMANHO DOS FILHOS = " + filhos.size());
            //percorrendo tabuleiro para verificar se a posicao esta ocupada
        }
        if (tabAtual[linhaColum[0]][linhaColum[1]] == -11) {//eh  apenas dama

            //  ArrayList<String> posicoesJogadasDama = retornaPosicaoJogadaASerRealizada(indiceJogada, true, 1);
            ArrayList<String> posicoesJogadasVoltaComendoEsq = listaDePosicoesPecaDamaParaCapturaEsquerda(indiceJogada);
            ArrayList<String> posicoesJogadasVoltaComendoDir = listaDePosicoesPecaPretaParaCapturaDireita(indiceJogada);

            if (posicoesJogadasVoltaComendoDir.size() > 0) {

                Collections.sort(posicoesJogadasVoltaComendoDir);
                boolean confereDamas = true;
                for (int k = 0; confereDamas == true && k < posicoesJogadasVoltaComendoDir.size(); k++) {

                    int aSerComida = (Integer.parseInt(posicoesJogadasVoltaComendoDir.get(k))) - 5;
                    int comeu = (Integer.parseInt(posicoesJogadasVoltaComendoDir.get(k)));

                    int[] linhaColunaComeu = retornaLinhaColuna(String.valueOf(comeu));
                    int[] linhaColunaComida = retornaLinhaColuna(String.valueOf(aSerComida));

                    if ((tabAtual[linhaColunaComida[0]][linhaColunaComida[1]] == 1 || tabAtual[linhaColunaComida[0]][linhaColunaComida[1]] == 11) && tabAtual[linhaColunaComeu[0]][linhaColunaComeu[1]] == 0) {

                        int tabul[][] = criaTabNovo(indiceJogada, posicoesJogadasVoltaComendoDir.get(k), tabAtual);
                        filhos.add(removePecaComidaTab(tabul, String.valueOf(aSerComida)));
                        confereDamas = false;
                        //  System.out.println("ADD "+ aSerComida+ ", "+indiceJogada);
                    } else if ((tabAtual[linhaColunaComida[0]][linhaColunaComida[1]] == 0) && (tabAtual[linhaColunaComeu[0]][linhaColunaComeu[1]] == 0)) {
                        int tabul[][] = criaTabNovo(indiceJogada, posicoesJogadasVoltaComendoDir.get(k), tabAtual);
                        filhos.add(tabul);
                        //ta livre, incrementa o k
                    } else {
                        confereDamas = false;
                    }
                }
            }


            if (posicoesJogadasVoltaComendoEsq.size() > 0) {
                Collections.sort(posicoesJogadasVoltaComendoEsq);
                boolean condicaoDama = true;
                for (int k = 0; condicaoDama == true && k < posicoesJogadasVoltaComendoEsq.size(); k++) {

                    int comida = (Integer.parseInt(posicoesJogadasVoltaComendoEsq.get(k))) - 4;
                    int comeu = (Integer.parseInt(posicoesJogadasVoltaComendoEsq.get(k)));

                    int[] linhaColunaComeu = retornaLinhaColuna(String.valueOf(comeu));
                    int[] linhaColunaComida = retornaLinhaColuna(String.valueOf(comida));

                    if ((tabAtual[linhaColunaComida[0]][linhaColunaComida[1]] == 1 || tabAtual[linhaColunaComida[0]][linhaColunaComida[1]] == 11) && tabAtual[linhaColunaComeu[0]][linhaColunaComeu[1]] == 0) {
//                System.out.println("\n\n\n");
//                System.out.println("comeu... "+ comeu+ " comida... "+ comida +" indice......"   +indiceJogada);
//               System.out.println(  posicoesJogadasComendoDir.get(pos));
                        //   System.out.println("criou + DIREITA");
                        int tabul[][] = criaTabNovo(indiceJogada, posicoesJogadasVoltaComendoEsq.get(k), tabAtual);
                        filhos.add(removePecaComidaTab(tabul, String.valueOf(comida)));
//        System.out.println("ADD "+ comida+ ", "+indiceJogada);
                    } else if ((tabAtual[linhaColunaComida[0]][linhaColunaComida[1]] == 0) && (tabAtual[linhaColunaComeu[0]][linhaColunaComeu[1]] == 0)) {
                     int tabul[][] = criaTabNovo(indiceJogada, posicoesJogadasVoltaComendoEsq.get(k), tabAtual);
                        filhos.add(tabul);
                        //ta livre, incrementa o k              
                    } else {
                        condicaoDama = false;
                    }
                }

            }

            if (posicoesJogadasComendoDir.size() > 0) {
                Collections.sort(posicoesJogadasComendoDir);
                boolean condicaoDama = true;
                for (int k = 0; condicaoDama == true && k < posicoesJogadasComendoDir.size(); k++) {

                    int comida = (Integer.parseInt(posicoesJogadasComendoDir.get(k))) - 4;
                    int comeu = (Integer.parseInt(posicoesJogadasComendoDir.get(k)));

                    int[] linhaColunaComeu = retornaLinhaColuna(String.valueOf(comeu));
                    int[] linhaColunaComida = retornaLinhaColuna(String.valueOf(comida));

                    if ((tabAtual[linhaColunaComida[0]][linhaColunaComida[1]] == 1 || tabAtual[linhaColunaComida[0]][linhaColunaComida[1]] == 11) && tabAtual[linhaColunaComeu[0]][linhaColunaComeu[1]] == 0) {
//                System.out.println("\n\n\n");
//                System.out.println("comeu... "+ comeu+ " comida... "+ comida +" indice......"   +indiceJogada);
//               System.out.println(  posicoesJogadasComendoDir.get(pos));
                        //   System.out.println("criou + DIREITA");
                        int tabul[][] = criaTabNovo(indiceJogada, posicoesJogadasComendoDir.get(k), tabAtual);
                        filhos.add(removePecaComidaTab(tabul, String.valueOf(comida)));
//        System.out.println("ADD "+ comida+ ", "+indiceJogada);
                    } else if ((tabAtual[linhaColunaComida[0]][linhaColunaComida[1]] == 0) && (tabAtual[linhaColunaComeu[0]][linhaColunaComeu[1]] == 0)) {
                     int tabul[][] = criaTabNovo(indiceJogada, posicoesJogadasComendoDir.get(k), tabAtual);
                        filhos.add(tabul);
                        //ta livre, incrementa o k
                    
                    } else {
                        condicaoDama = false;
                    }
                }

            }

            if (posicoesJogadasComendoEsq.size() > 0) {
                Collections.sort(posicoesJogadasComendoEsq);
                boolean condicaoDama = true;
                for (int k = 0; condicaoDama == true && k < posicoesJogadasComendoEsq.size(); k++) {

                    int comida = (Integer.parseInt(posicoesJogadasComendoEsq.get(k))) + 4;
                    int comeu = (Integer.parseInt(posicoesJogadasComendoEsq.get(k)));

                    int[] linhaColunaComeu = retornaLinhaColuna(String.valueOf(comeu));
                    int[] linhaColunaComida = retornaLinhaColuna(String.valueOf(comida));

                    if ((tabAtual[linhaColunaComida[0]][linhaColunaComida[1]] == 1 || tabAtual[linhaColunaComida[0]][linhaColunaComida[1]] == 11) && tabAtual[linhaColunaComeu[0]][linhaColunaComeu[1]] == 0) {
//                System.out.println("\n\n\n");
//                System.out.println("comeu... "+ comeu+ " comida... "+ comida +" indice......"   +indiceJogada);
//               System.out.println(  posicoesJogadasComendoDir.get(pos));
                        //   System.out.println("criou + DIREITA");
                        int tabul[][] = criaTabNovo(indiceJogada, posicoesJogadasComendoEsq.get(k), tabAtual);
                        filhos.add(removePecaComidaTab(tabul, String.valueOf(comida)));
//        System.out.println("ADD "+ comida+ ", "+indiceJogada);
                    } else if ((tabAtual[linhaColunaComida[0]][linhaColunaComida[1]] == 0) && (tabAtual[linhaColunaComeu[0]][linhaColunaComeu[1]] == 0)) {
                     int tabul[][] = criaTabNovo(indiceJogada, posicoesJogadasComendoEsq.get(k), tabAtual);
                        filhos.add(tabul);
                        //ta livre, incrementa o k              
                    } else {
                        condicaoDama = false;
                    }
                }

            }
        }
        return filhos;
    }//verificaAdjacentes

    private int[][] criaTabNovo(String anterior, String aSerJogada, int[][] tabAtual) {
        int tabNovo[][] = new int[8][8];
        //retorna linha e coluna
        int[] antes = retornaLinhaColuna(anterior);
        int[] prox = retornaLinhaColuna(aSerJogada);

//        System.out.println("linha antes"+antes[0]+"coluna antes"+antes[1]);
//        System.out.println("linha proxz"+prox[0]+"coluna prox"+prox[1]);

        //   System.out.println("TABATRUALAAAAAAAAAAAAAAAAAAAA");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tabNovo[i][j] = tabAtual[i][j];
                //           System.out.println("/"+tabAtual[i][j] +"  i__"+i+" j__"+j);
            }
        }

//        if (tabAtual[antes[0]][antes[1]] != 0 && tabNovo[prox[0]][prox[1]] == 0) {
        //System.out.println("ENTROU CRIA TAB FOR ");
        //System.out.println("\n\n\n\n");
        tabNovo[prox[0]][prox[1]] = tabAtual[antes[0]][antes[1]];
        //         System.out.println("\n\n");
        tabNovo[antes[0]][antes[1]] = 0;
        //   System.out.println("antes" + tabNovo[antes[0]][antes[1]] + " linha" + antes[0] + " coluna" + antes[1]);
        //   System.out.println("prox" + tabNovo[prox[0]][prox[1]] + " linha" + prox[0] + " coluna" + prox[1]);
        //    }

//          for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                System.out.println(" i = " +i+ " j =  "+ j   +"tabuleiro novo-->"+tabNovo[i][j]);
//                System.out.println(" i = " +i+ " j =  "+ j   +"tabuleiro antigo..."+tabAtual[i][j]);
//            }
//        }
        //}
        return tabNovo;
    }//criaTabNovo

    public int[] retornaLinhaColuna(String indice) {
        int[] linhaColuna = new int[2];
        switch (indice) {
            case "1":
                linhaColuna[0] = 7;
                linhaColuna[1] = 7;
                break;
            case "2":
                linhaColuna[0] = 7;
                linhaColuna[1] = 5;
                break;
            case "3":
                linhaColuna[0] = 7;
                linhaColuna[1] = 3;
                break;
            case "4":
                linhaColuna[0] = 7;
                linhaColuna[1] = 1;
                break;
            case "6":
                linhaColuna[0] = 6;
                linhaColuna[1] = 6;
                break;
            case "7":
                linhaColuna[0] = 6;
                linhaColuna[1] = 4;
                break;
            case "8":
                linhaColuna[0] = 6;
                linhaColuna[1] = 2;
                break;
            case "9":
                linhaColuna[0] = 6;
                linhaColuna[1] = 0;
                break;
            case "10":
                linhaColuna[0] = 5;
                linhaColuna[1] = 7;
                break;
            case "11":
                linhaColuna[0] = 5;
                linhaColuna[1] = 5;
                break;
            case "12":
                linhaColuna[0] = 5;
                linhaColuna[1] = 3;
                break;
            case "13":
                linhaColuna[0] = 5;
                linhaColuna[1] = 1;
                break;
            case "15":
                linhaColuna[0] = 4;
                linhaColuna[1] = 6;
                break;
            case "16":
                linhaColuna[0] = 4;
                linhaColuna[1] = 4;
                break;
            case "17":
                linhaColuna[0] = 4;
                linhaColuna[1] = 2;
                break;
            case "18":
                linhaColuna[0] = 4;
                linhaColuna[1] = 0;
                break;
            case "19":
                linhaColuna[0] = 3;
                linhaColuna[1] = 7;
                break;
            case "20":
                linhaColuna[0] = 3;
                linhaColuna[1] = 5;
                break;
            case "21":
                linhaColuna[0] = 3;
                linhaColuna[1] = 3;
                break;
            case "22":
                linhaColuna[0] = 3;
                linhaColuna[1] = 1;
                break;
            case "24":
                linhaColuna[0] = 2;
                linhaColuna[1] = 6;
                break;
            case "25":
                linhaColuna[0] = 2;
                linhaColuna[1] = 4;
                break;
            case "26":
                linhaColuna[0] = 2;
                linhaColuna[1] = 2;
                break;
            case "27":
                linhaColuna[0] = 2;
                linhaColuna[1] = 0;
                break;
            case "28":
                linhaColuna[0] = 1;
                linhaColuna[1] = 7;
                break;
            case "29":
                linhaColuna[0] = 1;
                linhaColuna[1] = 5;
                break;
            case "30":
                linhaColuna[0] = 1;
                linhaColuna[1] = 3;
                break;
            case "31":
                linhaColuna[0] = 1;
                linhaColuna[1] = 1;
                break;
            case "33":
                linhaColuna[0] = 0;
                linhaColuna[1] = 6;
                break;
            case "34":
                linhaColuna[0] = 0;
                linhaColuna[1] = 4;
                break;
            case "35":
                linhaColuna[0] = 0;
                linhaColuna[1] = 2;
                break;
            case "36":
                linhaColuna[0] = 0;
                linhaColuna[1] = 0;
                break;
        }
        return linhaColuna;
    }

    public String ConverteMatrizEmIndice(JButton[][] matrizTab, String linhaColuna) {

        if (linhaColuna.equals("0,0")) {
            return "36";
        }
        if (linhaColuna.equals("0,2")) {
            return "35";
        }
        if (linhaColuna.equals("0,4")) {
            return "34";
        }
        if (linhaColuna.equals("0,6")) {
            return "33";
        }
        if (linhaColuna.equals("1,1")) {
            return "31";
        }
        if (linhaColuna.equals("1,3")) {
            return "30";
        }
        if (linhaColuna.equals("1,5")) {
            return "29";
        }
        if (linhaColuna.equals("1,7")) {
            return "28";
        }
        if (linhaColuna.equals("2,0")) {
            return "27";
        }
        if (linhaColuna.equals("2,2")) {
            return "26";
        }
        if (linhaColuna.equals("2,4")) {
            return "25";
        }
        if (linhaColuna.equals("2,6")) {
            return "24";
        }
        if (linhaColuna.equals("3,1")) {
            return "22";
        }
        if (linhaColuna.equals("3,3")) {
            return "21";
        }
        if (linhaColuna.equals("3,5")) {
            return "20";
        }
        if (linhaColuna.equals("3,7")) {
            return "19";
        }
        if (linhaColuna.equals("4,0")) {
            return "18";
        }
        if (linhaColuna.equals("4,2")) {
            return "17";
        }
        if (linhaColuna.equals("4,4")) {
            return "16";
        }
        if (linhaColuna.equals("4,6")) {
            return "15";
        }
        if (linhaColuna.equals("5,1")) {
            return "13";
        }
        if (linhaColuna.equals("5,3")) {
            return "12";
        }
        if (linhaColuna.equals("5,5")) {
            return "11";
        }
        if (linhaColuna.equals("5,7")) {
            return "10";
        }
        if (linhaColuna.equals("6,0")) {
            return "9";
        }
        if (linhaColuna.equals("6,2")) {
            return "8";
        }
        if (linhaColuna.equals("6,4")) {
            return "7";
        }
        if (linhaColuna.equals("6,6")) {
            return "6";
        }
        if (linhaColuna.equals("7,1")) {
            return "4";
        }
        if (linhaColuna.equals("7,3")) {
            return "3";
        }
        if (linhaColuna.equals("7,5")) {
            return "2";
        }
        if (linhaColuna.equals("7,7")) {
            return "1";
        }
        return "100";

    }

    public int verificaIndiceExtremidade(String indice) {
        int extremidade = 0;
        //verifica abaixo
        for (int j = 0; j < baixo.length; j++) {
            if (baixo[j].equals(indice)) {
                extremidade = 1;
            }
        }
        //verifica esquerda
        for (int i = 0; i < esquerda.length; i++) {
            if (esquerda[i].equals(indice)) {
                extremidade = 2;
            }
        }
        //verifica acima
        for (int i = 0; i < acima.length; i++) {
            if (acima[i].equals(indice)) {
                extremidade = 3;
            }
        }
        //verifica abaixo
        for (int i = 0; i < direita.length; i++) {
            if (direita[i].equals(indice)) {
                extremidade = 4;
            }
        }



        return extremidade;
    }

    public ArrayList<String> retornaPosicaoJogadaASerRealizada(String indice, boolean eDama, int jogador) {
        String[] posicoesPossiveis = new String[8];
        ArrayList<String> listaPosicoesJogadas = new ArrayList<>();

        int numExtremidade = this.verificaIndiceExtremidade(indice);
        int cont = 0;
        boolean cond = true;
        // caso a jogada a ser escolhida seja do usuario
        if (jogador == 1) {
            //caso não seja dama - valida somente posicao posicao de avanco
            if (eDama) {
                //caso esteja em alguma extremidade
                if (numExtremidade != 0) {
                    switch (numExtremidade) {
                        case 1:
                        case 2:
                            while (cond) {
                                Integer valor = (Integer.valueOf(indice) - Integer.valueOf(5));
                                if (valor > 0) {
                                    posicoesPossiveis[cont] = String.valueOf(valor);
                                    cont++;
                                } else {
                                    cond = false;
                                }
                            }
                            break;

                        case 3:
                        case 4:
                            while (cond) {
                                Integer valor = (Integer.valueOf(indice) - Integer.valueOf(4));
                                if (valor > 0) {
                                    posicoesPossiveis[cont] = String.valueOf(valor);
                                    cont++;
                                } else {
                                    cond = false;
                                }
                            }
                            break;

                    }

                }
                //caso a peca não seja dama    
            } else {
                //caso esteja na extremidade a esquerda
                if (numExtremidade == 2) {
                    //  System.out.println("Entrou numExtremidade=2");
                    Integer valor = (Integer.valueOf(indice) - Integer.valueOf(5));

                    listaPosicoesJogadas.add(String.valueOf(valor));

                    posicoesPossiveis[0] = String.valueOf(valor);
                    //     System.out.println("Jogar  na posicao:" + valor);
                    this.possibilidadePecasJogadas = 1;
                } else {
                    if (numExtremidade == 4) {

                        if (!indice.equals("1")) {
                            //    System.out.println("Entrou numExtremidade=4");
                            Integer valor = (Integer.valueOf(indice) - Integer.valueOf(4));
                            listaPosicoesJogadas.add(String.valueOf(valor));

                            posicoesPossiveis[0] = String.valueOf(valor);

                            //   System.out.println("Jogar  na posicao:" + valor);
                            this.possibilidadePecasJogadas = 1;
                        }

                    } else {
                        if (numExtremidade == 3) {

                            if (indice.equals("36")) {
                                //   System.out.println("Entrou numExtremidade=3");
                                Integer valor = (Integer.valueOf(indice) - Integer.valueOf(5));
                                posicoesPossiveis[0] = String.valueOf(valor);
                                listaPosicoesJogadas.add(String.valueOf(valor));

                                //     System.out.println("Jogar  na posicao:" + valor);
                                this.possibilidadePecasJogadas = 1;
                            } else {
                                //      System.out.println("Entrou numExtremidade=3");
                                Integer valor = (Integer.valueOf(indice) - Integer.valueOf(5));
                                posicoesPossiveis[0] = String.valueOf(valor);
                                listaPosicoesJogadas.add(String.valueOf(valor));

                                //     System.out.println("Jogar  na posicao:" + valor);
                                valor = Integer.valueOf(indice) - Integer.valueOf(4);
                                posicoesPossiveis[1] = String.valueOf(valor);
                                listaPosicoesJogadas.add(String.valueOf(valor));

                                //   System.out.println("Jogar  na posicao:" + valor);
                                this.possibilidadePecasJogadas = 2;
                            }
                        } else {
                            if (numExtremidade == 1) {
                                //              System.out.println("Parabéns Chegou a dama");
                            } else {
                                //      System.out.println("Entrou numExtremidade=0");
                                Integer valor = (Integer.valueOf(indice) - Integer.valueOf(5));
                                //      System.out.println("Jogar  na posicao:" + valor);
                                posicoesPossiveis[0] = String.valueOf(valor);
                                listaPosicoesJogadas.add(String.valueOf(valor));

                                valor = Integer.valueOf(indice) - Integer.valueOf(4);
                                posicoesPossiveis[1] = String.valueOf(valor);
                                listaPosicoesJogadas.add(String.valueOf(valor));

                                //   System.out.println("Jogar  na posicao:" + valor);
                                this.possibilidadePecasJogadas = 2;
                            }
                        }
                    }
                }
            }

            // caso o jogador seja (-1) computador
        } else {
            if (eDama) {
                //jogada referente ao usuário caso não seja dama     
            } else {
                //verifica qual a posicao do indice
                if (numExtremidade == 2) {
                    if (!indice.equals("36")) {
                        Integer valor = (Integer.valueOf(indice) + Integer.valueOf(4));
                        posicoesPossiveis[0] = String.valueOf(valor);
                        listaPosicoesJogadas.add(String.valueOf(valor));
                        //    System.out.println("Jogar  na posicao:" + valor);
                        this.possibilidadePecasJogadas = 2;
                    }
                } else {
                    if (numExtremidade == 4) {
                        Integer valor = (Integer.valueOf(indice) + Integer.valueOf(5));
                        posicoesPossiveis[0] = String.valueOf(valor);
                        listaPosicoesJogadas.add(String.valueOf(valor));

                        //     System.out.println("Jogar  na posicao:" + valor);
                        this.possibilidadePecasJogadas = 2;
                    } else {
                        if (numExtremidade == 3) {
                            //        System.out.println("Parabén você chegou a dama");
                        } else {
                            if (numExtremidade == 1) {
                                if (!indice.equals("1")) {
                                    Integer valor = (Integer.valueOf(indice) + Integer.valueOf(5));
                                    posicoesPossiveis[0] = String.valueOf(valor);
                                    listaPosicoesJogadas.add(String.valueOf(valor));

                                    //        System.out.println("Jogar  na posicao:" + valor);
                                    valor = (Integer.valueOf(indice) + Integer.valueOf(4));
                                    posicoesPossiveis[1] = String.valueOf(valor);
                                    listaPosicoesJogadas.add(String.valueOf(valor));

                                    //            System.out.println("Jogar  na posicao:" + valor);
                                    this.possibilidadePecasJogadas = 2;
                                } else {
                                    Integer valor = (Integer.valueOf(indice) + Integer.valueOf(5));
                                    posicoesPossiveis[0] = String.valueOf(valor);
                                    listaPosicoesJogadas.add(String.valueOf(valor));

                                    //           System.out.println("Jogar  na posicao:" + valor);
                                    this.possibilidadePecasJogadas = 2;
                                }
                                //caso não esteja em nenhuma extremidade
                            } else {
                                //        System.out.println("Entrou numExtremidade=0");
                                Integer valor = (Integer.valueOf(indice) + Integer.valueOf(5));
                                //    System.out.println("Jogar  na posicao:" + valor);
                                posicoesPossiveis[0] = String.valueOf(valor);
                                listaPosicoesJogadas.add(String.valueOf(valor));

                                valor = Integer.valueOf(indice) + Integer.valueOf(4);
                                posicoesPossiveis[1] = String.valueOf(valor);
                                listaPosicoesJogadas.add(String.valueOf(valor));

                                //        System.out.println("Jogar  na posicao:" + valor);
                                this.possibilidadePecasJogadas = 2;
                            }
                        }

                    }

                }
            }
        }
        //return posicoesPossiveis;
        return listaPosicoesJogadas;

    }

    public ArrayList<String> listaDePosicoesPecaBrancaParaCapturaEsquerda(String jogada) {

        //this.posicoesCapturaEsquerdaPreta.clear();
        ArrayList<String> posicoesCapturaEsquerdaBranca = new ArrayList<String>();
        //     System.out.println("ENTROU LISTA DE POSICOES PARA CAPTURA - ESQUERDA");
        int numeroPosicoesCapturadas = 0;
        int quantidadeCaptura = 0;
        //this.posicoesCaptura=null;
        //    System.out.println("Entrada no método");   
        //verifica a possibilidade de realizar a captura da proxima peça -sera feita na classe tabuleiro
        // int quemOcupa = mov.verificaQuemOcupouAPosicao(jogadasPecasBrancas, jogadasPecasPretas, damasBrancas, damasPretas,jogadasAserRealizadas[0]);
        int cont = 0;
        boolean cond = true;
        int posicaoCaptura = 0;
        int estaExtremidade0 = 7;
        while (cond) {
            if (cont == 0) {
                posicaoCaptura = Integer.valueOf(jogada) - 8;
                estaExtremidade0 = verificaIndiceExtremidade(jogada);

            } else {
                posicaoCaptura = posicaoCaptura - 8;
            }

            //verifica se o número esta na extremidade
            int estaNaExtremidade1 = verificaIndiceExtremidade(String.valueOf(posicaoCaptura));
            int estaNaExtremidade2 = verificaIndiceExtremidade(String.valueOf(posicaoCaptura + 4));
            //caso o numero calculado ja esteja na extremidade
            if (estaNaExtremidade1 != 0 || estaNaExtremidade2 != 0 || estaExtremidade0 == 2) {
                //verifica se alguem ocupa a posicao
                if (estaNaExtremidade1 == 2 || estaNaExtremidade1 == 1) {
                    posicoesCapturaEsquerdaBranca.add(String.valueOf(posicaoCaptura));
                }


                //caso a posicao de captura não esteja ainda na extremidade -deve continuar verificando
            } else {
                posicoesCapturaEsquerdaBranca.add(String.valueOf(posicaoCaptura));

                quantidadeCaptura++;
            }
            cont++;
            cond = false;
        }
        //  System.out.println("POSICOES PARA CAPTURA A ESQUERDA:" + posicoesCapturaEsquerdaBranca.size()); 

        return posicoesCapturaEsquerdaBranca;
    }

    public ArrayList<String> listaDePosicoesPecaBrancaParaCapturaDireita(String jogada) {

        ArrayList<String> posicoesCapturaDireitaBranca = new ArrayList<>();
        int quantidadeCaptura = 0;
        //System.out.println("ENTROU LISTA DE POSICOES PARA CAPTURA - DIREITA");
        int numeroPosicoesCapturadas = 0;
        //this.posicoesCaptura=null;
        //   System.out.println("Entrada no método");   
        // verifica a possibilidade de realizar a captura da proxima peça -sera feita na classe tabuleiro
        // int quemOcupa = mov.verificaQuemOcupouAPosicao(jogadasPecasBrancas, jogadasPecasPretas, damasBrancas, damasPretas,jogadasAserRealizadas[0]);
        int cont = 0;
        boolean cond = true;
        int posicaoCaptura = 0;
        int estaExtremidade0 = 7;
        while (cond) {
            if (cont == 0) {
                posicaoCaptura = Integer.valueOf(jogada) - 10;
                estaExtremidade0 = verificaIndiceExtremidade(jogada);


            } else {
                posicaoCaptura = posicaoCaptura - 10;
            }

            //verifica se o número esta na extremidade
            int estaNaExtremidade1 = verificaIndiceExtremidade(String.valueOf(posicaoCaptura));
            int estaNaExtremidade2 = verificaIndiceExtremidade(String.valueOf(posicaoCaptura + 5));
            //caso o numero calculado ja esteja na extremidade
            if (estaNaExtremidade1 != 0 || estaNaExtremidade2 != 0 || estaExtremidade0 == 4) {
                //verifica se alguem ocupa a posicao
                if (estaNaExtremidade1 == 4 || estaNaExtremidade1 == 1) {
                    posicoesCapturaDireitaBranca.add(String.valueOf(posicaoCaptura));
                    System.out.println("Inserido na lista:" + posicaoCaptura);
                }
                //cond = false;
                //caso a posicao de captura não esteja ainda na extremidade -deve continuar verificando
            } else {
                posicoesCapturaDireitaBranca.add(String.valueOf(posicaoCaptura));
                quantidadeCaptura++;
            }
            cont++;
            cond = false;
        }
        quantidadeCaptura = 0;



        //  System.out.println("POSICOES PARA A CAPTURA A DIREITA:"+posicoesCapturaDireitaBranca.size());   
        return posicoesCapturaDireitaBranca;

    }

//     public int verificaQuemOcupouAPosicao(List<String> jogadasPecasBrancas, List<String> jogadasPecasPretas, List<String> damasBrancas, List<String> damasPretas, String indice) {
//    //    System.out.println("Entrou verifica posicao");
//        int jogador = 0;
//        Iterator i = jogadasPecasBrancas.iterator();
//        Iterator j = jogadasPecasPretas.iterator();
//        Iterator k = damasBrancas.iterator();
//        Iterator l = damasPretas.iterator();
//
//        //verifica na lista contendo as posições ocupadas pelas pecas brancas 
//        while (i.hasNext()) {
//            String valor = String.valueOf(i.next());
//            if (valor.equals(indice)) {
//                jogador = 1;
//            }
//        }
//        if (!damasBrancas.isEmpty()) {
//            // verifica na lista de damas brancas
//            while (k.hasNext()) {
//                String valor = String.valueOf(k.next());
//                if (valor.equals(indice)) {
//                    jogador = 2;
//                }
//            }
//        }
//        //verifica na lista contendo as posições ocupadas pelas pecas pretas 
//        while (j.hasNext()) {
//            String valor = String.valueOf(j.next());
//            if (valor.equals(indice)) {
//                jogador = -1;
//            }
//        }
//
//        if (!damasPretas.isEmpty()) {
//            //verifica na lista contendo as posições ocupadas pelas damas pretas 
//            while (l.hasNext()) {
//                String valor = String.valueOf(l.next());
//                if (valor.equals(indice)) {
//                    jogador = -2;
//                }
//            }
//        }
//      //  System.out.println("Retorno verifica se esta ocupada:" + jogador);
//        return jogador;
//
//    }
    private int[][] removePecaComidaTab(int[][] tabul, String peca) {

        int tabuleiro[][] = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tabuleiro[i][j] = tabul[i][j];
            }
        }
        int[] remove = retornaLinhaColuna(peca);
        tabuleiro[remove[0]][remove[1]] = 0;

        return tabuleiro;
    }

    public ArrayList<String> listaDePosicoesPecaDamaParaCapturaEsquerda(String jogada) {
        ArrayList<String> posicoesCapturaEsquerdaPreta = new ArrayList<String>();
        System.out.println("ENTROU LISTA DE POSICOES PARA CAPTURA - ESQUERDA");
        int numeroPosicoesCapturadas = 0;
        int quantidadeCaptura = 0;
        //this.posicoesCaptura=null;
        System.out.println("Entrada no método");
        // verifica a possibilidade de realizar a captura da proxima peça -sera feita na classe tabuleiro
        // int quemOcupa = mov.verificaQuemOcupouAPosicao(jogadasPecasBrancas, jogadasPecasPretas, damasBrancas, damasPretas,jogadasAserRealizadas[0]);
        int cont = 0;
        boolean cond = true;
        int posicaoCaptura = 0;
        int estaExtremidade0 = 7;
        while (cond) {
            if (cont == 0) {
                posicaoCaptura = Integer.valueOf(jogada) + 10;
                estaExtremidade0 = verificaIndiceExtremidade(jogada);

            } else {
                posicaoCaptura = posicaoCaptura + 10;
            }

            //verifica se o número esta na extremidade
            int estaNaExtremidade1 = verificaIndiceExtremidade(String.valueOf(posicaoCaptura));
            int estaNaExtremidade2 = verificaIndiceExtremidade(String.valueOf(posicaoCaptura - 5));
            //caso o numero calculado ja esteja na extremidade
            if (estaNaExtremidade1 != 0 || estaNaExtremidade2 != 0 || estaExtremidade0 == 2) {
                //verifica se alguem ocupa a posicao
                if (estaNaExtremidade1 == 2 || estaNaExtremidade1 == 3) {
                    posicoesCapturaEsquerdaPreta.add(String.valueOf(posicaoCaptura));
                }

                cond = false;
                //caso a posicao de captura não esteja ainda na extremidade -deve continuar verificando
            } else {
                posicoesCapturaEsquerdaPreta.add(String.valueOf(posicaoCaptura));
                quantidadeCaptura++;
            }
            cont++;
            cond = false;
        }
        quantidadeCaptura = 0;
        System.out.println("POSICOES PARA CAPTURA A ESQUERDA:" + posicoesCapturaEsquerdaPreta.size());
        return posicoesCapturaEsquerdaPreta;
    }//capturaDamaEsquerda

    public ArrayList<String> listaDePosicoesPecaPretaParaCapturaDireita(String jogada) {
        ArrayList<String> posicoesCapturaDireitaPreta = new ArrayList<String>();
        System.out.println("ENTROU LISTA DE POSICOES PARA CAPTURA - DIREITA");
        int numeroPosicoesCapturadas = 0;
        int quantidadeCaptura = 0;
        //this.posicoesCaptura=null;
        System.out.println("Entrada no método");
        // verifica a possibilidade de realizar a captura da proxima peça -sera feita na classe tabuleiro
        // int quemOcupa = mov.verificaQuemOcupouAPosicao(jogadasPecasBrancas, jogadasPecasPretas, damasBrancas, damasPretas,jogadasAserRealizadas[0]);
        int cont = 0;
        boolean cond = true;
        int posicaoCaptura = 0;
        int estaExtremidade0 = 7;
        while (cond) {
            if (cont == 0) {
                posicaoCaptura = Integer.valueOf(jogada) + 8;
                estaExtremidade0 = verificaIndiceExtremidade(jogada);

            } else {
                posicaoCaptura = posicaoCaptura + 8;
            }

            //verifica se o número esta na extremidade
            int estaNaExtremidade1 = verificaIndiceExtremidade(String.valueOf(posicaoCaptura));
            int estaNaExtremidade2 = verificaIndiceExtremidade(String.valueOf(posicaoCaptura - 4));
            //caso o numero calculado ja esteja na extremidade
            if (estaNaExtremidade1 != 0 || estaNaExtremidade2 != 0 || estaExtremidade0 == 4) {
                //verifica se alguem ocupa a posicao
                if (estaNaExtremidade1 == 4 || estaNaExtremidade1 == 3) {
                    posicoesCapturaDireitaPreta.add(String.valueOf(posicaoCaptura));
                    System.out.println("Inserido na lista:" + posicaoCaptura);
                }
                cond = false;
                //caso a posicao de captura não esteja ainda na extremidade -deve continuar verificando
            } else {
                posicoesCapturaDireitaPreta.add(String.valueOf(posicaoCaptura));
                quantidadeCaptura++;
            }
            cont++;
            cond = false;
        }
        quantidadeCaptura = 0;

        System.out.println("POSICOES PARA A CAPTURA A DIREITA:" + posicoesCapturaDireitaPreta.size());
        return posicoesCapturaDireitaPreta;
    }//capturaDamaDireita
}//No//No