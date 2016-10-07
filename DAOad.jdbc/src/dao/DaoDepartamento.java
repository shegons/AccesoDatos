package dao;

import java.sql.Connection;

import excepciones.BusinessException;
import jdbc.ConexionJDBC;
import pojo.Departamento;

	//nombre de la clase del pojo y el tipo de la clave primaria
	public class DaoDepartamento extends DaoGenerico<Departamento, Integer>{


		public void grabar(Departamento d) throws BusinessException{
			Connection con = ConexionJDBC.getConnection();
			
			/*
			 * PreparedStatement
			 * 	ResultSet
			 * 	try{
			 * 		String sql = "Insert into departamento values..)
			 */
			
		}
		
		
	
	}
