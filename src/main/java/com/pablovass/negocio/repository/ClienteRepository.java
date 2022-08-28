package com.pablovass.negocio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pablovass.modelo.Cliente;;

/**
 * interfas para definir las operaciones de bdd relacionado con clientes
 * @author pablovass
 * 
 * */
public interface ClienteRepository extends JpaRepository<Cliente, String> {
	/**
	 * Definicion de metodos para buscar los clientes por su apellido
	 * @param apellidoCli
	 * @return
	 * */
	public Cliente findByApellidoCli(String apellidoCli);
	
	public Cliente findByIdentificacion(String identificacionCli);
	
}
