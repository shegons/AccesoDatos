package _01ejemplos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class _03ConexionFicheroPropiedades {

	public static void main(String[] args) {

		Connection con = null;

		try {
			Properties propiedades = new Properties();
			propiedades.load(new FileInputStream("configuracion\\PropiedadesCiclismo.txt"));
			DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
			con = ds.getConnection();
			System.out.println("Conexion realizada");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
