package jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import excepciones.ConnectionException;

public class ConexionJDBC {

	//dos formas de hacer la conexion, por fichero o por paramentros
	
	private static Connection con;
	
	private String driver;
	private String url;
	private String user;
	private String pass;
	
	private String ficheroConfiguracion;
	
	
	public ConexionJDBC(String driver, String url, String user, String pass){
		this.driver=driver;
		this.url=url;
		this.user=user;
		this.pass=pass;
	}
	
	public ConexionJDBC(String ficheroConfiguracion){
		this.ficheroConfiguracion=ficheroConfiguracion;
	}
	
	
	private void conectar(String driver, String url, String user, String pass) throws ConnectionException{
		 
		con = null;
		
		//Creamos datasource y configuramos sus parametros
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(pass);
		
		//realizamos la conexion
		try{
			con = ds.getConnection();
		} catch (SQLException e) {
			//guarda las excepciones y los errores severos en un archivo
			Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, "Error de conexión", e);
			throw new ConnectionException();
		}		
	}
	
	private void conectar(String ficheroConfiguracion) throws ConnectionException{
		
		con = null;
		
		try{
			Properties propiedades = new Properties();
			propiedades.load(new FileInputStream(ficheroConfiguracion));
			
			DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
			con = ds.getConnection();
			
		} catch (FileNotFoundException e){
			Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, "Fichero configuración incorrecto", e);
			throw new ConnectionException();
		} catch (IOException e){
			Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, "Fichero configuración mal formado", e);
			throw new ConnectionException();
		} catch (SQLException e) {
			Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, "Error de conexion", e);
			throw new ConnectionException();
		} catch (Exception e){
			Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, "Error de conexion", e);
			throw new ConnectionException();
		}
	}
	
	public void conectar(){
		if(con == null){
			if(ficheroConfiguracion!=null) conectar(ficheroConfiguracion);
			else conectar(driver, url, user, pass);
		}
	}
	
	//apunta a la bd
	public static Connection getConnection(){
		return con;
	}
	
	public void desconectar(){
		try{
			if(con!=null && !con.isClosed()) con.close();
		} catch (SQLException e){
			Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, "Error de desconexion", e);
		}
	}
	
	public static void cerrar(Statement stmt){
		try{
			if(stmt!=null && !stmt.isClosed()) stmt.close();
		} catch (SQLException e){
			Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, "Error al cerrar Statement", e);
		}
	}
	
	public static void cerrar(ResultSet rs){
		try{
			if(rs!=null && !rs.isClosed()) rs.close();
		} catch (SQLException e){
			Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, "Error de conexion", e);
		}
	}
}
