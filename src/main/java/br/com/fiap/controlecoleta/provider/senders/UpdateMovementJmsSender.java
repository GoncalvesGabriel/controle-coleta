package br.com.fiap.controlecoleta.provider.senders;

import static java.nio.charset.StandardCharsets.UTF_8;

import br.com.fiap.controlecoleta.config.jms.ActiveMQProperties;
import br.com.fiap.controlecoleta.entity.FinancialMovement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class UpdateMovementJmsSender implements IntegrationMessageSender<FinancialMovement> {

  @Autowired
  private ActiveMQProperties properties;

  @Autowired
  private JmsTemplate jmsTemplate;

  @Override
  public void send(byte[] message) {
    this.jmsTemplate.convertAndSend(this.properties.getToUpdateMovementQueue(), new String(message, UTF_8));
  }
}
