/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euromilhoes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Ricardo Carvalho
 */
public class Chave implements Serializable {
    
    private List<Integer> numeros, estrelas;
    
    public Chave(){
        numeros= new ArrayList<>();
        estrelas= new ArrayList<>();
        gerarNumeros();
        gerarEstrelas();
    }
    
    public Chave(List<Integer> numeros, List<Integer> estrelas){
        this.numeros= numeros;
        this.estrelas= estrelas;
    }
    
    private void gerarNumeros(){
        for (int i = 0; i < 5; i++) {
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
    
    public List<Integer> getNumeros(){
         return numeros;
    }
    
    
    public List<Integer> getEstrelas(){
         return estrelas;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.numeros);
        hash = 29 * hash + Objects.hashCode(this.estrelas);
        return hash;
    }
    
    public int[] hashArrays() {
    
    	String s1,s2,s3,s4="";
    
	    	s1=this.numeros.get(0)+""+this.numeros.get(1);
	    	s2=this.numeros.get(2)+""+this.numeros.get(3);
	    	s3=this.numeros.get(4)+"";
	    	s4=this.estrelas.get(0)+""+this.numeros.get(1);
	    	
	    String[] shingles= {s1,s2,s3,s4};
	    
	    int[] hashes=new int[4];
	    
	    for(int i=0;i<hashes.length;i++) {
	    	hashes[i]=shingles[i].hashCode();
	    }
	    	
	    return hashes;
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

    @Override
    public String toString() {
        return "Chave{" + "numeros=" + numeros + ", estrelas=" + estrelas + '}';
    }
}
