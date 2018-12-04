/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euromilhoes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Ricardo Carvalho
 */
public class AppUtilities {

    public static void main(String[] args) {
        DatabaseUtilities.loadJogadores();
        for (Jogador j : DatabaseUtilities.getJogadores()) {
            System.out.println(j);
            if (!j.getNome().contains("randomPlayer")) {
                System.out.println(j.getMapa().values());
            }
        }
        System.out.println();
        DatabaseUtilities.loadDates();
        for (Date d : DatabaseUtilities.getDates()) {
            System.out.println(d);
        }
        System.out.println();
        DatabaseUtilities.loadSorteios();
        for (Date d : DatabaseUtilities.getSorteios().keySet()) {
            System.out.print(d + ": ");
            System.out.println(DatabaseUtilities.getSorteios().get(d));
        }
        /*for (int i = 0; i < 100; i++) {
            Chave premio= new Chave();
            ChavesPremio cp = new ChavesPremio(premio);
            CountingBloomFilter cbf = new CountingBloomFilter(10778691);
            List<Chave> l= cp.getChaves();
            for (int j = 0; j < 10778691; j++) {
                cbf.insert(l.get(j));
            }
            Chave teste= new Chave();
            if(cbf.membershipTest(teste)){
                System.out.println(i+ ":Prémio!!!!");
                System.out.println(premio);
                System.out.println(teste);
                System.out.println();
            }
        }*/
 /*Chave c= null;
        CountingBloomFilter<Chave> cbm= null;
        List<Chave> pr= null;
        boolean b;
        Chave teste= null;
        for (int i = 0; i < 10; i++) {
            c= new Chave();
            ChavesPremio cp = new ChavesPremio(c, 1, 2);
            pr = cp.getChaves();
            cbm = new CountingBloomFilter<>(pr.size());
            for (Chave ch : pr) {
                cbm.insert(ch);
            }
            teste = new Chave();
            b = cbm.membershipTest(teste);
            if (b) {
                System.out.println("Chave: " + c);
                System.out.println("Teste: " + teste);
            }
            System.out.println("n: 1 | e: 2  : " + b);
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
                        System.out.println("Chave: " + c);
                        System.out.println("Teste: " + teste);
                    }
                    System.out.println("n: " + j + "| e: " + k + "  : " + b);
                }
            }
        }*/

    }

    protected static void loadInfo() {
        DatabaseUtilities.loadJogadores();
        DatabaseUtilities.loadDates();
        DatabaseUtilities.loadSorteios();
        DatabaseUtilities.loadFrequenciaNumeros();
        DatabaseUtilities.loadFrequenciaEstrelas();
    }

    protected static void saveInfo() {
        DatabaseUtilities.saveJogadores();
        DatabaseUtilities.saveDates();
        DatabaseUtilities.saveSorteios();
        DatabaseUtilities.saveFrequenciaNumeros();
        DatabaseUtilities.saveFrequenciaEstrelas();
    }

    protected static void defaultUsersJogada(Date d) {
        for (Jogador j : DatabaseUtilities.getJogadores()) {
            if (j.getNome().contains("randomPlayer")) {
                Chave c = new Chave();
                for (int i : c.getNumeros()) {
                    DatabaseUtilities.insertNumber(i);
                }
                for (int i : c.getEstrelas()) {
                    DatabaseUtilities.insertEstrela(i);
                }
                addJogadaToDatabase(d, j, c.getNumeros(), c.getEstrelas());
            }
        }
    }

    protected static int applyCountingBloomFilterToCheckAwards(Date d, Jogador jog) {
        ChavesPremio cp = new ChavesPremio(DatabaseUtilities.getSorteios().get(d), 1, 2);
        List<Chave> keys = cp.getChaves();
        CountingBloomFilter<Chave> awards = new CountingBloomFilter<>(keys.size());
        for (int i = 0; i < keys.size(); i++) {
            awards.insert(keys.get(i));
        }
        if (awards.membershipTest(jog.getMapa().get(d))) {
            return premioByCombination(1, 2);
        }
        for (int j = 2; j < 6; j++) {
            for (int k = 0; k < 3; k++) {
                cp = new ChavesPremio(DatabaseUtilities.getSorteios().get(d), j, k);
                keys = cp.getChaves();
                awards = new CountingBloomFilter<>(keys.size());
                for (int i = 0; i < keys.size(); i++) {
                    awards.insert(keys.get(i));
                }
                if (awards.membershipTest(jog.getMapa().get(d))) {
                    return premioByCombination(j, k);
                }
            }
        }
        return 0;
    }

    private static int premioByCombination(int nums, int estrls) {
        if (nums == 1 && estrls == 2) {
            return 11;
        } else if (nums == 2 && estrls == 0) {
            return 5;
        } else if (nums == 2 && estrls == 1) {
            return 8;
        } else if (nums == 2 && estrls == 2) {
            return 22;
        } else if (nums == 3 && estrls == 0) {
            return 14;
        } else if (nums == 3 && estrls == 1) {
            return 16;
        } else if (nums == 3 && estrls == 2) {
            return 126;
        } else if (nums == 4 && estrls == 0) {
            return 80;
        } else if (nums == 4 && estrls == 1) {
            return 194;
        } else if (nums == 4 && estrls == 2) {
            return 5321;
        } else if (nums == 5 && estrls == 0) {
            return 48956;
        } else if (nums == 5 && estrls == 1) {
            return 840782;
        } else if (nums == 5 && estrls == 2) {
            return 70000000;
        } else {
            return 0;
        }
    }

    protected static int[] mostFrequentChave() {
        int[] mostFreq = new int[7];
        int index = 0;
        List<Integer> freqNum = new ArrayList<>(DatabaseUtilities.numberCount().values());
        Collections.sort(freqNum);
        freqNum = freqNum.subList(45, 50);
        for (Entry<Integer, Integer> entry : DatabaseUtilities.numberCount().entrySet()) {
            for (int i = 0; i < 5; i++) {
                if (entry.getValue().equals(freqNum.get(i))) {
                    mostFreq[index] = entry.getKey();
                    index++;
                }
            }
            if (index == 5) {
                break;
            }
        }
        List<Integer> freqEstrl = new ArrayList<>(DatabaseUtilities.estrelaCount().values());
        Collections.sort(freqEstrl);
        freqEstrl = freqEstrl.subList(10, 12);
        for (Entry<Integer, Integer> entry : DatabaseUtilities.estrelaCount().entrySet()) {
            for (int i = 0; i < 2; i++) {
                if (entry.getValue().equals(freqEstrl.get(i))) {
                    mostFreq[index] = entry.getKey();
                    index++;
                }
            }
            if (index == 7) {
                break;
            }
        }
        return mostFreq;
    }

    protected static Date[] datesList() {
        return DatabaseUtilities.getDates().toArray(new Date[0]);
    }

    protected static Integer[] chave(Date d) {
        Chave c = DatabaseUtilities.getSorteios().get(d);
        List<Integer> chave = Stream.concat(c.getNumeros().stream(), c.getEstrelas().stream()).collect(Collectors.toList());
        return chave.toArray(new Integer[0]);
    }

    protected static boolean userInDatabase(String name, String pass) {
        Jogador j = new Jogador(name, pass);
        return DatabaseUtilities.getJogadores().contains(j);
    }

    protected static Jogador getUser(String name, String pass) {
        Jogador j = new Jogador(name, pass);
        List<Jogador> list = DatabaseUtilities.getJogadores();
        return list.get(list.indexOf(j));
    }

    protected static Date[] getSorteiosDates() {
        return DatabaseUtilities.getSorteios().keySet().toArray(new Date[0]);
    }

    protected static String getChaveStringByUser(Date d, Jogador j) {
        List<Integer> numeros = j.getMapa().get(d).getNumeros();
        List<Integer> estrelas = j.getMapa().get(d).getEstrelas();
        String s = "Números: " + numeros.get(0);
        for (int i = 1; i < 5; i++) {
            s += ", " + numeros.get(i);
        }
        s += "\nEstrelas: " + estrelas.get(0) + ", " + estrelas.get(1);
        return s;
    }

    protected static boolean nameInDatabase(String name) {
        Iterator it = DatabaseUtilities.getJogadores().iterator();
        while (it.hasNext()) {
            if (((Jogador) it.next()).getNome().equals(name)) {
                return true;
            }
        }
        return false;
    }

    protected static boolean dateInDatabase(int day, int month, int year) {
        Date d = new GregorianCalendar(year, month, day).getTime();
        return DatabaseUtilities.getDates().contains(d);
    }

    protected static boolean dateInDatabase(Date d) {
        return DatabaseUtilities.getDates().contains(d);
    }

    protected static void addUserToDatabase(String name, String password) {
        DatabaseUtilities.addJogador(new Jogador(name, password));
    }

    protected static boolean addDateToDatabase(int day, int month, int year) {
        GregorianCalendar c = new GregorianCalendar(year, month, day);
        if (c.get(Calendar.DAY_OF_WEEK) == 3 || c.get(Calendar.DAY_OF_WEEK) == 6) {
            DatabaseUtilities.addDate(new GregorianCalendar(year, month, day).getTime());
            return true;
        }
        return false;
    }

    protected static void addSorteioToDatabase(Date d) {
        DatabaseUtilities.addSorteio(d, new Chave());
    }

    protected static void addJogadaToDatabase(Date d, Jogador j, List<Integer> nums, List<Integer> estrelas) {
        for (int i : nums) {
            DatabaseUtilities.insertNumber(i);
        }
        for (int i : nums) {
            DatabaseUtilities.insertEstrela(i);
        }
        DatabaseUtilities.addJogada(d, j, new Chave(nums, estrelas));
    }

    protected static boolean removeUserFromDatabase(String name) {
        return DatabaseUtilities.removeJogador(new Jogador(name, passwordByName(name)));
    }

    private static String passwordByName(String name) {
        for (Jogador j : DatabaseUtilities.getJogadores()) {
            if (j.getNome().equals(name)) {
                return j.getPassword();
            }
        }
        return null;
    }

    protected static boolean removeDateFromDatabase(int day, int month, int year) {
        return DatabaseUtilities.removeDate(new GregorianCalendar(year, month, day).getTime());
    }

    protected static boolean validDate(int day, int month, int year) {
        //o ano´mínimo considerado válido é 2018
        return (month > 0 && month <= 12 && day > 0 && day <= diasDoMes(month) && year >= 2018 && year <= 2022) || (month == 2 && day == 29 && anoBissexto(year) && year >= 2018 && year <= 2022);
    }

    private static boolean anoBissexto(int y) { //função protegida que verifica se um ano é bissexto
        return y % 400 == 0 || (y % 4 == 0 && y % 100 != 0);
    }

    private static int diasDoMes(int m) { //retorna os dias de cada mes
        int d;
        switch (m) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                d = 31;
                break;
            case 2:
                d = 28;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                d = 30;
                break;
            default:
                d = -1;
                break;
        }
        return d;
    }
}
