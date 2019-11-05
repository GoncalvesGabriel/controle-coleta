package br.com.fiap.controlecoleta.provider;


import br.com.fiap.controlecoleta.provider.senders.IntegrationMessageSender;
import br.com.fiap.controlecoleta.provider.senders.UpdateMovementJmsSender;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JmsIntegrationProvider implements IntegrationProvider  {

  private Map<IntegrationAction, IntegrationMessageSender> integrationSenders = new HashMap<IntegrationAction, IntegrationMessageSender>();

  @Autowired
  private UpdateMovementJmsSender movementJmsSender;

  @PostConstruct
  public void init(){
    integrationSenders.put(IntegrationAction.MOVEMENT_UPDATE, movementJmsSender);
  }

  @Override
  public IntegrationMessageSender getSender(IntegrationAction action){
    return integrationSenders.get(action);
  }
}
