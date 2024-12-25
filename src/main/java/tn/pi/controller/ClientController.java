package tn.pi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.model.Client;
import tn.pi.service.ClientService;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public String getAllClients(Model model) {
        List<Client> clients = clientService.getAllClients();
        model.addAttribute("clients", clients);
        return "clients/clients"; // Assure-toi que le template "clients.html" existe
    }

    @GetMapping("/add")
    public String addClientForm(Model model) {
        model.addAttribute("client", new Client());
        return "clients/add_client"; // Assure-toi que le template "add_client.html" existe
    }

    @PostMapping
    public String createClient(@ModelAttribute Client client) {
        clientService.createClient(client);
        return "redirect:/clients"; // Redirige vers la liste des clients après la création
    }

    @GetMapping("/edit/{id}")
    public String editClientForm(@PathVariable Long id, Model model) {
        clientService.getClientById(id).ifPresent(c -> model.addAttribute("client", c));
        return "clients/edit_client"; // Assure-toi que le template "edit_client.html" existe
    }

    @PostMapping("/edit/{id}")
    public String updateClient(@PathVariable Long id, @ModelAttribute Client client) {
        clientService.updateClient(id, client);
        return "redirect:/clients"; // Redirige vers la liste des clients après la mise à jour
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return "redirect:/clients"; // Redirige vers la liste des clients après la suppression
    }
}
