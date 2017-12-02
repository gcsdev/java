/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

/**
 *
 * @author acer note
 */
public class RetornoMinimax {
    int value; //valor da avaliacao estatica
    No AUXILIAR;//melhor
    
    public RetornoMinimax() {
        value = 0;
        AUXILIAR = new No();
    }
    
}
