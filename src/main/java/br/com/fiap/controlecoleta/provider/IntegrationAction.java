package br.com.fiap.controlecoleta.provider;

import lombok.Getter;

public enum IntegrationAction {

  MOVEMENT_UPDATE("Movement update");

  @Getter
  private final String descrition;

  IntegrationAction(String descrition) {
    this.descrition = descrition;
  }
}
