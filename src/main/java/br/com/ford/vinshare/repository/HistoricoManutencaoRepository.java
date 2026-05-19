package br.com.ford.vinshare.repository;

import br.com.ford.vinshare.model.HistoricoManutencao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HistoricoManutencaoRepository extends JpaRepository<HistoricoManutencao, Long> {

    List<HistoricoManutencao> findByVeiculoVinOrderByDataManutencaoDesc(String vin);

    @Query("SELECT COUNT(h) FROM HistoricoManutencao h WHERE h.dataManutencao >= :desde")
    Long countManutencoesDesde(LocalDate desde);
}
