package br.com.fiap.controlecoleta.entity;

import br.com.fiap.controlecoleta.entity.converters.TypeMovementConverter;
import br.com.fiap.controlecoleta.entity.enumx.TypeMovement;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

@Entity
@Table( name = "FINANCIAL_MOVEMENT")
public @Data class FinancialMovement {

  @Id
  @Column(name = "FINANCIAL_MOVEMENT_ID")
  @GeneratedValue( strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(name = "CPF_CNPJ", nullable = false)
  private String cpfCnpj;

  @Column(name = "DATE_TIME")
  @Convert(converter = LocalDateTimeConverter.class)
  private LocalDateTime dateTime;

  @NotNull
  @Column(name = "TYPE", nullable = false)
  @Convert(converter = TypeMovementConverter.class)
  private TypeMovement type;

  @NotNull
  @Column( name = "VALUE", nullable = false)
  private Double value;

  @OneToOne
  @JoinColumn(name = "COLLECT_ID")
  private Collect collect;

}
