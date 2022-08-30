
package com.pablovass.vista.resources;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

import com.pablovass.modelo.Cliente;
import com.pablovass.negocio.services.ClienteService;
import com.pablovass.vista.resources.vo.ClienteVO;


/**
 * Clase que representa el servicio web del cliente 
 * @author pablovass
 *
 */
@RestController
@RequestMapping("/api/cliente")
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
    @Operation(summary = "Crear Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Servicio para crear un nuevo cliente",
                    content = { @Content(mediaType = "/{identificacion}",
                            schema = @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "201", description = "Cliente creado correctamente",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Solisitud Ivalida",
                    content = @Content) })
    public ResponseEntity<Cliente> createCliente(@RequestBody ClienteVO clienteVo) {
        Cliente cliente = new Cliente();
        cliente.setNombreCli(clienteVo.getNombreCli());
        cliente.setApellidoCli(clienteVo.getApellidoCli());
        cliente.setDireccionCli(clienteVo.getDireccionCli());
        cliente.setTelefonoCli(clienteVo.getTelefonoCli());
        cliente.setEmailCli(clienteVo.getEmailCli());
        return new ResponseEntity<>(this.clienteService.create(cliente), HttpStatus.CREATED);
    }

    @PutMapping("/{identificacion}")
    @Operation(summary = "Actualizar Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Servicio para actulizar un nuevo cliente",
                    content = { @Content(mediaType = "/{identificacion}",
                            schema = @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "201", description = "Cliente actualizado correctamente",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = " Cliente no encontrado",
                    content = @Content) })
    public ResponseEntity<Cliente> updateCliente(@PathVariable("identificacion") String identificacion, ClienteVO clienteVo) {
        Cliente cliente = this.clienteService.findByIdentificacion(identificacion);
        if (cliente == null) {
            return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
        } else {
            cliente.setNombreCli(clienteVo.getNombreCli());
            cliente.setApellidoCli(clienteVo.getApellidoCli());
            cliente.setDireccionCli(clienteVo.getDireccionCli());
            cliente.setTelefonoCli(clienteVo.getTelefonoCli());
            cliente.setEmailCli(clienteVo.getEmailCli());

        }
        return new ResponseEntity<>(this.clienteService.update(cliente), HttpStatus.OK);
    }

    /**
     * metodo que borra el cliente
     * @param identificacion
     */

    @DeleteMapping("/{identificacion}")
    @Operation(summary = "Cliente Eliminado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servicio para eliminar un cliente",
                    content = { @Content(mediaType = "/{identificacion}",
                            schema = @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "201", description = "Cliente eliminado correctamente",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = " Cliente no encontrado",
                    content = @Content) })
    public void removeCliente(@PathVariable("identificacion") String identificacion) {
        //realizamos la consulta del cliente
        Cliente cliente = this.clienteService.findByIdentificacion(identificacion);
        if (cliente != null) {
            this.clienteService.delete(cliente);
        }
    }

    @GetMapping
    @Operation(summary = "Listar Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Servicio para listar todos los clientes",
                    content = { @Content(mediaType = "/",
                            schema = @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "201", description = "Clientes Encontrados",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = " Cliente no encontrado",
                    content = @Content) })
   public ResponseEntity<List<Cliente>> findAll() {

        return ResponseEntity.ok(this.clienteService.findAll());
    }
}
