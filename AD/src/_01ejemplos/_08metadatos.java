package _01ejemplos;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;


public class _08metadatos {
	
	
	public static void main(String[] args) {
		
		Connection con = null;
		Properties propiedades = new Properties();
		//ConexionJdbc conJdbc = new ConexionJdbc();
		
		try {
			propiedades.load(new FileInputStream("configuracion/PropiedadesCiclismo.txt"));
			DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
			con = ds.getConnection();
			DatabaseMetaData dbmd = con.getMetaData();
			
			//Info BD
			infoProducto(dbmd);
			
			//Info driver
			infoDriver(dbmd);
			
			//Info Tablas
			infoTablas(dbmd);
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Conectado");
		
	}
	public static void infoProducto(DatabaseMetaData dbmd) throws SQLException
	{
		System.out.println("INFORMACIÓnN SOBRE EL DBMS");
		//Nombre del SGBD
		System.out.println("Base de datos: " + dbmd.getDatabaseProductName());
		
		//Versión del SGBD
		System.out.println("Version: " + dbmd.getDatabaseProductVersion());
		
		//usuario conectado
		System.out.println("Usuario: " + dbmd.getUserName());
		
		//Url de la base de datos
		System.out.println("URL: " +  dbmd.getURL());
		
		
		System.out.println();
	}
	
	public static void infoDriver(DatabaseMetaData dbmd) throws SQLException
	{
		System.out.println("INFORMACIÓN SOBRE EL DRIVER");
		System.out.println("Driver: " + dbmd.getDriverName());
		System.out.println("Version: " + dbmd.getDriverVersion());
	}

	public static void infoTablas(DatabaseMetaData dbmd) throws SQLException
	{
		ResultSet rst = dbmd.getTables(null, null, null, new String[] {"TABLE"});
		System.out.println("TABLAS");
		while(rst.next())
		{
			System.out.println(rst.getString("TABLE_CAT"));	
			System.out.println(rst.getString("TABLE_NAME"));
			System.out.println(rst.getString("REMARKS"));
		}
		System.out.println();
				
	}
	
	
	
}
