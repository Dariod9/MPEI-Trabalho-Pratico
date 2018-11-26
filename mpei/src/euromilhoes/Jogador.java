package euromilhoes;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Jogador implements java.io.Serializable {

    private Map<Date, Chave> mapa;
    private String nome, password;

    public Jogador(String nome, String password){
        mapa= new TreeMap<>();
        this.nome= nome;
        this.password= password;
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
    
    public void addChave(Date data, Chave chave){
        mapa.put(data, chave);
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
