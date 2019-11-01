package br.com.fiap.controlecoleta.repository;

import br.com.fiap.controlecoleta.entity.vo.CollectVo;
import java.util.List;

public interface CollectCustomRepository {

  List<CollectVo> findActiveCollects(String cpFCnpj);

}
