/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euromilhoes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ricardo Carvalho
 */
public class CountingBloomFilter<T> implements Serializable { // Data type of T should be serialiable  
    
    private int[] buckets;
    
    private static int k, p= 207786913;
    
    private final static int countingMaxValue= Integer.MAX_VALUE;
    
    private Map<Integer,Integer> seeds;
            
    private final double probabilityFP= 0.001;
    
    public CountingBloomFilter(int m){
        int size= (optimalSize(m));
        buckets= new int[size];
        k= optimalKValue(size, m);       
        seeds= generateSeeds();
    }
    
    public int[] s(){ return buckets; }

    public int k(){ return k; }
    
    private int optimalSize(int m) {  return (int) Math.ceil(((((m * Math.log(probabilityFP)) / Math.log(1 / Math.pow(2, Math.log(2))))))); }
            
    private int optimalKValue(int n, int m){ return (int)Math.round(((n/m)*Math.log(2))); }
    
    private int [] getPositions(T c){
        int[] positions= new int[k];
        for (int i = 0; i < k; i++) {
            int a= (Integer)seeds.keySet().toArray()[i];
            positions[i]= Math.abs(((a* c.hashCode() + seeds.get(a)) % p) % buckets.length);
        }
        return positions;
    }
    
    public void insert(T c){
        if(c==null){ throw new NullPointerException("Element can't be null"); }
        int [] positions= getPositions(c);
        for (int i = 0; i < positions.length; i++) {
            if(buckets[positions[i]] < countingMaxValue){
                buckets[positions[i]]+= 1;
            }
        }
    }
    
    public void delete(T c){
        if(c==null){ throw new NullPointerException("Element can't be null"); }
        if(!membershipTest(c)){ throw new IllegalArgumentException("Element isn't present"); }
        int [] positions= getPositions(c);
        for (int i = 0; i < positions.length; i++) {
            if(buckets[positions[i]] >= 1 && buckets[positions[i]] < countingMaxValue){
                buckets[positions[i]]-= 1;
            }
        }
    }
    
    public boolean membershipTest(T c){
        if(c==null){ throw new NullPointerException("Element can't be null"); }
        int [] positions= getPositions(c);
        for (int i = 0; i < positions.length; i++) {
            if(buckets[positions[i]] == 0){
                return false;
            }
        }
        return true;
    }
    
    public int count(T c){
        if(c==null){ throw new NullPointerException("Element can't be null"); }
        int [] positions= getPositions(c);
        int minValue= countingMaxValue;
        for (int i = 0; i < positions.length; i++) {
            if(buckets[positions[i]] < countingMaxValue){
                minValue= buckets[positions[i]];
            }
        }
        return minValue;
    }
    
    private Map<Integer,Integer> generateSeeds(){
        //a-b
        Map<Integer,Integer> seeds= new HashMap<>();
        while(seeds.size()<k){
            int a= (int) (k+ Math.random()*30);
            int b= (int) (k+Math.random()*20);
            seeds.put(a, b);
        }
        return seeds;
    }
}
