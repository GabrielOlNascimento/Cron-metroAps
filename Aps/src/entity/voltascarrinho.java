package entity;

public class voltascarrinho {
    
    private int id;
    private int volta_numero;
    private String tempo;
    private String piloto;
    private String equipe;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public int getVolta_numero() {
        return volta_numero;
    }

    public void setVolta_numero(int volta_numero) {
        this.volta_numero = volta_numero;
    }

    public String getPiloto() {
        return piloto;
    }

    public void setPiloto(String piloto) {
        this.piloto = piloto;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }
    
}
