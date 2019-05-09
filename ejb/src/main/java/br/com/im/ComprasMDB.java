package br.com.im;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destinationLoookup", propertyValue = "jms/queue/pQueue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/queue/pQueue") })
public class ComprasMDB implements MessageListener {
	
	@Inject
	private JMSContext context;
	
	@Resource(lookup="java:/jms/queue/rQueue")
	private Queue rQueue;

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage tMsg = (TextMessage) message;
			System.out.println("\nPedido Recebido:");
			try {
				String pedido = tMsg.getText();
				context.createProducer().send(rQueue, pedido);
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}

	}

}
