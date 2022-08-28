/**
 * 
 */
package com.pablovass.ereservasiones.vista.resources;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pablovass.ereservasiones.modelo.Cliente;
import com.pablovass.ereservasiones.negocio.services.ClienteService;
import com.pablovass.ereservasiones.vista.resources.vo.ClienteVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
/**
 * Clase que representa el servicio web del cliente 
 * @author pablovass
 *
 */
@RestController
@RequestMapping("/api/cliente")
@Api("cliente")
public class ClienteResource {
	private final ClienteService clienteService;

	public ClienteResource(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
/**
 * 
 * @param clienteVo
 * @return
 */
	@PostMapping
	@ApiOperation(value="Crear Cliente", notes="Servicio para crear un nuevo cliente")
	@ApiResponses(value= {@ApiResponse(code=201, message="Cliente creado correctamente"),
			@ApiResponse(code=400, message="Solisitud Ivalida")})
	public ResponseEntity<Cliente>createCliente(@RequestBody ClienteVO clienteVo){
		Cliente cliente=new Cliente();
		cliente.setNombreCli(clienteVo.getNombreCli());
		cliente.setApellidoCli(clienteVo.getApellidoCli());
		cliente.setDireccionCli(clienteVo.getDireccionCli());
		cliente.setTelefonoCli(clienteVo.getTelefonoCli());
		cliente.setEmailCli(clienteVo.getEmailCli());
		return new ResponseEntity<>(this.clienteService.create(cliente),HttpStatus.CREATED);
	}
	@PutMapping("/{identificacion}")
	@ApiOperation(value="Actualizar Cliente", notes="Servicio para actulizar un nuevo cliente")
	@ApiResponses(value= {@ApiResponse(code=201, message="Cliente actualizado correctamente"),
			@ApiResponse(code=404, message="Cliente no encotrado")})
	public ResponseEntity<Cliente>updateCliente(@PathVariable("identificacion")String identificacion,ClienteVO clienteVo){
		Cliente cliente=this.clienteService.findByIdentificacion(identificacion);
		if (cliente ==null) {
			return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		}else {
			cliente.setNombreCli(clienteVo.getNombreCli());
			cliente.setApellidoCli(clienteVo.getApellidoCli());
			cliente.setDireccionCli(clienteVo.getDireccionCli());
			cliente.setTelefonoCli(clienteVo.getTelefonoCli());
			cliente.setEmailCli(clienteVo.getEmailCli());
			
		}
		return new ResponseEntity<>(this.clienteService.update(cliente),HttpStatus.OK);
	}
	/**
	 * metodo que borra el cliente 
	 * @param identificacion
	 */
	@DeleteMapping("/{identificacion}")
	@ApiOperation(value="Cliente Eliminado", notes="Servicio para eliminar un cliente")
	@ApiResponses(value= {@ApiResponse(code=201, message="Cliente eliminado correctamente"),
			@ApiResponse(code=404, message="Cliente no encontrado")})
	public void removeCliente(@PathVariable("identificacion")String identificacion) {
		//realizamos la consulta del cliente 
		Cliente cliente=this.clienteService.findByIdentificacion(identificacion);
		if(cliente != null) {
			this.clienteService.delete(cliente);
		}
	}
	@GetMapping
	@ApiOperation(value="Listar Cliente", notes="Servicio para listar todos los clientes")
	@ApiResponses(value= {@ApiResponse(code=201, message="Clientes Encontrados"),
			@ApiResponse(code=404, message="Clientes no encontrados")})
	public ResponseEntity<List<Cliente>>findAll(){
		return ResponseEntity.ok(this.clienteService.findAll());
	}
}
