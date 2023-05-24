package com.gymoderno.dao;

import org.springframework.data.repository.CrudRepository;

import com.gymoderno.model.Clientes;

public interface IClientesdao extends CrudRepository<Clientes, Long>{

}
