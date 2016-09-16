package _02ejercicios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class _02update {


	public static void main(String[] args) {

		Connection con = null;
		Statement stmt = null;
		try {
			Properties propiedades = new Properties();
			propiedades.load(new FileInputStream("configuracion\\PropiedadesCiclismo.txt"));
			DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
			con = ds.getConnection();
			System.out.println("Conexion realizada");

			stmt = con.createStatement();
			String sql = "update maillot set premio=premio+10000 where codigo='MGE'";
			stmt.executeUpdate(sql);
			System.out.println("Actualizaciones realizadas");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null && !stmt.isClosed())
					stmt.close();
				System.out.println("Desconectado");
			} catch (SQLException e) {
				e.printStackTrace();
			}

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
