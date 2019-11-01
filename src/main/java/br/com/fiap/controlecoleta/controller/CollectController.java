package br.com.fiap.controlecoleta.controller;

import br.com.fiap.controlecoleta.entity.Collect;
import br.com.fiap.controlecoleta.entity.vo.CollectVo;
import br.com.fiap.controlecoleta.service.CollectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collect")
public class CollectController {

  @Autowired
  private CollectService service;

  @PostMapping(value = "/create", consumes = "application/json")
  public ResponseEntity<Long> collect(@RequestBody CollectVo collectVo) {
    Collect collect = service.registreCollect(collectVo);
    return ResponseEntity.status(HttpStatus.CREATED).body(collect.getId());
  }

  @GetMapping(value = "/activeCollect")
  public ResponseEntity<List<CollectVo>> getActiveCollect(String cpfCnpj) {
    List<CollectVo> collectVoList = service.getActiveCollects(cpfCnpj);
    return ResponseEntity.status(HttpStatus.OK).body(collectVoList);
  }

  @DeleteMapping(value = "/cancel")
  public ResponseEntity cancelCollect(Long collectId) {
    if (service.cancelCollect(collectId)) {
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping(value = "/toCollect")
  public ResponseEntity toCollect(Long collectId) {
    if (service.toCollect(collectId)) {
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping(value = "/toFinish")
  public ResponseEntity toFinish(Long collectId, Double value) {
    if (service.toFinish(collectId, value)) {
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }
}
