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

public class _04insertEquipo {

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
	      stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); //aqui se anyadiran los parametros del resultset
	      String sql = "select nomeq, director from equipo";
	      rs = stmt.executeQuery(sql);
	      
	     //se posiciona en la ultima posicion, reservando un espacio de memoria para la nueva fila
	     rs.moveToInsertRow();
	     //referenciamos los valores que queremos anyadir con el nombre del campo o el num y el nuevo valor
	     rs.updateString("nomeq", "nomeq1");
	     rs.updateString("director", "dir1");
	     //guardamos la insercion
	     rs.insertRow();
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
