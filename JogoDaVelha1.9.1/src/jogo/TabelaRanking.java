/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author StackHolder
 */
public class TabelaRanking extends AbstractTableModel{
    
    private static final int COL_USUARIO1=0;
    private static final int COL_EMPATE=1;
    private static final int COL_USUARIO2=2;
    
    private List valores;
    
    
    public TabelaRanking (List pontos){
        this.valores = pontos; 
    
    
    }
    
    
    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }
    
    
    @Override
    public String  getColumnName(int column) {  
    //Qual é o nome das nossas colunas
    if (column==COL_USUARIO1) return "USUÁRIO1";
    if (column==COL_EMPATE) return "EMPATE";
    if (column==COL_USUARIO2) return "USUARIO2";
    return ""; //nunca deve ocorrer
    
    }
    

   // @Override
//    public Object getValueAt(int rowIndex, int columnIndex) {
//        //Precisamos retornar o valor da coluna column e da linha row. 
//        
//        
//        
//    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
    
}
