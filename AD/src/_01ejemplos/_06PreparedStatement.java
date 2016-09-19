package _01ejemplos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class _06PreparedStatement {

	public static void main(String[] args) {
		
		//indicar la posicion de los interrogantes para asignar las variables a la consulta
		//insert into equipo values(?,?)
		//bucle para insertar valores que se necesite
		//------------------------------
		//definir url de la bd
		//asignar a una variable la conexion con la bd con url, user y pw
		//asignar a una variable la consulta sin parametros
		//a partir de la variable anterior, se pasan los parametros a la select con set y la pos del ?
		//con esa variable, ejecutamos los cambios
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/ciclismo" ;
	
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String nomEquipo="ONCE";
		
		
		try{
			con = DriverManager.getConnection(jdbcUrl, "root", "mysql");
			
			
			//Ciclistas de un equipo que introduzca el usuario
			pstmt=con.prepareStatement("select nombre, nomeq from ciclista where nomeq=?");
			//en el interrogante numero 1 asigna el valor de nomEquipo
			pstmt.setString(1, nomEquipo);
			
			//
			rs = pstmt.executeQuery();
			
						
			
			//insertar dos equipos
			
			while(rs.next()){		    	 
				//coger valor del campo 1 (nombre)
		    	  System.out.println(rs.getString(1));
		    	  //coger valor del campo 2 (nomeq)
		    	  System.out.println(rs.getString(2));
		      }
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	}
}
