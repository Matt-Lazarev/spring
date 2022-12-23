package com.lazarev.springcache.service;

import com.lazarev.springcache.entity.Client;
import com.lazarev.springcache.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final CacheManager cacheManager;

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    @Cacheable("clients")
    public Client getClientById(Integer id){
        return clientRepository.findById(id).orElse(null);
    }

    @Cacheable("clients")
    public Client getClientByEmail(String email){
        System.out.println("GetClientByEmail: " + email);
        return clientRepository.findClientByEmail(email);
    }

    @Cacheable(value = "clients", key = "#lastname")
    public Client getClientByFirstnameAndLastname(String firstname, String lastname){
        System.out.println("");
        return clientRepository.findClientByFirstnameAndLastname(firstname, lastname);
    }

    @Cacheable(value = "clients", key = "#client")
    public Client saveClient(Client client){
        return clientRepository.save(client);
    }

    @CachePut(value = "clients", key = "#client.email")
    public Client updateClient(Integer id, Client client){
        Client updatableClient = clientRepository.findById(id).orElseThrow();
        updatableClient.setFirstname(client.getFirstname());
        updatableClient.setLastname(client.getLastname());

        return clientRepository.save(updatableClient);
    }

    @CacheEvict("clients")
    public void deleteClientById(Integer id){
        clientRepository.deleteById(id);
    }

}
