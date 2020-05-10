package daoPizzaria;

import conexaoPizzaria.ConexaoPizzaria;
import java.sql.*;
import modeloPizzaria.GerentePizzaria;

public class GerentePizzariaDAO {
    Connection con = null;
    public GerentePizzariaDAO(){
        con = new ConexaoPizzaria().conectar();
    }
    
    public String inserir(GerentePizzaria c){
        String status = "Gerente inserido com sucesso!";
        String sql = "INSERT INTO gerente (cpf, nome, telefone, email) values (?,?,?,?)";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getCpf());
            ps.setString(2, c.getNome());
            ps.setString(3, c.getTelefone());
            ps.setString(4, c.getEmail());
            
            int n = ps.executeUpdate();
            if(n == 0){
                status = "Erro ao inserir";
            }
        }catch(Exception e){
            status = "CPF já cadastrado!";
        }
        return status;
    }

    public String atualizar(GerentePizzaria c){
        String status = "Gerente atualizado com sucesso!";
        String sql = "UPDATE gerente SET nome = ?, telefone = ?, email = ? WHERE cpf = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getNome());
            ps.setString(2, c.getTelefone());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getCpf());
            ps.executeUpdate();
            
        }catch(Exception e){
            status = "Erro ao atualizar o gerente";
        }
        return status;
    }
    
    public String excluir(GerentePizzaria c){
        String status = "Gerente excluido com sucesso!";
        try{
            String sql = "DELETE FROM gerente WHERE cpf = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getCpf());
            ps.executeUpdate();
        }catch(Exception e){
            status = e.getMessage();
        }
        return status;
    }
}
