package Entidad;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author adrian
 * @class Contacto.java
 */
public class Contacto implements Serializable, Comparable<Contacto> { // implements Comparable<Contacto>{
	
	String nombre;
	String telefono;
	Calendar fechaNac;
	String dni;
	
	public Contacto() {
	}
	
	public Contacto(String nom, String tel, Calendar fechaNac, String dni) {
		nombre = nom;
		telefono = tel;
		fechaNac = this.fechaNac;
		dni = this.dni;
	}
	
	public Contacto(String nom) {
		nombre = nom;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public Calendar getFechaNac() {
		return fechaNac;
	}
	
	public void setFechaNac(Calendar fechaNac) {
		this.fechaNac = fechaNac;
	}
	
	public String getDni() {
		return dni;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	@Override
	public String toString() {
		String fechaNacString;
		
		// definimos el formato de la fecha
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		// convertimos en String, con el formato anterior, la Date introducida como Calendar
		fechaNacString = df.format(fechaNac.getTime());
		
		return "Nombre: " + nombre + "\nTelefono: " + telefono + "\nFecha de Nacimiento: " + fechaNacString + "\nDNI: " + dni + "\n";
	}
	
	@Override
	public int compareTo(Contacto contc) {
		String contcNombre = contc.getNombre(); // creamos los String con
												// los Nombres de cada
												// uno de los objetos
		return this.nombre.compareTo(contcNombre); // comparamos los Strings, que nos
													// devuelve -1,0 o 1; que permite
													// su ordenación automática
	}
}
