/**
 * 
 */
package com.pablovass.negocio.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.pablovass.modelo.Cliente;
import com.pablovass.modelo.Reserva;

/**
 * interfas para definir las operaciones de bdd relacionado con Reserva
 * @author pablovass
 *
 */
public interface ReservaRepository extends JpaRepository<Reserva, String>{
	@Query("Select r from reserva r where r.fechaIngresoRes =:fechaInicio and r.fechaSalidaRes=:fechaSalida ")
	public List<Reserva>find(@Param("fechaInicio") Date fechaInicio, @Param("fechaSalida") Date fechaSalida);	

}
