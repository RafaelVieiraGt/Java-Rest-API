package com.example.demo.repositorio;

import com.example.demo.modelo.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Repositorio extends CrudRepository<ClienteModel, String> {

    List<ClienteModel> findAll();

    List<ClienteModel> findByNomeContaining(String nome);

    ClienteModel findByCpfCnpj(String cpfCnpj);

}

