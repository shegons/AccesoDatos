package _02ejercicios;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class _05insertStatement {

	static BufferedReader tec=new BufferedReader (new InputStreamReader(System.in));
	
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
		
		String nomEquipo;
		String director;

		
		try{
			con = DriverManager.getConnection(jdbcUrl, "root", "mysql");
			//Ciclistas de un equipo que introduzca el usuario
			pstmt=con.prepareStatement("insert equipo values(?,?)");			
			//insertar dos equipos

			
			for(int i=0; i<2; i++){
				nomEquipo = tec.readLine();
				pstmt.setString(1, nomEquipo);
				
				director= tec.readLine();
				pstmt.setString(2, director);
				
				pstmt.executeUpdate();
			}  
			
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	}
}
