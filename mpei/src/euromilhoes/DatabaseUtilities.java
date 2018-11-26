/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euromilhoes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ricardo Carvalho
 */
public class DatabaseUtilities {

    private static List<Jogador> jogadores = new ArrayList<>();
    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static SecureRandom rnd = new SecureRandom();

    public static List<Jogador> getJogadores() {
        return jogadores;
    }

    protected static void loadJogadores() {
        try {
            FileInputStream fileIn = new FileInputStream("src/euromilhoes/data/data01.bin");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            jogadores = (List<Jogador>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return;
        }
    }

    protected static void saveJogadores() {
        try {
            FileOutputStream fileOut = new FileOutputStream("src/euromilhoes/data/data01.bin");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(jogadores);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    
    protected static void add(Jogador j){
        jogadores.add(j);
        saveJogadores();
    }
    
    private static void generatePlayers(){
        for (int i = 0; i < 1000; i++) {
            jogadores.add(new Jogador("randomPlayer"+i, randomString(8)));
        }
        saveJogadores();
    }
    
    private static String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }
}
