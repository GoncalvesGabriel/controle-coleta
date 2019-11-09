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
    sb.append(" ctl.id as id, ");
    sb.append(" ctl.cpfCnpj as cpfCnpj, ");
    sb.append(" ctl.fastCollect as fastCollect, ");
    sb.append(" ctl.address as address, ");
    sb.append(" ctl.scheduledDate as scheduledDate, ");
    sb.append(" ctl.collectDate as collectDate, ");
    sb.append(" ctl.timeRange as timeRange, ");
    sb.append(" ctl.status as status, ");
    sb.append(" finMov.id as financialMovementId ");
    sb.append(" FROM ");
    sb.append(" Collect clt ");
    sb.append(" LEFT JOIN clt.financialMovement finMov ");
    sb.append(" WHERE ");
    sb.append(" ctl.cpfCnpj = :cpfCnpj ");

    params.put("cpfCnpj", cpfCnpj);

    Query execQuery = entityManager.createQuery(sb.toString());
    execQuery.unwrap(org.hibernate.query.Query.class).setResultTransformer(new AliasToBeanResultTransformer(CollectVo.class));
    QueryBuilder.bindParameters(execQuery, params);

    return execQuery.getResultList();
  }
}
