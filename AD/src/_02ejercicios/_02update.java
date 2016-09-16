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

public class _02update {

	public static void main(String[] args) {

	    Connection con = null;
	    Statement stmt = null;
	   // ResultSet rs = null;

	    try {
	      Properties propiedades = new Properties();
	      propiedades.load(new FileInputStream("Configuracion/PropiedadesCiclismo.txt"));
	      DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
	      con = ds.getConnection();
	      
	      // Realizamos la consulta
	      stmt = con.createStatement();
	      String sql = "update maillot set premio=premio+10000 where codigo='MGE'";
	      stmt.executeQuery(sql);
	      
	     
	    } catch (FileNotFoundException e) {

	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	  }
	
}
