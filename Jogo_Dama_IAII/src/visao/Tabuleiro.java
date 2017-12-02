/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.Captura;
import controle.Escolha;
import controle.Movimento;
import controle.RetornoPecas;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import modelo.Pecas;

/**
 *
 * @author StackHolder
 */
public class Tabuleiro implements ActionListener {

    private GridLayout layout;
    private JFrame base;
    private JComboBox escolha;
    private JButton[][] matriz;
    private int posx;
    private int posy;
    private Pecas pE = new Pecas();
    private Movimento movimentoPecas = new Movimento();
    private Escolha escolhe = new Escolha();
    private int profundidadeArvore = 2;
    private Captura captura = new Captura();
    private ArrayList<String> posicoesPecaBranca = new ArrayList<>();
    private ArrayList<String> posicoesPecaPreta = null;//new ArrayList<>();
    private ArrayList<String> posicaoDamasPreta = new ArrayList<>();
    private ArrayList<String> posicaoDamasBranca = new ArrayList<>();
    private String[] extremidadeEsquerda = {"9", "18", "27", "36"};
    private String[] extremidadeDiretita = {"1", "10", "19", "28"};
    private String[] extremidadeRodape = {"1", "2", "3", "4"};
    private String[] extremidadeCabecalho = {"33", "34", "35", "36"};
    private String[] posicoesASeremJogadas;
    private String indiceAnterior;
    private int errosPorJogada = 0;
    private int quantidadePodeSerJogada = 0;
    private String pecaJogada;
    private String indiceAtual;
    private int posAnteriorI;
    private int posAnteriorJ;
    private int posAtualI;
    private int posAtualJ;
    private boolean correspondeAoSegundoClik = false;
    private int jogador = 1;
    private int jogadasporVez = 0;
    private boolean primeiraJogada = true;
    RetornoPecas rp = new RetornoPecas();
    private boolean podeCapturar;
    private String indiceCaptura = "";

    Tabuleiro() {

        desenhaTabuleiro();
    }

    public void desenhaTabuleiro() {

        base = new JFrame("");
        base.setLocation(posx, posy);
        base.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        base.setVisible(true);
        layout = new GridLayout(8, 8);

        base.setTitle("Jogo Damas");
        base.setLayout(layout);
        addBotoes();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                base.add(matriz[i][j]);
            }
        }
        base.setSize(430, 430);
        base.show();



        //caso seja a primeira jogada -  chama jogada inicial
        if (primeiraJogada) {
            pE.possicaoPecaInicial(matriz, pE.getPecaBranca(), pE.getPecaPreta());
            pE.jogadasIniciaisBranca(posicoesPecaBranca);
            this.posicoesPecaPreta = pE.jogadasIniciaisPreta();



            movimentoPecas.jogadaInicial(posicoesPecaBranca, matriz, pE.getPecaBranca());
            primeiraJogada = false;
            this.jogador = pE.mudaJogador(jogador, base);


        }



        // movimentoPecas.jogadaInicial(this.extremidadeRodape, this.extremidadeEsquerda, this.extremidadeCabecalho, this.extremidadeDiretita, this.posicoesPecaBranca, this.posicoesPecaPreta, this.posicaoDamasBranca, this.posicaoDamasPreta, "1", matriz, pecaBranca);
        // movimentoPecas.jogadaInicial(posicoesPecaPreta, matriz,pE.getPecaPreta());
    }

    public void addBotoes() {

        matriz = new JButton[8][8];
        int cont = 0;

        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                cont = 0;
            } else {
                cont = 1;
            }

            for (int j = 0; j < 8; j++) {
                // instancia os botoes
                matriz[i][j] = new JButton("");
                if (cont % 2 == 0) {
                    matriz[i][j].setBackground(Color.gray);
                    matriz[i][j].addActionListener(this);
                } else {
                    matriz[i][j].setBackground(Color.DARK_GRAY);
                    matriz[i][j].addActionListener(this);
                    matriz[i][j].removeActionListener(this);
                }
                cont++;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // verifica se houve alguma marcação
                if (e.getSource().equals(matriz[i][j])) {
                    //verifica se corresponde ao 1º click - (peça  que será modificada) 
                    if (jogadasporVez == 0) {
                        System.out.println("Click de escolha de posicao");
                        this.pecaJogada = "";
                        this.pecaJogada = movimentoPecas.ConverteMatrizEmIndice(matriz, (i + "," + j));
                        System.out.println("Peca Jogada a ser mudada = " + this.pecaJogada);
                        this.indiceAnterior = this.pecaJogada;
                        this.posAnteriorI = i;
                        this.posAnteriorJ = j;
                        this.posicoesASeremJogadas = movimentoPecas.retornaPosicaoJogadaASerRealizada(this.pecaJogada, false, this.jogador, extremidadeRodape, extremidadeEsquerda, extremidadeCabecalho, extremidadeDiretita);
                        System.out.println("Tamanho das quant de posicoes" + this.posicoesASeremJogadas.length);
                        this.podeCapturar = verificaPossibilidadeCaptura(this.posicoesASeremJogadas, posicoesPecaBranca, posicoesPecaPreta, posicaoDamasBranca, posicaoDamasPreta, pecaJogada);
                        //caso seja válido a captura  
                        if (podeCapturar) {
                            JOptionPane.showMessageDialog(null, "Válida a jogada de captura");
                        }

                        jogadasporVez++;
                    } else {//caso seja o segundo click do usuário - posicao para onde será modificada

                        String jogadaAtual = "";
                        jogadaAtual = movimentoPecas.ConverteMatrizEmIndice(matriz, (i + "," + j));
                        int quemOcupa = movimentoPecas.verificaQuemOcupouAPosicao(posicoesPecaBranca, posicoesPecaPreta, posicaoDamasBranca, posicaoDamasPreta, jogadaAtual);
                        // verificar se a jogada a ser realizada pertence ao array de jogadas possiveis

                        if (movimentoPecas.verificaJogadaComAsPossiveis(this.posicoesASeremJogadas, jogadaAtual, movimentoPecas.getPossibilidadePecasJogadas())) {
                            System.out.println("ENTROU VERIFICA JOGADA COM AS POSSIVEIS");
                            // verificar se a posicao disponivel não esta ocupada
                            if (quemOcupa == 0) {
                                System.out.println("ENTROU QUEM OCUPA ");
                                //muda o icone da peça para null - peca deixa de existir na posicao
                                matriz[posAnteriorI][posAnteriorJ].setIcon(null);
                                //insere a peca preta na  nova posicao 
                                movimentoPecas.mapeiaRealizaJogadasIndice(jogadaAtual, matriz, pE.getPecaPreta());
                                movimentoPecas.insereRemovePosicoesNaLista(posicoesPecaPreta, jogadaAtual, indiceAnterior);
                                //pedra branca é removida
                                if (this.podeCapturar){
                                movimentoPecas.mapeiaRealizaJogadasIndice(this.indiceCaptura, matriz, null);
                                posicoesPecaBranca.remove(this.indiceCaptura);                                    
                                this.podeCapturar=false;
                                }
                              
                                this.jogadasporVez = 0;
                                jogadasporVez = 0;
                                this.jogador = pE.mudaJogador(this.jogador, base);


                                //apos realizada a jogada - chama a jogada da máquina



                                try {
                                    // this.profundidadeArvore++;
                                    rp = movimentoPecas.realizaJogada(posicoesPecaPreta, posicoesPecaBranca, posicaoDamasBranca, posicaoDamasPreta, matriz, pE.getPecaBranca(), this.profundidadeArvore, '1', jogadaAtual);
                                    posicoesPecaPreta = rp.posicoesPecaPreta;
                                    posicoesPecaBranca = rp.posicoesPecaBranca;
                                    posicaoDamasBranca = rp.posicaoDamasBranca;
                                    posicaoDamasPreta = rp.posicaoDamasPreta;
                                    
                                    
                                  verificaPossuiDamasBrancas(posicoesPecaBranca,extremidadeRodape,posicaoDamasBranca); 
                                  verificaPossuiDamasPretas(posicoesPecaPreta,extremidadeCabecalho,posicaoDamasPreta); 
                                   


                                    atualizaTabuleiro(posicoesPecaPreta, posicoesPecaBranca, posicaoDamasBranca, posicaoDamasPreta, matriz, pE.getPecaBranca(), pE.getPecaPreta(),pE.getDamaBranca(),pE.getDamaPreta());
                                    this.jogador = pE.mudaJogador(this.jogador, base);
                                } catch (CloneNotSupportedException ex) {
                                    Logger.getLogger(Tabuleiro.class.getName()).log(Level.SEVERE, null, ex);
                                }

//                    //caso a posicao esteja ocupada
                            } else {
//                        //verifica se quem ocupa é adversário ou não
                                //caso seja posicao ocupada por um adversário
                                if (quemOcupa == 1 || quemOcupa == 2) {

                                    JOptionPane.showMessageDialog(null, "Posicao ocupada Peca Adversária");
                                    jogadasporVez = 0;



                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Jogada não permitida");
                        }

                    }
                }
            }

        }
    }

    public boolean verificaPossibilidadeCaptura(String[] posicoesASeremJogadas, ArrayList<String> posicoesPecaBranca, ArrayList<String> posicoesPecaPreta, ArrayList<String> posicaoDamasBranca, ArrayList<String> posicaoDamasPreta, String jogadaAtual) {
        System.out.println("ENTROU VERIFICA POSSIBILIDADE DE CAPTURA");
        String[] posicoesASeremCapturadas;
        int quemOcupa1 = 0;
        int quemOcupa2 = 0;
        boolean capturar = false;
        System.out.println("POSICAO A SER JOGADA = " + posicoesASeremJogadas[0]+" e "+posicoesASeremJogadas[1]);
        //verifica se a quantidade de pecas a ser jogada é 1
        if (movimentoPecas.getPossibilidadePecasJogadas() == 1) {
            System.out.println("ENTROU QUANTIDADE DE PECAS IGUAL A 1");
            //verifica se esta peca a ser jogada esta ocupada     
            quemOcupa1 = movimentoPecas.verificaQuemOcupouAPosicao(posicoesPecaBranca, posicoesPecaPreta, posicaoDamasBranca, posicaoDamasPreta, posicoesASeremJogadas[0]);
            //caso esteja ocupada pela peca adversária 
            if (quemOcupa1 == 1 || quemOcupa1 == 2) {
                System.out.println("ENTROU QUEM OCUPA POSICAO 1 E 2");
                //verifica qual extremidade ela esta     
                int ext = movimentoPecas.verificaIndiceExtremidade(jogadaAtual, this.extremidadeRodape, this.extremidadeEsquerda, this.extremidadeCabecalho, this.extremidadeDiretita);
                if (ext == 4) { //caso esteja na extremidade 4
                    System.out.println("ENTROU EXTREMIDADE = 4");
                    int quemOcupaAtual = movimentoPecas.verificaQuemOcupouAPosicao(posicoesPecaBranca, posicoesPecaPreta, posicaoDamasBranca, posicaoDamasPreta, String.valueOf(Integer.valueOf(posicoesASeremJogadas[0])+5));
                    //verifica se a peca a frente esta ocupada
                    if (quemOcupaAtual == 0) {
                        this.posicoesASeremJogadas[1] = (String.valueOf(Integer.valueOf(posicoesASeremJogadas[0]) + 5));
                        this.indiceCaptura=(posicoesASeremJogadas[0]);
                        movimentoPecas.setPossibilidadePecasJogadas(2);
                        capturar = true;
                    }
                } else {
                    if (ext == 2) { //caso esteja na extremidade 4
                        System.out.println("ENTROU EXTREMIDADE = 2");
                        int quemOcupaAtual = movimentoPecas.verificaQuemOcupouAPosicao(posicoesPecaBranca, posicoesPecaPreta, posicaoDamasBranca, posicaoDamasPreta, String.valueOf(Integer.valueOf(posicoesASeremJogadas[0]) + 4));
                        //verifica se a peca a frente esta ocupada
                        if (quemOcupaAtual == 0) {
                            this.posicoesASeremJogadas[1] = (String.valueOf(Integer.valueOf(posicoesASeremJogadas[0]) + 4));
                            movimentoPecas.setPossibilidadePecasJogadas(2);
                            this.indiceCaptura=(posicoesASeremJogadas[0]);
                           capturar = true;
                        }
                    }
                }
            }
        } else {
            quemOcupa1 = movimentoPecas.verificaQuemOcupouAPosicao(posicoesPecaBranca, posicoesPecaPreta, posicaoDamasBranca, posicaoDamasPreta, posicoesASeremJogadas[0]);
            quemOcupa2 = movimentoPecas.verificaQuemOcupouAPosicao(posicoesPecaBranca, posicoesPecaPreta, posicaoDamasBranca, posicaoDamasPreta, posicoesASeremJogadas[1]);
            //
            System.out.println("RETORNO QUEM OCUPA"+quemOcupa1);
            System.out.println("QUEM OCUPA1: "+quemOcupa1+"POSICAO ANALIZADA:"+posicoesASeremJogadas[0]);
            System.out.println("QUEM OCUPA2: "+quemOcupa2+"POSICAO ANALIZADA:"+posicoesASeremJogadas[1]);
            if (quemOcupa1 == 1 || quemOcupa1 == 2) {
                //verifica qual extremidade ela esta     
                int quemOcupaAtual = movimentoPecas.verificaQuemOcupouAPosicao(posicoesPecaBranca, posicoesPecaPreta, posicaoDamasBranca, posicaoDamasPreta, (String.valueOf(Integer.valueOf(posicoesASeremJogadas[0])+5)));
                int ext= movimentoPecas.verificaIndiceExtremidade(posicoesASeremJogadas[0], extremidadeRodape, extremidadeEsquerda, extremidadeCabecalho, extremidadeDiretita);
                
                //verifica se a peca a frente esta ocupada
                if (quemOcupaAtual == 0 && ext!=2) {
                    System.out.println("ENTROU NINGUEM OCUPA POSICAO DE CAPTURA");
                    this.posicoesASeremJogadas[2] = (String.valueOf(Integer.valueOf(posicoesASeremJogadas[0]) + 5));
                    movimentoPecas.setPossibilidadePecasJogadas(3);
                    this.indiceCaptura=(posicoesASeremJogadas[0]);                        
                    capturar = true;
                }
            } else {
                if (quemOcupa2 == 1 || quemOcupa2 == 2) {
                    int quemOcupaAtual = movimentoPecas.verificaQuemOcupouAPosicao(posicoesPecaBranca, posicoesPecaPreta, posicaoDamasBranca, posicaoDamasPreta, String.valueOf(Integer.valueOf(posicoesASeremJogadas[1]) + 4));
                    System.out.println("POSICAO VERIFICADA"+String.valueOf(Integer.valueOf(posicoesASeremJogadas[1]) + 4));
                    int ext= movimentoPecas.verificaIndiceExtremidade(posicoesASeremJogadas[1], extremidadeRodape, extremidadeEsquerda, extremidadeCabecalho, extremidadeDiretita);
                    System.out.println("QUEM OCUPA POSICAO A SER CAPTURADA: "+quemOcupaAtual);
                    //verifica se a peca a frente esta ocupada
                    if (quemOcupaAtual == 0 && ext!=4) {
                        this.posicoesASeremJogadas[2] = ( String.valueOf(Integer.valueOf(posicoesASeremJogadas[1])+4));
                        movimentoPecas.setPossibilidadePecasJogadas(3);
                        this.indiceCaptura=(posicoesASeremJogadas[1]);
                       capturar = true;
                    }
                }
            }
        }
        System.out.println("SAIDA PODE CAPTURAR = " + capturar);
        return capturar;
    }
    
     public void  verificaPossuiDamasBrancas(ArrayList<String> listaBranca, String [] extremidadeAbaixo,ArrayList<String> listaDamasBrancas){
      String indiceAserModificadoParaDama="";
      
         for (int i = 0; i < listaBranca.size(); i++) {
             for (int j = 0; j < extremidadeAbaixo.length; j++) {
                 if (listaBranca.get(i).equals(extremidadeAbaixo[j])) {
                     indiceAserModificadoParaDama = listaBranca.get(i);
                 }
             }
         }

         listaBranca.remove(indiceAserModificadoParaDama);
         listaDamasBrancas.add(indiceAserModificadoParaDama);
    
    
   
    }
      public void  verificaPossuiDamasPretas(ArrayList<String> listaPreta, String [] extremidadeAcima,ArrayList<String> listaDamasPretas){
      String indiceAserModificadoParaDama="";
      
         for (int i = 0; i < listaPreta.size(); i++) {
             for (int j = 0; j < extremidadeAcima.length; j++) {
                 if (listaPreta.get(i).equals(extremidadeAcima[j])) {
                     indiceAserModificadoParaDama = listaPreta.get(i);
                 }
             }
         }

         listaPreta.remove(indiceAserModificadoParaDama);
         listaDamasPretas.add(indiceAserModificadoParaDama);
    
    
   
    }
    
    
    
    

    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro();
    }

    public void setQuantidadePodeSerJogada(int quantidadePodeSerJogada) {
        this.quantidadePodeSerJogada = quantidadePodeSerJogada;
    }

    public void atualizaTabuleiro(ArrayList<String> listaPretas, ArrayList<String> listaBrancas, ArrayList<String> listaDamasBrancas, ArrayList<String> listaDamasPretas, JButton[][] matrizTab, ImageIcon peca1, ImageIcon peca2,ImageIcon damabranca,ImageIcon damapreta) {
        for (int i = 1; i <= 32; i++) {

            if (i != 5 && i != 14 && i != 23 && i != 32) {
                movimentoPecas.mapeiaRealizaJogadasIndice(String.valueOf(i), matrizTab, null);
            }
        }
        System.out.println("POSICOES PECA BRANCA");
        for (int i = 0; i < listaBrancas.size(); i++) {
            movimentoPecas.mapeiaRealizaJogadasIndice(listaBrancas.get(i), matrizTab, peca1);

            if (i != listaBrancas.size() - 1) {
                System.out.print("" + listaBrancas.get(i) + ",");
            } else {
                System.out.println("" + listaBrancas.get(i) + "");
            }
        }
        System.out.println("POSICOES PECA PRETA");
        for (int i = 0; i < listaPretas.size(); i++) {
            movimentoPecas.mapeiaRealizaJogadasIndice(listaPretas.get(i), matrizTab, peca2);
            if (i != listaPretas.size() - 1) {
                System.out.print("" + listaPretas.get(i) + ",");
            } else {
                System.out.print("" + listaPretas.get(i) + " ");
            }
          }
        System.out.println("");
        if (!listaDamasBrancas.isEmpty()){
         System.out.println("POSICOES DAMA BRANCA");
        for (int i = 0; i < listaDamasBrancas.size(); i++) {
            movimentoPecas.mapeiaRealizaJogadasIndice(listaDamasBrancas.get(i), matrizTab,damabranca);

            if (i != listaBrancas.size() - 1) {
                System.out.print("" + listaDamasBrancas.get(i) + ",");
            } else {
                System.out.println("" + listaDamasBrancas.get(i) + "");
            }
        }
                   }
        
        System.out.println("");
        if (!listaDamasPretas.isEmpty()){
         System.out.println("POSICOES DAMA PRETA");
        for (int i = 0; i < listaDamasPretas.size(); i++) {
            movimentoPecas.mapeiaRealizaJogadasIndice(listaDamasPretas.get(i), matrizTab,damapreta);

            if (i != listaDamasPretas.size() - 1) {
                System.out.print("" + listaDamasPretas.get(i) + ",");
            } else {
                System.out.println("" + listaDamasPretas.get(i) + "");
            }
        }
            System.out.println("");
        }
        
        
        

    }
}
