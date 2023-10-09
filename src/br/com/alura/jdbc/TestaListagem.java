package br.com.alura.jdbc;

import br.com.alura.jdbc.factory.ConnectionFactory;

import java.sql.*;

public class TestaListagem {

    public static void main(String[] args) throws SQLException {

        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.recuperarConexao();

        PreparedStatement stm =
                connection.prepareStatement("SELECT ID, NOME, DESCRICAO FROM PRODUTO");
        stm.execute();

        ResultSet rst = stm.getResultSet();

        while(rst.next()){
            Integer id = rst.getInt("ID");
            System.out.println(id);
            String nome = rst.getString("NOME");
            System.out.println(nome);
            String descricao = rst.getNString("DESCRICAO");
            System.out.println(descricao);
        }

        connection.close();
    }
}
