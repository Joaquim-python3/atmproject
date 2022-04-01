/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import banco.ConectaBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Usuario;

/**
 *
 * @author LENOVO
 */
public class UsuarioDao {
    
    private Connection con = ConectaBD.getConexao();
    
    public void cadastrar(Usuario usuario){
        try{
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO cadastros (nome, cpf, bancoCliente, senha, saldo) VALUES (?,?,?,?,?)");
            
            ps.setString(1, usuario.getNome()); // a primeira interrogacao
            ps.setString(2, usuario.getCpf()); // segunda interrogacao
            ps.setString(3, usuario.getBanco()); //...
            ps.setString(4, usuario.getSenha());
            ps.setDouble(5, usuario.getSaldo());
            
            ps.execute();
            
        }catch(SQLException e){
            System.out.println("Erro:"+e);
        }
        
    }
    
    public int entrar(String cpf, String senha){
        
        try{
            Usuario u = new Usuario();
        
        PreparedStatement psPesquisa = con.prepareStatement(
                "SELECT * FROM cadastros");
        
        ResultSet rs = psPesquisa.executeQuery();
        
         while(rs.next()){ 
                
            u.setIdUsuario(rs.getInt("idUsuario"));
            u.setNome(rs.getString("nome"));
            u.setCpf(rs.getString("cpf"));
            u.setBanco(rs.getString("bancoCliente"));
            u.setSenha(rs.getString("senha"));
            u.setSaldo(rs.getDouble("saldo"));
                
            if(u.getCpf().equals(cpf) && u.getSenha().equals(senha)){
                return 1;
            }
         }
        
        }catch(SQLException e){
            System.out.println("Erro:"+e);
        }
        return 0;
    }
}
