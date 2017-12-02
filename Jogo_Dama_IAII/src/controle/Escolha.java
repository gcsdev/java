/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author StackHolder
 */
public class Escolha {
    
   
    
    
    private int quemOcupou;
    private String deOnde="";
    Movimento mov = new Movimento();
    Captura cap =  new Captura ();
   
    
    
    public void jogadaAutomatica (JButton[][] matriz,ImageIcon peca,List<String>listaPecaBranca,List<String>listaPecaPreta,List<String>listaDamaBranca,List<String>listaDamaPreta,String[] rodape,String [] esquerda,String [] acima,String [] direita ){
       System.out.println("ENTROU MÉTODO JOGADA AUTOMÁTICA");
       //indice de jogada será retornado da árvore
       this.deOnde = "8";
       String paraOnde = "90";
       //verificar quais são as duas jogadas possiveis
       String[] jogadasPossiveis = mov.retornaPosicaoJogadaASerRealizada(deOnde, false, -1, rodape, esquerda, acima, direita);
       //verificar se estas duas pecas possuem pecas ocupantes e por quem estão sendo ocupadas
       this.quemOcupou = mov.verificaQuemOcupouAPosicao(listaPecaBranca, listaPecaPreta, listaDamaBranca, listaDamaPreta, deOnde);
       //retornar lista com posições de captura direita 
       cap.listaDePosicoesPecaPretaParaCapturaDireita(deOnde, quemOcupou, jogadasPossiveis, listaPecaBranca, listaPecaPreta, listaDamaBranca, listaDamaPreta,rodape, esquerda, acima, direita);
       //retorna um array list contendo pecas para captura a direita
        ArrayList<String> capturaDireita = cap.getPosicoesCapturaDireitaPreta();
       // retorrnar lista de posições para captura a esquerda
       cap.listaDePosicoesPecaPretaParaCapturaEsquerda(deOnde, quemOcupou, jogadasPossiveis, listaPecaBranca, listaPecaPreta, listaDamaBranca, listaDamaPreta,rodape, esquerda, acima, direita);
       //retorna um array list contendo pecas para captura a esquerda
       ArrayList<String> capturaEsquerda = cap.getPosicoesCapturaEsquerdaPreta();
       //retorna uma lista verificando se as posições de captura estao preenchidas
       cap.verificaCondicaoCapturaPecaPretaDireita(deOnde, quemOcupou, jogadasPossiveis, listaPecaBranca, listaPecaPreta, listaDamaBranca, listaDamaPreta,rodape, esquerda, acima, direita);
       //retorna um array com cada uma das posições
       //cap.
        
        
        
   }
    
     public String getDeOnde() {
        return deOnde;
    }

    public int getQuemOcupou() {
        return quemOcupou;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
