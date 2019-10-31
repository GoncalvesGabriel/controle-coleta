package br.com.fiap.controlecoleta.entity.converters;

import br.com.fiap.controlecoleta.entity.enumx.RangeTime;
import javax.persistence.AttributeConverter;

public class RangeTimeConverter implements AttributeConverter<RangeTime, Integer> {

  @Override
  public Integer convertToDatabaseColumn(RangeTime rangeTime) {
    if (rangeTime == null) {
      return null;
    }
    return rangeTime.getId();
  }

  @Override
  public RangeTime convertToEntityAttribute(Integer integer) {
    return RangeTime.getEnum(integer);
  }
}
