package com.gymoderno.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gymoderno.dao.IClientesdao;
import com.gymoderno.model.Clientes;
import com.gymoderno.response.ClientesResponseRest;


@Service
public class ClientesServiceImpl implements IClientesServices {

	@Autowired
	private IClientesdao clientesDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ClientesResponseRest> search() {

		ClientesResponseRest response = new ClientesResponseRest();

		try {

			List<Clientes> clientes = (List<Clientes>) clientesDao.findAll();

			response.getClientesResponse().setClientes(clientes);
			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");

		} catch (Exception e) {

			response.setMetadata("Respuesta no ok", "-1", "Error al Consultar");
			e.getStackTrace();
			return new ResponseEntity<ClientesResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<ClientesResponseRest>(response, HttpStatus.OK);
	}

}
