/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jantarDosfilosofos_DIJSKTRA;


import java.util.Random;

public class Filosofos extends Thread {
    private int cadeira;
    private Mesa m;
   
    public Filosofos(int cadeira, Mesa m) {
        this.cadeira = cadeira;
        this.m = m;
    }
    public void run() {
        while (true) {
            pensar();
            m.pegarGarfo(cadeira);
            comer();
            m.largarGarfos(cadeira);
            m.mostraEstados();
        }
    }
    public void pensar() {
        try {
            Thread.sleep(Integer.valueOf(retornaTempo()));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public void comer() {
        try {
            Thread.sleep(Integer.valueOf(retornaTempo()));
        } catch (Exception e) {
            // TODO: handle exception
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