package com.gymoderno.services;

import org.springframework.http.ResponseEntity;

import com.gymoderno.model.Clientes;
import com.gymoderno.response.*;

public interface IClientesServices {
	
	public ResponseEntity<ClientesResponseRest> search();
	public ResponseEntity<ClientesResponseRest> searchById(Long id);
	public ResponseEntity<ClientesResponseRest> save(Clientes clientes);

}
