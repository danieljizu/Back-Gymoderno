package com.gymoderno.response;

import java.util.List;

import com.gymoderno.model.Clientes;

import lombok.Data;

@Data
public class ClientesResponse {
	
	private List<Clientes> clientes;

}
