/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author LENOVO
 */
public class ConectaBD {
    public static Connection getConexao() {
        try {
            // aqui quando for usar outro banco de dados, mudas apenas o nome
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atmproject", "root", "");
            System.out.println("conectado com sucesso!");
            return connection;
        } catch (SQLException e) {
            System.out.println("CONECTABD Erro: "+ e);
        }
        return null;
    }
}
