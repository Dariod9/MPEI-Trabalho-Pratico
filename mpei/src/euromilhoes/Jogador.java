package euromilhoes;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Jogador implements Serializable {

    private Map<Date, Chave> mapa;
    private String nome, password;
    private int totalPremios;
    private ContadorEstocastico premiosEsperados;

    public Jogador(String nome, String password){
        mapa= new TreeMap<>();
        this.nome= nome;
        this.password= password;
        totalPremios= 0;
        premiosEsperados= new ContadorEstocastico((double)1 /13);
    }

    public Map<Date, Chave> getMapa() {
        return mapa;
    }

    public String getNome() {
        return nome;
    }

    public String getPassword() {
        return password;
    }
    
    public int getTotalPremios(){
        return totalPremios;
    }
    
    public int getPremiosEsperados(){
        return premiosEsperados.contagem();
    }
    
    public void addChave(Date data, Chave chave){
        mapa.put(data, chave);
    }
    
    public void setPremio(int premio){
        totalPremios+= premio;
    }
    
    public void setEvento(){
        premiosEsperados.evento();
    }

    @Override
    public String toString() {
        return "Jogador{" + "nome=" + nome + ", password=" + password + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jogador other = (Jogador) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }
}
