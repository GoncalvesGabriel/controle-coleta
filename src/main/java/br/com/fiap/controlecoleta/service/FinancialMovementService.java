package br.com.fiap.controlecoleta.service;

import br.com.fiap.controlecoleta.entity.FinancialMovement;
import br.com.fiap.controlecoleta.entity.enumx.TypeMovement;
import br.com.fiap.controlecoleta.repository.financialmovement.FinancialMovementRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinancialMovementService {

  @Autowired
  private FinancialMovementRepository repossitory;

  public void rescue(Double value, String cpfCnpj) {
    FinancialMovement movement = new FinancialMovement();
    movement.setType(TypeMovement.OUTFLOW);
    movement.setDateTime(LocalDateTime.now());
    movement.setCpfCnpj(cpfCnpj);
    movement.setValue(value);
    repossitory.save(movement);
  }
}
