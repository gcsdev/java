/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jantarDosfilosofos_DIJSKTRA;

import java.util.concurrent.Semaphore;

public class Mesa {

    private final int n = 5;
    private final int faminto = 1;
    private final int pensando = 0;
    private final int comendo = 2;
    private int estado[];
    private Semaforo s[] = new Semaforo[] { new Semaforo(0,"Filoso1"),
            new Semaforo(0,"Filoso2"), new Semaforo(0,"Filoso3"), new Semaforo(0,"Filoso4"),
            new Semaforo(0,"Filoso5") };
    private Semaforo mutex;
    private int left, right;
    Jantar_Filosofos j ;

    public Mesa(Jantar_Filosofos j) {
        estado = new int[5];
        mutex = new Semaforo(1,"Corrente");
        this.j=j;
    }

    public void pegarGarfo(int i) {        
        mutex.down();
        estado[i] = faminto;
        testar(i);
        mutex.Up();
        s[i].down();      
    }

    public void testar(int i) {
       int ESQUERDA = (i+n-1)%n;
       int DIREITA = (i+1)%n;
       if (estado[i] == faminto && estado[ESQUERDA] != comendo
                && estado[DIREITA] != comendo) {
            estado[i] = comendo;
            s[i].Up();
        }
    }

    public void largarGarfos(int i) {
        mutex.down();
        estado[i] = pensando;
        testar(left(i));
        testar(right(i));
        mutex.Up();

    }

    public int left(int i) {
        left = (i + n - 1) % n;
        return left;
    }

    public int right(int i) {
        right = (i + n) % n;
        return right;
    }

   public int getEstado(int i){
        return estado[i];
    }

    public void mostraEstados() {
        for (int n = 0; n < this.n; n++) {
            System.out.printf("F%d ", (n + 1));
            switch (estado[n]) {
                case pensando: {
                    j.setControle((n + 1), 0);
                    break;
                }
                case faminto: {
                    j.setControle((n + 1), 2);
                    break;
                }
                case comendo: {
                    j.setControle((n + 1), 1);
                    break;
                }
                default: {
                    break;
                }
            }
        }
     }
}