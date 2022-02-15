package com.polygogo.backend.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.polygogo.backend.models.entity.Properties;

public interface IPropertiesDao extends CrudRepository<Properties, Long>{

}
