package br.com.ford.vinshare.repository;

import br.com.ford.vinshare.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    Optional<Veiculo> findByVin(String vin);

    List<Veiculo> findByClienteId(Long clienteId);

    @Query("SELECT v FROM Veiculo v JOIN FETCH v.cliente WHERE v.vin = :vin")
    Optional<Veiculo> findByVinWithCliente(String vin);
}
