/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euromilhoes;

/**
 *
 * @author Ricardo Carvalho
 */
public class TestContadorEstocastico {
    
    public static void main(String[] args){
        ContadorEstocastico ce1= new ContadorEstocastico(0.25);
        for (int i = 0; i < 100000000; i++) {
            ce1.evento();
        }
        System.out.println("Terminado o primeiro ciclo...");
        for (int i = 0; i < 100000000; i++) {       
            ce1.evento();
        }
        System.out.println((double) ce1.contagem() / 100000000 ); //Deve ser perto de 0.5
        
        
        System.out.println("Segundo contador estocástico...");
        ContadorEstocastico ce2= new ContadorEstocastico();
        for (int i = 0; i < 100000000; i++) {
            ce2.evento();
        }
        System.out.println(ce2.contagem());
    }
}
