package com.projet_micro_service.client.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet_micro_service.client.model.ClientModel;

@Repository
public interface ClientRepositoryInterface extends JpaRepository<ClientModel, Integer> {

    ClientModel findById(int id);

    void deleteById(int id);
}