package Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import DAO.Conexion;
import Entidad.Contacto;

/**
 * @author adrian
 * @class main.java
 */
public class main {
	
	public static void main(String argm[]) {
		String nombre, dni, telefono;
		Calendar fechaNac;
		BufferedReader tec = new BufferedReader(new InputStreamReader(System.in));
		try {
			Conexion conexion_DB = new Conexion();
			System.out.println("Abrir Conexion");
			Connection con = conexion_DB.AbrirConexion();
			System.out.println("Conexion abierta");
			
			// insertamos un contacto
			Contacto contc = new Contacto();
			System.out.println("Introduce el nombre del Contacto");
			nombre = tec.readLine();
			contc.setNombre(nombre);// introducimos al objeto creado
			
			System.out.println("Introduce el telefono del Contacto");
			telefono = tec.readLine();
			contc.setTelefono(telefono);// introducimos al objeto creado
			
			System.out.println("Introduce la fecha de nacimiento del Contacto");
			fechaNac = fechaValidada();
			contc.setFechaNac(fechaNac);// introducimos al objeto creado
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy");
			String anyoNac = df.format(fechaNac);
			
			System.out.println("Introduce el DNI del contacto");
			dni = tec.readLine();
			contc.setDni(dni);// introducimos al objeto creado
			
			// insertamos un contacto en la BBDD
			PreparedStatement stmt;
			String query = "INSERT INTO contactos VALUES (?,?,?,?)";
			stmt = con.prepareStatement(query);
			stmt.setString(1, dni);
			stmt.setString(2, nombre);
			stmt.setString(3, telefono);
			stmt.setString(4, anyoNac);
			stmt.executeUpdate();
			
			System.out.println("Cerrar Conexion");
			conexion_DB.CerrarConexion(con);
			System.out.println("Conexion cerrada");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public static Calendar fechaValidada() {
		BufferedReader tec = new BufferedReader(new InputStreamReader(System.in));
		Calendar fecha = null;
		String strDia, strMes, strAño;
		int dia = -1, mes = -1, año = -1;
		int añoActual;
		boolean isDia = false, isMes = false, isAño = false;
		
		// primero de nada, sacamos el año actual, para la posterior validacion
		Date fechaActual = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		añoActual = Integer.parseInt(df.format(fechaActual));
		
		try {
			while (isDia == false) {
				System.out.println("Introduce el dia de su nacimiento");
				strDia = tec.readLine();
				
				dia = Integer.parseInt(strDia);
				if (dia > 0 && dia < 32)
					isDia = true;
					
				if (isDia == false)
					System.out.print("ERROR. ");
			}
			
			while (isMes == false) {
				System.out.println("Introduce el mes de su nacimiento");
				strMes = tec.readLine();
				
				mes = Integer.parseInt(strMes);
				if (mes > 0 && mes < 13) {
					isMes = true; // en principio cumple, por lo que cambia a TRUE
					// ahora comprobamos los posibles fallos añadidos
					if (mes == 2 && dia > 29)
						isMes = false;
					if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30)
						isMes = false;
						
				}
				
				if (isMes == false)
					System.out.print("ERROR. ");
			}
			
			while (isAño == false) {
				System.out.println("Introduce el año de su nacimiento");
				strAño = tec.readLine();
				año = Integer.parseInt(strAño);
				if (año > 1900 && año <= añoActual) {
					isAño = true; // en principio cumple, por lo que cambia a TRUE
					// ahora comprobamos los posibles fallos añadidos
					if (año % 4 != 0 && mes == 2 && dia == 29)
						isAño = false;
				}
				
				if (isAño == false)
					System.out.print("ERROR. ");
			}
			
			fecha = new GregorianCalendar(año, mes - 1, dia);// esta es la manera de introducir los datos para la fecha definida en Calendar
			// el mes-1 se pone porque el Calendar empieza a contar los meses desde 0
			
			// System.out.println(fecha.get(Calendar.DATE) + "/" + (fecha.get(Calendar.MONTH)+1)
			// + "/" + fecha.get(Calendar.YEAR));
			
		} catch (IOException e) {
			System.out.println("ERROR AL INTRODUCIR LOS DATOS DE LA FECHA DE CUMPLEAÑOS");
		}
		
		return fecha;
	}
	
}
