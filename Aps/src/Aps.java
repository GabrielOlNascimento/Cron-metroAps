import Dao.voltascarrinhoDAO;
import entity.voltascarrinho;

public class Aps {
    public static void main(String[] args) {
        
    

    voltascarrinho v = new voltascarrinho();
    v.setId(1);
    v.setVolta_numero(2);
    v.setTempo_total("40");
    v.setPiloto("40");
    v.setEquipe("40");
    v.setVolta_tempo(20);
    
    
        
    new voltascarrinhoDAO().inserirInfo(v);
    }
}

