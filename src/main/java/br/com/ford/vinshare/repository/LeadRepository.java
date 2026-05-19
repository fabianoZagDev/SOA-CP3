package br.com.ford.vinshare.repository;

import br.com.ford.vinshare.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {

    List<Lead> findByStatusOrderByScorePropensaoDesc(Lead.StatusLead status);

    @Query("SELECT l FROM Lead l JOIN FETCH l.veiculo v JOIN FETCH v.cliente ORDER BY l.scorePropensao DESC")
    List<Lead> findAllWithVeiculoAndCliente();

    @Query("SELECT COUNT(l) FROM Lead l WHERE l.status = :status")
    Long countByStatus(Lead.StatusLead status);

    @Query("SELECT AVG(l.scorePropensao) FROM Lead l")
    Double avgScorePropensao();

    Optional<Lead> findByVeiculoVin(String vin);
}
