package tn.pi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.pi.model.Client;
import tn.pi.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> updateClient(Long id, Client client) {
        if (clientRepository.existsById(id)) {
            client.setId(id);
            return Optional.of(clientRepository.save(client));
        }
        return Optional.empty();
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
