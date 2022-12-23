package com.example.web.controller;

import com.example.web.entity.Client;
import com.example.web.entity.Order;
import com.example.web.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public String getAllClientsPage(Model model){
        model.addAttribute("clients", clientService.getAllClients());
        return "clients";
    }

    @GetMapping("/clients/new")
    public String getAddNewClientPage(Model model){
        model.addAttribute("client", new Client());
        return "new_client";
    }

    @PostMapping("/clients")
    public String createClientAndRedirect(@ModelAttribute Client client){
        clientService.saveClient(client);
        return "redirect:/clients";
    }

    @GetMapping("/clients/{clientId}/orders")
    public String getAllOrdersPage(@PathVariable Integer clientId, Model model){
        model.addAttribute("orders", clientService.getAllClientOrders(clientId));
        return "orders";
    }

    @GetMapping("/clients/{clientId}/add-order")
    public String getAddNewOrderPage(@PathVariable Integer clientId, Model model){
        model.addAttribute("order", new Order());
        return "new_order";
    }

    @PostMapping("/clients/{clientId}/orders")
    public String addOrderAndRedirect(@PathVariable Integer clientId,
                                      @ModelAttribute Order order){
        clientService.addOrderToClient(order, clientId);
        return "redirect:/clients/%d/orders".formatted(clientId);
    }
}
