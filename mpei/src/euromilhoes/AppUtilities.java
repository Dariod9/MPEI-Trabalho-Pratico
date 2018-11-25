/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euromilhoes;

import java.util.Iterator;

/**
 *
 * @author Ricardo Carvalho
 */
public class AppUtilities {
    
    /*public static void main(String[] args){
        DatabaseUtilities.loadJogadores();
        for(Jogador j: DatabaseUtilities.getJogadores()){
            System.out.println(j);
        }    
    }*/
    
    protected static void loadInfo(){
        DatabaseUtilities.loadJogadores();
    }
    
    protected static boolean userInDatabase(String name, String pass){
        Jogador j=new Jogador(name, pass);
        return DatabaseUtilities.getJogadores().contains(j);
    }
    
    protected static boolean nameInDatabase(String name){
        Iterator it= DatabaseUtilities.getJogadores().iterator();
        while(it.hasNext()){
            if(((Jogador) it.next()).getNome().equals(name)){
                return true;
            }
        }
        return false;
    }
    
    protected static void addUserToDatabase(String name, String password){
        DatabaseUtilities.add(new Jogador(name,password));
    }
}
