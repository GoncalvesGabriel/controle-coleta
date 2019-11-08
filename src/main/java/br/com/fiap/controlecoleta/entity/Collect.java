package br.com.fiap.controlecoleta.entity;


import br.com.fiap.controlecoleta.entity.converters.CollectStatusConverter;
import br.com.fiap.controlecoleta.entity.converters.TimeRangeConverter;
import br.com.fiap.controlecoleta.entity.enumx.CollectStatus;
import br.com.fiap.controlecoleta.entity.enumx.TimeRange;
import br.com.fiap.controlecoleta.entity.enumx.TypeMovement;
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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;


@Entity
@Table( name = "COLLECT")
@NoArgsConstructor
public @Data class Collect {

  @Id
  @Column(name = "COLLECT_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "CPF_CNPJ")
  private String cpfCnpj;

  @Column(name =  "COLLECT_FAST")
  private boolean fastCollect;

  @Column(name = "ADDRESS")
  private String address;

  @Column(name = "SCHEDULED_DATE")
  private LocalDate scheduledDate;

  @Column(name = "COLLECT_DATE")
  @Convert( converter = LocalDateTimeConverter.class)
  private LocalDateTime collectDate;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "collect")
  private FinancialMovement financialMovement;

  @Column(name = "TIME_RANGE")
  @Convert(converter = TimeRangeConverter.class)
  private TimeRange timeRange;

  @Column(name = "STATUS")
  @Convert(converter = CollectStatusConverter.class)
  private CollectStatus status;

  public void toCollect() {
    this.setStatus(CollectStatus.COLLECTED);
    this.financialMovement = new FinancialMovement();
    financialMovement.setCollect(this);
    financialMovement.setCpfCnpj(this.getCpfCnpj());
    financialMovement.setDateTime(LocalDateTime.now());
    financialMovement.setType(TypeMovement.INPUT);
    financialMovement.setValue(0.0);
  }

  public void toFinish(Double value){
    this.setStatus(CollectStatus.FINISHED);
    this.getFinancialMovement().setValue(value);
  }

  public void toCancel() {
    this.setStatus(CollectStatus.CANCELED);
  }

  @Builder
  private Collect(
      Long id,
      String cpfCnpj,
      boolean fastCollect,
      String address,
      LocalDate scheduledDate,
      LocalDateTime collectDate,
      FinancialMovement financialMovement,
      TimeRange timeRange,
      CollectStatus status) {
    this.id = id;
    this.cpfCnpj = cpfCnpj;
    this.fastCollect = fastCollect;
    this.address = address;
    this.scheduledDate = scheduledDate;
    this.collectDate = collectDate;
    this.financialMovement = financialMovement;
    this.timeRange = timeRange;
    this.status = status;
  }

}
