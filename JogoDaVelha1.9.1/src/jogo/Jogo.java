/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import sun.awt.SunHints;


/**
 * Esta classe é utilizada para criar a lógica 
 * do JOGO DA VELHA  assim como a sua  GUI do 
 * @author Gustavo Carvalho Souza
 */
public class Jogo implements ActionListener{
    
    private GridLayout layout;
    private JFrame janela;
    private JComboBox escolha;
    private int opcaoJogadores;
    private ImageIcon x;
    private ImageIcon o;
    private int posx;
    private int posy;
    private boolean cond;
    private JButton[][] matriz;
    private boolean termino=false;
    private String marcacao="";
    private int [] linhaColuna= new int [2];
    private int [] valoresFornecidos = new int [8]; 
    private int [] pontuacao= new int [3];
    private int numJogadas=0;
    private String conteudoConhecimento= "";
    private int []  jogadasRealizadas = new int [9];
    private int cont=0;
    private  int indice;
    private boolean jogaPrimeiro=true;
    private int varControle=1;
   
    private int quantJogadas;
    
   
    
    public Jogo (){
    
     desenha(); 
    
    }
    
    
    /** Método utilizado para desenhar a interface do Jogo.
     * Carrega uma imagem referente a marcação do jogador corrente (xis ou zero)
     */
    
    public void desenha (){
           
    
    janela = new JFrame(""); 
    janela.setLocation(posx,posy); 
    x=new ImageIcon("src\\jogo\\logoBCC.jpg");
    o=new ImageIcon("src\\jogo\\logoUnifal.jpg");
    janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    janela.setVisible(true);
    layout= new GridLayout(3,3);
    
    janela.setTitle("Jogo da Velha");
    janela.setLayout(layout);   
   
    addBotoes();
    for (int i =0;i<3;i++)
    {
    for (int j=0;j<3;j++){
          janela.add(matriz[i][j]);
    }
    }
    janela.setSize(350, 350);
    janela.show();
    termino = false;
    
    if (jogaPrimeiro){
        marcacao="";
    marcacao+="X";
    }else{
        marcacao="";
    marcacao+="0";
    if (varControle==1){
                try {
                    maquinaComecaJogar();
                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }
       varControle++;
    }
   
    }
    } 
    
    /* Método utilizado para construir uma matriz referente 
     * a quantidade de Grids criada (3 X 3)
     */
    
    public void addBotoes() {
        
        matriz = new JButton[3][3];
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                // instancia os botoes
                matriz[i][j] = new JButton("");
                matriz[i][j].addActionListener(this);
            }
        }
    }
    
    
    
    
    
    /** Método utilizado para verifificar se exixtem marcações
     * em uma mesma linha,iguais. Esta verificação é feita a 
     * partir da comparação de duas células
     * @return "x" ou "o" - marcação do jogador vencedor
     */
    public String verificaLinhas() {
        if ((comparaCelula(0, 0, 0, 1)) && (comparaCelula(0, 0, 0, 2))) {
            if (matriz[0][0].getIcon().equals(x)) {
                pontuacao[0]+=1; 
                return "x";
            } else {
                pontuacao[2]+=1;
                return "O";
            }
        }
        if ((comparaCelula(1, 0, 1, 1)) && (comparaCelula(1, 0, 1, 2))) {

            if (matriz[1][0].getIcon().equals(x)) {
                pontuacao[0]+=1;
                return "x";
            } else {
                pontuacao[2]+=1;
                return "O";
            }
        }
        if ((comparaCelula(2, 0, 2, 1)) && (comparaCelula(2,0, 2, 2))) {
            if (matriz[2][0].getIcon().equals(x)) {
                 pontuacao[0]+=1;
                return "x";
            } else {
                 pontuacao[2]+=1;
                return "0";
            }
        }
        return null;
    }
    /** Método utilizado para verifificar se exixtem marcações
     * em uma mesma coluna,iguais. Esta verificação é feita a 
     * partir da comparação de duas células
     * @return "x" ou "o" - marcação do jogador vencedor
     */
    
    public String verificaColunas() {
        if (comparaCelula(0, 0, 1, 0) && comparaCelula(0, 0, 2, 0)) {
            if (matriz[0][0].getIcon().equals(x)) {
               pontuacao[0]+=1;
                return "x";                
            } else {
                pontuacao[2]+=1;
                return "O";
            }
        }
        if (comparaCelula(0, 1, 1, 1) && comparaCelula(0, 1, 2, 1)) {
            if (matriz[0][1].getIcon().equals(x)) {
                pontuacao[0]+=1;
                return "x";
            } else {
                pontuacao[2]+=1;
                return "O";
            }
        }
        if (comparaCelula(0, 2, 1, 2) && comparaCelula(0, 2, 2, 2)) {
            if (matriz[0][2].getIcon().equals(x)) {
                pontuacao[0]+=1;
                return "x";
            } else {
                 pontuacao[2]+=1;
                return "O";
            }
        }
        return null;
    }
    
    /**Método utilizado para verificar se existem marcações iguais 
     * nas diagonais
     * @return "x" ou "o" - marcação do jogador vencedor
     */

    private String verificaDiagonais() {
        
        if (comparaCelula(0, 0, 1, 1) && comparaCelula(0, 0, 2, 2)) {
            if (matriz[0][0].getIcon().equals(x)) {
                pontuacao[0] += 1;
                return "x";
            } else {
                pontuacao[0] += 1;
                return "O";
            }
        }
        if (comparaCelula(0, 2, 1, 1) && comparaCelula(0, 2, 2, 0)) {
            if (matriz[0][2].getIcon().equals(x)) {
                pontuacao[0] += 1;
                return "x";
            } else {
                pontuacao[2] += 1;
                return "O";
            }
        }
        return null;
    
    }
   
    /** Método utilizado para verificar se
     * duas células sao iguais
     * @param celula11, celula12, celula21,celula22
     * @return true - caso sejam iguais duas células
     * @return false - caso não sejam iguais as duas células
     *  
     */
    
    public boolean comparaCelula(int celula11, int celula12, int celula21, int celula22) {
        if ( (matriz[celula11][celula12].getIcon()!= null) &&  (matriz[celula21][celula22] != null) ) {

            if (matriz[celula11][celula12].getIcon().equals(matriz[celula21][celula22].getIcon())) {
                return true;
            }
        }
        return false;
    }
    
    
    
    /**Método utilizado para verificar se houve algum gamhador 
     * a partir das análises de verificação de linhas, colunas
     * e diagonais
     */
    
    public void verificaGanhador (){
     String campeao="";
   
     //verifica se houve ganhador na marcação de linhas
     if ((campeao=verificaLinhas())!=null){
         JOptionPane.showMessageDialog(null,"O vencedor é:"+campeao);
         conteudoConhecimento+=campeao;
         //Dados inseridos na BaseDeConhecimento   
      String jogadasRisco = geraConteudoConhecimento(this.jogadasRealizadas, this.quantJogadas, campeao,false);
         
         try {
                insereNoBancoConhecimento(geraConteudoConhecimento(this.jogadasRealizadas, this.quantJogadas, campeao,true), true);
            } catch (IOException ex) {
                Logger.getLogger(Jogo.class.getName()).log(Level.SEVERE, null, ex);
            }
         //Dados inseridos na Base de Dados Processados
         if (!jogadasRisco.equals("")){
         try {
                insereBaseDadosProcessados(jogadasRisco, false);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
           }
            termino = true;
         
    }
     campeao="";
     //verifica se houve ganhador na marcação de colunas
     if ((campeao=verificaColunas())!=null){
         JOptionPane.showMessageDialog(null,"O vencedor é:"+campeao);
         conteudoConhecimento+=campeao;
       String jogadasRisco = geraConteudoConhecimento(this.jogadasRealizadas, this.quantJogadas, campeao,false);

         //Dados inseridos na BaseDeConhecimento   
         try {
                insereNoBancoConhecimento(geraConteudoConhecimento(this.jogadasRealizadas, this.quantJogadas, campeao,true), true);
            } catch (IOException ex) {
                Logger.getLogger(Jogo.class.getName()).log(Level.SEVERE, null, ex);
            }
         //Dados inseridos na Base de Dados Processados
         if (!jogadasRisco.equals("")){
         try {
                insereBaseDadosProcessados(jogadasRisco, false);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
           }
        
          termino = true;
     }
    campeao="";
    //Verifica se houve ganhador nas diagonais
    if ((campeao=verificaDiagonais())!=null){
     JOptionPane.showMessageDialog(null,"O vencedor é:"+campeao);
     String jogadasRisco = geraConteudoConhecimento(this.jogadasRealizadas, this.quantJogadas, campeao,false);

     conteudoConhecimento+=campeao;
    //Dados inseridos na BaseDeConhecimento   
         try {
                insereNoBancoConhecimento(geraConteudoConhecimento(this.jogadasRealizadas, this.quantJogadas, campeao,true), true);
            } catch (IOException ex) {
                Logger.getLogger(Jogo.class.getName()).log(Level.SEVERE, null, ex);
            }
         //Dados inseridos na Base de Dados Processados
           if (!jogadasRisco.equals("")){
         try {
                insereBaseDadosProcessados(jogadasRisco, true);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
           }
      
     termino = true;
    
    }
    if ( (empate()) && (campeao==null)){
   
     pontuacao[1]=0;
     JOptionPane.showMessageDialog(null,"Não houve vencedor:");
    
     pontuacao[1]+=1;
     termino = true;
    
    }
    } 
    
    
    
    /**Método utilizado para realizar uma jogada 
     * automática a partir  de análise  de jogadas
     *@param indice - conforme realizado no mapeamento 
     * 
     */    
    
    public void realizaJogadaAPartirDoIndice(String indice){
    
     switch(indice){
     case"1": matriz[0][0].setIcon(o);
              matriz[0][0].removeActionListener(this);
              break;
     
     case"2": matriz[0][1].setIcon(o);
              matriz[0][1].removeActionListener(this);
              break;
     
     case"3": matriz[0][2].setIcon(o);
              matriz[0][2].removeActionListener(this);
              break;
     
     case"4": matriz[1][0].setIcon(o);
              matriz[1][0].removeActionListener(this);
              break;
     
     case"5": matriz[1][1].setIcon(o);
              matriz[1][1].removeActionListener(this);
              break;
     
     case"6": matriz[1][2].setIcon(o);
              matriz[1][2].removeActionListener(this);
              break;
     
     case"7": matriz[2][0].setIcon(o);
              matriz[2][0].removeActionListener(this);
              break;
     
     case"8": matriz[2][1].setIcon(o);
              matriz[2][1].removeActionListener(this);
              break;
     
     case"9": matriz[2][2].setIcon(o);
              matriz[2][2].removeActionListener(this);
              break;
      }
      }
    
    /**Método utilizado para facilitar a manipulação
     * da matriz 
     * @param senteca - do tipo (0,1)
     * @return indice equivalente ao mapeamento realizado 
     */    
    
    public String converteMatrizEmIndice(String sentenca){
    
    if (sentenca.equals("0,0")) return "1"; 
    if (sentenca.equals("0,1")) return "2"; 
    if (sentenca.equals("0,2")) return "3"; 
    if (sentenca.equals("1,0")) return "4"; 
    if (sentenca.equals("1,1")) return "5"; 
    if (sentenca.equals("1,2")) return "6"; 
    if (sentenca.equals("2,0")) return "7"; 
    if (sentenca.equals("2,1")) return "8"; 
    if (sentenca.equals("2,2")) return "9"; 
     return "";
    
    }
    
    /**Método utilizado para escolher a melhor jogada a ser 
     * realizada a partir de duas jogadas realizadas anteriormente 
     * Para esta escolha são utilizados dados do arquivo contendo a 
     * relação de jogadas de risco
     * @param indice1 - indice equivalente a uma jogada realizada
     * @param indice2 - indice equivalente a uma outra jogada realizada
     * @see jogo.LeituraBC
     */
    
    public String metodoInferenciaDoisIndices(int indice1, int indice2) throws FileNotFoundException {
        String indice = "";
        LeituraBC leitura = new LeituraBC();
        ArrayList doisElementos = leitura.jogadasConhecidosDoisIndices(indice1, indice2);
        if (!doisElementos.isEmpty()) {
            Integer[] indicesValidos = (Integer[]) doisElementos.get(0);

            for (int i = 0; i < indicesValidos.length; i++) {
                if ((indicesValidos[i] != indice1) && (indicesValidos[i] != indice2)) {
                 
                    indice = String.valueOf(indicesValidos[i]);
                }
            }
        return indice;
        }
       
        return "14";
    }

    public void jogadaAutomatica (){
   
    String i= verificaJogadasRealizadas(this.jogadasRealizadas);        
    realizaJogadaAPartirDoIndice(i);
    jogadasRealizadas[this.cont]=Integer.valueOf(i);
    this.cont+=1;
    this.quantJogadas++;
    
    }
    
    
    /** Método utilizado para verificar quais jogadas foram relizadas
     * As jogadas realizadas estão sendo inseridas em um array
     *@return indice  - numero para uma nova jogada
     *@param int[]jogadas - lista de jogadas que ja foram realizadas
     */
    
    public String  verificaJogadasRealizadas(int [] jogadas ){
        boolean condicao = true;
        boolean numJogado=false;
       
        while (condicao) {
        numJogado=false;
       //Escolhe de forma randômica um indice
        this.indice = (int) (1 + (Math.random() * 9));
       //Verifica se o indice escolhido ainda não foi jogado         
            for (int j = 0; j < this.cont; j++) {
                if (this.indice == jogadas[j]) {
                    numJogado = true;
                }
            }
            if (!numJogado) {
                condicao = false;
              
                return String.valueOf(this.indice);
            }
        }
       
    return ""; // nunca irá ocorrer
    
    }
        
    /**Método utilizado para iniciar automaticamente a jogada*/ 
    
    public void maquinaComecaJogar () throws FileNotFoundException {
    LeituraBC leituraBC = new LeituraBC();       
        
    if (leituraBC.retornaEstado()){
       String indice="";
       int tam  =leituraBC.getJogadasDeRiscos().size();      
       int posicao = (int)( Math.random()*(tam-1));      
       Integer [] indiceIniciado = (Integer[]) leituraBC.getJogadasDeRiscos().get(posicao);
       indice  = String.valueOf(indiceIniciado[0]);
       this.realizaJogadaAPartirDoIndice(indice);
       this.jogadasRealizadas[this.cont]=indiceIniciado[0];
       quantJogadas++;
       mudaJogador();
       this.cont++;    
       
    }else{
    matriz[1][1].setIcon(o);
    matriz[1][1].removeActionListener(this);
    this.jogadasRealizadas[this.cont]=5;
    quantJogadas++;
    mudaJogador();
    this.cont++;
    
    }
    
    }
    
    /*Método utilizado para tratar os cliques em 
     * cada um dos botoes.
     **/
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
      

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (e.getSource().equals(matriz[i][j])) {
                    // Caso a opção escolhida seja para jogar entre dois usuários
                    if (opcaoJogadores == 1) {

                        if (marcacao.compareToIgnoreCase("X") == 0) {
                            matriz[i][j].setIcon(x);
                        } else {
                            matriz[i][j].setIcon(o);
                        }
                        matriz[i][j].removeActionListener(this);
                        verificaGanhador();
                        mudaJogador();
                    } //caso a opção escolhida seja para jogar com a máquina
                    else {
                        if (marcacao.compareToIgnoreCase("X") == 0) {
                            String parOrdenado = "";
                            matriz[i][j].setIcon(x);
                            parOrdenado = converteMatrizEmIndice(i + "," + j);
                            jogadasRealizadas[this.cont] = Integer.valueOf(parOrdenado);
                            matriz[i][j].removeActionListener(this);
                            quantJogadas += 1;
                            cont += 1;
                            verificaGanhador();
                            //Verifica se houve término do jogo
                            if (termino) {
                                novoJogo();
                                break;
                            }else{
                            mudaJogador();
                                try {
                                    // jogadaAutomatica();
                                    condicaoJogadaMaquina(this.jogadasRealizadas);
                                } catch (FileNotFoundException ex) {
                                    System.out.println(ex.getMessage());
                                }
                            
                            verificaGanhador();
                            //Verifica se houve término do jogo
                            if (termino) {
                                novoJogo();
                                break;
                            }else{
                            
                            mudaJogador();
                            }
                            }
                        }
                        
                    }
                }
            }
        }
     }
    
    
    
    /**Método utilizado para gerar uma jogada a partir 
     * de uma base de dados com as possiveis jogadas de risco registrados
     * @param jogadas - array com as jogadas realizadas
     */    
    
    public void condicaoJogadaMaquina(int [] jogadas) throws FileNotFoundException{
      
       int cont1 = this.cont-1;
       int cont2 = cont1 - 2;
       int cont3 = this.cont - 2;
       int  cont4 = cont3 - 2;
       boolean  condicao1 = true;
       boolean  condicao2 = true;
       boolean condicao3 = true;
       boolean condicao4 = true;
       
       
       if (this.cont>=3 &&(!this.jogaPrimeiro )){         

 
     //Prepara um indice de ataque para fazer a jogada
     
       while (cont3 >= 2 && condicao1) {
                cont4 = cont3 - 2;
                while (cont4 >= 0 && condicao2) {
                    boolean existeNumero=false;
                    String indiceAtaque = "";
                    indiceAtaque = metodoInferenciaDoisIndices(jogadas[cont3], jogadas[cont4]);
                   //Verifico se o indice não esta nas jogadas realizadas
                    for (int i = 0; i < this.cont; i++) {
                        if (Integer.valueOf(indiceAtaque) == jogadas[i]) {
                            existeNumero = true;
                        }
                    }
                    if (!indiceAtaque.equals("14")) {
                        if (!existeNumero) {
                            realizaJogadaAPartirDoIndice(indiceAtaque);
                            System.out.println("Jogada com indice de Ataque");
                            jogadas[this.cont] = Integer.valueOf(indiceAtaque);
                            this.cont++;
                            this.quantJogadas++;
                            condicao1 = false;
                            condicao2 = false;
                        }
                    }
                    cont4 -= 2;
                }
                cont3 -= 2;

            }
     

     //Prepara um indice de defesa para fazer a jogada
            
     if (condicao1 && condicao2) {       
        while (cont1>= 3 && condicao1) {
            cont2 = cont1 - 2;
            while (cont2 >= 1 && condicao2) {
                boolean numeroExistennte =false; 
                String indiceDefesa = "";
                System.out.println("Cont1="+cont1+"Indice1:"+jogadas[cont1]);
                System.out.println("Cont2="+cont2+"Indice2:"+jogadas[cont2]);
                indiceDefesa = metodoInferenciaDoisIndices(jogadas[cont1], jogadas[cont2]);
                System.out.println("Indice de Defesa Escolhido:"+indiceDefesa);
                for (int i = 0; i < this.cont; i++) {
                    if (Integer.valueOf(indiceDefesa) == jogadas[i]) {
                       numeroExistennte = true;
                    }
                }
                
                //Verifica se o indice escolhido não foi realizado
                //Verifica se o retorno do método de Inferencia não foi nulo
                
                if (!indiceDefesa.equals("14")) {
                    if (!numeroExistennte) {
                        realizaJogadaAPartirDoIndice(indiceDefesa);
                        System.out.println("Jogada com Indice de defesa");
                        jogadas[this.cont] = Integer.valueOf(indiceDefesa);
                        this.cont++;
                        this.quantJogadas++;
                        condicao1 = false;
                        condicao2 = false;
                    }
                }
                cont2 -= 2;
            }
            cont1 -= 2;
        }
 
     }
        //Procurar jogada de defesa       
 
          //Caso o indice de ataque e o indice de defesa não satisfaça a proxima jogada 

        if (condicao1 && condicao2) {
            //Verifica se o indice de jogada 5 não foi jogado
          
            jogadaAutomatica();
            System.out.println("Jogada Automatica");
         }


       }// Para a segunda jogada da máquina dá-se preferência para a jogada 5 
       else {  
           if (this.cont < 3) {
               
                String indiceMaisUtilizado = this.verificaJogadasMaisRealizadas(); 
                realizaJogadaAPartirDoIndice(indiceMaisUtilizado);
                this.jogadasRealizadas[this.cont]=Integer.valueOf(indiceMaisUtilizado);
                this.cont++;
                this.quantJogadas++;
                //   jogadaAutomatica();
              // }
    
            }else{ 
               // Caso o usuário tenha começado  - jogaPrimeiro==True 
  //             System.out.println("Teste ---------------");
           
               ////////////////////////////////////////////
               
//               cont1 = this.cont - 1;
//               cont2 = cont1 - 2;
               cont3 = this.cont - 1;
               cont4 = cont3 - 2;
           
          
               //Gerar indice de ataque ....            
                
                     
                  if (this.cont>=4){
                   
                    cont1 = this.cont -2;
                    cont2 = cont1 - 2;
                
                    
                      while (cont1 >= 3 && condicao3) {
                          cont2 = cont1 - 2;
                          while (cont2 >= 1 && condicao4) {
                             boolean jaExiste=false; 
                              String indiceAtaque = "";
                              System.out.println("Cont1=" + cont1 + "Indice1:" + jogadas[cont1]);
                              System.out.println("Cont2=" + cont2 + "Indice2:" + jogadas[cont2]);
                              indiceAtaque = metodoInferenciaDoisIndices(jogadas[cont1], jogadas[cont2]);
                              System.out.println("Indice de Ataque Escolhido:" + indiceAtaque);
                              for (int i = 0; i < this.cont; i++) {
                                  if (Integer.valueOf(indiceAtaque) == jogadas[i]) {
                                      jaExiste = true;
                                  }
                              }

                              //Verifica se o indice escolhido não foi realizado
                              //Verifica se o retorno do método de Inferencia não foi nulo

                              if (!indiceAtaque.equals("14")) {
                                  if (!jaExiste) {
                                      realizaJogadaAPartirDoIndice(indiceAtaque);
                                      System.out.println("Jogada com Indice de Ataque"+indiceAtaque);
                                      jogadas[this.cont] = Integer.valueOf(indiceAtaque);
                                      this.cont++;
                                      this.quantJogadas++;
                                      condicao3 = false;
                                      condicao4 = false;
                                  }
                              }
                              cont2 -= 2;
                          }
                          cont1 -= 2;
                      }
                  
                  ///////////////
                  }
                 
                 
                            
               
             if (condicao3 && condicao4 ){
               while (cont3 >= 2 && condicao3) {
                   cont4 = cont3 - 2;
                   while (cont4 >= 0 && condicao4) {
                       boolean jaExisteNumero = false;
                       String indiceDefesa1 = "";
                        System.out.println("Cont1=" + cont3 + "Indice1:" + jogadas[cont3]);
                              System.out.println("Cont2=" + cont4 + "Indice2:" + jogadas[cont4]);
                       indiceDefesa1 = metodoInferenciaDoisIndices(jogadas[cont3], jogadas[cont4]);
                       for (int i = 0; i < this.cont; i++) {
                           if (Integer.valueOf(indiceDefesa1) == jogadas[i]) {
                               jaExisteNumero = true;
                           }
                       }
                       if (!indiceDefesa1.equals("14")) {
                           if (!jaExisteNumero) {
                               realizaJogadaAPartirDoIndice(indiceDefesa1);
                               System.out.println("Jogada com indice de Defesa"+indiceDefesa1);
                               jogadas[this.cont] = Integer.valueOf(indiceDefesa1);
                               this.cont++;
                               this.quantJogadas++;
                               condicao3 = false;
                               condicao4 = false;
                           }
                       }
                       cont4 -= 2;
                   }
                   cont3 -= 2;

               }
             }
               
               //Realiza jogada com indice de Ataque
               //Verifica se não foi utilizado um índice de defesa
              
             
            if (condicao3 && condicao4 ){
            
                System.out.println("Jogada Automática");
                jogadaAutomatica();            
                     
            }
                           
               
           }
       }
      
        
    }
    
    /**Método utilizaddo para verificar as jogadas mais realizadas
     * a partir da base de conhecimento. Será utilizada para a  segunda jogada  
     * a ser marcada pela marcada pela máquina
     * Espera-se que com o tempo haja uma maior ocorrência do numero 5 na base de Dados
     * @see jogo.LeituraBC - utilizado para trazer para uma estrutura 
     */

    public String verificaJogadasMaisRealizadas() throws FileNotFoundException {
        int maiorQuantidadeElemento = -1;
        int indiceMaiorQuantidadeElemento = 10;
        Integer[] todosElementosArray = null;
        LeituraBC leituraBC = new LeituraBC();
        ArrayList todosElementos = leituraBC.getJogadasDeRiscos();
        int[] nJogadasPorIndice = new int[9]; // vetor que armazena a quantidade de jogadas em determinado indice 


        for (int i = 0; i < todosElementos.size(); i++) {
            todosElementosArray = (Integer[]) todosElementos.get(i);

            for (int j = 0; j < 3; j++) {

                nJogadasPorIndice[todosElementosArray[j] - 1] += 1;
            }
        }
        
        for (int i = 0; i < nJogadasPorIndice.length; i++) {

            if (nJogadasPorIndice[i] > maiorQuantidadeElemento && ((i+1)!=this.jogadasRealizadas[0] ) && ((i+1)!=this.jogadasRealizadas[1] )  ) {
                maiorQuantidadeElemento = nJogadasPorIndice[i];
                indiceMaiorQuantidadeElemento = i + 1;
            }
        }
        System.out.println("Indice de Retorno - MaisRealizadas:"+indiceMaiorQuantidadeElemento);
        return String.valueOf(indiceMaiorQuantidadeElemento);
        
    }
    
    
    /** Método utilizado para gerar o conteúdo da Base De Conhecimento
     * Método utilizado para gerar o conteúdo da base de Dados Processados
     * @param jogadas - array contendo as jogadas realizadas
     * @param qtdJogadas - contém a quantidade de jogadas realizadas
     * @param vencedor  - x ou o
     * @param condicional - se true monta a base de Conhecimento
     * @param condicional - se false monta a base de dados processados
     * 
     */
    
    public String geraConteudoConhecimento(int [] jogadas, int qtdJogadas, String vencedor, boolean condicional ){
        String conteudo="";
        int iterador=0;
        
        if (condicional){
        conteudo+=quantJogadas+" ";
        if (jogaPrimeiro){
          conteudo+="x ";}
        else{
         conteudo+="o ";
        }
        conteudo+=vencedor+" ";
        }
        //Utilizado para verificar quem foi o ganhador e qual a sequencia vitoriosa 
        
        if (vencedor.equals("x")){
        if (jogaPrimeiro){
           for (int j=0;j<this.cont;j++){
             if (j%2==0){
               conteudo+=jogadas[j]+" ";
               iterador++;
             }
           }
            conteudo+=" ";
         }else{
           for (int j=0;j<this.cont;j++){
             if (j%2!=0){
               conteudo+=jogadas[j]+" ";
               iterador++;
             }
           }
            conteudo+=" ";
        }
        }
        if (vencedor.equals("O")){
        if (jogaPrimeiro){
           for (int j=0;j<this.cont;j++){
             if (j%2!=0){
               conteudo+=+jogadas[j]+" ";
               iterador++;
             }
           }
            conteudo+="";
         }else{
           for (int j=0;j<this.cont;j++){
             if (j%2==0){
               conteudo+=jogadas[j]+" ";
               iterador++;
             }
           }
           conteudo+=" ";
        }
        }
        
        //Utilizado para imprimir todas as jogadas 
       if (condicional){
        for (int i =0;i<this.cont;i++){
            if (i!=0){
        conteudo+=" "+jogadas[i];        
            }else{
            
        conteudo+=jogadas[i];
            }
        }
       }
       
       if (condicional){
         return conteudo;
       }else{
       if (iterador==3){
       return conteudo;
       }
       
       }
       
       
       return ""; // nunca irá ocorrer
       
  
    
    }
    /**Método utilizado para fazer com que haja uma mudança na marcação do jogador
     * a medida que o jogador mudar
     */
    
    public void mudaJogador() {
     if(marcacao.compareToIgnoreCase("X")==0)
		{
			marcacao="";
			marcacao+="0";
                        
                        janela.setTitle("Jogo da Velha. Jogador: Máquina");
		}
		else
		{
			marcacao="";
			marcacao+="x";
                        janela.setTitle("Jogo da Velha. Jogador: Usuário");
		}	
	}
    
    public boolean empate() {
      
		boolean temp=true;
		for(int i =0;i< 3; i++)
		{
			for(int j=0;j<3;j++)
			{
				if(matriz[i][j].getIcon()==null)
					temp=false;//se tiver alguma que nao foi marcada, ainda nao ocorreu empate.
			}
		}
		return temp;
    }

    /**
     * Exibe um painel de opção. Cada botao tem um numero, começando do 0. Como
     * o "YES" é o primeiro (esquerda para direita) seu número é 0. Este número
     * é retornado assim que o botão é pressionado, então se o retorno for 0, um
     * novo jogo começa.
     	 */

    private void novoJogo() {
        
		if(JOptionPane.showConfirmDialog(null, "Novo jogo?", "Novo", JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, null)==0)
		{
				posx=janela.getX();//guarda a posição da janela, para que outra seja criada na mesma.
				posy=janela.getY();
				janela.setVisible(false);
				janela.disable();
                                
            
                                cont=0;
                                this.varControle=1;
                                this.jogaPrimeiro=!this.jogaPrimeiro;
                                quantJogadas=0;
                                desenha();
		}
                else{
                            
                     quantJogadas=0;
                     cont=0;                     
                     System.exit(0);
                }
        
    }
    
    
     public void insereNoBancoConhecimento(String conteudo, boolean noFim ) throws IOException {
         FileWriter fw = new FileWriter("src\\jogo\\BancoDeConhecimento.txt", true);
         fw.write(conteudo+"\n");                
         fw.close();     
     
     }
     
     public void insereBaseDadosProcessados(String conteudo, boolean noFim ) throws IOException{
     FileWriter fw = new FileWriter("src\\jogo\\DadosProcessados.txt",true);
     fw.write(conteudo+"\n");
     fw.close();
     }
     
     
     
    
    
    public static void main(String[] args) {
        Jogo jogo = new Jogo();
      }


    
  

 /** Método chamado caso não haja ganhador (verificado nos métodos anteriores).
  *Verifica todas células, vendo se existe alguma vazia.
  */
    

    public void setCond(boolean cond) {
        this.cond = cond;
    }

    public int getOpcaoJogadores() {
        return opcaoJogadores;
    }

    public void setOpcaoJogadores(int opcaoJogadores) {
        this.opcaoJogadores = opcaoJogadores;
    }
    
    public void verificaDadosNoBanco (){
    
    
    
    
    }

    public boolean isJogaPrimeiro() {
        return jogaPrimeiro;
    }

    public void setJogaPrimeiro(boolean jogaPrimeiro) {
        this.jogaPrimeiro = jogaPrimeiro;
    }

    public int[] getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int[] pontuacao) {
        this.pontuacao = pontuacao;
    }
    
    
    
    
    
    
    
}
