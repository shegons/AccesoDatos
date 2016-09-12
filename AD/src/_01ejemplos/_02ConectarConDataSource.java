package _01ejemplos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class _02ConectarConDataSource {

	static String DRIVER = "com.mysql.jdbc.Driver";
	static String URL = "jdbc:mysql://localhost:3306/ciclismo";
	static String USR = "root";
	static String PWD = "mysql";

	public static void main(String[] args) {

		Connection con = null;

		//Creamos datasource y configuramos sus parametros
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(DRIVER);
		ds.setUrl(URL);
		ds.setUsername(USR);
		ds.setPassword(PWD);

		//Realizamos la conexion
		try {
			con = ds.getConnection();
			System.out.println("Conectado");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		//Cerramos la conexion
			try {
				if (con != null && !con.isClosed())
					con.close();
				System.out.println("Desconectado");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
