package com.gymoderno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymoderno.model.Clientes;
import com.gymoderno.response.ClientesResponseRest;
import com.gymoderno.services.IClientesServices;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class ClientesRestController {
	
	@Autowired
	private IClientesServices services;
	
	
	@GetMapping("/clientes")
	public ResponseEntity<ClientesResponseRest> searchcustoms() {
		
		ResponseEntity<ClientesResponseRest> response = services.search();
		return response;
	}
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<ClientesResponseRest> searchcustomsbyid(@PathVariable Long id) {
		
		ResponseEntity<ClientesResponseRest> response = services.searchById(id);
		return response;
	}

	@PostMapping("/clientes")
	public ResponseEntity<ClientesResponseRest> savecustom(@RequestBody Clientes clientes) {
		
		ResponseEntity<ClientesResponseRest> response = services.save(clientes);
		return response;
	}
	
	@PutMapping("/clientes/{id}")
	public ResponseEntity<ClientesResponseRest> updatecustom(@RequestBody Clientes clientes, @PathVariable Long id) {
		
		ResponseEntity<ClientesResponseRest> response = services.update(clientes , id);
		return response;
	}
}
