package com.okayo.facturation.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.okayo.facturation.classes.Client;
import com.okayo.facturation.repository.ClientRepository;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Client newClient = client.get();
        newClient.setNom(clientDetails.getNom());
        newClient.setAdresse(clientDetails.getAdresse());

        Client updatedClient = clientRepository.save(newClient);
        return ResponseEntity.ok(updatedClient);
    }

}
