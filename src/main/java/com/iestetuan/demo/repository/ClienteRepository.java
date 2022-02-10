package com.iestetuan.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iestetuan.demo.model.Cliente;
/**
 *  DAO de la tabla de base de datos Cliente
 *
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
