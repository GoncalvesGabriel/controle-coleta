package br.com.fiap.controlecoleta.repository.financialmovement;

import br.com.fiap.controlecoleta.entity.FinancialMovement;
import br.com.fiap.controlecoleta.util.QueryBuilder;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class FinancialMovementCustomRepositoryImpl implements FinancialMovementCustomRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Double getBalance(String cpfCnpj) {
    Map<String, Object> params =  new HashMap<>();
    StringBuilder sb = new StringBuilder();
    sb.append("SELECT sum(fm.value) FROM ");
    sb.append(FinancialMovement.class.getCanonicalName()).append(" AS fm ");
    sb.append("WHERE fm.cpfCnpj = :cpfCnpj");
    params.put("cpfCnpj", cpfCnpj);

    Query query = entityManager.createQuery(sb.toString());
    QueryBuilder.bindParameters(query, params);

    Double balance = (Double) query.getSingleResult();
    return balance != null ? balance : Double.valueOf("0");
  }
}
