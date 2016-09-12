package _01ejemplos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class _01ConectarConDriverManage {	

		static String DRIVER = "com.mysql.jdbc.Driver";
		static String URL = "jdbc:mysql://localhost:3306/ciclismo";
		static String USR = "root";
		static String PWD = "mysql";

		public static void main(String[] args) {

			Connection con = null;
			try {
				Class.forName(DRIVER).newInstance();
				System.out.println("Registrado");
				con = DriverManager.getConnection(URL, USR, PWD);
				System.out.println("Conectado");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
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
