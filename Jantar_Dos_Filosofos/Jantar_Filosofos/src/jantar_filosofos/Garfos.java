/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jantar_filosofos;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author StackHolder
 */
public class Garfos {
    int quantidadeGarfos = 5;
    private  boolean [] disponibilidade = new boolean [quantidadeGarfos]; 
    
    
    public Garfos(){}
    
    /**Método utilizado para pegar dois garfos de forma  de forma sincrinizada*/
    public synchronized void pegarGarfos (Filosofos filosofo){
        int identificador;
        identificador = filosofo.getIdentificador();
        while (disponibilidade[identificador] || disponibilidade[((identificador+1)%5)]){
        //Espera até ser liberado caso o seu garfo  ou o garfo do vizinho a direita esteja sendo usado
        //passa para o estado de faminto
        filosofo.setEstatus(2); 
            try {
                wait();
            } catch (Exception ex) {
                Logger.getLogger(Garfos.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
        }
         disponibilidade[identificador]=true;
         disponibilidade[((identificador+1)%5)]=true;
         //passa para o estatus de comendo
         filosofo.setEstatus(1);   
    } 
    
    public synchronized void retornarGarfos (Filosofos filosofo){
    int identificador;
    //qual filósofo irá liberar os garfos
    identificador = filosofo.getIdentificador();
    disponibilidade[identificador]=false;
    int aux = (identificador+1)%5;
    disponibilidade[aux]=false;
    try {
    //liberou os garfos    
    notifyAll();}catch (Exception ex){}
    
    
    
    }
    
    
    
    
    
    
    
}
