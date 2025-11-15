# Planilha de Testes
## Planilha: 
https://docs.google.com/spreadsheets/d/1gzVLSP6HJE16QurfVsWetMXFsBcW5Z0W/edit?gid=1251103490#gid=1251103490

![WhatsApp Image 2025-11-15 at 18 12 30](https://github.com/user-attachments/assets/241f28ba-58c3-4d28-8edd-2be05aafa988)


# Notação de Grafo de Fluxo
```
package login;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User { //N1

    public Connection conectarDB(){ //N2
        Connection conn = null;
        try { //N3 – tenta fazer a conexão
            Class.forName("com.mysql.cj.jdbc.Driver"); //N4 – carrega driver

            String url = "jdbc:mysql://localhost:3306/usuarios";
            String usuario = "root";
            String senha = "";

            conn = DriverManager.getConnection(url, usuario, senha); //N6
        } catch (Exception e) { } //N7  falha na conexão

        return conn; //N8  retorno da conexão 
    }

    public String nome = "";       // Recebe o nome do usuário
    public boolean result = false; // Resultado final da verificação

    // Verifica se o usuário existe
    public boolean verificarUsuario(String login, String senha) { //N9
        String sql = "";
        Connection conn = conectarDB(); //N10

        // Montagem da instrução SQL
        sql += "select nome from usuario ";
        sql += "where nome = '" + login + "'";
        sql += " and senha = '" + senha + "';";

        try { //N11
            Statement st = conn.createStatement(); //N12 cria o statement
            ResultSet rs = st.executeQuery(sql);   //N13 executa a query

            if (rs.next()) { //N14 – decisão: usuário encontrado?
                result = true;  //N15
                nome = rs.getString("nome");
            }

        } catch (Exception e) { } //N16 erro na execução

        return result; //N17 retorna verdadeiro ou falso
    }

}

```





# Representação gráfica

![WhatsApp Image 2025-11-15 at 18 12 49](https://github.com/user-attachments/assets/d0e6cfc8-7ac5-49a8-9908-8f4b422d0d38)
![WhatsApp Image 2025-11-15 at 18 13 13](https://github.com/user-attachments/assets/965b93a0-ca02-4a33-a06a-d2d8fa670752)


# Complexidade ciclomática:
Arestas (E): 12
Nós (N): 9

Cálculo:

M = E - N + 2P
M = 12 - 9 + 2 * 2
M = 5

## Complexidade ciclomática = 5

# Caminhos Básicos
## conectarDB()
<br> N2 → N3 → N4 → N5 → N6 → N8
<br> N2 → N3 → N7 → N8
<br> N2 → N3 → N4 → N5 → (falha N6) → N7 → N8
<br> N2 → N3 → N7 → N8
## verificarUsuario()
<br> N9 → N10 → N11 → N12 → N13 → N14(true) → N15 → N17
<br> N9 → N10 → N11 → N12 → N13 → N14(false) → N17
<br> N9 → N10 → N11 → N16 → N17
<br> N9 → N10(null) → N11 → N16 → N17
<br> N9 → N10 → N11 → N12 → (falha N13) → N16 → N17
