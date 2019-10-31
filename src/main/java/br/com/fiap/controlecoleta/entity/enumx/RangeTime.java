package br.com.fiap.controlecoleta.entity.enumx;

import lombok.Getter;
import lombok.ToString;

@ToString(of = "description")
public enum RangeTime {

  MORNING(1, "8H00 ~ 12H00"),

  AFETERNON(2, "13H00 ~ 17H00"),

  NIGTH(3, "18H00 ~ 22H00");

  @Getter
  private final Integer id;

  @Getter
  private final String description;

  RangeTime(Integer id, String description) {
    this.id = id;
    this.description = description;
  }

  public static RangeTime getEnum(Integer id) {
    for (RangeTime range : RangeTime.values()) {
      if (range.getId().equals(id)) {
        return range;
      }
    }
    throw new RuntimeException("Range de horário não disponível para coleta");
  }

}
