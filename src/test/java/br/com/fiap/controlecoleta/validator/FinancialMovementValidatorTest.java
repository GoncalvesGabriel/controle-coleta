package br.com.fiap.controlecoleta.validator;

import br.com.fiap.controlecoleta.entity.FinancialMovement;
import br.com.fiap.controlecoleta.entity.enumx.TypeMovement;
import br.com.fiap.controlecoleta.repository.financialmovement.FinancialMovementRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FinancialMovementValidatorTest {

  @InjectMocks
  private FinancialMovementValidator validator;

  @Mock
  private FinancialMovementRepository repository;

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void validateSucessInput() {
    FinancialMovement financialMovement = new FinancialMovement();
    financialMovement.setValue(10000.0);
    financialMovement.setCpfCnpj("02755409100");
    financialMovement.setType(TypeMovement.INPUT);

    validator.validateInsert(financialMovement);
  }

  @Test
  public void validateInvalidValueInput() {
    FinancialMovement financialMovement = new FinancialMovement();
    financialMovement.setValue(-10000.0);
    financialMovement.setCpfCnpj("02755409100");
    financialMovement.setType(TypeMovement.INPUT);

    expectedException.expectMessage("Valor inválido para a operação");

    validator.validateInsert(financialMovement);
  }

  @Test
  public void validateSucessOutFlow() {
    Mockito.when(repository.getBalance(Mockito.anyString())).thenReturn(100001.0);

    FinancialMovement financialMovement = new FinancialMovement();
    financialMovement.setValue(-10000.0);
    financialMovement.setCpfCnpj("02755409100");
    financialMovement.setType(TypeMovement.OUTFLOW);

    validator.validateInsert(financialMovement);
  }

  @Test
  public void validateInvalidValueOutflow() {
    Mockito.when(repository.getBalance(Mockito.anyString())).thenReturn(100001.0);

    FinancialMovement financialMovement = new FinancialMovement();
    financialMovement.setValue(10000.0);
    financialMovement.setCpfCnpj("02755409100");
    financialMovement.setType(TypeMovement.OUTFLOW);

    expectedException.expectMessage("Valor inválido para a operação");

    validator.validateInsert(financialMovement);
  }

  @Test
  public void validateOutflowNotBalanceAvaliable() {
    double balance = 1001.0;
    Mockito.when(repository.getBalance(Mockito.anyString())).thenReturn(balance);

    FinancialMovement financialMovement = new FinancialMovement();
    double value = -10000.0;
    financialMovement.setValue(value);
    financialMovement.setCpfCnpj("02755409100");
    financialMovement.setType(TypeMovement.OUTFLOW);
    double valueAbs = Math.abs(value);
    String message = String
        .format("Valor do resgate (R$ %s) é menor que valor de disponível R$ %s para saque ",
            valueAbs, balance);

    expectedException.expectMessage(message);

    validator.validateInsert(financialMovement);
  }
}