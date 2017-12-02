/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jantar_filosofos;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author StackHolder
 */
public class Filosofos extends Thread{
    
    private int identificador;
    private int estatus; //0 -pensa; 1-come; 2-faminto
    private Jantar jantar;
    
    
    public Filosofos ( int identificador, Jantar jantar){
    this.identificador=identificador;
    this.jantar=jantar;
    }
    
     int getIdentificador() {
        return identificador;
    }

   public void setEstatus(int i) {
     estatus =i;
       switch (i){
           case 0 :jantar.setControle(identificador,0);break;
           case 1 :jantar.setControle(identificador,1);break;
           case 2 :jantar.setControle(identificador,2);break;
                  
       }
       
    }
   
    
    
    private void pensando (){
        try {
           
            Thread.sleep(Integer.valueOf(retornaTempo()));
        } catch (InterruptedException ex) {
            Logger.getLogger(Filosofos.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    private void comendo (){
        try {
          
            Thread.sleep(Integer.valueOf(retornaTempo()));
        } catch (InterruptedException ex) {
            Logger.getLogger(Filosofos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void run (){
    
    while (true){
    setEstatus(0); // pensando
    pensando(); // bloqueio por algum momento
    jantar.garfos.pegarGarfos(this); //pega os garfos
    comendo();
    jantar.garfos.retornarGarfos(this);
    
    }
    
    }
    public String retornaTempo (){
       int sorteio = new Random().nextInt(5)+1;
       switch (sorteio){
           case 1: return "100";
           case 2: return "200";
           case 3: return "300";
           case 4: return "400";
           case 5: return "500";       
       }
        
    return "";
    } 
    
    
    
    
}
