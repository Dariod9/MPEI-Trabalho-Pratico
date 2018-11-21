/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euromilhoes;

import java.util.Iterator;
import java.util.Objects;
import java.util.TreeSet;

/**
 *
 * @author Ricardo Carvalho
 */
public class Chave {
    
    private TreeSet<Integer> numeros, estrelas= new TreeSet<>();
    
    public Chave(){
        gerarNumeros();
        gerarEstrelas();
    }
    
    public Chave(TreeSet<Integer> numeros, TreeSet<Integer> estrelas){
        this.numeros= numeros;
        this.estrelas= estrelas;
    }
    
    private void gerarNumeros(){
        for (int i = 0; i < 6; i++) {
            int num;
            do {
                num= (int) (1 + Math.random()*50);
            } while (numeros.contains(num));
            numeros.add(num);
        }
    }
    
    private void gerarEstrelas(){
        for (int i = 0; i < 2; i++) {
            int est;
            do {
                est= (int) (1 + Math.random()*12);
            } while (estrelas.contains(est));
            estrelas.add(est);
        }
    }
    
    public int[] getNumeros(){
         int[] nums= new int[5];
         Iterator<Integer> itr= numeros.iterator();
         int i= 0;
         while(itr.hasNext()){
             nums[i]= itr.next().intValue();
             i++;        
         }
         return nums;
    }
    
    
    public int[] getEstrelas(){
         int[] ests= new int[2];
         Iterator<Integer> itr= estrelas.iterator();
         int i= 0;
         while(itr.hasNext()){
             ests[i]= itr.next().intValue();
             i++;        
         }
         return ests;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Chave other = (Chave) obj;
        if (!Objects.equals(this.numeros, other.numeros)) {
            return false;
        }
        if (!Objects.equals(this.estrelas, other.estrelas)) {
            return false;
        }
        return true;
    }
}
