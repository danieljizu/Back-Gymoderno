package com.gymoderno.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ClientesResponseRest> searchById(Long id) {

		ClientesResponseRest response = new ClientesResponseRest();
		List<Clientes>list = new ArrayList<>();
		
		try {
			
			Optional<Clientes> clientes =  clientesDao.findById(id);
			if(clientes.isPresent()) {
				list.add(clientes.get());
				response.getClientesResponse().setClientes(list);
				response.setMetadata("Respuesta ok", "00", "Categoria Encontrada");
			}else {
				
				response.setMetadata("Respuesta no ok", "-1", "Categoria no Encontrada");
				return new ResponseEntity<ClientesResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		}catch (Exception e) {
			
			response.setMetadata("Respuesta no ok", "-1", "Error al consultar por ID");
			e.getStackTrace();
			return new ResponseEntity<ClientesResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<ClientesResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<ClientesResponseRest> save(Clientes clientes) {
		
		ClientesResponseRest response = new ClientesResponseRest();
		List<Clientes>list = new ArrayList<>();
		float IMC =0;
		String ob = null;
		
		try {
			
			Clientes clientesSaved = clientesDao.save(clientes);
			
			if(clientesSaved != null) {
				
				float pes = clientesSaved.getPeso();
				float alt = clientesSaved.getAltura();
				
				IMC = pes/(alt * alt);
				
				if (IMC < 16) {
					
					ob= "Citerio de ingreso en Hospital";
					clientesSaved.setObservacion(ob);
					list.add(clientesSaved);
					
				}else if(IMC ==16 || IMC < 17) {
					
					ob= "Infrapeso";
					clientesSaved.setObservacion(ob);
					list.add(clientesSaved);
					
				}else if(IMC == 17 || IMC < 18) {
					
					ob= "Bajo Peso";
					clientesSaved.setObservacion(ob);
					list.add(clientesSaved);
					
				}else if(IMC ==18 || IMC < 25) {
					
					ob= "Peso Normal (Saludable)";
					clientesSaved.setObservacion(ob);
					list.add(clientesSaved);
					
				}else if(IMC == 25 || IMC < 30) {
					
					ob= "Sobrepeso (obesidad de grado 1)";
					clientesSaved.setObservacion(ob);
					list.add(clientesSaved);
					
				}else if(IMC == 30 || IMC < 35) {
					
					ob= "Sobre Peso Cronico (obesidad de grado 2)";
					clientesSaved.setObservacion(ob);
					list.add(clientesSaved);
					
				}else if(IMC == 35 || IMC <= 40) {
					
					ob= "Obesidad Premorbida (obesidad de grado 3)";
					clientesSaved.setObservacion(ob);
					list.add(clientesSaved);
					
				}else {
					
					ob= "Obesidad Morbida (obesidad de grado 4)";
					clientesSaved.setObservacion(ob);
					list.add(clientesSaved);
					
				}
				response.getClientesResponse().setClientes(list);
				response.setMetadata("Respuesta ok", "00", "Categoria Guardada");
			}else {
				response.getClientesResponse().setClientes(list);
				response.setMetadata("Respuesta no ok", "-1", "Categoria no Guardada");
			}
		}catch (Exception e) {
			
			response.setMetadata("Respuesta no ok", "-1", "Categoria no Guardada");
			e.getStackTrace();
			return new ResponseEntity<ClientesResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ClientesResponseRest>(response, HttpStatus.OK);

	}

}
