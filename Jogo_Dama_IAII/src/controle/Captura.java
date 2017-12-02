/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author StackHolder
 */
public class Captura {
    
    Movimento mov = new Movimento();
    
    private ArrayList<String> posicoesCapturaEsquerdaPreta=new ArrayList<>();
    private ArrayList<String> posicoesCapturaEsquerdaBranca=new ArrayList<>();
    private ArrayList<String> posicoesCapturaDireitaPreta=new ArrayList<>();
    private ArrayList<String> posicoesCapturaDireitaBranca=new ArrayList<>();
    private ArrayList<String> possibilidadeCapturaEsquerdaPreta=new ArrayList<>();
    private ArrayList<String> possibilidadeCapturaEsquerdaBranca=new ArrayList<>();
    private ArrayList<String> possibilidadeCapturaDireitaPreta=new ArrayList<>();
    private ArrayList<String> possibilidadeCapturaDireitaBranca=new ArrayList<>();
    private ArrayList<String> pecaASerCapturadaDireitaBranca=new ArrayList<>();
    private ArrayList<String> possibilidadePecaASerCapturadaDireitaBranca=new ArrayList<>();
    private ArrayList<String> possibilidadePecaASerCapturadaEsquerdaBranca=new ArrayList<>();
    
    
    
    
    int quantidadeCaptura=0;
   
    
    
    /**Método utilizado para realizar uma lista de possiveis posicoes para a captura
     * sendo o jogador corrente uma peca
     */
    
    public void posicoesDiagonaisParaCapturaPeca(String jogada,List<String>posicoesCaptura ){
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**Método responsável por preencher possiveis posições para realizar a captura a esquerda a partir de uma jogada 
    */
    
    
    public  void listaDePosicoesPecaPretaParaCapturaEsquerda(String jogada,int extremidade, String [] jogadasAserRealizadas,List<String> jogadasPecasBrancas, List<String> jogadasPecasPretas, List <String>damasBrancas,List<String>damasPretas,String [] baixo,String [] esquerda,String [] acima,String [] direita){
        this.posicoesCapturaEsquerdaPreta.clear();
     System.out.println("ENTROU LISTA DE POSICOES PARA CAPTURA - ESQUERDA");
        int numeroPosicoesCapturadas=0;
     //this.posicoesCaptura=null;
        System.out.println("Entrada no método");   
     // verifica a possibilidade de realizar a captura da proxima peça -sera feita na classe tabuleiro
          // int quemOcupa = mov.verificaQuemOcupouAPosicao(jogadasPecasBrancas, jogadasPecasPretas, damasBrancas, damasPretas,jogadasAserRealizadas[0]);
           int cont=0;
           boolean cond=true;
           int posicaoCaptura=0;
           int estaExtremidade0=7;
            while (cond) {
                if (cont == 0) {
                    posicaoCaptura = Integer.valueOf(jogada) + 10;
                    estaExtremidade0=mov.verificaIndiceExtremidade(jogada, baixo, esquerda, acima, direita);
         
                } else {
                    posicaoCaptura = posicaoCaptura + 10;
                }
                
                //verifica se o número esta na extremidade
                int estaNaExtremidade1 = mov.verificaIndiceExtremidade(String.valueOf(posicaoCaptura), baixo, esquerda, acima, direita);
                int estaNaExtremidade2 = mov.verificaIndiceExtremidade(String.valueOf(posicaoCaptura-5), baixo, esquerda, acima, direita);
                //caso o numero calculado ja esteja na extremidade
                if (estaNaExtremidade1 != 0 || estaNaExtremidade2!=0|| estaExtremidade0==2) {
                    //verifica se alguem ocupa a posicao
                    if (estaNaExtremidade1==2 ||estaNaExtremidade1==3 ){
                    this.posicoesCapturaEsquerdaPreta.add(String.valueOf(posicaoCaptura));
                    }
                    
                    cond = false;
                    //caso a posicao de captura não esteja ainda na extremidade -deve continuar verificando
                } else {
                    this.posicoesCapturaEsquerdaPreta.add(String.valueOf(posicaoCaptura));
                    this.quantidadeCaptura++;
                }
                cont++;
            }     
            this.quantidadeCaptura=0;
        System.out.println("POSICOES PARA CAPTURA A ESQUERDA:" +this.posicoesCapturaEsquerdaPreta.size()); 
    
    }
    
    /**Método responsável por preencher possiveis posições para realizar a captura a esquerda a partir de uma jogada -peça Branca
    */
    
    public  void listaDePosicoesPecaBrancaParaCapturaEsquerda(String jogada,int extremidade, String [] jogadasAserRealizadas,List<String> jogadasPecasBrancas, List<String> jogadasPecasPretas, List <String>damasBrancas,List<String>damasPretas,String [] baixo,String [] esquerda,String [] acima,String [] direita){
     this.posicoesCapturaEsquerdaPreta.clear();
     System.out.println("ENTROU LISTA DE POSICOES PARA CAPTURA - ESQUERDA");
        int numeroPosicoesCapturadas=0;
     //this.posicoesCaptura=null;
        System.out.println("Entrada no método");   
     // verifica a possibilidade de realizar a captura da proxima peça -sera feita na classe tabuleiro
          // int quemOcupa = mov.verificaQuemOcupouAPosicao(jogadasPecasBrancas, jogadasPecasPretas, damasBrancas, damasPretas,jogadasAserRealizadas[0]);
           int cont=0;
           boolean cond=true;
           int posicaoCaptura=0;
           int estaExtremidade0=7;
            while (cond) {
                if (cont == 0) {
                    posicaoCaptura = Integer.valueOf(jogada) - 8;
                    estaExtremidade0=mov.verificaIndiceExtremidade(jogada, baixo, esquerda, acima, direita);
         
                } else {
                    posicaoCaptura = posicaoCaptura - 8;
                }
                
                //verifica se o número esta na extremidade
                int estaNaExtremidade1 = mov.verificaIndiceExtremidade(String.valueOf(posicaoCaptura), baixo, esquerda, acima, direita);
                int estaNaExtremidade2 = mov.verificaIndiceExtremidade(String.valueOf(posicaoCaptura+4), baixo, esquerda, acima, direita);
                //caso o numero calculado ja esteja na extremidade
                if (estaNaExtremidade1 != 0 || estaNaExtremidade2!=0|| estaExtremidade0==2) {
                    //verifica se alguem ocupa a posicao
                    if (estaNaExtremidade1==2 ||estaNaExtremidade1==1 ){
                    this.posicoesCapturaEsquerdaBranca.add(String.valueOf(posicaoCaptura));
                    }
                    
                    cond = false;
                    //caso a posicao de captura não esteja ainda na extremidade -deve continuar verificando
                } else {
                    this.posicoesCapturaEsquerdaBranca.add(String.valueOf(posicaoCaptura));
                    this.quantidadeCaptura++;
                }
                cont++;
            }     
            this.quantidadeCaptura=0;
        System.out.println("POSICOES PARA CAPTURA A ESQUERDA:" +this.posicoesCapturaEsquerdaPreta.size()); 
    
    }
    
    
    
    
    /**Método responsável por preencher possiveis posições para realizar a captura a direita a partir de uma jogada 
    */
    
    public  void listaDePosicoesPecaPretaParaCapturaDireita(String jogada,int extremidade, String [] jogadasAserRealizadas,List<String> jogadasPecasBrancas, List<String> jogadasPecasPretas, List <String>damasBrancas,List<String>damasPretas,String [] baixo,String [] esquerda,String [] acima,String [] direita){
      this.posicoesCapturaDireitaPreta.clear();
      System.out.println("ENTROU LISTA DE POSICOES PARA CAPTURA - DIREITA");
        int numeroPosicoesCapturadas=0;
     //this.posicoesCaptura=null;
        System.out.println("Entrada no método");   
     // verifica a possibilidade de realizar a captura da proxima peça -sera feita na classe tabuleiro
          // int quemOcupa = mov.verificaQuemOcupouAPosicao(jogadasPecasBrancas, jogadasPecasPretas, damasBrancas, damasPretas,jogadasAserRealizadas[0]);
           int cont=0;
           boolean cond=true;
           int posicaoCaptura=0;
           int estaExtremidade0=7;
            while (cond) {
                if (cont == 0) {
                    posicaoCaptura = Integer.valueOf(jogada) + 8;
                    estaExtremidade0=mov.verificaIndiceExtremidade(jogada, baixo, esquerda, acima, direita);
         
                    
                } else {
                    posicaoCaptura = posicaoCaptura + 8;
                }
                
                //verifica se o número esta na extremidade
                int estaNaExtremidade1 = mov.verificaIndiceExtremidade(String.valueOf(posicaoCaptura), baixo, esquerda, acima, direita);
                int estaNaExtremidade2 = mov.verificaIndiceExtremidade(String.valueOf(posicaoCaptura-4), baixo, esquerda, acima, direita);
                //caso o numero calculado ja esteja na extremidade
                if (estaNaExtremidade1 != 0 || estaNaExtremidade2 != 0 || estaExtremidade0==4) {
                    //verifica se alguem ocupa a posicao
                    if (estaNaExtremidade1==4||estaNaExtremidade1==3){
                      this.posicoesCapturaDireitaPreta.add(String.valueOf(posicaoCaptura));
                        System.out.println("Inserido na lista:"+posicaoCaptura);
                    }
                    cond = false;
                    //caso a posicao de captura não esteja ainda na extremidade -deve continuar verificando
                } else {
                    this.posicoesCapturaDireitaPreta.add(String.valueOf(posicaoCaptura));
                    this.quantidadeCaptura++;
                }
                cont++;
            }     
            this.quantidadeCaptura=0;
          
        System.out.println("POSICOES PARA A CAPTURA A DIREITA:"+this.posicoesCapturaDireitaBranca.size());   
    
    }
    
    /**Método responsável por preencher possiveis posições para realizar a captura a direita a partir de uma jogada -peca Branca
    */
    
    public  void listaDePosicoesPecaBrancaParaCapturaDireita(String jogada,int extremidade, String [] jogadasAserRealizadas,List<String> jogadasPecasBrancas, List<String> jogadasPecasPretas, List <String>damasBrancas,List<String>damasPretas,String [] baixo,String [] esquerda,String [] acima,String [] direita){
      this.posicoesCapturaDireitaPreta.clear();
      System.out.println("ENTROU LISTA DE POSICOES PARA CAPTURA - DIREITA");
        int numeroPosicoesCapturadas=0;
     //this.posicoesCaptura=null;
        System.out.println("Entrada no método");   
     // verifica a possibilidade de realizar a captura da proxima peça -sera feita na classe tabuleiro
          // int quemOcupa = mov.verificaQuemOcupouAPosicao(jogadasPecasBrancas, jogadasPecasPretas, damasBrancas, damasPretas,jogadasAserRealizadas[0]);
           int cont=0;
           boolean cond=true;
           int posicaoCaptura=0;
           int estaExtremidade0=7;
            while (cond) {
                if (cont == 0) {
                    posicaoCaptura = Integer.valueOf(jogada) - 10;
                    estaExtremidade0=mov.verificaIndiceExtremidade(jogada, baixo, esquerda, acima, direita);
         
                    
                } else {
                    posicaoCaptura = posicaoCaptura -10;
                }
                
                //verifica se o número esta na extremidade
                int estaNaExtremidade1 = mov.verificaIndiceExtremidade(String.valueOf(posicaoCaptura), baixo, esquerda, acima, direita);
                int estaNaExtremidade2 = mov.verificaIndiceExtremidade(String.valueOf(posicaoCaptura+5), baixo, esquerda, acima, direita);
                //caso o numero calculado ja esteja na extremidade
                if (estaNaExtremidade1 != 0 || estaNaExtremidade2 != 0 || estaExtremidade0==4) {
                    //verifica se alguem ocupa a posicao
                    if (estaNaExtremidade1==4||estaNaExtremidade1==1){
                     this.posicoesCapturaDireitaBranca.add(String.valueOf(posicaoCaptura));
                        System.out.println("Inserido na lista:"+posicaoCaptura);
                    }
                    cond = false;
                    //caso a posicao de captura não esteja ainda na extremidade -deve continuar verificando
                } else {
                    this.posicoesCapturaDireitaBranca.add(String.valueOf(posicaoCaptura));
                    this.quantidadeCaptura++;
                }
                cont++;
            }     
            this.quantidadeCaptura=0;
          
        System.out.println("POSICOES PARA A CAPTURA A DIREITA:"+this.posicoesCapturaDireitaBranca.size());   
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**Método resposável por retornar uma lista contendo indicações se pode ou não capturar
      * sequencias de peças a partir da jogada realizada - para a esquerda
      * @return  posicoesCaptura - lista contendo 0 para a possibilidae de captura, 1 caso contrário
      */
    
   
     public void verificaCondicaoCapturaPecaPretaEsquerda (String jogada,int extremidade, String [] jogadasAserRealizadas,List<String> jogadasPecasBrancas, List<String> jogadasPecasPretas, List <String>damasBrancas,List<String>damasPretas,String [] baixo,String [] esquerda,String [] acima,String [] direita){
         System.out.println("ENTROU VERIFICA POSIBILIDADES A ESQUERDA");
    //caso indice não esteja na extremidade    
        if (extremidade == 0) {
          // verifica a possibilidade de realizar a captura da proxima peça -sera feita na classe tabuleiro
          // int quemOcupa = mov.verificaQuemOcupouAPosicao(jogadasPecasBrancas, jogadasPecasPretas, damasBrancas, damasPretas,jogadasAserRealizadas[0]);
           int cont=0;
           boolean cond=true;
           int posicaoCaptura=0;
           int estaNaExtremidade0=7;
            while (cond) {
                if (cont == 0) {
                    posicaoCaptura = Integer.valueOf(jogada) + 10;
                    estaNaExtremidade0=mov.verificaIndiceExtremidade(jogada, baixo, esquerda, acima, direita);
                } else {
                    posicaoCaptura = posicaoCaptura + 10;
                }
                //verifica se o número esta na extremidade
                int estaNaExtremidade1 = mov.verificaIndiceExtremidade(String.valueOf(posicaoCaptura), baixo, esquerda, acima, direita);
                int estaNaExtremidade2 = mov.verificaIndiceExtremidade(String.valueOf(posicaoCaptura-5), baixo, esquerda, acima, direita);
                //caso o numero calculado ja esteja na extremidade
                if (estaNaExtremidade1 != 0  || estaNaExtremidade2!=0 || estaNaExtremidade0==2 ) {
                    if (estaNaExtremidade1==2 ||estaNaExtremidade1==3){
                    //verifica se alguem ocupa a posicao
                    int quemOcupou = mov.verificaQuemOcupouAPosicao(jogadasPecasBrancas, jogadasPecasPretas, damasBrancas, damasPretas, String.valueOf(posicaoCaptura));
                    if (quemOcupou == 0) {
                        //permissão para captura - ninguem ocupa a posicao
                        this.possibilidadeCapturaEsquerdaPreta.add("0");
                    } else {
                        //permissão para  não captura - esta ocupando a posicao
                        this.possibilidadeCapturaEsquerdaPreta.add("1");
                    }
                    }
                    cond = false;
                    //caso a posicao de captura não esteja ainda na extremidade -deve continuar verificando
                } else {
                    int quemOcupou = mov.verificaQuemOcupouAPosicao(jogadasPecasBrancas, jogadasPecasPretas, damasBrancas, damasPretas, String.valueOf(posicaoCaptura));
                    if (quemOcupou == 0) {
                        this.possibilidadeCapturaEsquerdaPreta.add("0");
                    } else {
                        this.possibilidadeCapturaEsquerdaPreta.add("1");
                    }
                }
                cont++;
            }
         }
       
         System.out.println("POSSIBILIDADES A ESQUERDA: "+this.possibilidadeCapturaEsquerdaPreta.size());
        this.possibilidadeCapturaEsquerdaPreta.clear();
      
     }
   
  
       /**Método resposável por retornar uma lista contendo indicações se pode ou não capturar
      * sequencias de peças a partir da jogada realizada - para a esquerda
      * @return  posicoesCaptura - lista contendo 0 para a possibilidae de captura, 1 caso contrário
      */
    
   
     public void verificaCondicaoCapturaPecaBrancaEsquerda (String jogada,int extremidade, String [] jogadasAserRealizadas,List<String> jogadasPecasBrancas, List<String> jogadasPecasPretas, List <String>damasBrancas,List<String>damasPretas,String [] baixo,String [] esquerda,String [] acima,String [] direita){
       this.possibilidadeCapturaEsquerdaPreta.clear();
         System.out.println("ENTROU VERIFICA POSIBILIDADES A ESQUERDA");
    //caso indice não esteja na extremidade    
        if (extremidade == 0) {
          // verifica a possibilidade de realizar a captura da proxima peça -sera feita na classe tabuleiro
          // int quemOcupa = mov.verificaQuemOcupouAPosicao(jogadasPecasBrancas, jogadasPecasPretas, damasBrancas, damasPretas,jogadasAserRealizadas[0]);
           int cont=0;
           boolean cond=true;
           int posicaoCaptura=0;
           int estaNaExtremidade0=7;
            while (cond) {
                if (cont == 0) {
                    posicaoCaptura = Integer.valueOf(jogada) -8;
                    estaNaExtremidade0=mov.verificaIndiceExtremidade(jogada, baixo, esquerda, acima, direita);
                } else {
                    posicaoCaptura = posicaoCaptura -8;
                }
                //verifica se o número esta na extremidade
                int estaNaExtremidade1 = mov.verificaIndiceExtremidade(String.valueOf(posicaoCaptura), baixo, esquerda, acima, direita);
                int estaNaExtremidade2 = mov.verificaIndiceExtremidade(String.valueOf(posicaoCaptura+4), baixo, esquerda, acima, direita);
                //caso o numero calculado ja esteja na extremidade
                if (estaNaExtremidade1 != 0  || estaNaExtremidade2!=0 || estaNaExtremidade0==2 ) {
                    if (estaNaExtremidade1==2 ||estaNaExtremidade1==1){
                    //verifica se alguem ocupa a posicao
                    int quemOcupou = mov.verificaQuemOcupouAPosicao(jogadasPecasBrancas, jogadasPecasPretas, damasBrancas, damasPretas, String.valueOf(posicaoCaptura));
                    if (quemOcupou == 0) {
                        //permissão para captura - ninguem ocupa a posicao
                        this.possibilidadeCapturaEsquerdaBranca.add("0");
                    } else {
                        //permissão para  não captura - esta ocupando a posicao
                        this.possibilidadeCapturaEsquerdaBranca.add("1");
                    }
                    }
                    cond = false;
                    //caso a posicao de captura não esteja ainda na extremidade -deve continuar verificando
                } else {
                    int quemOcupou = mov.verificaQuemOcupouAPosicao(jogadasPecasBrancas, jogadasPecasPretas, damasBrancas, damasPretas, String.valueOf(posicaoCaptura));
                    if (quemOcupou == 0) {
                        this.possibilidadeCapturaEsquerdaBranca.add("0");
                    } else {
                        this.possibilidadeCapturaEsquerdaBranca.add("1");
                    }
                }
                cont++;
            }
         }
       
         System.out.println("POSSIBILIDADES A ESQUERDA: "+this.possibilidadeCapturaEsquerdaPreta.size());
       
      
     }
  
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     /**Método resposável por retornar uma lista contendo indicações se pode ou não capturar
      * sequencias de peças a partir da jogada realizada - para a direita
      * @return  posicoesCaptura - lista contendo 0 para a possibilidae de captura, 1 caso contrário
      */
     
     public void verificaCondicaoCapturaPecaPretaDireita(String jogada,int extremidade, String [] jogadasAserRealizadas,List<String> jogadasPecasBrancas, List<String> jogadasPecasPretas, List <String>damasBrancas,List<String>damasPretas,String [] baixo,String [] esquerda,String [] acima,String [] direita){
         System.out.println("ENTROU VERIFICA POSSIBILIDADES A DIREITA");
         int cont=0;
        boolean cond=true;
        int posicaoCaptura=0;
           int estaExtremidade0=mov.verificaIndiceExtremidade(jogada, baixo, esquerda, acima, direita);
         System.out.println("EXTREMIDADE0="+estaExtremidade0);
           while (cond) {
                if (cont == 0) {
                    posicaoCaptura = Integer.valueOf(jogada) + 8;
                } else {
                    posicaoCaptura = posicaoCaptura + 8;
                }
                //verifica se o número esta na extremidade
                int estaNaExtremidade1 = mov.verificaIndiceExtremidade(String.valueOf(posicaoCaptura), baixo, esquerda, acima, direita);
                int estaNaExtremidade2 = mov.verificaIndiceExtremidade(String.valueOf(posicaoCaptura-4), baixo, esquerda, acima, direita);
                //caso o numero calculado ja esteja na extremidade
                if (estaNaExtremidade1 != 0 ||estaNaExtremidade2 != 0 || estaExtremidade0 == 4) {
                    //verifica se alguem ocupa a posicao
               if (estaNaExtremidade1 == 4 ||estaNaExtremidade1==3){
                    int quemOcupou = mov.verificaQuemOcupouAPosicao(jogadasPecasBrancas, jogadasPecasPretas, damasBrancas, damasPretas, String.valueOf(posicaoCaptura));
                    if (quemOcupou == 0) {
                         this.possibilidadeCapturaDireitaPreta.add("0");
                    } else {
                         this.possibilidadeCapturaDireitaPreta.add("1");
                       }
                }
                    cond = false;
                    //caso a posicao de captura não esteja ainda na extremidade -deve continuar verificando
                } else {
                    int quemOcupou = mov.verificaQuemOcupouAPosicao(jogadasPecasBrancas, jogadasPecasPretas, damasBrancas, damasPretas, String.valueOf(posicaoCaptura));
                    if (quemOcupou == 0) {                       
                         this.possibilidadeCapturaDireitaPreta.add("0");
                    } else {
                         this.possibilidadeCapturaDireitaPreta.add("1");
                      
                    }
                }
                cont++;
            }
           
           System.out.println("POSSIBILIDADES A DIREITA : "+this.possibilidadeCapturaDireitaPreta.size());
           this.possibilidadeCapturaDireitaPreta.clear();
         }

     /**Método resposável por retornar uma lista contendo indicações se pode ou não capturar
      * sequencias de peças a partir da jogada realizada - para a direita
      * @return  posicoesCaptura - lista contendo 0 para a possibilidae de captura, 1 caso contrário
      */
     
     public void verificaCondicaoCapturaPecaBrancaDireita(String jogada,int extremidade, String [] jogadasAserRealizadas,List<String> jogadasPecasBrancas, List<String> jogadasPecasPretas, List <String>damasBrancas,List<String>damasPretas,String [] baixo,String [] esquerda,String [] acima,String [] direita){
           this.possibilidadeCapturaDireitaPreta.clear();
         System.out.println("ENTROU VERIFICA POSSIBILIDADES A DIREITA");
         int cont=0;
        boolean cond=true;
        int posicaoCaptura=0;
           int estaExtremidade0=mov.verificaIndiceExtremidade(jogada, baixo, esquerda, acima, direita);
         System.out.println("EXTREMIDADE0="+estaExtremidade0);
           while (cond) {
                if (cont == 0) {
                    posicaoCaptura = Integer.valueOf(jogada) -10;
                } else {
                    posicaoCaptura = posicaoCaptura-10;
                }
                //verifica se o número esta na extremidade
                int estaNaExtremidade1 = mov.verificaIndiceExtremidade(String.valueOf(posicaoCaptura), baixo, esquerda, acima, direita);
                int estaNaExtremidade2 = mov.verificaIndiceExtremidade(String.valueOf(posicaoCaptura+5), baixo, esquerda, acima, direita);
                //caso o numero calculado ja esteja na extremidade
                if (estaNaExtremidade1 != 0 ||estaNaExtremidade2 != 0 || estaExtremidade0 == 4) {
                    //verifica se alguem ocupa a posicao
               if (estaNaExtremidade1 == 4 ||estaNaExtremidade1==1){
                    int quemOcupou = mov.verificaQuemOcupouAPosicao(jogadasPecasBrancas, jogadasPecasPretas, damasBrancas, damasPretas, String.valueOf(posicaoCaptura));
                    if (quemOcupou == 0) {
                         this.possibilidadeCapturaDireitaBranca.add("0");
                    } else {
                         this.possibilidadeCapturaDireitaBranca.add("1");
                       }
                }
                    cond = false;
                    //caso a posicao de captura não esteja ainda na extremidade -deve continuar verificando
                } else {
                    int quemOcupou = mov.verificaQuemOcupouAPosicao(jogadasPecasBrancas, jogadasPecasPretas, damasBrancas, damasPretas, String.valueOf(posicaoCaptura));
                    if (quemOcupou == 0) {                       
                         this.possibilidadeCapturaDireitaBranca.add("0");
                    } else {
                         this.possibilidadeCapturaDireitaBranca.add("1");
                      
                    }
                }
                cont++;
            }
           
           System.out.println("POSSIBILIDADES A DIREITA : "+this.possibilidadeCapturaDireitaBranca.size());
         }

     
     
     
     /**Método utilizado para retornar as pecas que podem ser capturadas pela Branca*/
//     
//      public  void listaDePosicoesQuePodemSerCapturadasBrancaEsquerda(String jogada,int extremidade, String [] jogadasAserRealizadas,List<String> jogadasPecasBrancas, List<String> jogadasPecasPretas, List <String>damasBrancas,List<String>damasPretas,String [] baixo,String [] esquerda,String [] acima,String [] direita){
//   //     this.pecaASerCapturadaEsquerdaBranca.clear();
//     System.out.println("ENTROU LISTA DE POSICOES PARA CAPTURA - ESQUERDA");
//        int numeroPosicoesCapturadas=0;
//     //this.posicoesCaptura=null;
//        System.out.println("Entrada no método");   
//     // verifica a possibilidade de realizar a captura da proxima peça -sera feita na classe tabuleiro
//          // int quemOcupa = mov.verificaQuemOcupouAPosicao(jogadasPecasBrancas, jogadasPecasPretas, damasBrancas, damasPretas,jogadasAserRealizadas[0]);
//           int cont=1;
//           boolean cond=true;
//           int posicaoCaptura=0;
//           int estaExtremidade0=7;
//            while (cond) {
//                if (cont == 1) {
//                    posicaoCaptura = Integer.valueOf(jogada) - 4;
//                    estaExtremidade0=mov.verificaIndiceExtremidade(jogada, baixo, esquerda, acima, direita);
//         
//                } else {
//                    posicaoCaptura = posicaoCaptura - 8;
//                }
//                
//                //verifica se o número esta na extremidade
//                int estaNaExtremidade1 = mov.verificaIndiceExtremidade(String.valueOf(posicaoCaptura), baixo, esquerda, acima, direita);
//                int estaNaExtremidade2 = mov.verificaIndiceExtremidade(String.valueOf(posicaoCaptura+4), baixo, esquerda, acima, direita);
//                //caso o numero calculado ja esteja na extremidade
//                if (estaNaExtremidade1 != 0 || estaNaExtremidade2!=0|| estaExtremidade0==2) {
//                    //verifica se alguem ocupa a posicao
//                    if (estaNaExtremidade1==2 ||estaNaExtremidade1==1 ){
//                    this.pecaASerCapturadaEsquerdaBranca.add(String.valueOf(posicaoCaptura));
//                    }
//                    
//                    cond = false;
//                    //caso a posicao de captura não esteja ainda na extremidade -deve continuar verificando
//                } else {
//                    this.pecaASerCapturadaEsquerdaBranca.add(String.valueOf(posicaoCaptura));
//                    this.quantidadeCaptura++;
//                }
//                cont++;
//            }     
//            this.quantidadeCaptura=0;
//        System.out.println("POSICOES PARA CAPTURA A ESQUERDA:" +this.pecaASerCapturadaDireitaBranca.size()); 
//    
//    }
//     
     
     /**Método utilizado para retornar as pecas que podem ser capturadas pela Branca*/
     
      public  void listaDePosicoesQuePodemSerCapturadasBrancaDireita(String jogada,int extremidade, String [] jogadasAserRealizadas,List<String> jogadasPecasBrancas, List<String> jogadasPecasPretas, List <String>damasBrancas,List<String>damasPretas,String [] baixo,String [] esquerda,String [] acima,String [] direita){
        this.pecaASerCapturadaDireitaBranca.clear();
     System.out.println("ENTROU LISTA DE POSICOES PARA CAPTURA - ESQUERDA");
        int numeroPosicoesCapturadas=0;
     //this.posicoesCaptura=null;
        System.out.println("Entrada no método");   
     // verifica a possibilidade de realizar a captura da proxima peça -sera feita na classe tabuleiro
          // int quemOcupa = mov.verificaQuemOcupouAPosicao(jogadasPecasBrancas, jogadasPecasPretas, damasBrancas, damasPretas,jogadasAserRealizadas[0]);
           int cont=1;
           boolean cond=true;
           int posicaoCaptura=0;
           int estaExtremidade0=7;
            while (cond) {
                if (cont == 1) {
                    posicaoCaptura = Integer.valueOf(jogada) -5;
                    estaExtremidade0=mov.verificaIndiceExtremidade(jogada, baixo, esquerda, acima, direita);
         
                } else {
                    posicaoCaptura = posicaoCaptura - 10;
                }
                
                //verifica se o número esta na extremidade
                int estaNaExtremidade1 = mov.verificaIndiceExtremidade(String.valueOf(posicaoCaptura), baixo, esquerda, acima, direita);
                int estaNaExtremidade2 = mov.verificaIndiceExtremidade(String.valueOf(posicaoCaptura+5), baixo, esquerda, acima, direita);
                //caso o numero calculado ja esteja na extremidade
                if (estaNaExtremidade1 != 0 || estaNaExtremidade2!=0|| estaExtremidade0==2) {
                    //verifica se alguem ocupa a posicao
                    if (estaNaExtremidade1==2 ||estaNaExtremidade1==1 ){
                    this.pecaASerCapturadaDireitaBranca.add(String.valueOf(posicaoCaptura));
                    }
                    
                    cond = false;
                    //caso a posicao de captura não esteja ainda na extremidade -deve continuar verificando
                } else {
                    this.pecaASerCapturadaDireitaBranca.add(String.valueOf(posicaoCaptura));
                    this.quantidadeCaptura++;
                }
                cont++;
            }     
            this.quantidadeCaptura=0;
        System.out.println("POSICOES PARA CAPTURA A ESQUERDA:" +this.pecaASerCapturadaDireitaBranca.size()); 
    
    }
      
      
      
     
     
     
     
     
     
     
     
     
     
     
     
    public void listaPosicoesQuePodemSerCapturadasEsquerda(){
    
    
   
    
    
    
    
    
    
    
    
    
    
    
    }
    
    public void listaPosicoesQuePodemSerCapturadasDireita(){}
    
    
    
    public void listaPossibilidadeParaCapturadasEsquerda(){}
    
    public void listaPossibilidadeParaCapturadasDireita(){}
    
    
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    
     
    /**Método utilizado para realizar uma lista de possiveis posicoes para a captura
     * sendo o jogador corrente uma pecapreta da extremidade - usado apenas quando as pecas são de ida 
     * return posicoesCaptura - uma lista contendo informacões se é possivel ou nao capturar, 1 nao pode capturar, 0 pode capturar 
     */
    public String [] verificaCondicaoCapturaPecaPretaExtremidade (String jogada,int extremidade, String [] jogadasAserRealizadas,List<String> jogadasPecasBrancas, List<String> jogadasPecasPretas, List <String>damasBrancas,List<String>damasPretas,String [] baixo,String [] esquerda,String [] acima,String [] direita){
    String [] posicoesCaptura={"x","x"};
    int numeroPosicoesCapturadas=0;
        
    //caso indice não esteja na extremidade    
        int cont=0;
           boolean cond=true;
           int posicaoCaptura=0;
           
            
            //caso o indice esteja na extremidade inicio
     
            if (extremidade == 1) {
            } else {
                //caso o indice esteja na extremidade esquerda
                if (extremidade == 2) {
                } else {
                    ////caso o indice esteja na extremidade acima
                    if (extremidade == 3) {
                    } else {
                        if (extremidade == 4) {
                            
                        }
                    }
                }
            }
      
         return posicoesCaptura;
    
     }

    public int getQuantidadeCaptura() {
        return quantidadeCaptura;
    }

    /**
     * @return the posicoesCaptura
     */
    public ArrayList<String> getPosicoesCapturaEsquerdaPreta() {
        return posicoesCapturaEsquerdaPreta;
    }

    public ArrayList<String> getPosicoesCapturaDireitaPreta() {
        return posicoesCapturaDireitaPreta;
    }

    public ArrayList<String> getPossibilidadeCapturaEsquerdaPreta() {
        return possibilidadeCapturaEsquerdaPreta;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
