package projetoFinalcap1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projetoFinalcap1.dto.ClientDTO;
import projetoFinalcap1.entities.Client;
import projetoFinalcap1.repository.ClientRepository;
import projetoFinalcap1.services.exceptions.DatabaseException;
import projetoFinalcap1.services.exceptions.ResourceNotFoundException;

import javax.persistence.EntityNotFoundException;
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
        Client client = entity.orElseThrow(() -> new ResourceNotFoundException("Id " + id + " inexistente para atualização no banco de dados"));
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        Client entity = new Client();
        entity.setName(dto.getName());
        entity.setBirthDate(dto.getBirthDate());
        entity.setIncome(dto.getIncome());
        entity.setChildren(dto.getChildren());
        entity.setCpf(dto.getCpf());
        entity = clientRepository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(ClientDTO dto, Long id) {
        try {
            Client entity = clientRepository.getOne(id);
            entity.setName(dto.getName());
            entity.setBirthDate(dto.getBirthDate());
            entity.setIncome(dto.getIncome());
            entity.setChildren(dto.getChildren());
            entity = clientRepository.save(entity);
            return new ClientDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id " + id + " inexistente no banco de dados!");
        }
    }

    public void delete(Long id) {
        try {
            clientRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id " + id + " inexistente no banco de dados!");
        }
    }
}
