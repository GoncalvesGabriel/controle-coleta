package br.com.fiap.controlecoleta.service;

import br.com.fiap.controlecoleta.entity.Collect;
import br.com.fiap.controlecoleta.entity.enumx.CollectStatus;
import br.com.fiap.controlecoleta.entity.vo.CollectVo;
import br.com.fiap.controlecoleta.provider.IntegrationAction;
import br.com.fiap.controlecoleta.provider.IntegrationProvider;
import br.com.fiap.controlecoleta.repository.collect.CollectRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectService {

  @Autowired
  private CollectRepository repository;

  @Autowired
  private IntegrationProvider integrationProvider;

  public Collect registreCollect(CollectVo collectVo) {
    Collect collect = new Collect();
    collect.setCpfCnpj(collectVo.getCpfCnpj());
    collect.setAdress(collectVo.getAdress());
    collect.setCollectDate(collectVo.getCollectDate());
    collect.setFastCollect(collectVo.isFastCollect());
    collect.setScheduledDate(collectVo.getScheduledDate());
    collect.setTimeRange(collectVo.getTimeRange());
    collect.setStatus(CollectStatus.SCHEDULE);
    return repository.save(collect);
  }

  public List<CollectVo> getActiveCollects(String cpfCnpj) {
    return repository.findActiveCollectsByCpfCnpj(cpfCnpj);
  }

  public boolean cancelCollect(Long collectId) {
    Optional<Collect> collectOp = repository.findById(collectId);
    if (collectOp.isPresent()) {
      Collect collect = collectOp.get();
      collect.toCancel();
      repository.save(collect);
      return true;
    } else {
      return false;
    }
  }

  public boolean toCollect(Long collectId) {
    Optional<Collect> collectOp = repository.findById(collectId);
    if (collectOp.isPresent()) {
      Collect collect = collectOp.get();
      collect.toCollect();
      repository.save(collect);
      return true;
    } else {
      return false;
    }
  }

  public boolean toFinish(Long collectId, Double value) {
    Optional<Collect> collectOp = repository.findById(collectId);
    if (collectOp.isPresent()) {
      Collect collect = collectOp.get();
      collect.toFinish(value);
      repository.save(collect);
      byte[] message = collect.getCpfCnpj().getBytes();
      integrationProvider.getSender(IntegrationAction.MOVEMENT_UPDATE).send(message);
      return true;
    } else {
      return false;
    }
  }
}
