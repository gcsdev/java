/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author acer note
 */
public class LeituraBC {
    
    ArrayList JogadasVencedoras = new ArrayList(); // array de vetores {[1,2,3,4,5,6],[5.2.6.4.7]}
    ArrayList ganhouJogo = new ArrayList(); // array de char {X.0.0,0,X,X}
    ArrayList iniciouJogo = new ArrayList(); // array de char {{X.0.0,0,X,X}}
    ArrayList QuantJogadas = new ArrayList();// arrayList com numero de jogadas {9.6.5.8.9.5}
    private ArrayList JogadasDeRiscos = new ArrayList(); //
    private boolean retornaEstado=false;
    
    LeituraBC() throws FileNotFoundException{
        BufferedReader arquivo = new BufferedReader(new FileReader("src\\jogo\\DadosProcessados.txt"));
       
        String linha;
        try {
            while (arquivo.ready()) {
                retornaEstado= true;
                linha = arquivo.readLine();
                
                String[] conteudoLinha = linha.split(" ");
              
                Integer[] sequenciaVencedora = new Integer[3];
                int k=0;
                for (int j = 0; j < 3; j++,k++) {
                 
                    sequenciaVencedora[j] = Integer.parseInt(conteudoLinha[k]);
                }
                JogadasDeRiscos.add(sequenciaVencedora);
            }
        } catch (IOException ex) {
        }
    }
  
    public ArrayList leituraJogadasVencedoras() throws FileNotFoundException {
        return JogadasVencedoras;
    }
    
    public ArrayList leituraVencedores(){
        return ganhouJogo;
    }
    
    public ArrayList leituraIniciouJogo(){
        return iniciouJogo;
    }
    
    public ArrayList leituraJogadasDeRiscos(){
         return JogadasDeRiscos; 
    }
    
    public ArrayList jogadasConhecidosDoisIndices(int a, int b){
       
        boolean contemA, contemB;
        ArrayList relativasIndices = new ArrayList();
        
        for (int i = 0; i < JogadasDeRiscos.size(); i++) {
            Integer[] AUX = (Integer[]) JogadasDeRiscos.get(i);
            contemA = false; contemB = false; 
            
                 for(int j=0;j<AUX.length;j++){  
                   if (AUX[j] == a){contemA = true;}
                   if (AUX[j] == b){contemB = true;}
                 }
                 if (contemA && contemB){ relativasIndices.add(AUX);
                 }
                   
           }
        return relativasIndices;
    }
    
    public boolean retornaEstado(){
     return retornaEstado; 
        
    
    
    
    }
    
    
    
    
    
    public ArrayList jogadasConhecidoUmIndice(int a){
         boolean contem;
        ArrayList relativasIndice = new ArrayList();
        
        for (int i = 0; i < JogadasDeRiscos.size(); i++) {
            Integer[] AUX = (Integer[]) JogadasDeRiscos.get(i);
            contem = false; 
            
                 for(int j=0;j<AUX.length;j++){  
                   if (AUX[j] == a){
                        contem = true;
                   }
                   
                    }
                 if (contem){ relativasIndice.add(AUX);
                 }
           }
        return relativasIndice;
    }

    public ArrayList getJogadasDeRiscos() {
        return JogadasDeRiscos;
    }
    
    
    
}
