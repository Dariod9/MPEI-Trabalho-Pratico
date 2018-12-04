package euromilhoes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Minhash {

	private int size;
	private int hashes=7500;
	private static int p= 207786913;
	
	
	public ArrayList<Chave> getMostSimilar(Chave c, ArrayList<Chave> chaves){
		
		ArrayList<Chave> simKeys= new ArrayList<Chave>();
    	ArrayList<Chave> list = new ArrayList<Chave>();
    	
    	list.add(c);
    	list.addAll(chaves);
    	
    	ArrayList<Integer[]> minHashValues = getMinHashes(list);
    	ArrayList<Integer> positions = getSimPos(minHashValues);
    	
    	for (int i=0; i<positions.size(); i++) {
    		simKeys.add(chaves.get(positions.get(i))); 
    		}
    	
    	return simKeys;
		
	}
	
	
	public ArrayList<Integer> getSimPos(ArrayList<Integer[]> hashArrays ){
	
		ArrayList<Integer> pos = new ArrayList<Integer>();
    	pos.add(0);
    	
    	double sim=commonElem(hashArrays.get(0), hashArrays.get(1));
    	for (int i=1; i<hashArrays.size()-1; i++) {
    		
    		if ((sim<commonElem(hashArrays.get(0), hashArrays.get(i+1))+0.5)&&(sim>commonElem(hashArrays.get(0), hashArrays.get(i+1))-0.5)) {
    			pos.add(i);
    			
    		} else if (sim<commonElem(hashArrays.get(0), hashArrays.get(i+1))) {
    			sim=commonElem(hashArrays.get(0), hashArrays.get(i+1));
    			pos.clear();
    			pos.add(i);
    		}
    	}
		return pos;
	}
		
	
	public ArrayList<Integer[]> getMinHashes(ArrayList<Chave> chaves){
		Map<Integer,Integer> seeds= generateSeeds(hashes);
		ArrayList<Integer[]> hashArrays= new ArrayList<Integer[]>();
		
		for(int i=0;i<chaves.size();i++) {	//Percorrendo todas as chaves
			
			int[] valHashes=chaves.get(i).hashArrays();
			ArrayList<Integer> tmp=new ArrayList<Integer>();
			Integer[] pHash=new Integer[hashes];
			
			
			for(int j=0;j<hashes;j++) {		//Encontrar o menor valor de hash para uma chave, usando todas as seeds
				int min=90;
				tmp.clear();
				
				for(int i2=0;i2<valHashes.length;i2++) {	
					
					int s1=(int) seeds.keySet().toArray()[j];
					int s2= seeds.get(s1);
					
					tmp.add(valHashes[i2]^(s1*s2));
				
					for(int i3=0;i3<tmp.size();i++) {
						if(tmp.get(i)<min) min=tmp.get(i);
					}
				
				}
				
				pHash[j]=min;
			}
			
			hashArrays.add(pHash);
			
		}
		
		return hashArrays;
		
	}
	
	

	public int hash(Chave c, ArrayList<Chave> chaves) {
	
		int index=-1;
		size=chaves.size();
		
		if(!chaves.contains(c)) System.out.println("A chave não existe");
		
		else {
			
			for(int i=0;i<size;i++) {
				if(chaves.get(i).equals(c)) index=i;
			}
		}
	
		
		int tmp= (Integer) generateSeeds(hashes).keySet().toArray()[index];
		
		return Math.abs((tmp* c.hashCode() + generateSeeds(hashes).get(tmp)) % p);
	}
	
	
	
	public double commonElem(Integer[] a1,Integer[] a2) {
        
		int size=1;
        int count=0;
        
        Integer[] ar, small, big;
        
        if(a1.length == a2.length) {
            
        	size = a1.length;
            
        	for(int i=0; i< size; i++){				//Comparar cada elemento do primeiro conjunto
        		
        		for(int j=0;j<a2.length;j++) {		//com cada elemento do segundo conjunto
        			
        			if(a1[i].equals(a2[j])) count++;
        			
        		}	
        	}
            	
        } 
        
        else {
        	if(a1.length> a2.length) {
        		big = a1;
        		small = a2;
        	} else {
        		big = a1;
        		small = a2;
        	}
        
            size = big.length;
            
            ar = new Integer[size];
            
            //Preencher array menor com null depois
            for(int i=0; i<size; i++){
                if(i<small.length)
                    ar[i] = small[i];
                else
                    ar[i] = null;
            }
            
            
            for(int i=0; i<size; i++){
            	
            	for(int j=0;j<ar.length;j++) {
            		if(big[i].equals(ar[j])) count++; }
            }
        }
        
        
        return (double)count/size;
    }
	
	
	//Seeds
	private Map<Integer,Integer> generateSeeds(int hashes){
        //a-b
        Map<Integer,Integer> seeds= new HashMap<>();
        while(seeds.size()<hashes){
            int a= (int) (hashes+ Math.random()*30);
            int b= (int) (hashes+Math.random()*20);
            seeds.put(a, b);
        }
        return seeds;
    }
	
	 private static double randBetween(double min, double max) { return Math.random()*Math.abs(max-min)+min; }
     
	 private int optimalKValue(int size){ return (int)Math.round((size*Math.log(2))); }
	 
	 
}
