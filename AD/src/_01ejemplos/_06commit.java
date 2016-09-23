package _01ejemplos;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class _06commit {

	public static void main(String[] args) {
		
		//Realiza una transaccion que inserte un ciclista nuevo de un equipo que no
		//tenemos aun en la base de datos.
		//Se han de ejecutar las dos insercciones. Al final de la transaccion tiene que
		//estar insertado el equipo y ciclista
		//
		//nombre=equipo1
		//director="Manolo"
		//ciclista=nombre
		//
		
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean autocommitActual = false;
		
		
		try {
			Properties propiedades = new Properties();
			propiedades.load(new FileInputStream("configuracion/PropiedadesCiclismo.txt"));
			DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
			con = ds.getConnection();
			System.out.println("Conectado");
			
			
			//guardamos estado actual de autocommit
			autocommitActual = con.getAutoCommit();
			
			//comezamos una transaccion
			con.setAutoCommit(false);
			
			//se prepara el pstmt con el insert
			pstmt = con.prepareStatement("insert into equipo values(?,?)");
					
			pstmt.setString(1,"Equipo11");
			pstmt.setString(2, "Manolo");
			pstmt.executeUpdate();
			
			pstmt = con.prepareStatement("insert into ciclista (nombre, nomeq) values(?,?)");
			
			pstmt.setString(1,"pepito");
			pstmt.setString(2, "equipo11");
			pstmt.executeUpdate();
			
			//Grabamos la transaccion
			con.commit();
			con.setAutoCommit(autocommitActual);
		
			System.out.println("Fin");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		} catch (Exception e) {
			try{
				con.rollback();
			}catch(SQLException e1){
				e1.printStackTrace();
			}
		}finally{
			try{
				autocommitActual=con.getAutoCommit();
				if(con!=null) con.setAutoCommit(autocommitActual);
				if(pstmt!=null && !pstmt.isClosed()) pstmt.close();
				if(con != null && !con.isClosed()) con.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
	}
}
