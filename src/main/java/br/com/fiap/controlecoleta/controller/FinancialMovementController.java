package br.com.fiap.controlecoleta.controller;

import br.com.fiap.controlecoleta.service.FinancialMovementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "API para controle de movimentos financeiros")
@RestController
@RequestMapping("/financialMovement")
public class FinancialMovementController {

  @Autowired
  private FinancialMovementService service;

  @ApiOperation(value = "Realiza o resgate de um valor para o usuário", consumes = "application/json", httpMethod = "POST")
  @ApiResponse(code = 200, message = "Resgate realizado com sucesso")
  @PostMapping(value = "/rescue")
  public ResponseEntity rescue(
      @ApiParam(value = "Valor de resgate") Double value,
      @ApiParam(value = "CPF/CNPJ do usuário") String cpfCnpj) {
    service.rescue(value, cpfCnpj);
    return ResponseEntity.ok().build();
  }

  @ApiOperation(value = "Obtém o saldo de um usuário", consumes = "application/json", httpMethod = "GET")
  @ApiResponse(code = 200, message = "Saldo obtido com sucesso")
  @GetMapping(value = "/balance/{cpfCnpj}")
  public ResponseEntity<Double> getBalance(
      @PathVariable("cpfCnpj") String cpfCnpj) {
    Double balance = service.getBalance(cpfCnpj);
    return ResponseEntity.ok(balance);
  }

}
