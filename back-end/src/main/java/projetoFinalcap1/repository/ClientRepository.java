package projetoFinalcap1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projetoFinalcap1.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
