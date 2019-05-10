package br.com.im;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

@Stateless
public class Reposicao {
	
	@Inject
	private JMSContext context;
	
	@Resource(lookup="java:/jms/queue/rQueue")
	private Queue rQueue;
	
	public void teste(String pedido) {
		System.out.println(pedido);
		context.createProducer().send(rQueue, pedido);
		System.out.println("Pedido enviado!");
	}

}
