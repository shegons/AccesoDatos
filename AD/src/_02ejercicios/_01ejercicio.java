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

public class _01ejercicio {

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
	      stmt = con.createStatement();
	      String sql = "select nompuerto, km from etapa, puerto where puerto.netapa=etapa.netapa and altura > 1000";
	      rs = stmt.executeQuery(sql);
	      
	      // Recorremos el ResultSet
	      while (rs.next()) {
	        //se puede poner el nombre del campo o el numero de orden de la columna del SELECT
	        System.out.println(rs.getString("nompuerto"));  //se puede cambiar el nombre por la posicion, en este caso 1
	        System.out.println(rs.getString("km")); //en este caso seria 2
	      }
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