package euromilhoes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MinHashing {

    private Integer[] seeds;
    private int hashes; //numHash: number of hash functions needed 
    // in this small case, we set it size of text a + size of text b in the test case

    public MinHashing(int hashes) {
        this.hashes = hashes;
        seeds = generateSeeds();
    }

    public List<Chave> similarList(Chave c, List<Chave> list) {
        List<Chave> aux = new ArrayList<>();
        for (Chave ch : list){
            if (similarity(c, ch) > 0.3) {
                aux.add(ch);
            }
        }
        return aux;
    }

    public double similarity(Chave c1, Chave c2) {

        int[][] minHashValues = new int[2][hashes];

        Arrays.fill(minHashValues[0], Integer.MAX_VALUE);
        Arrays.fill(minHashValues[1], Integer.MAX_VALUE);

        Integer[] values1 = getHash(seeds, c1);
        Integer[] values2 = getHash(seeds, c2);

        int similarity = 0;
        for (int i = 0; i < hashes; i++) {
            //int a = r.nextInt() + 1;

            minHashValues[0][i] = Math.min(minHashValues[0][i], values1[i]);
            // for(Integer s : c2)
            minHashValues[1][i] = Math.min(minHashValues[1][i], values2[i]);

            if (minHashValues[0][i] == minHashValues[1][i]) {
                similarity++;
            }
        }
        return (double) similarity / hashes;
    }

    private Integer[] getHash(Integer[] seeds, Chave c) {

        List<Integer> values= new LinkedList<>();

        for (int i = 0; i < hashes; i++) {
            for (int j = 0; j < c.hashArrays().length; j++) {
                values.add(c.hashArrays()[j] ^ seeds[i]);
            }
        }
        return values.toArray(new Integer[0]);
    }
    
    private Integer[] generateSeeds() {
        Integer[] seeds= new Integer[hashes];
        for (int i = 0; i < hashes; i++) {
            seeds[i] = Math.abs((int)(Math.random()*Integer.MAX_VALUE));
        }
        return seeds;
    }
}
