
package daoPizzaria;
import conexaoPizzaria.ConexaoPizzaria;
import java.sql.*;
import modeloPizzaria.FuncionarioPizzaria;

public class FuncionarioPizzariaDAO {
    Connection con = null;
    public FuncionarioPizzariaDAO(){
        con = new ConexaoPizzaria().conectar();
}

    public String inserir(FuncionarioPizzaria c){
        String status = "Funcionário inserido com sucesso!";
        String sql = "INSERT INTO funcionario (cpf, nome, telefone, email) values (?,?,?,?)";
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

    public String atualizar(FuncionarioPizzaria c){
        String status = "Funcionario atualizado com sucesso!";
        String sql = "UPDATE funcionario SET nome = ?, telefone = ?, email = ? WHERE cpf = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getNome());
            ps.setString(2, c.getTelefone());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getCpf());
            ps.executeUpdate();
            
        }catch(Exception e){
            status = "Erro ao atualizar o Funcionário";
        }
        return status;
    }
    
    public String excluir(FuncionarioPizzaria c){
        String status = "Funcionário excluido com sucesso!";
        try{
            String sql = "DELETE FROM funcionario WHERE cpf = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getCpf());
            ps.executeUpdate();
        }catch(Exception e){
            status = e.getMessage();
        }
        return status;
    }
}
