package rep;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class ConsultasSimplesPreparedStatement {

	
	public static void main(String[] args) {
	
		//conexion
		Connection con = null;
		//datos para poder conectarse
		Properties propiedades = new Properties();
		//realizar la consulta
		PreparedStatement pstmt = null;
		//mostrar los resultados
		ResultSet rs = null;
		
		try {
			//pasamos los datos 
			propiedades.load(new FileInputStream("configuracion\\PropiedadesInventarioMatisse.txt"));
			DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
			
			//conectar a la bd
			con = ds.getConnection();
			
			//despues de conectar a la bd, hacer la consulta
			
			String sqla = "select idusuario, nombre, apellido1, apellido2 from usuario" ;
			String sqlb = "select idusuario, nombre, apellido1, apellido2, grupo from usuario";
			String sqlc = "select idusuario, usuario.nombre, usuario.apellido1, departamento from usuario, departamento"
					+ " where departamento.iddepartamento=usuario.departamento and departamento.nombre='Matemáticas'";
			String sqld = "select idusuario, usuario.nombre, usuario.apellido1, departamento from usuario, departamento"
					+ " where departamento.iddepartamento=usuario.departamento and departamento.nombre=?";
			
			String sqle = "select usuario.idusuario, usuario.nombre, usuario.apellido1, usuario.departamento from usuario, departamento"
					+ " where departamento.iddepartamento=usuario.departamento and departamento.nombre=? and "
					+ "usuario.apellido1 like ?";

			String sqlf = "select nombre, apellido1, apellido2 from usuario order by apellido1";
			
			pstmt = con.prepareStatement(sqlf);
			//set ?
//			String departamento = "Informática";
//			String contieneApellido = "Cobos";
			//ANTES DE EJECUTAR LA CONSULTA
//			pstmt.setString(1, departamento);
//			pstmt.setString(2, "%"+contieneApellido+"%");
			//asignar al rs el ejecutar la consulta
			rs = pstmt.executeQuery();
			
		
			rs.first();
			System.out.print(rs.getString(1)+" ");
			System.out.print(rs.getString(2)+" ");
			System.out.println(rs.getString(3)+" ");
			
			rs.last();			
			System.out.print(rs.getString(1)+" ");
			System.out.print(rs.getString(2)+" ");
			System.out.println(rs.getString(3)+" ");
			
	
//			while(rs.next()){				
//				System.out.print(rs.getString(1)+" ");
//				System.out.print(rs.getString(2)+" ");
//				System.out.println(rs.getString(3)+" ");
////				System.out.println(rs.getString(4)+" ");
////				System.out.println(rs.getString(5));
//			}
						
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(con != null && !con.isClosed()) con.close();
				
				System.out.println("Desconectado");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}	
	}		
}
