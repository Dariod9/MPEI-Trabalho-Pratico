/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euromilhoes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ricardo Carvalho
 */
public class TestMinHashing {
    
    public static void main(String[] args){
        
        
        MinHashing mh= new MinHashing(50);
        Chave c= new Chave();
        List<Chave> list= new ArrayList<>();
        
        for(int i= 0; i< 100; i++){
            Chave c1= new Chave();
            System.out.println("Similaridade entre: "+c+" e "+c1+": "+mh.similarity(c, c1));
        }
        
        for (int i = 0; i < 10000; i++) {
            list.add(new Chave());
        }
        System.out.println("Chave: "+c);
        System.out.println("Similares: "+mh.similarList(c, list));
        
    }
}
