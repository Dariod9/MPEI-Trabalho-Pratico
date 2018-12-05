/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euromilhoes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Ricardo Carvalho
 */
public class ChavesPremio {

    private List<Chave> chaves = new ArrayList<>();
    private final Chave sorteio;

    public ChavesPremio(Chave sorteio) {
        this.sorteio = sorteio;
        gerarChaves();
    }
    
    public ChavesPremio(Chave sorteio, int numeros, int estrelas) {
        this.sorteio = sorteio;
        gerarChaves(numeros, estrelas);
    }

    public List<Chave> getChaves() {
        return chaves;
    }

    public Chave getSorteio() {
        return sorteio;
    }

    private void gerarChaves() {
        addToChavesList(2, 0);
        addToChavesList(2, 1);
        addToChavesList(1, 2);
        addToChavesList(3, 0);
        addToChavesList(3, 1);
        addToChavesList(2, 2);
        addToChavesList(4, 0);
        addToChavesList(3, 2);
        addToChavesList(4, 1);
        addToChavesList(4, 2);
        addToChavesList(5, 0);
        addToChavesList(5, 1);
        addToChavesList(5, 2);
    }

    private void gerarChaves(int nums, int estrls) {
        addToChavesList(nums, estrls);
    }
     
    private void addToChavesList(int nums, int estrls) {
        List<List<Integer>> numsComb = new ArrayList<>();
        List<List<Integer>> estrlsComb = new ArrayList<>();

        //Números   
        for (List<Integer> l1 : combinationsInList(nums, sorteio.getNumeros())) {
            for (List<Integer> l2 : combinationsOutList(50, sorteio.getNumeros().size() - nums, sorteio.getNumeros())) {
                List<Integer> numeros = Stream.concat(l1.stream(), l2.stream()).collect(Collectors.toList());
                Collections.sort(numeros);
                numsComb.add(numeros);
            }
        }

        //Estrelas
        for (List<Integer> l3 : combinationsInList(estrls, sorteio.getEstrelas())) {
            for (List<Integer> l4 : combinationsOutList(12, sorteio.getEstrelas().size() - estrls, sorteio.getEstrelas())) {
                List<Integer> estrelas = Stream.concat(l3.stream(), l4.stream()).collect(Collectors.toList());
                Collections.sort(estrelas);
                estrlsComb.add(estrelas);
            }
        }

        //Junta tudo numa lista
        for (List<Integer> l1 : estrlsComb) {
            for (List<Integer> l2 : numsComb) {
                Chave c = new Chave(l2, l1);
                chaves.add(c);
            }
        }
    }
    
    //Todas as combinações de números que não pertencem a uma lista(k números de (1,n))
    private List<List<Integer>> combinationsOutList(int n, int k, List list) {
        List<List<Integer>> result = new ArrayList<>();

        if (n <= 0 || n < k) {
            return result;
        }

        List<Integer> item = new ArrayList<>();
        col(n, k, 1, list, item, result); // because it need to begin from 1

        return result;
    }
    
    //Todas as combinações de números pertencem a uma lista(k números)
    private List<List<Integer>> combinationsInList(int k, List list) {
        List<List<Integer>> result = new ArrayList<>();

        List<Integer> item = new ArrayList<>();
        cil(k, 0, list, item, result); // because it need to begin from 1

        return result;
    }

    private void col(int n, int k, int start, List<Integer> list, List<Integer> item, List<List<Integer>> res) {
        if (item.size() == k) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (item.contains((Integer) it.next())) {
                    return;
                }
            }
            res.add(new ArrayList<>(item));
            return;
        }

        for (int i = start; i <= n; i++) {
            item.add(i);
            col(n, k, i + 1, list, item, res);
            item.remove(item.size() - 1);
        }
    }

    private void cil(int k, int start, List<Integer> list, List<Integer> item, List<List<Integer>> res) {
        if (item.size() == k) {
            res.add(new ArrayList<>(item));
            return;
        }

        for (int i = start; i < list.size(); i++) {
            item.add(list.get(i));
            cil(k, i + 1, list, item, res);
            item.remove(item.size() - 1);
        }
    }
}