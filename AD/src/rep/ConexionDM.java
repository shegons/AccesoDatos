package rep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDM {

	static BufferedReader tec=new BufferedReader (new InputStreamReader(System.in));
	static String DRIVER = "com.mysql.jdbc.Driver";
	
	public static void main(String[] args) throws IOException {
		
		Connection con = null;
	
		System.out.println("El nombre del servidor");
		String server = tec.readLine();
		
		System.out.println("Introduce el puerto");
		int port = Integer.parseInt(tec.readLine());
		
		System.out.println("Introduce el nombre de la base de datos");
		String bd = tec.readLine();
		
		//ruta de la bd
		String url = "jdbc:mysql://"+server+":"+port+"/"+bd;
		
		System.out.println("Introduce el nombre de usuario");
		String user = tec.readLine();
		
		System.out.println("Introduce la contraseña");
		String pass = tec.readLine();
		
		try {
			Class.forName(DRIVER).newInstance();
			System.out.println("Se ha registrado el driver");
			//conectar con la bd
			con = DriverManager.getConnection(url, user, pass);
			System.out.println("Se ha conectado con la base de datos "+bd);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} finally{
			
				try {
					if(con != null && !con.isClosed()) con.close();
					System.out.println("Desconectado");
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}				
	}
}
