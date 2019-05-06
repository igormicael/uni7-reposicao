package br.com.im;

import javax.ejb.ActivationConfigProperty;
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

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage tMsg = (TextMessage) message;
			System.out.println("\nPedido Recebido:");
			try {
				System.out.println(tMsg.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}

	}

}
