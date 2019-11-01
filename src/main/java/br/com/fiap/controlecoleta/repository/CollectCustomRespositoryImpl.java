package br.com.fiap.controlecoleta.repository;

import br.com.fiap.controlecoleta.entity.Collect;
import br.com.fiap.controlecoleta.entity.vo.CollectVo;
import br.com.fiap.controlecoleta.util.QueryBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class CollectCustomRespositoryImpl implements CollectCustomRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<CollectVo> findActiveCollects(String cpFCnpj) {
    Map<String, Object> params =  new HashMap<>();
    StringBuilder sb = new StringBuilder();
    sb.append("SELECT ");
    sb.append(" ctl.id as id, ");
    sb.append(" ctl.cpfCnpj as cpfCnpj, ");
    sb.append(" ctl.fastCollect as fastCollect, ");
    sb.append(" ctl.adress as adress, ");
    sb.append(" ctl.scheduledDate as scheduledDate, ");
    sb.append(" ctl.collectDate as collectDate, ");
    sb.append(" ctl.timeRange as timeRange, ");
    sb.append(" ctl.status as status, ");
    sb.append(" finMov.id as financialMovementId ");
    sb.append(" FROM ");
    sb.append(Collect.class.getCanonicalName());
    sb.append(" as clt ");
    sb.append(" LEFT JOIN clt.financialMovement finMov ");
    sb.append(" WHERE ");
    sb.append(" ctl.cpfCnpj = :cpfCnpj ");

    params.put("cpfCnpj", cpFCnpj);

    TypedQuery<CollectVo> execQuery = entityManager.createQuery(sb.toString(), CollectVo.class);
    QueryBuilder.bindParameters(execQuery, params);

    return execQuery.getResultList();
  }
}
