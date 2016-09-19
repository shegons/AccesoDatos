package _02ejercicios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class _06consultasSimplesStatement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			
			Properties propiedades = new Properties();
		    propiedades.load(new FileInputStream("Configuracion/PropiedadesCiclismo.txt"));
		    DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
		    con = ds.getConnection();			
		    stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); 
		    
		    //definir consulta y ejecutar
		    
		    //a
//		    String sql = "select dorsal, nombre, nomeq from ciclista";		    
//		    rs = stmt.executeQuery(sql);
//		    
//		    //recorremos el ResultSet
//		    while(rs.next()){
//		    	System.out.print(rs.getString(1)+" ");
//			    System.out.print(rs.getString(2)+" ");
//			    System.out.print(rs.getString(3));
//			    System.out.println();
//		    }
		    
		    //b
//		    String sql = "select dorsal, nombre, director from ciclista, equipo where ciclista.nomeq=equipo.nomeq";		    
//		    rs = stmt.executeQuery(sql);
//		    
//		    //recorremos el ResultSet
//		    while(rs.next()){
//		    	System.out.print(rs.getString(1)+" ");
//			    System.out.print(rs.getString(2)+" ");
//			    System.out.print(rs.getString(3));
//			    System.out.println();
//		    }
		    
		    
		    
		    //c
		    String sql = "select dorsal, nombre, director from ciclista, equipo where ciclista.nomeq=equipo.nomeq and ciclista.nomeq='ONCE'";		    
		    rs = stmt.executeQuery(sql);
		    
		    //recorremos el ResultSet
		    while(rs.next()){
		    	System.out.print(rs.getString(1)+" ");
			    System.out.print(rs.getString(2)+" ");
			    System.out.print(rs.getString(3));
			    System.out.println();
		    }
		    
		    
		} catch (FileNotFoundException e) {

	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
		
		
		//Dorsal, nombre y equipo de todos los ciclistas
		
		//Dorsal, nombre y director de todos los ciclistas.
		
		//
	}

}
