package br.com.fiap.controlecoleta.entity.vo;

import br.com.fiap.controlecoleta.entity.enumx.CollectStatus;
import br.com.fiap.controlecoleta.entity.enumx.TimeRange;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@ApiModel(value = "Collect", description = "Representa os dados de uma coleta")
public @Data class CollectVo {

  @ApiModelProperty(value = "ID da coleta")
  private Long id;

  @ApiModelProperty(value = "CPF ou CNPJ do usuário", example = "\"12345678911\"")
  private String cpfCnpj;

  @ApiModelProperty(value = "Coleta rápida", example = "\"false\"")
  private boolean fastCollect;

  @ApiModelProperty(value = "Endereço", example = "\"Rua celso afonso, 552\"")
  private String address;

  @ApiModelProperty(value = "Data agendada", example = "\"YYYY-MM-DD\"")
  private LocalDate scheduledDate;

  private LocalDateTime collectDate;

  private Long financialMovementId;

  @ApiModelProperty(value = "Intervalo de horário", example = "\"MORNING\"")
  private TimeRange timeRange;

  @ApiModelProperty(value = "Status da coleta", example = "\"SCHEDULED\"")
  private CollectStatus status;

  @Builder
  private CollectVo(Long id,
                   String cpfCnpj,
                   boolean fastCollect,
                   String address,
                   LocalDate scheduledDate,
                   LocalDateTime collectDate,
                   Long financialMovementId,
                   TimeRange timeRange,
                   CollectStatus status) {
    this.id = id;
    this.cpfCnpj = cpfCnpj;
    this.fastCollect = fastCollect;
    this.address = address;
    this.scheduledDate = scheduledDate;
    this.collectDate = collectDate;
    this.financialMovementId = financialMovementId;
    this.timeRange = timeRange;
    this.status = status;
  }
}
