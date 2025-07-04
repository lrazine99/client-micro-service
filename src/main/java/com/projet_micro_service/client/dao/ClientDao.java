package com.projet_micro_service.client.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet_micro_service.client.model.ClientModel;

@Service
public class ClientDao {

    @Autowired
    private ClientRepositoryInterface clientRepository;

    public List<ClientModel> findAll() {
        return clientRepository.findAll();
    }

    public ClientModel findById(int id) {
        return clientRepository.findById(id);
    }

    public ClientModel save(ClientModel client) {
        return clientRepository.save(client);
    }

    public void deleteById(int id) {
        clientRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return clientRepository.existsById(id);
    }
}