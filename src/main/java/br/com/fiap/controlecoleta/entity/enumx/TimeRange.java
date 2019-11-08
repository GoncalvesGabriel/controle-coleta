package br.com.fiap.controlecoleta.entity.enumx;

import lombok.Getter;
import lombok.ToString;

@ToString(of = "description")
public enum TimeRange {

  MORNING(1, "8H00 ~ 12H00"),

  AFTERNOON(2, "13H00 ~ 17H00"),

  NIGHT(3, "18H00 ~ 22H00");

  @Getter
  private final Integer id;

  @Getter
  private final String description;

  TimeRange(Integer id, String description) {
    this.id = id;
    this.description = description;
  }

  public static TimeRange getEnum(Integer id) {
    for (TimeRange range : TimeRange.values()) {
      if (range.getId().equals(id)) {
        return range;
      }
    }
    throw new RuntimeException("Intervalo de horário não disponível para coleta");
  }

}
