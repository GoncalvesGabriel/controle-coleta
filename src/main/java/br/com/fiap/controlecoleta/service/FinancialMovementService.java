package br.com.fiap.controlecoleta.service;

import br.com.fiap.controlecoleta.entity.FinancialMovement;
import br.com.fiap.controlecoleta.entity.enumx.TypeMovement;
import br.com.fiap.controlecoleta.provider.IntegrationAction;
import br.com.fiap.controlecoleta.provider.IntegrationProvider;
import br.com.fiap.controlecoleta.repository.financialmovement.FinancialMovementRepository;
import br.com.fiap.controlecoleta.validator.FinancialMovementValidator;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinancialMovementService {

  @Autowired
  private FinancialMovementRepository repository;

  @Autowired
  private FinancialMovementValidator validator;

  @Autowired
  private IntegrationProvider integrationProvider;

  public void rescue(Double value, String cpfCnpj) {
    FinancialMovement movement = new FinancialMovement();
    movement.setType(TypeMovement.OUTFLOW);
    movement.setDateTime(LocalDateTime.now());
    movement.setCpfCnpj(cpfCnpj);
    movement.setValue(value);
    validator.validateInsert(movement);
    repository.save(movement);
    integrationProvider.getSender(IntegrationAction.MOVEMENT_UPDATE).send(cpfCnpj.getBytes());
  }

  public Double getBalance(String cpfCnpj) {
    return repository.getBalance(cpfCnpj);
  }
}
