package br.com.fiap.controlecoleta.repository.collect;

import br.com.fiap.controlecoleta.entity.vo.CollectVo;
import java.util.List;

public interface CollectCustomRepository {

  List<CollectVo> findActiveCollectsByCpfCnpj(String cpfCnpj);

}
