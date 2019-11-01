package br.com.fiap.controlecoleta.repository.collect;

import br.com.fiap.controlecoleta.entity.Collect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectRepository extends JpaRepository<Collect, Long>, CollectCustomRepository {

}
