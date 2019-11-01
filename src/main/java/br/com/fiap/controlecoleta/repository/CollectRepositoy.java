package br.com.fiap.controlecoleta.repository;

import br.com.fiap.controlecoleta.entity.Collect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectRepositoy extends JpaRepository<Collect, Long>, CollectCustomRepository {

}
