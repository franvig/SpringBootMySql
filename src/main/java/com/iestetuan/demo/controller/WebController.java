package com.iestetuan.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iestetuan.demo.model.Cliente;
import com.iestetuan.demo.service.ClienteService;

/**
 * Nos encontramos ante una clase RestController
 * Desde aquí procesaremos todas las peticiones Rest
 * 
 * Lo que devolvemos en los métodos, son los cuerpos de la respuesta, 
 * transformados a JSON
 *
 */
@RestController
@CrossOrigin(origins = "*")
//@RequestMapping("/webapp")
public class WebController {
	
	//Inyección de dependencia a clienteService
	@Autowired
	private ClienteService clienteServiceWeb;
	
	/**
	 * GET
	 * @return
	 */
	@GetMapping("/clientes")
	public List<Cliente> getClientes() {
		return clienteServiceWeb.loadClientesActivos();
	}
	
	/**
	 * Get by id
	 * @PathVariable nos permite inyectar un fragmento de la URL en una variable
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/clientes/{id}")
	public Cliente getEgm(@PathVariable(value = "id") Long id){
		return clienteServiceWeb.loadClienteById(id);		
	}
	
	/**
	 * Put: update
	 * @RequestBody inyecta el cuerpo de la petición en un objeto
	 * @param cliente
	 */
	@PutMapping("/clientes")
    public void updateCliente(
			@RequestBody Cliente cliente){
        clienteServiceWeb.updateCliente(cliente);
    }
	
	/**
	 * PUT con id
	 * @param id
	 * @param cliente
	 */
	@PutMapping("/clientes/{id}")
    public void IncrementarAccesoCliente(@PathVariable(value = "id") Long id,
    		@RequestBody Cliente cliente){
        clienteServiceWeb.incrementarAcceso(id, cliente.getNumero());
	}
	
	/**
	 * POST: Crear
	 * @param cliente
	 */
	@PostMapping("/clientes")
    public void addClient(@RequestBody Cliente cliente) {
		clienteServiceWeb.addCliente(cliente);
    }
	
	/**
	 * Delete: Borrar
	 * @param id
	 */
	@DeleteMapping("/clientes/{id}")
	public void deleteClient(@PathVariable(value = "id") Long id) {
		clienteServiceWeb.removeCliente(id);
    }

}
