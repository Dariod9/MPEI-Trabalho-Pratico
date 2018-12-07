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
    private static CountingBloomFilter<String> frequenciaNumeros= new CountingBloomFilter<>(50);
    private static CountingBloomFilter<String> frequenciaEstrelas= new CountingBloomFilter<>(12);
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
            FileInputStream fileIn = new FileInputStream("euromilhoes/data/data01.bin");
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
            FileOutputStream fileOut = new FileOutputStream("euromilhoes/data/data01.bin");
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
            FileInputStream fileIn = new FileInputStream("euromilhoes/data/data02.bin");
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
            FileOutputStream fileOut = new FileOutputStream("euromilhoes/data/data02.bin");
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
            FileInputStream fileIn = new FileInputStream("euromilhoes/data/data03.bin");
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
            FileOutputStream fileOut = new FileOutputStream("euromilhoes/data/data03.bin");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(sorteios);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    
    protected static void loadFrequenciaNumeros() {
        try {
            FileInputStream fileIn = new FileInputStream("euromilhoes/data/data04.bin");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            frequenciaNumeros = (CountingBloomFilter<String>) in.readObject();
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

    protected static void saveFrequenciaNumeros() {
        try {
            FileOutputStream fileOut = new FileOutputStream("euromilhoes/data/data04.bin");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(frequenciaNumeros);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    
    protected static void loadFrequenciaEstrelas() {
        try {
            FileInputStream fileIn = new FileInputStream("euromilhoes/data/data05.bin");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            frequenciaEstrelas = (CountingBloomFilter<String>) in.readObject();
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

    protected static void saveFrequenciaEstrelas() {
        try {
            FileOutputStream fileOut = new FileOutputStream("euromilhoes/data/data05.bin");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(frequenciaEstrelas);
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
    
    protected static void addJogada(Date d, Jogador j, Chave c){
        jogadores.get(jogadores.indexOf(j)).addChave(d, c);
    }
    
    protected static void insertNumber(int n){
        frequenciaNumeros.insert(Integer.toString(n));
    }
    
    protected static void insertEstrela(int n){
        frequenciaEstrelas.insert(Integer.toString(n));
    }
    
    protected static Map<Integer,Integer> numberCount(){
        Map<Integer,Integer> count= new TreeMap<>();
        for (int i = 0; i < 50; i++) {
            count.put(i,frequenciaNumeros.count(Integer.toString(i)));
        }
        return count;
    }
    
    protected static Map<Integer,Integer> estrelaCount(){
        Map<Integer,Integer> count= new TreeMap<>();
        for (int i = 0; i < 12; i++) {
            count.put(i, frequenciaEstrelas.count(Integer.toString(i)));
        }
        return count;
    }
    
    protected static void generatePlayers(){
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