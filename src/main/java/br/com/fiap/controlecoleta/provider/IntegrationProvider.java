package br.com.fiap.controlecoleta.provider;

import br.com.fiap.controlecoleta.provider.senders.IntegrationMessageSender;

public interface IntegrationProvider {

  IntegrationMessageSender getSender(IntegrationAction action);
}
