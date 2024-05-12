package Dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Conexão.Conexao;
import entity.voltascarrinho;

public class voltascarrinhoDAO {

    public void inserirInfo(voltascarrinho voltascarrinho){

        String sql = "INSERT INTO VOLTASCARRINHO(ID, VOLTA_NUMERO, TEMPO, PILOTO, EQUIPE) VALUES (?,?,?,?,?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(3, voltascarrinho.getTempo());
            ps.setInt(1, voltascarrinho.getId());
            ps.setInt(2, voltascarrinho.getVolta_numero());
            ps.setString(4, voltascarrinho.getPiloto());
            ps.setString(5, voltascarrinho.getEquipe());

            ps.execute();
            ps.close();
        
        
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}