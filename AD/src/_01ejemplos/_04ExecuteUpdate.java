package _01ejemplos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class _04ExecuteUpdate {

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

			String sql = "CREATE TABLE representante (" + "id int(10) unsigned NOT NULL auto_increment,"
					+ "nombre varchar(50) NOT NULL default ''," + "fecha_ingreso date NOT NULL default '0000-00-00',"
					+ "salario float NOT NULL default '0'," + "PRIMARY KEY (id))";
			stmt.executeUpdate(sql);

			System.out.println("Tabla creada");

			sql = "INSERT INTO representante VALUES (1,'pepe', '2005-01-01',12000)";
			stmt.executeUpdate(sql);
			System.out.println("Inserciones realizadas");
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
