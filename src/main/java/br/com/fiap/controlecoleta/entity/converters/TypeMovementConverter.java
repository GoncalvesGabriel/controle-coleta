package br.com.fiap.controlecoleta.entity.converters;

import br.com.fiap.controlecoleta.entity.enumx.TypeMovement;
import javax.persistence.AttributeConverter;

public class TypeMovementConverter implements AttributeConverter<TypeMovement, Integer> {

  @Override
  public Integer convertToDatabaseColumn(TypeMovement typeMovement) {
    if (typeMovement == null) {
      return null;
    }
    return typeMovement.getId();
  }

  @Override
  public TypeMovement convertToEntityAttribute(Integer id) {
    return TypeMovement.getEnum(id);
  }
}
