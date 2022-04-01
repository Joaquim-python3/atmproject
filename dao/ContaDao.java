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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Usuario;

/**
 *
 * @author LENOVO
 */
public class ContaDao {
    
    private Connection con = ConectaBD.getConexao();
    
    public void deposito(String cpf, String senha, double valor){
        
        try {
            int count = 0;
            Usuario u = new Usuario();
            
            PreparedStatement psPesquisa = con.prepareStatement(
                        "SELECT * FROM cadastros");
            
            ResultSet rs = psPesquisa.executeQuery(); //pega uma linha inteira
            
            while(rs.next()){ 
                
                u.setIdUsuario(rs.getInt("idUsuario"));
                u.setNome(rs.getString("nome"));
                u.setCpf(rs.getString("cpf"));
                u.setBanco(rs.getString("bancoCliente"));
                u.setSenha(rs.getString("senha"));
                u.setSaldo(rs.getDouble("saldo"));
                
                if (u.getSenha().equals(senha) && u.getCpf().equals(cpf)) {
                    count = 1;
                    
                    psPesquisa = con.prepareStatement(
                            "UPDATE cadastros SET saldo=? WHERE cpf=? AND senha=?");
                    
                        psPesquisa.setDouble(1, (u.getSaldo() + valor));
                        psPesquisa.setString(2, cpf);
                        psPesquisa.setString(3, senha);
                        
                        psPesquisa.execute();
                        
                    JOptionPane.showMessageDialog(null, "DEPOSITO REALIZADO COM SUCESSO", 
                            "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            if(count == 0){
                JOptionPane.showMessageDialog(null, "USUARIO NÃO ENCONTRADO, PREENCHA OS CAMPOS CORRETAMENTE", 
                             "Aviso", JOptionPane.WARNING_MESSAGE);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro:" +e);
        }
    }
    
    public void saque(String cpf, String senha, double valor){
        
        try {
            int count = 0;
            Usuario u = new Usuario();
            
            PreparedStatement psPesquisa = con.prepareStatement(
                    "SELECT * FROM cadastros");
            
            ResultSet rs = psPesquisa.executeQuery(); //pega uma linha inteira
            
            while(rs.next()){ 
                
                u.setIdUsuario(rs.getInt("idUsuario"));
                u.setNome(rs.getString("nome"));
                u.setCpf(rs.getString("cpf"));
                u.setBanco(rs.getString("bancoCliente"));
                u.setSenha(rs.getString("senha"));
                u.setSaldo(rs.getDouble("saldo"));
                
                if (u.getSenha().equals(senha) && u.getCpf().equals(cpf)) {
                    
                    if((u.getSaldo() - valor) < 0){
                        count = 1;
                        JOptionPane.showMessageDialog(null, "VALOR DO SAQUE MAIOR QUE O VALOR PRESENTE NA CONTA", 
                    "Aviso", JOptionPane.WARNING_MESSAGE);
                        
                    }else{
                        count = 1;
                        psPesquisa = con.prepareStatement(
                            
                            "UPDATE cadastros SET saldo=? WHERE cpf=? AND senha=?");
                    
                        psPesquisa.setDouble(1, (u.getSaldo() - valor));
                        psPesquisa.setString(2, cpf);
                        psPesquisa.setString(3, senha);
                        
                        psPesquisa.execute();
                        
                        JOptionPane.showMessageDialog(null, "SAQUE REALIZADO COM SUCESSO", 
                             "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                
            }
            
            if(count == 0){
                JOptionPane.showMessageDialog(null, "USUARIO NÃO ENCONTRADO, PREENCHA OS CAMPOS CORRETAMENTE", 
                             "Aviso", JOptionPane.WARNING_MESSAGE);
            }
            
            
            
        } catch (SQLException e) {
            System.out.println("Erro:" +e);
        }
   
    }
    
    public void mostrarSaldo(String cpf, String senha){
       try {
            int count=0;
            Usuario u = new Usuario();
            
            PreparedStatement psPesquisa = con.prepareStatement(
                    "SELECT * FROM cadastros");
            
            ResultSet rs = psPesquisa.executeQuery(); //pega uma linha inteira
            
            while(rs.next()){ 
                
                u.setIdUsuario(rs.getInt("idUsuario"));
                u.setNome(rs.getString("nome"));
                u.setCpf(rs.getString("cpf"));
                u.setBanco(rs.getString("bancoCliente"));
                u.setSenha(rs.getString("senha"));
                u.setSaldo(rs.getDouble("saldo"));
                
                if (u.getSenha().equals(senha) && u.getCpf().equals(cpf)) {
                    count=1;
                    JOptionPane.showMessageDialog(
                        null, "Nome: "+u.getNome()+"\nCPF: "+u.getCpf()+"\nBanco: "+u.getBanco()+"\nSaldo: R$"+u.getSaldo(), 
                            "SALDO", JOptionPane.INFORMATION_MESSAGE);
                
                
                }
            }
            
            if(count == 0){
                JOptionPane.showMessageDialog(null, "USUARIO NÃO ENCONTRADO, PREENCHA OS CAMPOS CORRETAMENTE", 
                             "Aviso", JOptionPane.WARNING_MESSAGE);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro:" +e);
        }
       
    }
    
    // NAO APAGAR
    public void depositarCPFBanco(String cpf, String banco, double valor){
        
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
                
                if(u.getCpf().equals(cpf) && u.getBanco().equals(banco)){
                    
                    psPesquisa = con.prepareStatement(
                            "UPDATE cadastros SET saldo=? WHERE cpf=? AND bancoCliente=?");
                    
                        psPesquisa.setDouble(1, (u.getSaldo() + valor));
                        psPesquisa.setString(2, cpf);
                        psPesquisa.setString(3, banco);
                        
                        psPesquisa.execute();
                    
                    JOptionPane.showMessageDialog(null, "TRANSFERENCIA REALIZADA COM SUCESSO", 
                             "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }
            } 
        }catch(SQLException e){
            System.out.println("Erro:"+e);
        }
    }
    
    public void transferir(
            String cpfRemetente, 
            String senhaRemetente, 
            String cpfDestinatario, 
            String bancoDestinatario, 
            double valor)
    {
        
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
                
                // sacando do remetente
                if(u.getCpf().equals(cpfRemetente ) && u.getSenha().equals(senhaRemetente)){
                    // verifica se o valor de saque é maior que o saldo
                    if(valor > u.getSaldo()){
                        JOptionPane.showMessageDialog(null, "VALOR DE TRANSFERENCIA MAIOR QUE O SALDO", 
                             "Aviso", JOptionPane.WARNING_MESSAGE);
                        
                        break;
                        
                    }else{
                        
                        //sacando do remetente
                        psPesquisa = con.prepareStatement(
                            "UPDATE cadastros SET saldo=? WHERE cpf=? AND senha=?");
                    
                        psPesquisa.setDouble(1, (u.getSaldo() - valor));
                        psPesquisa.setString(2, cpfRemetente);
                        psPesquisa.setString(3, senhaRemetente);
                        
                        psPesquisa.execute();
                        
                        // depositando no destinatario pelo cpf e pelo banco
                        depositarCPFBanco(cpfDestinatario, bancoDestinatario, valor);
                        

                    }
                }       
            }

            
        }catch(SQLException e){
            System.out.println("Erro:"+e);
        }

    }
}

