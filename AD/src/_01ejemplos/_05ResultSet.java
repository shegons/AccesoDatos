package _01ejemplos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class _05ResultSet {

	public static void main(String[] args) {

	    Connection con = null;
	    Statement stmt = null; 
	    ResultSet rs = null;

	    try {
	      Properties propiedades = new Properties();
	      propiedades.load(new FileInputStream("Configuracion/PropiedadesCiclismo.txt"));
	      DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
	      con = ds.getConnection();
	      
	      // Realizamos la consulta
	      //
	      stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); 
	      String sql = "select nompuerto, km from etapa, puerto where puerto.netapa=etapa.netapa and altura > 1000";
	      rs = stmt.executeQuery(sql);
	      
	      // Recorremos el ResultSet
	      //Sacar el ultimo
	      /*
	      rs.last();
	      System.out.println(rs.getString(1));
	      System.out.println(rs.getString(2));
	      */
	     //Sacar el segundo registro
	      /*
	      rs.absolute(2);
	      System.out.println(rs.getString(1));
	      System.out.println(rs.getString(2));
	      */
	      //Sacar los registros pares
	      int par = 2;
	      while(rs.next()){
	    	  rs.absolute(par);
	    	  par+=2;
	    	  System.out.println(rs.getString(1));
		      System.out.println(rs.getString(2));
	      }
	      
	      //Sacar el primero
	      /*
	      rs.first();
	      System.out.println(rs.getString(1));
	      System.out.println(rs.getString(2));
	      */
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
