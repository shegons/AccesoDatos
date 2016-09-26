package _02ejercicios;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class _08modificarBD {
	
	static BufferedReader tec=new BufferedReader (new InputStreamReader(System.in));

	public static void main(String[] args) {
		
		Connection con = null;		
		PreparedStatement pstmt = null;
		Properties propiedades = new Properties();
		try {
			propiedades.load(new FileInputStream("configuracion/PropiedadesCiclismo.txt"));
			DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
			con = ds.getConnection();
		
			
			
			String sql = "update maillot set premio=? where tipo=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1,100);
			pstmt.setString(2, "General");
			pstmt.executeUpdate();
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}

}
