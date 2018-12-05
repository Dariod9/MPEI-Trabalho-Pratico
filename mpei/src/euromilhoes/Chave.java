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
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Ricardo Carvalho
 */
public class Chave implements Serializable {

    private List<Integer> numeros, estrelas;

    public Chave() {
        numeros = new ArrayList<>();
        estrelas = new ArrayList<>();
        gerarNumeros();
        gerarEstrelas();
    }

    public Chave(List<Integer> numeros, List<Integer> estrelas) {
        this.numeros = numeros;
        this.estrelas = estrelas;
    }

    private void gerarNumeros() {
        for (int i = 0; i < 5; i++) {
            int num;
            do {
                num = (int) (1 + Math.random() * 50);
            } while (numeros.contains(num));
            numeros.add(num);
        }
        Collections.sort(numeros);
    }

    private void gerarEstrelas() {
        for (int i = 0; i < 2; i++) {
            int est;
            do {
                est = (int) (1 + Math.random() * 12);
            } while (estrelas.contains(est));
            estrelas.add(est);
        }
        Collections.sort(estrelas);
    }

    public List<Integer> getNumeros() {
        return numeros;
    }

    public List<Integer> getEstrelas() {
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
        
        List<Integer> total= Stream.concat(numeros.stream(), estrelas.stream()).collect(Collectors.toList());
        List<List<Integer>> shingles= new ArrayList<>();
        for (int i = 0; i < total.size()-1; i++) {
            List<Integer> tmp= new ArrayList<>();
            tmp.add(total.get(i));
            tmp.add(total.get(i+1));
            shingles.add(tmp);
        }
        int[] hashes= new int[shingles.size()];
        for (int i = 0; i < shingles.size(); i++) {
            hashes[i]= shingles.get(i).hashCode();
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