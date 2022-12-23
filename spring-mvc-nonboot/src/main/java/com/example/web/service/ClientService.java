package com.example.web.service;

import com.example.web.entity.Client;
import com.example.web.entity.Order;
import com.example.web.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients(){
        return clientRepository.getAllClients();
    }

    public Client getClientById(Integer id){
        return clientRepository.getClientById(id)
                .orElseThrow(()-> new NoSuchElementException(
                        "Client with id='%d' not found".formatted(id)));
    }

    public List<Order> getAllClientOrders(Integer id){
        return clientRepository.getAllOrdersByClientId(id);
    }

    public void saveClient(Client client){
        clientRepository.saveClient(client);
    }

    public void addOrderToClient(Order order, Integer clientId){
        clientRepository.getClientById(clientId)
                .ifPresent(client -> {
                    client.addOrder(order);
                    clientRepository.saveClient(client);
                });
    }
}
