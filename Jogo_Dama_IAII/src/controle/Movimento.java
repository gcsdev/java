/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author StackHolder
 */
public class Movimento {

     No Arvore = new No();
     private int possibilidadePecasJogadas;
   
    /**
     * Método utilizado para realizar as jogadas a partir de índices
     *
     * @param indice - indice correspondente a jogada a ser feita
     * @param matrizTab - posicao da matri\ que será modificado @peca - podendo
     * ser branca ou preta
     *
     */
    public String mapeiaRealizaJogadasIndice(String indice, JButton[][] matrizTab, ImageIcon peca) {
      //  System.out.println("Jogada a ser realizada: " + indice);
        switch (indice) {
            case "1":
                matrizTab[7][7].setIcon(peca);
                break;
            case "2":
                matrizTab[7][5].setIcon(peca);
                break;
            case "3":
                matrizTab[7][3].setIcon(peca);
                break;
            case "4":
                matrizTab[7][1].setIcon(peca);
                break;
            case "6":
                matrizTab[6][6].setIcon(peca);
                break;
            case "7":
                matrizTab[6][4].setIcon(peca);
                break;
            case "8":
                matrizTab[6][2].setIcon(peca);
                break;
            case "9":
                matrizTab[6][0].setIcon(peca);
                break;
            case "10":
                matrizTab[5][7].setIcon(peca);
                break;
            case "11":
                matrizTab[5][5].setIcon(peca);
                break;
            case "12":
                matrizTab[5][3].setIcon(peca);
                break;
            case "13":
                matrizTab[5][1].setIcon(peca);
                break;
            case "15":
                matrizTab[4][6].setIcon(peca);
                break;
            case "16":
                matrizTab[4][4].setIcon(peca);
                break;
            case "17":
                matrizTab[4][2].setIcon(peca);
                break;
            case "18":
                matrizTab[4][0].setIcon(peca);
                break;
            case "19":
                matrizTab[3][7].setIcon(peca);
                break;
            case "20":
                matrizTab[3][5].setIcon(peca);
                break;
            case "21":
                matrizTab[3][3].setIcon(peca);
                break;
            case "22":
                matrizTab[3][1].setIcon(peca);
                break;
            case "24":
                matrizTab[2][6].setIcon(peca);
                break;
            case "25":
                matrizTab[2][4].setIcon(peca);
                break;
            case "26":
                matrizTab[2][2].setIcon(peca);
                break;
            case "27":
                matrizTab[2][0].setIcon(peca);
                break;
            case "28":
                matrizTab[1][7].setIcon(peca);
                break;
            case "29":
                matrizTab[1][5].setIcon(peca);
                break;
            case "30":
                matrizTab[1][3].setIcon(peca);
                break;
            case "31":
                matrizTab[1][1].setIcon(peca);
                break;
            case "33":
                matrizTab[0][6].setIcon(peca);
                break;
            case "34":
                matrizTab[0][4].setIcon(peca);
                break;
            case "35":
                matrizTab[0][2].setIcon(peca);
                break;
            case "36":
                matrizTab[0][0].setIcon(peca);
                break;


        }
        return "";
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

    /**
     * Método utilizado para retirar e inserir as posições na lista
     *
     * @param lista - lista de posições a ser atualizada
     * @param indiceInserido - indice da posicao atual
     * @param indiceRemovido - indice da posicao anterior
     */
    public void insereRemovePosicoesNaLista(List<String> lista, String indiceInserido, String indiceRemovido) {
        Iterator i = lista.iterator();

        //retira posicao anterior da lista 
        lista.remove(indiceRemovido);
   //     System.out.println("Removida a peca da posicao:" + indiceRemovido);

        //insere posicao atual na lista
        lista.add(indiceInserido);
    //    System.out.println("Inserida a peca da posicao:" + indiceInserido);
    }

    /**
     * Método utilizado para verificar quem esta ocupando a posicao
     *
     * @param jogadasPecasBrancas -lista contendo as pocições ocupadas por pela
     * peca branca
     * @param jogadasPecasPretas - lista contendo as pocições ocupadas por pela
     * peca preta
     * @param indice - índice da jogada a ser verificado
     * @return jogador: 1 posicao ocupado pelo computador, -1 posicao ocupada
     * pelo usuário - 0posição não esta ocupada
     */
    public int verificaQuemOcupouAPosicao(List<String> jogadasPecasBrancas, List<String> jogadasPecasPretas, List<String> damasBrancas, List<String> damasPretas, String indice) {
    //    System.out.println("Entrou verifica posicao");
        int jogador = 0;
        Iterator i = jogadasPecasBrancas.iterator();
        Iterator j = jogadasPecasPretas.iterator();
        Iterator k = damasBrancas.iterator();
        Iterator l = damasPretas.iterator();

        //verifica na lista contendo as posições ocupadas pelas pecas brancas 
        while (i.hasNext()) {
            String valor = String.valueOf(i.next());
            if (valor.equals(indice)) {
                jogador = 1;
            }
        }
        if (!damasBrancas.isEmpty()) {
            // verifica na lista de damas brancas
            while (k.hasNext()) {
                String valor = String.valueOf(k.next());
                if (valor.equals(indice)) {
                    jogador = 2;
                }
            }
        }
        //verifica na lista contendo as posições ocupadas pelas pecas pretas 
        while (j.hasNext()) {
            String valor = String.valueOf(j.next());
            if (valor.equals(indice)) {
                jogador = -1;
            }
        }

        if (!damasPretas.isEmpty()) {
            //verifica na lista contendo as posições ocupadas pelas damas pretas 
            while (l.hasNext()) {
                String valor = String.valueOf(l.next());
                if (valor.equals(indice)) {
                    jogador = -2;
                }
            }
        }
      //  System.out.println("Retorno verifica se esta ocupada:" + jogador);
        return jogador;

    }

    /**
     * Método responsavel por retornar indices possiveis a partir de um clicado
     */
    public String[] retornaPosicaoJogadaASerRealizada(String indice, boolean eDama, int jogador, String[] baixo, String[] esquerda, String[] acima, String[] direita) {
        String[] posicoesPossiveis = new String[8];
        int numExtremidade = this.verificaIndiceExtremidade(indice, baixo, esquerda, acima, direita);
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
                //    System.out.println("Entrou numExtremidade=2");
                    Integer valor = (Integer.valueOf(indice) - Integer.valueOf(5));
                    posicoesPossiveis[0] = String.valueOf(valor);
                //    System.out.println("Jogar  na posicao:" + valor);
                    this.possibilidadePecasJogadas = 1;
                } else {
                    if (numExtremidade == 4) {
                    //    System.out.println("Entrou numExtremidade=4");
                        Integer valor = (Integer.valueOf(indice) - Integer.valueOf(4));
                        posicoesPossiveis[0] = String.valueOf(valor);
                    //    System.out.println("Jogar  na posicao:" + valor);
                        this.possibilidadePecasJogadas = 1;
                    } else {
                        if (numExtremidade == 3) {
                     //       System.out.println("Entrou numExtremidade=3");
                            Integer valor = (Integer.valueOf(indice) - Integer.valueOf(5));
                            posicoesPossiveis[0] = String.valueOf(valor);
                   //         System.out.println("Jogar  na posicao:" + valor);
                            valor = Integer.valueOf(indice) - Integer.valueOf(4);
                            posicoesPossiveis[1] = String.valueOf(valor);
                    //        System.out.println("Jogar  na posicao:" + valor);
                            this.possibilidadePecasJogadas = 2;
                        } else {
                            if (numExtremidade == 1) {
                       //         System.out.println("Parabéns Chegou a dama");
                            } else {
                      //          System.out.println("Entrou numExtremidade=0");
                                Integer valor = (Integer.valueOf(indice) - Integer.valueOf(5));
                       //         System.out.println("Jogar  na posicao:" + valor);
                                posicoesPossiveis[0] = String.valueOf(valor);
                                valor = Integer.valueOf(indice) - Integer.valueOf(4);
                                posicoesPossiveis[1] = String.valueOf(valor);
                       //         System.out.println("Jogar  na posicao:" + valor);
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
                    Integer valor = (Integer.valueOf(indice) + Integer.valueOf(4));
                    posicoesPossiveis[0] = String.valueOf(valor);
               //     System.out.println("Jogar  na posicao:" + valor);
                    this.possibilidadePecasJogadas = 1;
                } else {
                    if (numExtremidade == 4) {
                        Integer valor = (Integer.valueOf(indice) + Integer.valueOf(5));
                        posicoesPossiveis[0] = String.valueOf(valor);
                      //  System.out.println("Jogar  na posicao:" + valor);
                        this.possibilidadePecasJogadas = 1;
                    } else {
                        if (numExtremidade == 3) {
                //            System.out.println("Parabén você chegou a dama");
                        } else {
                            if (numExtremidade == 1) {
                                if (!indice.equals("1")) {
                                    Integer valor = (Integer.valueOf(indice) + Integer.valueOf(5));
                                    posicoesPossiveis[0] = String.valueOf(valor);
                              //      System.out.println("Jogar  na posicao:" + valor);
                                    valor = (Integer.valueOf(indice) + Integer.valueOf(4));
                                    posicoesPossiveis[1] = String.valueOf(valor);
                           //         System.out.println("Jogar  na posicao:" + valor);
                                    this.possibilidadePecasJogadas = 2;
                                } else {
                                    Integer valor = (Integer.valueOf(indice) + Integer.valueOf(5));
                                    posicoesPossiveis[0] = String.valueOf(valor);
                         //           System.out.println("Jogar  na posicao:" + valor);
                                    this.possibilidadePecasJogadas = 1;
                                }
                                //caso não esteja em nenhuma extremidade
                            } else {
                        //        System.out.println("Entrou numExtremidade=0");
                                Integer valor = (Integer.valueOf(indice) + Integer.valueOf(5));
                        //        System.out.println("Jogar  na posicao:" + valor);
                                posicoesPossiveis[0] = String.valueOf(valor);
                                valor = Integer.valueOf(indice) + Integer.valueOf(4);
                                posicoesPossiveis[1] = String.valueOf(valor);
                     //           System.out.println("Jogar  na posicao:" + valor);
                                this.possibilidadePecasJogadas = 2;
                            }
                        }

                    }

                }
            }
        }
        return posicoesPossiveis;
    }

    /**
     * Método utilizado para verificar se o índice a ser jogado esta dentro do
     * array de jogadas possiveis
     */
    public boolean verificaJogadaComAsPossiveis(String[] jogadas, String indice, int quant) {
        boolean jogaIndice = false;
      //  System.out.println("QUANTIDADE = "+quant);
        for (int i = 0; i < quant; i++) {
            if (jogadas[i].equals(indice)) {
                jogaIndice = true;
            }
        }
        return jogaIndice;
    }

    /**
     * Método utilizado para realizar a jogada inicial
     *
     * @param lista - lista da jogada de pedras pretas
     */
    public void jogadaInicial(List<String> lista, JButton[][] matriz, ImageIcon peca) {
        System.out.println("Realizando jogada Inicial");
        String posicaoAnterior = "24";
        String posicaoAtual = "19";
        //remove o indice da posicao anteriorda lista de jogadas
        lista.remove(posicaoAnterior);
        retiraIconedaPeca(2, 6, matriz);
        //insere o indice da posicao atual na lista de jogadas
  
        mapeiaRealizaJogadasIndice(posicaoAtual, matriz, peca);
        lista.add(posicaoAtual);

    }

    /**
     * Para cada peca movida é necessário retirar o icone equivalente a peca -
     * este méto é respon´savel para isto
     *
     */
    public void retiraIconedaPeca(int linha, int coluna, JButton[][] matrizTab) {
        System.out.println("Retira icone");
        matrizTab[linha][coluna].setIcon(null);

    }

    /**
     * Método utilizado para criair uma matriz mapeada (com as posições que
     * foram jogadas) para a utilização na arvore
     */
   public RetornoPecas realizaJogada(ArrayList<String> listaPretas, ArrayList<String> listaBrancas, ArrayList<String> listaDamasBrancas, ArrayList<String> listaDamasPretas, JButton[][] matriz, ImageIcon peca, int profundidade, char jogador,String indiceJogada) throws CloneNotSupportedException {
        //Método para a escolha do indice da jogada
        int[][] tab = new int[8][8];
        int[][] tabRetorno = new int[8][8];

        ArrayList<String> novaListPretas = new ArrayList<>();
        ArrayList<String> novaListBrancas = new ArrayList<>();
        ArrayList<String> novaListDamasPretas = new ArrayList<>();
        ArrayList<String> novaListDamasBrancas = new ArrayList<>();

        tab= mapeiaVetor(tab, listaPretas, 1);
        tab=mapeiaVetor(tab, listaBrancas, -1);
        tab=mapeiaVetor(tab, listaDamasBrancas, -11);
        tab=mapeiaVetor(tab, listaDamasPretas, 11);
        
       //  System.out.println("ANTES modificar[]");
//        for(int k=0;k<listaBrancas.size(); k++){
//            System.out.println(""+ listaBrancas.get(k));
//        }
//      
     //   System.out.println("VALOR PROFUNDIDADE="+profundidade);
        Arvore.proximaJogada(tab, profundidade, jogador, tabRetorno,indiceJogada);
      
        voltaLista(tabRetorno, novaListPretas, 1);
        voltaLista(tabRetorno, novaListBrancas, -1);
        voltaLista(tabRetorno, novaListDamasPretas, 11);
        voltaLista(tabRetorno, novaListDamasBrancas, -11);

        RetornoPecas rt = new RetornoPecas();
        rt.posicoesPecaBranca = novaListBrancas;        
        rt.posicoesPecaPreta= novaListPretas;
        rt.posicaoDamasBranca = novaListDamasBrancas;
        rt.posicaoDamasPreta = novaListDamasPretas;
        
        return rt;

    }//realizaJogada

    /**
     * Método utilizado para mapear a matriz para a utilização na árvore 1 para
     * para peças Pretas 11 para para peças com Damas Pretas 0 para para peças
     * Brancas 00 para para peças com damas Brancas
     */
    public int[][] mapeiaVetor(int tab[][], List<String> lista, int marca) {
      
        for (int k = 0; k < lista.size(); k++) {
            switch (lista.get(k)) {
                case "36":
                    tab[0][0] = marca;
                    break;
                case "35":
                    tab[0][2] = marca;
                    break;
                case "34":
                    tab[0][4] = marca;
                    break;
                case "33":
                    tab[0][6] = marca;
                    break;
                case "31":
                    tab[1][1] = marca;
                    break;
                case "30":
                    tab[1][3] = marca;
                    break;
                case "29":
                    tab[1][5] = marca;
                    break;
                case "28":
                    tab[1][7] = marca;
                    break;
                case "27":
                    tab[2][0] = marca;
                    break;
                case "26":
                    tab[2][2] = marca;
                    break;
                case "25":
                    tab[2][4] = marca;
                    break;
                case "24":
                    tab[2][6] = marca;
                    break;
                case "22":
                    tab[3][1] = marca;
                    break;
                case "21":
                    tab[3][3] = marca;
                    break;
                case "20":
                    tab[3][5] = marca;
                    break;
                case "19":
                    tab[3][7] = marca;
                    break;
                case "18":
                    tab[4][0] = marca;
                    break;
                case "17":
                    tab[4][2] = marca;
                    break;
                case "16":
                    tab[4][4] = marca;
                    break;
                case "15":
                    tab[4][6] = marca;
                    break;
                case "13":
                    tab[5][1] = marca;
                    break;
                case "12":
                    tab[5][3] = marca;
                    break;
                case "11":
                    tab[5][5] = marca;
                    break;
                case "10":
                    tab[5][7] = marca;
                    break;
                case "9":
                    tab[6][0] = marca;
                    break;
                case "8":
                    tab[6][2] = marca;
                    break;
                case "7":
                    tab[6][4] = marca;
                    break;
                case "6":
                    tab[6][6] = marca;
                    break;
                case "4":
                    tab[7][1] = marca;
                    break;
                case "3":
                    tab[7][3] = marca;
                    break;
                case "2":
                    tab[7][5] = marca;
                    break;
                case "1":
                    tab[7][7] = marca;
                    break;
            }//switch
        }//for
        return tab;
    }//mapeaVetor

    private void voltaLista(int[][] tabRetorno, ArrayList<String> novaList, int marca) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tabRetorno[i][j] == marca) {
                    novaList.add(voltaIndice("" + i + "," + j));
                }//if
            }//for i
        }//for j
    }//voltaLista

    private String voltaIndice(String linhaColuna) {
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
        return "erro";
    }

    /**
     * Método utilizado para verificar se o indice da jogada corresponde a
     * alguma extremidade
     *
     * @param indice - indice a ser verificado
     * @return extremidade:1 - caso esteja no rodapé
     * @return extremidade:2 - caso esteja no esquerda
     * @return extremidade:3 - caso esteja no cabecalho (parte de cima)
     * @return extremidade:4 - caso esteja no direita
     * @return extremidade:0 - caso nao esteja nas extremidades
     */
    public int verificaIndiceExtremidade(String indice, String[] baixo, String[] esquerda, String[] acima, String[] direita) {
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

    public String retornaDadosAuxiliares() {


        return "";
    }

    public int getPossibilidadePecasJogadas() {
        return possibilidadePecasJogadas;
    }

    public void setPossibilidadePecasJogadas(int possibilidadePecasJogadas) {
        this.possibilidadePecasJogadas = possibilidadePecasJogadas;
    }
    
    
    public int[] retornaLinhaColuna (String indice) {
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
                linhaColuna[1] = 3; break;
            case "13":
                linhaColuna[0] = 5;
                linhaColuna[1] = 1;
                break;
            case "15":
                linhaColuna[0] = 4;
                linhaColuna[1] = 6;break;
            case "16":
                linhaColuna[0] = 4;
                linhaColuna[1] = 4;break;
            case "17":
                linhaColuna[0] = 4;
                linhaColuna[1] = 2;
                break;
            case "18":
                linhaColuna[0] = 4;
                linhaColuna[1] = 0;break;
            case "19":
                linhaColuna[0] = 3;
                linhaColuna[1] = 7;break;
            case "20":
                linhaColuna[0] = 3;
                linhaColuna[1] = 5;break;
            case "21":
                linhaColuna[0] = 3;
                linhaColuna[1] = 3;break;
            case "22":
                linhaColuna[0] = 3;
                linhaColuna[1] = 1;break;
            case "24":
                linhaColuna[0] = 3;
                linhaColuna[1] = 6;break;
            case "25":
                linhaColuna[0] = 2;
                linhaColuna[1] = 4;break;
            case "26":
                linhaColuna[0] = 2;
                linhaColuna[1] = 2;break;
            case "27":
                linhaColuna[0] = 2;
                linhaColuna[1] = 0;break;
            case "28":
                linhaColuna[0] = 1;
                linhaColuna[1] = 7;break;
            case "29":
                linhaColuna[0] = 1;
                linhaColuna[1] = 5;break;
            case "30":
                linhaColuna[0] = 1;
                linhaColuna[1] = 3;break;
            case "31":
                linhaColuna[0] = 1;
                linhaColuna[1] = 1;break;
            case "33":
                linhaColuna[0] = 0;
                linhaColuna[1] = 6;break;
            case "34":
                linhaColuna[0] = 0;
                linhaColuna[1] = 4;break;
            case "35":
                linhaColuna[0] = 0;
                linhaColuna[1] = 2;break;
            case "36":
                linhaColuna[0] = 0;
                linhaColuna[1] = 0;break;
        }
 return linhaColuna;
    }




}//Movimento
