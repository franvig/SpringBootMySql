package com.iestetuan.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iestetuan.demo.model.Cliente;
import com.iestetuan.demo.repository.ClienteRepository;

/*
 * Todas las clases con @Service indican que nos encontramos ante una clase
 * de negocio. Aquí se implementa la lógica, es decir, procesamos
 * las demandas que recibimos desde la capa de presentación @Controller. 
 * En el caso de tener que acceder a una base de datos para consultas y 
 * modificaciones se haría desde aquí llamando a los DAOs @repository.
 */

@Service
public class ClienteService {
	
	//Inyección de dependencia del DAO de la tabla de base de datos Cliente
	@Autowired
	ClienteRepository clienteRepository;

	/**
	 * Método que carga todos los clientes en base de datos.
	 * Devuelve una Lista de Objetos Clientes.
	 * @return
	 */
	public List<Cliente> loadClientesActivos() {
		List<Cliente> lista = (List<Cliente>) clienteRepository.findAll();
		return lista;
		
	}

	/**
	 * Método que carga el Cliente por id
	 * @param id
	 * @return
	 */
	public Cliente loadClienteById(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (cliente.isPresent()) {
			return cliente.get();
		}
		return null;
	}

	/**
	 * Método que actualiza el Cliente por id
	 * @param id
	 * @return
	 */
	public void updateCliente(Cliente cliente) {
		clienteRepository.save(cliente);
	}
	
	/**
	 * Método que elimina el Cliente por id
	 * @param id
	 */
	public void removeCliente(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (cliente.isPresent())
			clienteRepository.delete(cliente.get());
	}

	/**
	 * Método que crea un cliente nuevo
	 * @param cliente
	 */
	public void addCliente(Cliente cliente) {
		clienteRepository.save(cliente);
	}

	/**
	 * Método que incrementa número de acceso
	 * @param id
	 * @param valor
	 */
	public void incrementarAcceso(Long id, Integer valor) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (cliente.isPresent()) {
			Cliente clienteUpdated = cliente.get();
			clienteUpdated.setNumero(clienteUpdated.getNumero() + valor);
			clienteRepository.save(clienteUpdated);
		}
		
	}

}
