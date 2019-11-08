package br.com.fiap.controlecoleta.entity.converters;

import br.com.fiap.controlecoleta.entity.enumx.TimeRange;
import javax.persistence.AttributeConverter;

public class TimeRangeConverter implements AttributeConverter<TimeRange, Integer> {

  @Override
  public Integer convertToDatabaseColumn(TimeRange timeRange) {
    if (timeRange == null) {
      return null;
    }
    return timeRange.getId();
  }

  @Override
  public TimeRange convertToEntityAttribute(Integer integer) {
    return TimeRange.getEnum(integer);
  }
}
