package euromilhoes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class minHashing {
	
	private static int p= 207786913;
	private int hashes; //numHash: number of hash functions needed 
 // in this small case, we set it size of text a + size of text b in the test case
 public double similarity(Chave c1,Chave c2, int hashes){
  
  long[][] minHashValues = new long[2][hashes];
  
  Arrays.fill(minHashValues[0], Long.MAX_VALUE);
  Arrays.fill(minHashValues[1], Long.MAX_VALUE);
  
  Random r = new Random(63689);
  
  Map<Integer,Integer> seeds= generateSeeds();
  
  Integer[] values1=getHash(seeds,c1);
  Integer[] values2=getHash(seeds,c2);
  
  int similarity = 0;
  for (int i = 0; i < hashes ; i++){
   //int a = r.nextInt() + 1;
   
    minHashValues[0][i] = Math.min(minHashValues[0][i], values1[i]);
  // for(Integer s : c2)
    minHashValues[1][i] = Math.min(minHashValues[1][i], values2[i]); 
   
    if(minHashValues[0][i] == minHashValues[1][i]){
    similarity++;
   }
  }
  return (double)similarity / hashes;
 }
 
 
 private Integer[] getHash(Map<Integer,Integer> seeds, Chave c) {
	 
	 Integer[] values= new Integer[hashes];
	 
	 for (int i = 0; i < hashes; i++) {
         int a= (Integer)seeds.keySet().toArray()[i];
         values[i]= Math.abs(((a* c.hashCode() + seeds.get(a)) % p));
     }
     return values;
 }
 
 private Map<Integer,Integer> generateSeeds(){
     //a-b
     Map<Integer,Integer> seeds= new HashMap<>();
     while(seeds.size()<hashes){
         int a= (int) (hashes+Math.random()*15);
         int b= (int) (hashes+Math.random()*10);
         seeds.put(a, b);
     }
     return seeds;
}
}