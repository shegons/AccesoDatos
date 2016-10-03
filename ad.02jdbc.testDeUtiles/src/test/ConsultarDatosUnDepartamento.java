package test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jdbc.ConexionJDBC;

public class ConsultarDatosUnDepartamento {

	public static void main(String[] args) {
		ConexionJDBC con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String fichero = "configuracion\\PropiedadesInventarioMatisse.txt";
		
		try{
			con = new ConexionJDBC(fichero);		
			con.conectar();
		
			String sql = "select * from departamento where nombre = ?";
			
			//devuelve objeto connection
			pstmt = con.getConnection().prepareStatement(sql);
			
			String nom = "Informática";
			pstmt.setString(1, nom);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				System.out.print(rs.getString(1));
				System.out.print(" - ");
				System.out.println(rs.getString(2));
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			con.cerrar(rs);
			con.cerrar(pstmt);
			con.desconectar();
		}
	}
}
