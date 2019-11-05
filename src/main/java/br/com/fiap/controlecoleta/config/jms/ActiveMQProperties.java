package br.com.fiap.controlecoleta.config.jms;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "controle.coleta.activemq")
public @Data class ActiveMQProperties {

  private String host;

  private String user;

  private String password;

  private String toUpdateMovementQueue;

}