package br.com.fiap.controlecoleta.controller;

import br.com.fiap.controlecoleta.entity.Collect;
import br.com.fiap.controlecoleta.entity.vo.CollectVo;
import br.com.fiap.controlecoleta.service.CollectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

@Api(value = "API para controle de coleta")
@RestController
@RequestMapping("/collect")
public class CollectController {

  @Autowired
  private CollectService service;

  @ApiOperation(value = "Registro de uma coleta", consumes = "application/json", httpMethod = "POST")
  @ApiResponse(code = 201, message = "Registro realizado com sucesso")
  @PostMapping(value = "/create", consumes = "application/json")
  public ResponseEntity<Long> collect(
      @RequestBody
      @ApiParam(name = "collect", value = "Dados da coleta") CollectVo collectVo) {
    Collect collect = service.registerCollect(collectVo);
    return ResponseEntity.status(HttpStatus.CREATED).body(collect.getId());
  }

  @ApiOperation(value = "Lista as coletas ativas para o CPF/CNPJ", consumes = "application/json", httpMethod = "GET")
  @ApiResponse(code = 200, message = "Lista obtida com sucesso")
  @GetMapping(value = "/activeCollect")
  public ResponseEntity<List<CollectVo>> getActiveCollect(String cpfCnpj) {
    List<CollectVo> collectVoList = service.getActiveCollects(cpfCnpj);
    return ResponseEntity.status(HttpStatus.OK).body(collectVoList);
  }

  @ApiOperation(value = "Cancela uma coleta dado seu ID", consumes = "application/json", httpMethod = "DELETE")
  @ApiResponses( value = {
      @ApiResponse(code = 200, message = "Coleta cancelada com sucesso"),
      @ApiResponse(code = 404, message = "Coleta não encontrada")
  }
  )
  @DeleteMapping(value = "/cancel")
  public ResponseEntity cancelCollect(Long collectId) {
    if (service.cancelCollect(collectId)) {
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }

  @ApiOperation(value = "Altera o status de uma coleta para COLLECTED", consumes = "application/json", httpMethod = "POST")
  @ApiResponses( value = {
      @ApiResponse(code = 200, message = "Status da coleta alterada com sucesso"),
      @ApiResponse(code = 404, message = "Coleta não encontrada")
  }
  )
  @PostMapping(value = "/toCollect")
  public ResponseEntity toCollect(Long collectId) {
    if (service.toCollect(collectId)) {
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }

  @ApiOperation(value = "Altera o status de uma coleta para FINISHED", consumes = "application/json", httpMethod = "POST")
  @ApiResponses( value = {
      @ApiResponse(code = 200, message = "Status da coleta alterada com sucesso"),
      @ApiResponse(code = 404, message = "Coleta não encontrada")
  }
  )
  @PostMapping(value = "/toFinish")
  public ResponseEntity toFinish(
      @ApiParam(name = "collectId", value = "ID da coleta") Long collectId,
      @ApiParam(name = "value", value = "Valor remunerado ao usuário referente à coleta") Double value) {
    if (service.toFinish(collectId, value)) {
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }
}
