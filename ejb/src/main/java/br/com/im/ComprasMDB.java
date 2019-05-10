package br.com.im;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destinationLoookup", propertyValue = "jms/queue/pQueue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/queue/pQueue") })
public class ComprasMDB implements MessageListener {
	
	@EJB
	private Reposicao reposicao;
	
	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage tMsg = (TextMessage) message;
			System.out.println("\nPedido Recebido:");
			try {
				String pedido = tMsg.getText();
				reposicao.teste(pedido);
				
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}

	}

}
