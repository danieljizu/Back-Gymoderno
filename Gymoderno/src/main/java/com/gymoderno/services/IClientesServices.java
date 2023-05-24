package com.gymoderno.services;

import org.springframework.http.ResponseEntity;

import com.gymoderno.response.*;

public interface IClientesServices {
	
	public ResponseEntity<ClientesResponseRest> search();

}
