package br.com.alura.jdbc;
import br.com.alura.jdbc.factory.ConnectionFactory;

import java.sql.*;

public class TestaInsercaoComParametro {

    public static void main(String[] args) throws SQLException {

        ConnectionFactory factory = new ConnectionFactory();
        try (Connection connection = factory.recuperarConexao()) {

            connection.setAutoCommit(false);

            try (PreparedStatement stm =
                         connection.prepareStatement("INSERT INTO PRODUTO(NOME, DESCRICAO) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            ) {

                adicionarVariavel("SMARTTV", "45 POLEGADAS", stm);
                adicionarVariavel("RADIO", "RADIO A BATERIA", stm);
                connection.commit();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ROLLBACK EXECUTADO");
                connection.rollback();
            }
        }
    }
    public static void adicionarVariavel (String nome, String descricao, PreparedStatement stm) throws SQLException {
        stm.setString(1, nome);
        stm.setString(2, descricao);

        if (nome.equals("RADIO")) {
            throw new RuntimeException("Não foi possivel adicionar o produto!");
        }

        stm.execute();
        try (ResultSet rst = stm.getGeneratedKeys()) {
            while (rst.next()) {
                Integer id = rst.getInt(1);
                System.out.println("O id criado foi: " + id);
            }
        }
    }

}

