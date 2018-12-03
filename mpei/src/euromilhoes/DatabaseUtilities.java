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
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Ricardo Carvalho
 */
public class DatabaseUtilities {

    private static List<Jogador> jogadores = new ArrayList<>();
    private static List<Date> dates= new ArrayList<>();
    private static Map<Date,Chave> sorteios= new TreeMap<>();
    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static SecureRandom rnd = new SecureRandom();

    public static List<Jogador> getJogadores() {
        return jogadores;
    }

    public static List<Date> getDates() {
        Collections.sort(dates);
        return dates;
    }

    public static Map<Date, Chave> getSorteios() {
        return sorteios;
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
    
    protected static void loadDates() {
        try {
            FileInputStream fileIn = new FileInputStream("src/euromilhoes/data/data02.bin");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            dates = (List<Date>) in.readObject();
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

    protected static void saveDates() {
        try {
            FileOutputStream fileOut = new FileOutputStream("src/euromilhoes/data/data02.bin");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(dates);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    
    protected static void loadSorteios() {
        try {
            FileInputStream fileIn = new FileInputStream("src/euromilhoes/data/data03.bin");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            sorteios = (Map<Date,Chave>) in.readObject();
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

    protected static void saveSorteios() {
        try {
            FileOutputStream fileOut = new FileOutputStream("src/euromilhoes/data/data03.bin");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(sorteios);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    
    protected static void addJogador(Jogador j){
        jogadores.add(j);
    }
    
    protected static boolean removeJogador(Jogador j){
        return jogadores.remove(j);
    }
    
    protected static void addDate(Date d){
        dates.add(d);
    }
    
    protected static boolean removeDate(Date d){
        return dates.remove(d);
    }
    
    protected static void addSorteio(Date d, Chave c){
        dates.remove(d);
        sorteios.put(d, c);
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
