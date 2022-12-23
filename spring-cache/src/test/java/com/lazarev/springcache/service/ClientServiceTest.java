package com.lazarev.springcache.service;

import com.lazarev.springcache.entity.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;


@SpringBootTest
class ClientServiceTest {

    @Autowired
    ClientService clientService;

    @Autowired
    CacheManager cacheManager;

    @Test
    void testCacheWithOneParameter(){
        Client client1 = new Client(null, "Mike", "Scott", "mike@gmail.com");
        Client client2 = new Client(null, "Bob", "Winner", "bob@gmail.com");
        clientService.saveClient(client1);
        clientService.saveClient(client2);

        clientService.getClientByEmail("mike@gmail.com");
        clientService.getClientByEmail("mike@gmail.com");
        clientService.getClientByEmail("bob@gmail.com");
        clientService.getClientByEmail("bob@gmail.com");

        System.out.println();
    }

    @Test
    void testCacheWithTwoParameters(){
        Client client1 = new Client(null, "Mike", "Scott", "mike@gmail.com");
        Client client2 = new Client(null, "Bob", "Winner", "bob@gmail.com");
        clientService.saveClient(client1);
        clientService.saveClient(client2);

        clientService.getClientByFirstnameAndLastname("Mike", "Scott");
        clientService.getClientByFirstnameAndLastname("Mike1", "Scott");
        clientService.getClientByFirstnameAndLastname("Bob", "Winner");
        clientService.getClientByFirstnameAndLastname("Bob1", "Winner");
    }

    @Test
    void testCacheWithObjectParameter(){
        Client client1 = new Client(null, "Mike", "Scott", "my@gmail.com");
        Client client2 = new Client(null, "James", "Watson", "my@gmail.com");
        Client client3 = new Client(null, "Mike", "Scott", "my@gmail.com");

        clientService.saveClient(client1);
        clientService.saveClient(client2);
        clientService.saveClient(client3);
        clientService.saveClient(client3);
    }

    @Test
    void testCachePut(){
        Client client1 = new Client(null, "Mike", "Scott", "my@gmail.com");
        Client client2 = new Client(null, "James", "Watson", "my@gmail.com");
        Client client3 = new Client(null, "Bob", "Winner", "my@gmail.com");

        Client firstSavedClient = clientService.saveClient(client1);
        Client updatedClient = clientService.updateClient(1, client2);
        Client savedClient = clientService.saveClient(client3);

        System.out.println(firstSavedClient);
        System.out.println(updatedClient);
        System.out.println(savedClient);
    }

    @Test
    void testCacheEvict(){
        Client client = new Client(null, "Mike", "Scott", "my@gmail.com");
        clientService.saveClient(client);

        System.out.println(clientService.getClientById(1));
        clientService.deleteClientById(1);
        System.out.println(clientService.getClientById(1));
    }

    @Test
    void testCacheExpireTime() throws InterruptedException {
        Client client = new Client(null, "Mike", "Scott", "mike@gmail.com");
        clientService.saveClient(client);

        clientService.getClientById(1);

        Thread.sleep(1001);

        clientService.getClientById(1);
    }
}