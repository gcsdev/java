/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author StackHolder
 */
public class Pecas {
    
    private ImageIcon pecaBranca;
    private ImageIcon pecaPreta;
    private ImageIcon damaBranca;
    private ImageIcon damaPreta;

    
    public Pecas(){
    this.pecaBranca=new ImageIcon("src\\visao\\imagens\\pecaBranca.png");
    this.pecaPreta=new ImageIcon("src\\visao\\imagens\\pecaPreta.png");
    this.damaBranca=new ImageIcon("src\\visao\\imagens\\damaBranca.jpg");
    this.damaPreta=new ImageIcon("src\\visao\\imagens\\damaPreta.jpg");
      
    }
    
    
    public void possicaoPecaInicial(JButton[][] matrizTab,ImageIcon peca1,ImageIcon peca2){
    
       int cont=0;
        //percorrendo linhas
        for (int i = 0; i < 8; i++) {
            //alternancia para preenchimento adequado
            if (i % 2 == 0) {
                cont = 0;
            } else {
                cont = 1;
            } 
            //preenchimento de colunas
            for (int j = 0; j < 8; j++) {
                // preenchimento das pecas brancas              
                if (i<=2){
                if (cont%2==0){
                    matrizTab[i][j].setIcon(peca1);
                }                    
                }else{
                if(i>=5){
                if (cont%2==0){
                    matrizTab[i][j].setIcon(peca2);
                }
                }
               }
                cont++;                
            }
        }
    
    }
    
    /**Método utilizado para inserir  em um array as jogadas iniciais do jogador 
     *da peca Preta 
     * @param lista - recebe um array a ser preenchido
     */
    
    public ArrayList<String> jogadasIniciaisPreta() {
      //  Iterator i = lista.iterator();
        
        ArrayList<String> lista = new ArrayList<>();
        
        for (int i = 0; i < 12; i++) {
             if(i>=4){
                 int valor =(i+2);
                     lista.add(String.valueOf(valor));
                //     System.out.println(""+(i+2));
            } else {                
                 int valor =(i+1);
                lista.add(String.valueOf(valor));
              //  System.out.println(""+(i+1));
            }
        }
        for(int i=0;i<lista.size();i++){
           // System.out.println(lista.get(i));
        }
       return lista;
        
    }
    
    
     /**Método utilizado para inserir  em um array as jogadas iniciais do jogador 
     *da peca Branca
     * @param lista - recebe um array a ser preenchido
     */
    
    public void jogadasIniciaisBranca(List<String>lista) {
        int cont = 24;
        for (int i = 0; i < 12; i++) {
            if (i >= 8) {
                lista.add(String.valueOf(cont + 1)) ;
            } else {
                lista.add(String.valueOf(cont));
            }
            cont++;
        }

    }

    
    /**Método utilizado para modificar o jogador corrente após uma jogada realizada
     * @param numJogador - identificador do jogador , 1:computador;-1 usuário
     */
    public int mudaJogador (int numJogador,JFrame base){
        
        if(numJogador==1)
		{
			
			numJogador=numJogador*(-1);
                        base.setTitle("Jogo Dama - Usuário");
		                  
                                  return numJogador;
                }
		else
		{
			numJogador=numJogador*(-1);
			base.setTitle("Jogo Dama - Computador");
		                 
                                   return numJogador;
		}	
        
    }
    
    
    /**Método utilizado para verificar se existe dama poe pecas*/
    
    public boolean existeDama(int [] listaDamas){
    boolean possui=false;
    if (listaDamas.length!=0){
        possui = true;
    }
    return  possui;
    }
    
    
    
    
    
    
    public void verificaJogadasRealizadas(){
    
    
    }

    public ImageIcon getPecaBranca() {
        return pecaBranca;
    }

    public void setPecaBranca(ImageIcon pecaBranca) {
        this.pecaBranca = pecaBranca;
    }

    public ImageIcon getPecaPreta() {
        return pecaPreta;
    }

    public void setPecaPreta(ImageIcon pecaPreta) {
        this.pecaPreta = pecaPreta;
    }

    public ImageIcon getDamaBranca() {
        return damaBranca;
    }

    public void setDamaBranca(ImageIcon damaBranca) {
        this.damaBranca = damaBranca;
    }

    public ImageIcon getDamaPreta() {
        return damaPreta;
    }

    public void setDamaPreta(ImageIcon damaPreta) {
        this.damaPreta = damaPreta;
    }
    
    
    
  
    
    
}
