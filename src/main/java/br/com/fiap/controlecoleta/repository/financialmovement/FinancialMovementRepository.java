package br.com.fiap.controlecoleta.repository.financialmovement;

import br.com.fiap.controlecoleta.entity.FinancialMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialMovementRepository extends JpaRepository<FinancialMovement, Long>, FinancialMovementCustomRepository {

}
