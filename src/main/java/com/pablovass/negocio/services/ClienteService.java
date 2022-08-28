/**
 * 
 */
package com.pablovass.negocio.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pablovass.modelo.Cliente;
import com.pablovass.negocio.repository.ClienteRepository;

/**
 * Clase pra definir los servicios de clientes 
 * @author pablovass
 *
 */
@Service // es como el @component pero orientado para los servicios
@Transactional(readOnly=true)// todos los metodos tratados en modo lectura 
public class ClienteService {
	private final ClienteRepository clienteRepository;

	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	/**
	 * Metodo para guardar un cliente
	 * @param cliente
	 * @return
	 */
	@Transactional
	public Cliente create (Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}
	/**
	 * Metodo para guardar un cliente 
	 * @param cliente
	 * @return
	 */
	@Transactional
	public Cliente update (Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}
	/**
	 * metodo para borrar un cliente 
	 * @param cliente
	 * @return
	 */
	@Transactional
	public void delete (Cliente cliente) {
		this.clienteRepository.delete(cliente);
	}
	/**
	 * Metodo para consultar un cliente por su identificacion 
	 * @param identificacionCli
	 * @return
	 */
	public Cliente findByIdentificacion(String identificacionCli) {
		return this.clienteRepository.findByIdentificacion(identificacionCli);
	}
	
	public List<Cliente>findAll(){
		return this.clienteRepository.findAll();
	}

	
}
