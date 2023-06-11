import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Utils {

    // Metodo para fechar recursos ao sair
    public static void sair() throws ClassNotFoundException, SQLException{
        MySQLJDBC instance = MySQLJDBC.getInstance();
        instance.closeConnection();   
    }

    // Metodo para imprimir Menu
    public static void menuPrint(){
        System.out.println("+------------------------ MENU -------------------------+");
        System.out.println("| 0 - Sair                                              |");
        System.out.println("| 1 - Listar nome dos Distritos de Portugal             |");
        System.out.println("| 2 - Listar nome dos Concelhos de determinado Distrito |");
        System.out.println("| 3 - Listar freguesias de determinado concelho         |");
        System.out.println("+-------------------------------------------------------+");
        System.out.print("Digite opcao: ");
    }

    // Metodo Listar distritos
    public static void getDistritos() throws SQLException, Exception {
        MySQLJDBC instance = MySQLJDBC.getInstance();
        Connection connection = instance.getConnection();
        //System.out.println(connection);
        String query = "SELECT * FROM distrito";
        // Create Statement
		Statement stmt = connection.createStatement();
        // Get Result Set 
		ResultSet rs = stmt.executeQuery(query);
        System.out.println("\n-----------------------------");
        System.out.println("| ID |    DISTRITO          |");
        System.out.println("-----------------------------");
        // Extract data from Result Set
		while(rs.next()){
            //Retrieve by column name
            int id = rs.getInt("id_distrito");
            String d = rs.getString("nome");
            //Display values
            System.out.printf("| %-2d | %-20s | %n", id, d); 
		}
		System.out.println("-----------------------------\n");
        rs.close();
        stmt.close();

    }

    // Metodo Listar concelhos de um Distrito
    //  @param - id do distrito (int)
    //  @return - sem retorno
    public static void getConcelhos(int distrito) throws SQLException, Exception {
        MySQLJDBC instance = MySQLJDBC.getInstance();
        Connection connection = instance.getConnection();
        //System.out.println(connection);
        String query = "SELECT id_concelho, nome FROM concelho WHERE id_distrito = " + distrito;
        // Create Statement
		Statement stmt = connection.createStatement();
        // Get Result Set 
		ResultSet rs = stmt.executeQuery(query);
        // Extract data from Result Set
        System.out.println("\n-----------------------------");
		System.out.println("| ID  |    CONCELHOS        |");
        System.out.println("-----------------------------");
		while(rs.next()){
            //Retrieve by column name
            int id = rs.getInt("id_concelho");
            String c = rs.getString("nome");
            //Display values
            System.out.printf("| %-2d | %-20s | %n", id, c); 
		}
		System.out.println("-----------------------------\n");  
        rs.close();
        stmt.close();
 
    }
    // Metodo Listar Freguesias de determinado Concelho
    //  @param - id do concelho (int)
    //  @return - sem retorno
    public static void getFreguesias(int concelho) throws SQLException, Exception {
        MySQLJDBC instance = MySQLJDBC.getInstance();
        Connection connection = instance.getConnection();
        String query = "SELECT f.nome AS freguesia, f.url AS url" +
            " FROM freguesia AS f WHERE f.id_concelho = " + concelho;

        // Create Statement
        Statement stmt = connection.createStatement();
        // Get Result Set 
        ResultSet rs = stmt.executeQuery(query);
        System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("|                             FREGUESIA                            |                                  URL                              |");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        // Extract data from Result Set  
        while(rs.next()){
            //Retrieve by column name
            String f = rs.getString("FREGUESIA");
            String url = rs.getString("URL");
            //Display values
            System.out.printf("|%-65s | %-65s | %n",f, url); 
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------\n"); 
        rs.close();
        stmt.close();
  
    }
}