package br.com.fiap.controlecoleta.repository.collect;

import br.com.fiap.controlecoleta.entity.Collect;
import br.com.fiap.controlecoleta.entity.vo.CollectVo;
import br.com.fiap.controlecoleta.util.QueryBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Component;

public class CollectCustomRepositoryImpl implements CollectCustomRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<CollectVo> findActiveCollectsByCpfCnpj(String cpfCnpj) {
    Map<String, Object> params =  new HashMap<>();
    StringBuilder sb = new StringBuilder();
    sb.append("SELECT ");
    sb.append(" clt.id AS id, ");
    sb.append(" clt.cpfCnpj AS cpfCnpj, ");
    sb.append(" clt.fastCollect AS fastCollect, ");
    sb.append(" clt.address AS address, ");
    sb.append(" clt.scheduledDate AS scheduledDate, ");
    sb.append(" clt.collectDate AS collectDate, ");
    sb.append(" clt.timeRange AS timeRange, ");
    sb.append(" clt.status AS status, ");
    sb.append(" finMov.id AS financialMovementId ");
    sb.append(" FROM ");
    sb.append(Collect.class.getCanonicalName()).append(" AS clt ");
    sb.append(" LEFT JOIN clt.financialMovement finMov ");
    sb.append(" WHERE ");
    sb.append(" clt.cpfCnpj = :cpfCnpj ");


    Query execQuery = entityManager.createQuery(sb.toString());
    execQuery.unwrap(org.hibernate.query.Query.class).setResultTransformer(new AliasToBeanResultTransformer(CollectVo.class));
    QueryBuilder.bindParameters(execQuery, params);

    return execQuery.getResultList();
  }
}
