package _02ejercicios;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class _07consultasSimplesPreparedStatement {

	static BufferedReader tec=new BufferedReader (new InputStreamReader(System.in));
	
public static void main(String[] args) {
	String jdbcUrl = "jdbc:mysql://localhost:3306/ciclismo" ;
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String nomEquipo="ONCE";
	
	try{
		con = DriverManager.getConnection(jdbcUrl, "root", "mysql");
		
		
		
		//a    
			//4 valores codigo, tipo, color, premio
			pstmt=con.prepareStatement("insert maillot values(?,?,?,?)");
			//coger valor del campo 1 (codigo)
	    	  String cod = tec.readLine();
	    	  pstmt.setString(1, cod);	    	  
	    	  //coger valor del campo 2 (tipo)
	    	  String tipo = tec.readLine();
	    	  pstmt.setString(2, tipo);
	    	//coger valor del campo 3 (color)
	    	  String color = tec.readLine();
	    	  pstmt.setString(3, color);
	    	//coger valor del campo 4 (premio)
	    	  int premio = Integer.parseInt(tec.readLine());
	    	  pstmt.setInt(4, premio);
	    	  
	    	  pstmt.executeUpdate();
	    	  
	    //b
	    	  
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
}
}
