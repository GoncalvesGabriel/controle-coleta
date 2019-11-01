package br.com.fiap.controlecoleta.service;

import br.com.fiap.controlecoleta.entity.Collect;
import br.com.fiap.controlecoleta.entity.enumx.CollectStatus;
import br.com.fiap.controlecoleta.entity.vo.CollectVo;
import br.com.fiap.controlecoleta.repository.CollectRepositoy;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectService {

  @Autowired
  private CollectRepositoy repositoy;

  public Collect registreCollect(CollectVo collectVo) {
    Collect collect = new Collect();
    collect.setCpfCnpj(collectVo.getCpfCnpj());
    collect.setAdress(collectVo.getAdress());
    collect.setCollectDate(collectVo.getCollectDate());
    collect.setFastCollect(collectVo.isFastCollect());
    collect.setScheduledDate(collectVo.getScheduledDate());
    collect.setTimeRange(collectVo.getTimeRange());
    collect.setStatus(CollectStatus.SCHEDULE);
    return repositoy.save(collect);
  }

  public List<CollectVo> getActiveCollects(String cpfCnpj) {
    return repositoy.findActiveCollects(cpfCnpj);
  }

  public boolean cancelCollect(Long collectId) {
    Optional<Collect> collectOp = repositoy.findById(collectId);
    if (collectOp.isPresent()) {
      Collect collect = collectOp.get();
      collect.toCancel();
      repositoy.save(collect);
      return true;
    } else {
      return false;
    }
  }

  public boolean toCollect(Long collectId) {
    Optional<Collect> collectOp = repositoy.findById(collectId);
    if (collectOp.isPresent()) {
      Collect collect = collectOp.get();
      collect.toCollect();
      repositoy.save(collect);
      return true;
    } else {
      return false;
    }
  }

  public boolean toFinish(Long collectId, Double value) {
    Optional<Collect> collectOp = repositoy.findById(collectId);
    if (collectOp.isPresent()) {
      Collect collect = collectOp.get();
      collect.toFinish(value);
      repositoy.save(collect);
      return true;
    } else {
      return false;
    }
  }
}
