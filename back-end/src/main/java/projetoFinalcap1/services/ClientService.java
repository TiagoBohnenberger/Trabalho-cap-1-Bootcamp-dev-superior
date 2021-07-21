package projetoFinalcap1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import projetoFinalcap1.dto.ClientDTO;
import projetoFinalcap1.entities.Client;
import projetoFinalcap1.repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {

        Page<Client> clients = clientRepository.findAll(pageRequest);
        return clients.map(client -> new ClientDTO(client));
    }
}
