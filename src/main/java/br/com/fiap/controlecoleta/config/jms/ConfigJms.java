package br.com.fiap.controlecoleta.config.jms;

import br.com.fiap.controlecoleta.config.jms.ActiveMQProperties;
import javax.jms.QueueConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@EnableJms
@Configuration
public class ConfigJms {

  @Autowired
  private ActiveMQProperties properties;

  @Bean
  public QueueConnectionFactory connectionFactory() {
    if ( "".equals(properties.getUser()) ) {
      return new ActiveMQConnectionFactory(properties.getHost());
    }
    return new ActiveMQConnectionFactory(properties.getUser(), properties.getPassword(), properties.getHost());
  }

  @Bean
  public JmsTemplate jmsTemplate() {
    return new JmsTemplate(connectionFactory());
  }

}
