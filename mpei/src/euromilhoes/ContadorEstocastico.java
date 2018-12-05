/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euromilhoes;

import java.io.Serializable;

/**
 *
 * @author Ricardo Carvalho
 */
public class ContadorEstocastico implements Serializable{
    
    private double prob;
    private int count=0;
    
    public ContadorEstocastico(){
        this.prob= 0.0;
    }
    
    public ContadorEstocastico(double prob){
        this.prob= prob;
    }
    
    public int contagem(){ return count; }
    
    public void evento(){
        if(prob>0){ //Tipo 1
            double random= Math.random();
            if(random<=prob){
                count++;
            }
        }
        else{ // Tipo 2
            double p= Math.pow(2, -count);
            double random= Math.random();
            if(random<=p){
                count++;
            }
        }
    }
}
