package com.projet_micro_service.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.projet_micro_service.client.dao.ClientDao;
import com.projet_micro_service.client.model.ClientModel;

@RestController
public class ClientController {

    @Autowired
    private final ClientDao clientDao;
    
    @Autowired
    private final RestTemplate restTemplate;

    public ClientController(ClientDao clientDao, RestTemplate restTemplate) {
        this.clientDao = clientDao;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/clients")
    public List<ClientModel> getClients() {
        System.out.println("Fetching all clients" + clientDao.findAll());
        return clientDao.findAll();
    }

    @GetMapping("/clients/{id}")
    public ClientModel getClientById(@PathVariable int id) {
        return clientDao.findById(id);
    }

    @PostMapping("/clients")
    public ClientModel createClient(@RequestBody ClientModel client) {
        return clientDao.save(client);
    }

    @DeleteMapping("/clients/{id}")
    public String deleteClient(@PathVariable int id) {
        clientDao.deleteById(id);
        return "{\"message\":\"Client with id " + id + " deleted\", \"status\":200}";
    }

    @GetMapping("/clients/{id}/orders")
    public String getClientOrders(@PathVariable int id) {
        try {
            String orderServiceUrl = "http://localhost:8081/orders/client/" + id;
            String response = restTemplate.getForObject(orderServiceUrl, String.class);
            return response;
        } catch (Exception e) {
            return "{\"message\":\"Error fetching orders for client " + id + "\", \"status\":500}";
        }
    }
}