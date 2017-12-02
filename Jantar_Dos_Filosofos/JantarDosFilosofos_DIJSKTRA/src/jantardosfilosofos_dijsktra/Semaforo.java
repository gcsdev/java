/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jantarDosfilosofos_DIJSKTRA;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



public class Semaforo {
    public int contador;
    public String nome;

    public Semaforo(int i, String nome){
        this.contador = i;
        this.nome = nome;
    }

    public synchronized void down(){
        try{
            //enquanto nao tem acesso ao semaforo... a thread aguarda...
            while( this.contador == 0 ){
               this.wait();
            }
            //quando a thread tem acesso, o semaforo eh decrementado em uma unidade...
            this.contador--;
        }
        catch( Exception e ){
            e.printStackTrace();
        }
    }

    public synchronized void Up(){
    //garante a atomicidade da execucao do método...

        //quando a thread libera o semaforo, ele eh incrementado em uma unidade...
        //isso possibilita que outra thread (que estah dormindo) possa acessar
        //a regiao critica controlada pelo semaforo.
        this.contador++;
        //acorda todas as outras threads que estao dormindo...
        //uma delas vai obter acesso a região crítica...
        this.notifyAll();
    }
}
