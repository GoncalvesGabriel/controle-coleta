package br.com.fiap.controlecoleta.validator;

import br.com.fiap.controlecoleta.entity.FinancialMovement;
import br.com.fiap.controlecoleta.entity.enumx.TypeMovement;
import br.com.fiap.controlecoleta.repository.financialmovement.FinancialMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FinancialMovementValidator implements Validator<FinancialMovement> {

  @Autowired
  private FinancialMovementRepository repository;

  @Override
  public void validateInsert(FinancialMovement entity) {
    if (TypeMovement.OUTFLOW.equals(entity.getType())) {
      validateAvaliableBalance(entity);
    }
  }

  private void validateAvaliableBalance(FinancialMovement entity) {
    Double balance = repository.getBalance(entity.getCpfCnpj());
    Double operationValue = Math.abs(entity.getValue());
    if (balance.compareTo(operationValue) < 0) {
      throw new RuntimeException(String
          .format("Valor do resgate (R$ %s) é menor que valor de disponível R$ %s para saque ", operationValue, balance));
    }
  }
}
