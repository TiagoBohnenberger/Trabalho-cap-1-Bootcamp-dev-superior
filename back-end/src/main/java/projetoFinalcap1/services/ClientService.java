package projetoFinalcap1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projetoFinalcap1.dto.ClientDTO;
import projetoFinalcap1.entities.Client;
import projetoFinalcap1.repository.ClientRepository;
import projetoFinalcap1.services.exceptions.ResourceNotFoundException;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {

        Page<Client> clients = clientRepository.findAll(pageRequest);
        return clients.map(client -> new ClientDTO(client));
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {

        Optional<Client> entity = clientRepository.findById(id);
        Client client = entity.orElseThrow(() -> new ResourceNotFoundException("Id " + id + " inexistente no banco de dados"));
        return new ClientDTO(client);
    }
}
