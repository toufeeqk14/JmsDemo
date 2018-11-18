package com.example.demo;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {

	private static String broker_Url="tcp://localhost:";
	public Producer() throws NamingException, JMSException {


		InitialContext jndi = new InitialContext();
		ConnectionFactory factory =new ActiveMQConnectionFactory("admin","admin",broker_Url+"61616");
		Connection connection = null;
		try {
			connection = factory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue("Test");
			MessageProducer producer = session.createProducer(destination);
			TextMessage message = session.createTextMessage("Hello ActiveMQ World");
			message.setJMSMessageID("ryth-123");
			producer.send(message);
			System.out.println("Message sent to the Broker is : " + message.getText());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

	}

	public static void main(String[] args) throws JMSException {
		try {
			new Producer();
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

}
