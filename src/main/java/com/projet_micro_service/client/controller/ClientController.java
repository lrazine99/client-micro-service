package com.projet_micro_service.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<ClientModel> getClientById(@PathVariable int id) {
        ClientModel client = clientDao.findById(id);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/clients")
    public ResponseEntity<ClientModel> createClient(@RequestBody ClientModel client) {
        ClientModel savedClient = clientDao.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<ClientModel> updateClient(@PathVariable int id, @RequestBody ClientModel client) {
        if (!clientDao.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        client.setId(id);
        ClientModel updatedClient = clientDao.save(client);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable int id) {
        if (!clientDao.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        clientDao.deleteById(id);
        return ResponseEntity.ok("{\"message\":\"Client with id " + id + " deleted\", \"status\":200}");
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