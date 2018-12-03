/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euromilhoes;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
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
        }
        System.out.println();
        DatabaseUtilities.loadDates();
        for(Date d: DatabaseUtilities.getDates()){
            System.out.println(d);
        }
        System.out.println();
        DatabaseUtilities.loadSorteios();
        for(Date d: DatabaseUtilities.getSorteios().keySet()){
            System.out.print(d+": ");
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
    }

    protected static void loadInfo() {
        DatabaseUtilities.loadJogadores();
        DatabaseUtilities.loadDates();
        DatabaseUtilities.loadSorteios();
    }

    protected static void saveInfo() {
        DatabaseUtilities.saveJogadores();
        DatabaseUtilities.saveDates();
        DatabaseUtilities.saveSorteios();
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

    protected static boolean removeUserFromDatabase(String name) {
        return DatabaseUtilities.removeJogador(new Jogador(name, passwordByName(name)));
    }

    private static String passwordByName(String name) {
        Iterator it = DatabaseUtilities.getJogadores().iterator();
        while (it.hasNext()) {
            Jogador j = (Jogador) it.next();
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
