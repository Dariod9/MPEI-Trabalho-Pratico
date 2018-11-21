/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euromilhoes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 *
 * @author Ricardo Carvalho
 */
public class Chave {
    
    private ArrayList<Integer> numeros, estrelas= new ArrayList<>();
    
    public Chave(){
        gerarNumeros();
        gerarEstrelas();
    }
    
    public Chave(ArrayList<Integer> numeros, ArrayList<Integer> estrelas){
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
        Collections.sort(numeros);
    }
    
    private void gerarEstrelas(){
        for (int i = 0; i < 2; i++) {
            int est;
            do {
                est= (int) (1 + Math.random()*12);
            } while (estrelas.contains(est));
            estrelas.add(est);
        }
        Collections.sort(estrelas);
    }
    
    public ArrayList<Integer> getNumeros(){
         return numeros;
    }
    
    
    public ArrayList<Integer> getEstrelas(){
         return estrelas;
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
