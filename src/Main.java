import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Main{
	public static void main (String[] args){
		Connection conn = null;
		try{
            // MySQL Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
            // MySQL User - change if necessary
            String userName = "root";
            // MySQL Password - change if necessary
            String password = "root";
            // Generic format of the string connection URL = "protocol//[hosts][/database][?properties]"
            String url = "jdbc:mysql://localhost:3306/bd_portugal";
            // Set Connection        
            conn = DriverManager.getConnection(url, userName, password);
            // Set Query
            String consulta = "SELECT * FROM distrito";
            // Run SQL -> start from here			   
            Statement stmt = null;
            ResultSet rs = null;
            try {
                // Create Statement
				stmt = conn.createStatement();
                // Get Result Set 
				rs = stmt.executeQuery(consulta);
                // Extract data from Result Set
				System.out.println("\n-------- BD PORTUGAL --------");
                System.out.println("\n| ID |    DISTRITO          |");
				while(rs.next()){
					//Retrieve by column name
					int id = rs.getInt("id_distrito");
					String d = rs.getString("nome");
                   	//Display values
                    System.out.printf("| %-2d | %-20s | %n", id, d); 
				}
				System.out.println("\n---------- END -------------\n");
			}catch(SQLException ex){
			// handle any errors
			   System.out.println("SQLException: " + ex.getMessage());
			   System.out.println("SQLState: " + ex.getSQLState());
			   System.out.println("VendorError: " + ex.getErrorCode());
			}finally{
				if(rs != null){
					try {
						rs.close();
					} catch (SQLException sqlEx) { } // ignore
					rs = null;
				}
				if(stmt != null){
					try {
						stmt.close();
					}catch (SQLException sqlEx) { } // ignore
					stmt = null;
				}
			}			   
// SQL end at here			   
		}catch(Exception ex){
			System.err.println("Não foi possivel Ligar ao Servidor da BD");
			ex.printStackTrace();
        }finally{
			if(conn != null){
				try{
					System.out.println("\n***** A tentar terminar ligação BD *****");
					conn.close ();					   
					System.out.println ("\n\nLigação encerrada...");
				}catch (Exception ex){
					System.out.println("Erro ao terminar a ligação BD!");
				}
			}
		}
	}
}