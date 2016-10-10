package interfaz;

import java.util.Scanner;

import dao.DaoDepartamento;
import excepciones.BusinessException;
import jdbc.ConexionJDBC;
import pojo.Departamento;

public class _01TestAnyadir {

	public static void main(String[] args) {
		ConexionJDBC conJDBC = null;
		
		try{
			Scanner tec = new Scanner(System.in);
			System.out.println("Nombre departamento: ");
			String nombre = tec.nextLine();
			
			conJDBC = new ConexionJDBC("configuracion/PropiedadesInventarioMatisse.txt");
			conJDBC.conectar();
			
			Departamento d = new Departamento(null, nombre);
			DaoDepartamento daod = new DaoDepartamento();
			daod.grabar(d);
			
			System.out.println(d.toString());
			
			
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally{
			conJDBC.desconectar();
		}


	}

}
