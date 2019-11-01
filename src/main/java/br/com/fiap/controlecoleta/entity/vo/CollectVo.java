package br.com.fiap.controlecoleta.entity.vo;

import br.com.fiap.controlecoleta.entity.FinancialMovement;
import br.com.fiap.controlecoleta.entity.enumx.CollectStatus;
import br.com.fiap.controlecoleta.entity.enumx.RangeTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

public @Data class CollectVo {

  private Long id;

  private String cpfCnpj;

  private boolean fastCollect;

  private String adress;

  private LocalDate scheduledDate;

  private LocalDateTime collectDate;

  private Long financialMovementId;

  private RangeTime timeRange;

  private CollectStatus status;
}
