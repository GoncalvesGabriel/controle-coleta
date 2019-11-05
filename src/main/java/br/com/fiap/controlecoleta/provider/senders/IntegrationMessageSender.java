package br.com.fiap.controlecoleta.provider.senders;

public interface IntegrationMessageSender<T> {

  void send(byte[] message);
}