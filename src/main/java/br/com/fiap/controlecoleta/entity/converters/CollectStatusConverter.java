package br.com.fiap.controlecoleta.entity.converters;

import br.com.fiap.controlecoleta.entity.enumx.CollectStatus;
import javax.persistence.AttributeConverter;

public class CollectStatusConverter implements AttributeConverter<CollectStatus, Integer> {

  @Override
  public Integer convertToDatabaseColumn(CollectStatus collectStatus) {
    if (collectStatus == null) {
      return null;
    }
    return collectStatus.getId();
  }

  @Override
  public CollectStatus convertToEntityAttribute(Integer integer) {
    return CollectStatus.getEnum(integer);
  }

}
