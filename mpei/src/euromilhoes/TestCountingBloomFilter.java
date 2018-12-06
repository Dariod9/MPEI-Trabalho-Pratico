/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euromilhoes;

import java.util.List;

/**
 *
 * @author Ricardo Carvalho
 */
public class TestCountingBloomFilter {

    public static void main(String[] args) {
        System.out.println("Teste da contagem dos números");
        CountingBloomFilter<Integer> numsCBM = new CountingBloomFilter<>(50);
        for (int i = 0; i < 100000; i++) {
            for (Integer it : new Chave().getNumeros()) {
                numsCBM.insert(it);
            }
        }
        for (int i = 1; i < 51; i++) {
            System.out.println(i + ": " + numsCBM.count(i));
        }

        System.out.println("Teste da verificação de premios");
        Chave c = null;
        CountingBloomFilter<Chave> cbm = null;
        List<Chave> pr = null;
        boolean b;
        Chave teste = null;
        for (int i = 0; i < 20; i++) {
            c = new Chave();
            ChavesPremio cp = new ChavesPremio(c, 1, 2);
            pr = cp.getChaves();
            cbm = new CountingBloomFilter<>(pr.size());
            for (Chave ch : pr) {
                cbm.insert(ch);
            }
            teste = new Chave();
            b = cbm.membershipTest(teste);
            if (b) {
                System.out.println("n: " + 1 + "| e: " + 2 + "  : " + b);
                System.out.println("Chave: " + c);
                System.out.println("Teste: " + teste);
            }
            for (int j = 2; j < 6; j++) {
                for (int k = 0; k < 3; k++) {
                    cp = new ChavesPremio(c, j, k);
                    pr = cp.getChaves();
                    cbm = new CountingBloomFilter<>(pr.size());
                    for (Chave ch : pr) {
                        cbm.insert(ch);
                    }
                    b = cbm.membershipTest(teste);
                    if (b) {
                        System.out.println("n: " + j + "| e: " + k + "  : " + b);
                        System.out.println("Chave: " + c);
                        System.out.println("Teste: " + teste);
                    }
                }
            }
        }
    }
}
