package DAO;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author adrian
 * @class Conexion.java
 */
public class Conexion {
	
	public Connection AbrirConexion() throws Exception {
		Connection con;
		try {
			Class.forName("com.mysql.jbdc.Driver");
			String urlOdbc = "jdbc:mysql://localhost:3360:agenda";
			con = (java.sql.DriverManager.getConnection(urlOdbc, "iaw", "asir"));
			return con;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new Exception("Ha sido imposible establecer la conexión" + e.getMessage());
		}
	}
	
	public void CerrarConexion(Connection con) throws Exception {
		try {
			if (con != null)
				con.close();
		} catch (Exception e) {
			throw new Exception("Ha sido imposible establecer la conexión" + e.getMessage());
		}
	}
}
