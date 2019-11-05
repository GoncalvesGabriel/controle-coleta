package br.com.fiap.controlecoleta.controller;

import br.com.fiap.controlecoleta.service.FinancialMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/financialMovement")
public class FinancialMovementController {

  @Autowired
  private FinancialMovementService service;

  @PostMapping(value = "/rescue")
  public ResponseEntity rescue(Double value, String cpfCnpj) {
    service.rescue(value, cpfCnpj);
    return ResponseEntity.ok().build();
  }

  @GetMapping(value = "/balance/{cpfCnpj}")
  public ResponseEntity<Double> getBalance(String cpfCnpj) {
    Double balance = service.getBalance(cpfCnpj);
    return ResponseEntity.ok(balance);
  }

}
