package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class User {

    // Dados de conexão e método responsável por retornar o objeto Connection
    public Connection conectarDB() {

        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/usuarios";
            String usuario = "root";
            String senha = "";

            conn = DriverManager.getConnection(url, usuario, senha);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }

    public String nome = "";
    public boolean result = false;

    // Checa se login e senha estão cadastrados na tabela
    public boolean verificarUsuario(String login, String senha) {

        String sql = "";
        Connection conn = conectarDB();

        sql += "select nome from usuario ";
        sql += "where nome = '" + login + "'";
        sql += " and senha = '" + senha + "';";

        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                result = true;
                nome = rs.getString("nome");
            }

        } catch (Exception e) { }

        return result;
    }
}
