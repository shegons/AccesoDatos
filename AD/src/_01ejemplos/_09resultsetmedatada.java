package _01ejemplos;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;


public class _09resultsetmedatada {

	
	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);
		Connection con = null;		
		Statement stm = null;
		
		try {
			Properties propiedades = new Properties();
			propiedades.load(new FileInputStream("configuracion/PropiedadesCiclismo.txt"));
			
			DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
			con = ds.getConnection();
			
			stm = con.createStatement(); 
			
			System.out.println("Tabla");
			String tabla = tec.nextLine();
			String sql = "SELECT * FROM "+tabla;
			ResultSet rs = stm.executeQuery(sql); 
			
			ResultSetMetaData rsmd = rs.getMetaData();
			
			int numColumnas = rsmd.getColumnCount();
			
			for (int i = 1; i <= numColumnas; i++) {
				System.out.println("Columna: "+i);
				System.out.println("Nombre: "+rsmd.getColumnName(i));
				System.out.println("Tipo: "+rsmd.getColumnType(i));
				System.out.println("Tipo: "+rsmd.getColumnTypeName(i));
				System.out.println("Display Size: "+rsmd.getColumnDisplaySize(i));
				System.out.println("ClassName: "+rsmd.getColumnClassName(i));
				System.out.println("Label: "+rsmd.getColumnLabel(i));
				System.out.println("Precision: "+rsmd.getPrecision(i));
				System.out.println("Escala: "+rsmd.getScale(i));
				System.out.println("Nullable: "+rsmd.isNullable(i));
				System.out.println("Auto increm: "+rsmd.isAutoIncrement(i));
				System.out.println("-------------------");
			}			
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}	
}
