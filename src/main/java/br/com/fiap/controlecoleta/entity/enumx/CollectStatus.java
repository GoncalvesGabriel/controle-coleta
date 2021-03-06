package br.com.fiap.controlecoleta.entity.enumx;

import lombok.Getter;
import lombok.ToString;

@ToString(of = "status")
public enum CollectStatus {

  SCHEDULED(1, "Agendada"),

  COLLECTED(2, "Coletada"),

  FINISHED(3, "Finalizada"),

  CANCELED(4, "Cancelada");

  @Getter
  private final Integer id;

  @Getter
  private final String status;

  CollectStatus(Integer id, String status) {
    this.id = id;
    this.status = status;
  }

  public static CollectStatus getEnum(Integer id) {
    for (CollectStatus status : CollectStatus.values()) {
      if (status.getId().equals(id)) {
        return status;
      }
    }
    throw new RuntimeException("Nenhum status definido para esse valor");
  }

}
