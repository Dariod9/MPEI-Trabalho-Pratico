/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euromilhoes;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ricardo Carvalho
 */
public class ChavesPremio {
    
    private LinkedList<Chave> chaves;
    private final Chave sorteio;
    
    public ChavesPremio(Chave sorteio){
        this.sorteio= sorteio;
        chaves= new LinkedList<>();
        gerarChaves();
    }
    
    private void gerarChaves(){
        n5e2();
    }
    
    private void n5e2(){
        chaves.add(sorteio);
    }
    
    private void n5e1(){
        for (int i = 0; i < 2; i++) {
            
        }
    }
    
    
}
