package br.com.fiap.controlecoleta.entity;


import br.com.fiap.controlecoleta.entity.converters.CollectStatusConverter;
import br.com.fiap.controlecoleta.entity.converters.RangeTimeConverter;
import br.com.fiap.controlecoleta.entity.enumx.CollectStatus;
import br.com.fiap.controlecoleta.entity.enumx.RangeTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;


@Entity
@Table( name = "COLLECT")
public @Data class Collect {

  @Id
  @Column(name = "COLLECT_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "CPF_CNPJ")
  private String cpfCnpj;

  @Column(name =  "COLLECT_FAST")
  private boolean fastCollect;

  @Column(name = "ADRESS")
  private String adress;

  @Column(name = "SCHEDULED_DATE")
  private LocalDate scheduledDate;

  @Column(name = "COLLECT_DATE")
  @Convert( converter = LocalDateTimeConverter.class)
  private LocalDateTime collectDate;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "collect")
  private FinancialMovement financialMovement;

  @Column(name = "TIME_RANGE")
  @Convert(converter = RangeTimeConverter.class)
  private RangeTime timeRange;

  @Column(name = "STATUS")
  @Convert(converter = CollectStatusConverter.class)
  private CollectStatus status;
}
