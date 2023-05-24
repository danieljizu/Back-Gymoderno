package com.gymoderno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
