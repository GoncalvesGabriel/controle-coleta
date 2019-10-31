package br.com.fiap.controlecoleta.entity.enumx;

import lombok.Getter;

public enum TypeMovement {

  INPUT(1, "Entrada"),

  OUTFLOW(2, "Saída");

  @Getter
  private final Integer id;

  @Getter
  private final String type;

  TypeMovement(Integer id, String type) {
    this.id = id;
    this.type = type;
  }

  public static TypeMovement getEnum(Integer id) {
    for (TypeMovement type : TypeMovement.values()) {
      if (type.getId().equals(id)) {
        return type;
      }
    }
    throw new RuntimeException("Tipo não disponível para o valor passado");
  }

}
