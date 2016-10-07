package dao;

import java.io.Serializable;
import java.util.List;

import excepciones.BusinessException;

public interface InterfaceDaoGenerico <T, ID extends Serializable> {
	
	/**
	 * Persiste el objeto dado.
	 * Tras la inseción el objeto tendrá actualizado su identificador. 
	 * @param objeto Objeto a insertar.
	 * @throws BussinessException si el objeto ya existia
	 */
	public void grabar (T objeto) throws BusinessException;
	
	/**
	 * Actualiza los datos persistidos de un objeto ya existente en la BD con los datos 
	 * contenidos en el pojo.
	 * @param objeto Objeto del que se toman los datos.
	 * @throws BusinessException si el objeto no existe.
	 */
	public void actualizar (T objeto) throws BusinessException;
	
	/**
	 * Graba o actualiza el objeto según convenga: Si existe lo actualiza y si 
	 * no existe lo graba.
	 * @param objeto Objeto del que se toman los datos
	 * @throws BusinessException
	 */
	public void grabarOActualizar (T objeto) throws BusinessException;
	
	/**
	 * Elimina el objeto indicado de la BD
	 * @param objeto Objeto a eliminar 
	 * @throws BusinessException si el objeto no existe o no se puede eliminar
	 */
	public void borrar (T objeto) throws BusinessException;
	
	/**
	 * Elimina el objeto indicado de la BD
	 * @param id Identificador del objeto a eliminar 
	 * @throws BusinessException si el objeto no existe o no se puede eliminar
	 */
	public void borrar (ID id) throws BusinessException;
	
	/**
	 * Devuelve el objeto cuyo id se indica-
	 * @param id identificador del objeto buscado
	 * @return el objeto buscado o null si no existe
	 */
	public T buscarPorId (ID id)  throws BusinessException;
	
	/**
	 * Devuelve una lista con todos los objetos de la base de datos
	 * @return lista con todos los objetos o una lista vacía si no hay ninguno.
	 */
	public List<T> buscarTodos()  throws BusinessException;
	
}
